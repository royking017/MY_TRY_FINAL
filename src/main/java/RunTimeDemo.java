import java.io.IOException;

/**
 * @author :luo wei
 * @description:  JDK封装运行时的环境，例如JVM的行为和状态每个java程勋都有一个自己的runtime实例
 * @date 2023/2/24/024 14:01
 */
public class RunTimeDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        Runtime runtime =Runtime.getRuntime();

        System.out.println("处理器数量 " + runtime.availableProcessors());
        System.out.println("空闲内存数 （G）：" + runtime.freeMemory()/1024);
        System.out.println("总内存数 （G）： " + runtime.totalMemory()/1024);
        System.out.println("最大内存 （G）： " + runtime.maxMemory()/1024);

        //运行计算器程序
        runtime.exec("calc.exe");

        //写出文件的全限定路径
        Process exec = runtime.exec("C:\\Program Files\\Microsoft Office\\root\\Office16\\WINWORD.EXE");
        //阻塞，等子线程执行完成
        exec.waitFor();
        System.out.println("子线程执行完毕");
        //强制销毁线程
        exec.destroyForcibly();

    }



}
