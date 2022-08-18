package JavaBasic.NO_001;

/**
 * @author 徐亚奎
 * @email 1256117748@qq.com
 * @date 2022-07-23 16:22
 * @Description
 */
public class TestDemo_01 {
    public static void main(String[] args) {
        B b = new B();
        b.setMsg("张三");
        System.out.println(b.getMsg());
    }

    static class A {
        private String msg;

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return this.msg;
        }
    }

    static class B extends A {
        public void print() {
//            System.out.println(msg); // 错误: msg定义为private，不可见
        }
    }

}
