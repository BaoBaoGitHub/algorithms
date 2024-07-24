import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        Set<String> set = strings.stream().filter((s) -> {
            return (s != null && s.length() != 0);
        }).collect(Collectors.toSet());
        System.out.println(set);
        for (String s : set) {
            System.out.println(s==null?"null":"不是 null");
        }
    }
}
