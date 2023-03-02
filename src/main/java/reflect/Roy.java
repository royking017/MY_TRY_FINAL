package reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Roy {
    /*
    为注解添加参数。
    语法:
    类型 参数名() [default 默认值]
    注:如果注解只有一个参数时，通常参数名使用value。
    原因:
    当我们定义参数后，比如下面的参数不用value，而使用:
    int num()
    那么外面在使用该注解并为参数赋值时，写法必须为:
    @AutoRunMethod(num=123)  即:@注解名(参数名=参数值)
    如果定义了多个参数时，使用格式:@注解名(参数名1=参数值1,参数名2=参数值2,...)
    注:参数的顺序可以不同，即:@注解名(参数名2=参数值2,参数名1=参数值1,...)也对

    如果只有一个参数，指定的是value，那么使用时可以忽略参数名，即:
    @AutoRunMethod(123)

 */
    String String() default "我是roy";
}
