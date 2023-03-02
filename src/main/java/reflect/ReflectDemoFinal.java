package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author :luo wei
 * @description:
 * @date 2023/2/26/026 22:54
 */
public class ReflectDemoFinal {

    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class cl = Class.forName("reflect.Book");
        Method[] methods = cl.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Roy.class)){
                Book book= (Book) cl.newInstance();
                Roy roy = method.getAnnotation(Roy.class);
                String string = roy.String();
                System.out.println(string);
                method.setAccessible(true);
                method.invoke(book);
            }
        }
    }

}
