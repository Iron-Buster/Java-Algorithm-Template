package 数据结构;

public class Tire {

    //===============================前缀树==========================
    // 一般用于解决判断字符串前缀等问题
    //==============================================================

    Node root = new Node();

    private void insert(String s) {
        int n = s.length();
        var cur = root;
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            if (cur.son[idx] == null) {
                cur.son[idx] = new Node();
            }
            cur = cur.son[idx];
        }
        cur.isEnd = true;
    }
    // 查找字符串
    private boolean search(String s) {
        int n = s.length();
        var cur = root;
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            if (cur.son[idx] == null) {
                return false;
            }
            cur = cur.son[idx];
        }
        return cur.isEnd;
    }

    // 查找前缀
    private boolean prefix(String pf) {
        int n = pf.length();
        var cur = root;
        for (int i = 0; i < n; i++) {
            int idx = pf.charAt(i) - 'a';
            if (cur.son[idx] == null) {
                return false;
            }
            cur = cur.son[idx];
        }
        return true;
    }

    class Node {
        Node[] son;
        boolean isEnd;
        public Node() {
            son = new Node[26];
            isEnd = false;
        }
    }

    public static void main(String[] args) {

    }
}
