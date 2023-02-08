package reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author :luo wei
 * @description:  可重入锁还提供了tryLock（）方法，用于限时请求锁资源，如果超时仍未请求到则放弃请求
 * @date 2023/1/30/030 14:22
 */
public class TryLock implements Runnable {

  ReentrantLock lock=new ReentrantLock();

  @Override
  public void run() {
    try {
      //如果使用无参的tryLock（）方法，那么假如没有请求到锁资源，线程就会立刻放弃请求
      if (lock.tryLock(5, TimeUnit.SECONDS)){

        Thread.sleep(5000);

      }

    } catch (InterruptedException e) {

      e.printStackTrace();

    }finally {

      if (lock.isHeldByCurrentThread()){

        lock.unlock();

      }
    }
  }
}
