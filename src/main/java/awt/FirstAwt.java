package awt;

import com.sun.javafx.logging.JFRInputEvent;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author :luo wei
 * @description:
 * @date 2023/2/13/013 21:41
 */
public class FirstAwt {

    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setSize(500,500);
        frame.setLocation(500,500);
        frame.setLayout(new FlowLayout());

        //设置点击弹出框的 × 就关闭弹窗并退出程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //添加一个确定按钮到弹窗里面去
        JButton b = new JButton("确定");
        frame.add(b);
        b.addActionListener((a) ->{
            System.out.println("hello");
            System.out.println(a);
        });

        //设置弹窗为可见
        frame.setVisible(true);
    }

}
