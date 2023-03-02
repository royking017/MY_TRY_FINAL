package thread;

import java.util.concurrent.TimeUnit;

/**
 * @author :luo wei
 * @description:   测试新起一个线程是否和主线程异步执行 ，结果 ---是
 * @date 2023/2/28/028 16:49
 */
public class AYNCtest {

    public static void main(String[] args) {
        System.out.println("启动。。。。");
        Thread t1 = new Thread(new Task1());
        t1.start();
        System.out.println("xxxxxxxxxxxxxxxx");
    }

    public static class Task1 implements Runnable{

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("-------开始继续执行--------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
