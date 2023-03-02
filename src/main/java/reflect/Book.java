package reflect;

/**
 * @author :luo wei
 * @description:
 * @date 2023/2/24/024 14:37
 */
public class Book {

    private Integer id;
    private String name;
    public Integer age;

    public Book() {
        System.out.println("Public 无参构造函数");
    }

    public Book(String name) {
        System.out.println("Public 带参构造函数");
    }

    private Book(String name,Double price){
        System.out.println("Private 带两参构造函数");
    }

    @Roy
    public void printAll(){
        System.out.println("公开方法");
    }

    private void printOne(){
        System.out.println("私有方法");
    }

    @Roy
    private void hello(){
        System.out.println("say  hello to  you");
    }

}
