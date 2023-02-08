package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author :luo wei
 * @description: 新建线程练习 线程有三种新建方式  1、extends  Thread;
 *                                          2、implements  Runnable;
 *                                          3、implements  Callable
 * @date 2023/1/29/029 15:09
 */
public class ThreadTest {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    Thread1 t1 = new Thread1();


    //通过Thread的构造函数将Thread2的实例作为参数传进去，从而新建线程Thread2
    Thread t2 = new Thread(new Thread2());
    t1.start();
    t2.start();

    //将callable的实例通过线程池提交执行
    Callable t3 = new Thread3();
    ThreadPoolExecutor executor =
        new ThreadPoolExecutor(5, 10, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(16));
    Future submit = executor.submit(t3);
    executor.shutdown();
    System.out.println(submit.get());

  }

  public static class Thread1 extends Thread {

    public void run() {
      System.out.println("我是使用第一种方法：继承Thread创建的");
    }
  }

  public static class Thread2 implements Runnable {

    @Override
    public void run() {
      System.out.println("我是使用第二种方法：实现Runnable创建的");
    }
  }

  public static class Thread3 implements Callable {

    @Override
    public Object call() {
      System.out.println("我是使用实现Callable实现的");
      return 111;
    }
  }
}
