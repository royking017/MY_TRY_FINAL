package threadpool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author :luo wei
 * @description:  定时线程池demo尝试 ,如果定时任务抛出了异常，那么后面的执行都会被中断，所以任务逻辑本身需要做好异常捕获的功能
 * @date 2023/2/8/008 14:26
 */
public class ScheduledExecutorDemo {

    public static void main(String[] args) {
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
