package threadpool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author :luo wei
 * @description:  定时线程池demo尝试 ,如果定时任务抛出了异常，那么后面的执行都会被中断，所以任务逻辑本身需要做好异常捕获的功能
 *
 * @date 2023/2/8/008 14:26
 */
public class ScheduledExecutorDemo {

    public static void main(String[] args) {

        /*
        cheduleAtFixedRate：是以上一个任务开始的时间计时，（initialDelay）秒过去后，检测上一个任务是否执行完毕，如果上一个任务执行完毕，
                            则当前任务立即执行，如果上一个任务没有执行完毕，则需要等上一个任务执行完毕后立即执行，
                            要注意如果你的执行任务的时间超过了间隔时间，那么就会变成一直连续循环执行，间隔时间的参数其实已经没发挥出作用了，
                            其实一般我们也不会犯这样的错误，只是要清楚，固定频率是有条件的就行。
        scheduleWithFixedDelay：是以上一个任务结束时开始计时，（initialDelay）秒过去后，立即执行。
         */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(() ->{
            //先声明一个日期格式，然后对localDateTime使用format（）方法进行格式化得到日期字符串
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println(formatter.format(LocalDateTime.now()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        },0,3, TimeUnit.SECONDS);



        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss");
        String format = formatter.format(LocalDateTime.now());
        System.out.println(format);
    }
}
