import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        System.out.println("start");
        assert true;
        System.out.println("go on");
        assert false:"stop";
        System.out.println("end");
        //Collections.sort();
        List list = new ArrayList<>();
        //list.sort();

    }
}