package reflect;

import java.lang.reflect.Method;

/**
 * @author :luo wei
 * @description: 有三种方法获取一个类的类对象： 1.Class cl = Class.forName("类的全限定名，从java包下面的包开始算，不包括java包")
 *                                         2.Class cl = Book.class
 *                                         3.Book book = new Book();
 * Class cl = boo.getClass();  通过实例来获取它的类对象
 * @date 2023/2/24/024 14:42
 */
public class ReflectDemo1 {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

//      Class clazz = Class.forName("reflect.Book");
        Class clazz = Book.class;

        //获取类名
        String name = clazz.getName();
        System.out.println("name = " + name);
        System.out.println("clazz.getSimpleName() = " + clazz.getSimpleName());

        //获取类的所在包名
        String packageName = clazz.getPackage().getName();
        System.out.println("PackageName = " + packageName);

        //获取该类的所有公开方法(包含从超类继承的方法)
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println("method.getName() = " + method.getName());
        }

        Book o = (Book) clazz.newInstance();
        System.out.println("Book类生成的实例" + o);
    }

}
