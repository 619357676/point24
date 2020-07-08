package test;

import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Suanf {
    private static final int COUNT = 5040;
    final static String[] OP = "+ - * /".split(" ");//正则表达式，将+-*/传入ops数组

    public static void main(String[] args) {
        System.out.println(solve(1, 2, 3, 4));//测试数据
    }
//算法
    private static List<String> randSearcher(int[] a) {
        for (int j = 0; j <64; j++) {//4个数和3个符号共4*4*4种算法
            String[] ops = new String[3];
            int x = j / 16;
            int y = j % 16 / 4;
            int z = j % 4;
            ops[0] = OP[x];
            ops[1] = OP[y];
            ops[2] = OP[z];

            List<String> exp = new Vector<>();
            for (Integer i : a) {
                exp.add(Integer.toString(i));//将输入的四个数转化成字符串的数字，并压入栈exp中
            }
            for (String op : ops) {
                exp.add(op);

            }

            int tot = 0;
            while (++tot < COUNT) {
                Collections.shuffle(exp);//使用默认随机源对列表进行置换，所有置换发生的可能性都是大致相等的
                double result = eval(exp);
                if (result == 24) {
                    return exp;
                }
            }
        }
        return null;
    }

    public static String solve(int a, int b, int c, int d) {
        List<String> exp = randSearcher(new int[]{a, b, c, d});
        if (exp == null) {
            return "无答案";
        }
        StringBuffer buffer = new StringBuffer();
        BinaryTree binaryTree = BinaryTree.Expression(exp);
        binaryTree.midVisit(buffer);//中序遍历
        return buffer.toString().substring(1,buffer.length()-1);//返回字符串的子字符串。
    }

    public static double eval(List<String> exp) {
        Stack<Double> S = new Stack<>();
        for (String tok : exp) {
            if (isNumber(tok)) {
                S.push(Double.parseDouble(tok));
            } else {
                if (S.isEmpty()) {
                    return -1;
                }
                double b = S.peek();//查看堆栈顶的对象
                S.pop();//移除堆栈顶部的对象，并作为此函数的值返回该对象。
                if (S.isEmpty()) {
                    return -1;
                }
                double a = S.peek();
                S.pop();
                double c = 0;
                switch (tok) {
                    case "+":
                        c = a + b;
                        break;
                    case "-":
                        c = a - b;
                        break;
                    case "*":
                        c = a * b;
                        break;
                    case "/":
                        if (b == 0) return -1;
                        c = a / b;
                        break;

                }
                S.push(c);
            }
        }
        return S.size() != 1 ? -1 : S.peek();
    }

    public static double eval(String[] exp) {
        return eval(Arrays.asList(exp));
    }

    public static boolean isNumber(String str) {//判断是否是数字
        char[] a = str.toCharArray();
        for (int i = 0; i < a.length; i++) {
            if (a[i] <= '9' && a[i] >= '0') return true;
        }
        return false;
    }
}
