import java.util.ArrayList;
import java.util.List;

public class NewFeaturesTest {

    public static void main(String args[]) {
        List<String> names = new ArrayList<>();
        names.add("Joemsu");
        names.add("GodnessY");
        names.iterator().forEachRemaining(c -> System.out.println("hi! " + c));
    }

}