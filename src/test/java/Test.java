public class Test {
    public static void main(String[] args) {
        String string = "test";
        Integer integer = new Integer(1);
        String str = String.format("This is a %s,%d",string,integer);
        System.out.println(str);
    }
}
