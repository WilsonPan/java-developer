
import java.lang.reflect.Method;

import com.app.demo.Runner;

public class App {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("com.app.demo.Runner");
        // 1. 获取Method
        Method method = cls.getDeclaredMethod("getName");

        // 2. 调用
        Object result = method.invoke(cls.newInstance());

        System.out.println(cls.getDeclaredMethod("getName").invoke(cls.newInstance()));
    }
}
