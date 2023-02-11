
import com.apps.Outer;

public class App {
    public static void main(String[] args) throws Exception {
        Outer.StaticInner staticInner = new Outer.StaticInner();
        System.out.println(staticInner.getRedius());
    }
}
