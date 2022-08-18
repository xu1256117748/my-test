import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 徐亚奎
 * @date 21/07/2021 18:44
 */
public class test3 {
    public static void main(String[] args) {
        Proxy.newProxyInstance(test2.class.getClassLoader(), test2.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代理前");
                Object invoke = method.invoke(proxy, args);
                System.out.println("代理后");
                return invoke;
            }
        });
        new test2().sayHello();
    }
}
