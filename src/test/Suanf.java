package test;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Suanf {
    private static final int MAX_RAND_SEARCH_COUNT = 5040;
    final static String[] OPS = "+ - * /".split(" ");//正则表达式，以空格分隔，存入+-*/


    public static void main(String[] args) {

        System.out.println(randSearcher(new int[]{1, 5, 5, 5}));
        System.out.println(randSearcher(new int[]{1, 2, 3, 4}));
        System.out.println(randSearcher(new int[]{1, 5, 7, 1}));
        System.out.println(randSearcher(new int[]{1, 1, 1, 1}));
    }
//核心算法
    private static List<String> randSearcher(int[] a) {//每种算法有4数字，3符号组成
        for (int j = 0; j < 4 * 4 * 4; j++) {//共有4*4*4种算法
            String[] ops = new String[3];
            int x = j / 16;
            int y = j % 16 / 4;
            int z = j % 4;
            ops[0] = OPS[x];
            ops[1] = OPS[y];
            ops[2] = OPS[z];

            List<String> exp = new Vector<>();//遍历，把将要输入的四个数数组a[]添加到exp中
            for (Integer i : a) {
                exp.add(Integer.toString(i));
            }
            for (String op : ops) {
                exp.add(op);

            }

            int tot = 0;
            while (++tot < MAX_RAND_SEARCH_COUNT) {
                Collections.shuffle(exp);//使用默认随机源对列表进行置换
                double result = Expression.eval(exp);
                if (result == 24) {
                    return exp;
                }
            }
        }
        return null;
    }


}
