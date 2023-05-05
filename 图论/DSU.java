package 图论;

public class DSU {

    //=========================并查集=========================
    // 一般用于处理图论中连通性问题
    //=======================================================

    int[] fa;
    int count; // 联通分量

    public DSU(int n) {
        fa = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }
        count = n;
    }
    // find根
    private int find(int x) {
        while (x != fa[x]) {
            fa[x] = fa[fa[x]]; // 路径压缩
            x = fa[x];
        }
        return x;
    }
    // 合并a, b
    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        fa[b] = a;
        count--;
    }

    // 判断是否属于同一联通分量
    private boolean isConnected(int a, int b) {
        return find(a) == find(b);
    }

    private int getCount() {
        return count;
    }

    public static void main(String[] args) {

    }
}
