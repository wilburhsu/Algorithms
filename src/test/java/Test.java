public class Test {
    private int hasErrorAndFinally() {
        try {
            System.out.println("try代码块已执行");
            int i = 1 / 0;
            System.out.println("i = " + i);
            System.out.println("try代码块已结束");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("catch代码块已执行");
            System.out.println("catch代码块已结束");
            return 2;
        } finally {
            System.out.println("finally代码块已执行");
            System.out.println("finally代码块已结束");
            return 3;
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.hasErrorAndFinally());
    }
}
