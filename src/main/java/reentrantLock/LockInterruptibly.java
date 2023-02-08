package reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author :luo wei
 * @description:   可中断式加锁，可避免死锁
 * @date 2023/1/30/030 14:11
 */
public class LockInterruptibly {

  private  static  ReentrantLock lock=new ReentrantLock();
  public static void main(String[] args) throws InterruptedException {
    //可以对线程的中断做出反应的加锁动作，在线程调用interrupt（）方法中断线程时，该线程会放弃申请锁，并释放自己拥有的锁资源
    lock.lockInterruptibly();
  }
}
