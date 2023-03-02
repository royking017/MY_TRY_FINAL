package pc;

/**
 * @author :luo wei
 * @description:
 * @date 2023/2/17/017 11:26
 */
public class PANDC {

    public static void main(String[] args) {
        Message msg = new Message();
        new Thread(new Producer(msg)).start();
        new Thread(new Consumer(msg)).start();
    }

    static class Consumer  implements Runnable{
        private Message msg;

        public Consumer(Message msg) {
            this.msg = msg;
        }

        @Override
        public void run() {

            for (int i = 0; i < 50; i++) {
               synchronized (msg){
                   System.out.println("第"+i + "个：" +msg.getName() + " 说 ： " + msg.getContent());
                   Thread.yield();
               }
            }
        }
    }

    static class  Producer implements Runnable{
        private Message msg;

        public Producer(Message msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
           for (int i = 0; i < 50; i++) {
               synchronized (msg){}
               if (i % 2 == 0){
                   msg.setName("第"+i + "个：" + "111");
                   msg.setContent("第"+i + "个：" +"i am first one");
                   try {
                       Thread.sleep(100);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   Thread.yield();
               }else{
                   msg.setName("第"+i + "个：" +"222");
                   msg.setContent("第"+i + "个：" +"i am second one");
                   try {
                       Thread.sleep(100);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   Thread.yield();
               }
           }

        }

    }

    static public class  Message{
        private String name;
        private String content;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

    }

}
