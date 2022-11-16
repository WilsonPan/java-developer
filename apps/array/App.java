import java.util.Arrays;

public class App {

    public static void main(String[] args) throws Exception {
        // 声明数组，未分配内存空间
        int[] values;

        // 分配内存空间
        values = new int[] { 10, 20, 30, 40, 50 };

        // System.out.println(Arrays.toString(values));

        App.Person p1 = new App.Person("小丽");
        App.Person p2 = new App.Person("小王");

        swap(p1, p2);

        System.out.println("p1:" + p1.getName());
        System.out.println("p2:" + p2.getName());
    }

    private static void swap(Person p1, Person p2) {
        Person temp = p1;
        p1 = p2;
        p2 = temp;

        System.out.println("p1:" + p1.getName());
        System.out.println("p2:" + p2.getName());
    }

    public static class Person {

        public Person(String name) {
            this.name = name;
        }

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}