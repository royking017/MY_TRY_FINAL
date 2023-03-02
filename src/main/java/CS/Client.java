package CS;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @description: 客户端
 * @Author: roy
 * @Date:2022/4/15 13:50
 **/
public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
    //套接字，封装了TCP协议的通讯细节，使用它可以与远端计算机建立TCP链接，并完成数据交换

    private Socket socket;
    //无参构造

    Client(){
        try {
            /**
             * new对象其实就是与远程计算机建立连接的过程，创建成功，对象初始化成功，否则构造器会抛出异常
             * 参数1：远程计算机ip地址
             * 参数2：远程计算机端口
             */
            System.out.println("正在连接服务端……");
            socket = new Socket("localhost",8088);
            System.out.println("服务端连接成功……");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 客户端开始工作
     */
    public void start(){
        try {
            //启动一个线程来捕获服务端发送的消息
            ServerHandler serverHandler = new ServerHandler();
            Thread thread = new Thread(serverHandler);
            thread.setDaemon(true); //设置读取线程为守护线程
            thread.start();

            //给服务器发送消息
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter,true);

            Scanner scanner = new Scanner(System.in);
            String toServer = "";
            while (true){
                toServer = scanner.nextLine();
                if ("exit".equals(toServer)){break;}
                printWriter.println(toServer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 该线程负责接受服务端发送过来的消息
     */
    private class ServerHandler implements Runnable{
        @Override
        public void run() {
            //获取服务端的输入流，获取服务端发过来的消息
            try {
                InputStream inputStream = socket.getInputStream();  //线程的run方法中不允许写throws
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ((line=bufferedReader.readLine())!=null){
                    System.out.println("服务端说："+line);
                }
            } catch (IOException e) {}
        }
    }
}
