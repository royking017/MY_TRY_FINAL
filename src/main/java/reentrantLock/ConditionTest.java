package reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author :luo wei
 * @description: 练习使用condition，类似于线程的wait（）方法和notify（）方法
 * @date 2023/1/31/031 11:01
 */
public class ConditionTest implements Runnable {

  private static ReentrantLock lock = new ReentrantLock();

  //通过可重入锁获取condition实例
  private static Condition condition = lock.newCondition();

    @Override
    public void run() {
      try {

        lock.lock();

        System.out.println(Thread.currentThread().getName() + " 开始await");

        condition.await();

        System.out.println(Thread.currentThread().getName() +" 结束await");

      } catch (InterruptedException e) {
        System.out.println(e);
      } finally {
        lock.unlock();
      }
    }


  public static void main(String[] args) throws InterruptedException {
    ConditionTest r1=new ConditionTest();
    Thread t=new Thread(r1);
    t.start();
    Thread.sleep(5000);

    lock.lock();
    condition.signal();
    lock.unlock();


  }

}
