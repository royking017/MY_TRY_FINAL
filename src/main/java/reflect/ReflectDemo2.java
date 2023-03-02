package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author :luo wei
 * @description:
 * @date 2023/2/24/024 16:24
 */
public class ReflectDemo2 {

    public static void main(String[] args)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class cls = Book.class;
        Constructor c = cls.getConstructor(String.class);
        Book book = (Book) c.newInstance("傲慢与偏见");
        System.out.println("通过一个参数的公开的构造方法生成的实例 ：" + book);

    }

}
