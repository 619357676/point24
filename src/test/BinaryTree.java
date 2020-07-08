package test;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BinaryTree {//定义二叉树
    String root;
    BinaryTree left, right;

    public BinaryTree(String root, BinaryTree left, BinaryTree right) {
        this.root = root;
        this.left = left;
        this.right = right;
    }

    public BinaryTree(String root) {
        this(root, null, null);
    }
    //中序遍历
    void midVisit() {
        System.out.print("(");
        if (left != null) {
            left.midVisit();
        }
        System.out.print(root);
        if (right != null) {
            right.midVisit();
        }
        System.out.print(")");
    }

    void midVisit(StringBuffer buffer) {//当对字符串进行修改的时候，需要使用 StringBuffer
        boolean isLeaf = (left == null && right == null);//没有子结点的结点
        if (!isLeaf) buffer.append("(");//append( )是往动态字符串数组添加
        if (left != null) {
            left.midVisit(buffer);
        }
        buffer.append(root);
        if (right != null) {
            right.midVisit(buffer);
        }
        if (!isLeaf) buffer.append(")");

    }

    static BinaryTree Expression(List<String> exp) {
        Stack<BinaryTree> S = new Stack<>();
        for (String tok : exp) {
            if (isNumber(tok)) {
                S.push(new BinaryTree(tok));
            } else {
                BinaryTree right = S.peek();
                S.pop();
                BinaryTree left = S.peek();
                S.pop();
                S.push(new BinaryTree(tok, left, right));
            }
        }

        return S.peek();

    }

    public static void main(String[] args) {
        BinaryTree left = new BinaryTree("1");
        BinaryTree right = new BinaryTree("5");
        BinaryTree tree = new BinaryTree("/", left, right);
        //tree.midVisit();

        BinaryTree btree = BinaryTree.Expression(Arrays.asList("5, 1, 5, /, -, 5, *".split("\\s*,\\s*")));
        // btree.midVisit();//可移植性差 耦合了System.out
        StringBuffer buffer = new StringBuffer();
        btree.midVisit(buffer);
        System.out.println(buffer);

    }

    public static boolean isNumber(String tok) {
        try {
            Double.parseDouble(tok);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
