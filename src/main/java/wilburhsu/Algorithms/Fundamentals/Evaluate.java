package wilburhsu.Algorithms.Fundamentals;

import edu.princeton.cs.algs4.StdIn;

import java.util.Stack;

/**
 * Dijkstra的双栈算术表达式求值法 P80
 * */
public class Evaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if(s.equals("("))
                ;
            else if(s.equals("+"))
                ops.push(s);
        }


    }
}
