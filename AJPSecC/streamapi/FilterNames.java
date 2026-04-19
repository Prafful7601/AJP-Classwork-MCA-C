package streamapi;

import java.util.Arrays;
import java.util.List;

public class FilterNames {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Amit", "Ravi", "Anil", "Vikas", "Ashok", "Rahul");

        names.stream()
             .filter(name -> name.contains("A"))
             .forEach(System.out::println);
    }
}
