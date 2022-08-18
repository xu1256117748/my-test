package JavaBasic.NO_001;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-07-23 16:22
 * @Description
 */
public class TestDemo_02 {
    public static void main(String[] args) {
        System.out.println("-----使用B的无参构造创建B的实例------");
        B b_1 = new B();
        System.out.println(b_1.name);


        System.out.println("-----使用B的含参构造创建B实例------");
        B b_2 = new B("张三");
        System.out.println(b_2.name);
    }

   static class A {
        public A() {         // 父类无参构造
            System.out.println("我是A的无参构造") ;
        }
    }
    static class B extends A {
        String name;
        public B() {         // 子类构造
            System.out.println("我是B的无参构造");
        }
        public B(String name) {         // 子类构造
            this.name = name;
            System.out.println("我是B的含参构造");
        }
    }


}
