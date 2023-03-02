package CS;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 服务器
 * @Author: roy
 * @Date:2022/4/15 13:50
 **/
public class Server {

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
    //serverSocket是运行在服务端的，它的作用是断开服务端口并监听，一旦有服务连接端口，则返回个Socket实例，并通过该socket与连接客户端交互

    private ServerSocket serverSocket;
    //所有的客户端输出溜，用于广播消息给客户端

    private PrintWriter[] allout={};


    Server(){
        try {
            System.out.println("服务端正在启动……");
            serverSocket = new ServerSocket(8088);
            System.out.println("服务端启动完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void start(){
        try {
            /**
             * accept：该方法是一个阻塞方法，调用后开始等待，直到客户端与服务端建立连接
             */
            while (true){
                System.out.println("等待客户端连接……");
                Socket accept = serverSocket.accept();
                System.out.println("一个客户端连接成功！");

                Runnable clientHandler = new ClientHandler(accept);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 该方法负责与特定的客户端交互
     */
    private class ClientHandler implements Runnable{
        private Socket socket;
        private String host;//客户端地址信息

        ClientHandler(Socket socket){
            this.socket = socket;
            this.host = socket.getInetAddress().getHostAddress();
        }
        @Override
        public void run() {
            PrintWriter printWriter = null;
            try {
                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                //socket获取输出流
                OutputStream outputStream = socket.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                BufferedReader bufferedInputStream = new BufferedReader(inputStreamReader);
                printWriter = new PrintWriter(bufferedWriter,true);

                /**
                 * 注意这里不能使用allout对象
                 */
                synchronized (Server.this){
                    //将输出流存入数组，先对原数组扩容，然后再将输出流放入到最后的位置
                    allout = Arrays.copyOf(allout,allout.length+1);
                    allout[allout.length-1] = printWriter;
                }

                //广播通知所有客户端，当前在线人数以及上线用户为谁
                sendMessage(host+"上线了！当前在线人数"+allout.length);
                try{
                    String line;
                    while ((line = bufferedInputStream.readLine()) != null) {
                        System.out.println(host+"说："+line);
                        //消息回复给客户端
                        sendMessage(line);
                    }
                }catch (Exception e){}
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                //当前客户端的输出流从allout数组中删除掉
                for (int i=0;i<allout.length;i++){
                    if (allout[i].equals(printWriter)){
                        synchronized (Server.this){
                            allout[i] = allout[allout.length-1];
                            allout = Arrays.copyOf(allout,allout.length-1);
                        }
                        //广播通知所有客户端，当前在线人数以及上线用户为谁
                        sendMessage(host+"下线了！当前在线人数"+allout.length);
                        break;
                    }
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 广播消息给所有客户端
         */
        public void sendMessage(String message){
            synchronized (Server.this){
                for (PrintWriter printWriter1:allout) {
                    printWriter1.println(message);
                }
            }
        }
    }
}
