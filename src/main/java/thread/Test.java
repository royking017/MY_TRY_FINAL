package thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author :luo wei
 * @description:
 * @date 2023/3/1/001 14:42
 */
public class Test {

    public static void main(String[] args) {
        List<String> list=new ArrayList();
        list.add("adsd");
        list.add("dsd");
        list.add("sd");
        list.add("sd");
        list.add("adsdaf");
        list.add("adsdsaf");
        list.add("adsdad");

        Iterator i = list.iterator();
        while (i.hasNext()){
            String next =(String) i.next();
            if (next.startsWith("a")){
                i.remove();
            }
        }
        list.forEach(System.out::println);

    }

}
