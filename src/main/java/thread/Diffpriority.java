package thread;

/**
 * @author :luo wei
 * @description: 线程的优先级练习  让线程优先级不同的两个线程去争夺同一个锁，以他们完成任务的先后作为优先级的影响力判断
 * @date 2023/1/30/030 10:01
 */
public class Diffpriority {

  public static class Low extends Thread {

    static int count = 0;

    public void run() {
      while (true) {
        synchronized (Diffpriority.class) {
          count++;
          if (count > 10000000) {
            System.out.println("low  完成任务");
            break;
          }
        }
      }
    }
  }

  public static class High extends Thread {

    static int count = 0;

    public void run() {
      while (true) {
        synchronized (Diffpriority.class) {
          count++;
          if (count > 10000000) {
            System.out.println("high  完成任务");
            break;
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    High h = new High();
    Low l = new Low();
    h.setPriority(Thread.MAX_PRIORITY);
    l.setPriority(Thread.MIN_PRIORITY);
    l.start();
    h.start();
  }

}
