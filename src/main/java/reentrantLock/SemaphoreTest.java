package reentrantLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author :luo wei
 * @description:   -->练习使用信号量，他是一个允许多个线程同时访问的类，可以指定最多多少个进入量
 *                 -->CountDownLatch：他是一个倒计时门栓，他可以指定倒计时几次，每执行一次countDown（）方法，倒计时减一，
 *                 await（）方法之后的代码块是等他倒计时完毕之后才执行的代码
 * @date 2023/1/31/031 13:55
 */
public class SemaphoreTest implements Runnable {

  private static volatile int count = 0;

  private static final Semaphore semaphore = new Semaphore(5);

  private static CountDownLatch end=new CountDownLatch(10);

  @Override
  public void run() {
    try {
      semaphore.acquire();
      Thread.sleep(5000);
      count++;
      System.out.println(Thread.currentThread().getName() + "  执行任务 , count :" + count);
      end.countDown();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      semaphore.release();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ExecutorService e = Executors.newFixedThreadPool(20);
    SemaphoreTest s = new SemaphoreTest();
    for (int i = 0; i < 10; i++) {
      e.submit(s);
    }
    end.await();
    System.out.println("count ："+ count);
    e.shutdown();
  }
}
