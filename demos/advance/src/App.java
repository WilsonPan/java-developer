import java.util.concurrent.locks.ReentrantLock;

import com.apps.advance.Person;

public class App {
    public static void main(String[] args) throws Exception {
        Person p = new Person("Wilson");
        p.execute();
    }
}
