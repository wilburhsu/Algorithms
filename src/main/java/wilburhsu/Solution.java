package wilburhsu;

import java.util.ArrayList;
import java.util.Stack;



public class Solution {
    private static Stack<String> stack = new Stack<>();
    private static ArrayList list = new ArrayList();
    public static void testPush(){
        stack.push(null);
        stack.push("1");
    }

    public static void main(String[] args) {
        Solution.testPush();
        System.out.println(stack.size());
//        while(!stack.empty())
//            System.out.println(stack.pop());
        stack.pop();
        System.out.println(stack.pop());
    }
}
