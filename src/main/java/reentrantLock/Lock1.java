package reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author :luo wei
 * @description: 练习使用可重入锁reentrantLOCK
 * @date 2023/1/30/030 13:58
 */
public class Lock1 implements Runnable {

  public static ReentrantLock lock = new ReentrantLock();

  /*
        添加参数true，表示是公平锁,而且需要维护一个有序队列，用来存放请求的线程，
        缺点是性能较低，维护有序队列的开销很大
   */
  //public static ReentrantLock lock = new ReentrantLock(true);

  @Override
  public void run() {
    System.out.println("开始进入可重入锁");
    lock.lock();
    try {

      Thread.sleep(10000);

    } catch (InterruptedException e) {

      e.printStackTrace();

    } finally {

      //必须在finally里进行解锁操作,判断是否是当前线程拥有锁，避免解了其他线程的锁
      if (lock.isHeldByCurrentThread()){

        lock.unlock();

      }

    }
  }


}
