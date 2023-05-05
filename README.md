# Java-Algorithm-Template

`一个真正的鳗` `JDK11` 😨😨😨


## java算法和数据结构模板库 (更新中😨)


### 重要工具 java快读模板 🤖

```java
class Main {

    static InputReader in = new InputReader();
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    static class InputReader {
        private StringTokenizer st;
        private BufferedReader bf;

        public InputReader() {
            bf = new BufferedReader(new InputStreamReader(System.in));
            st = null;
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(bf.readLine());
            }
            return st.nextToken();
        }

        public String nextLine() throws IOException {
            return bf.readLine();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
}
```


### 算法模板
**一些算法小技巧**
```java
class Main {
//    上取整
    void solve(int x, int y) {
        int ans = (x + y - 1) / y;
    }
//    负数取模
    void solve(int p) {
        int x = -1000;
        int v = (x % p + p) % p;
    }
}
```
1. 二进制枚举子集
```java
class Main {
    
    void solve(int n) {
        for (int i = 1; i < (1 << n); i++) {
            for (int j = i; j; j = (j - 1) & 1) {
                //...
            }
        }
    }
}
```
2. 快速排序
```java
class Main {
    void quickSort(int[] q, int l, int r) {
        int i = l - 1;
        int j = r + 1;
        int x = q[l + r >> 1];
        while (i < j) {
            do { i++; } while (q[i] < x);
            do { j--; } while (q[j] > x);
            if (i < j) swap(q, i, j);
        }
        quickSort(q, l, j);
        quickSort(q, j+ 1, r);
    }
    
    void swap(int[] q, int i, int j) {
        q[i] ^= q[j];
        q[j] ^= q[i];
        q[i] ^= q[j];
    }
}
```
3. 归并排序
```java
class Main {
    int[] tmp;
    void mergeSort(int[] q, int l, int r) {
        if (l >= r) return;
        int mid = l + r >> 1;
        mergeSort(q, l, mid);
        mergeSort(q, mid + 1, r);
        int k = 0;
        int i = l;
        int j = mid + 1;
        while (i <= mid && j <= r) {
            if (q[i] <= q[j]) tmp[k++] = q[i++];
            else tmp[k++] = q[j++];
        }
        while (i <= mid) tmp[k++] = q[i++];
        while (j <= r) tmp[k++] = q[j++];
        for (i = l, j = 0; i <= r; i++, j++) {
            q[i] = tmp[j];
        }
    }
}
```
4. **很重要的二分模板**
```java
class Main {
    
    boolean check(int mid) {
        // 检查mid是否满足某种性质 一般用于二分答案
        // 假如mid满足 那么 [mid+1, r]也满足
        // 假如mid不满足 那么 [l, mid-1]也不满足
        return true;
    } 
    // 最大值最小
    // 区间[l, r] 被划分为[l, mid] 和 [mid+1, r]时使用
    int bSearch1(int l, int r) {
        while (l < r) {
            int mid = l + r >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    // 最小值最大
    // 区间[l, r] 被划分为[l, mid-1] 和 [mid, r]时使用
    int bSearch2(int l, int r) {
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
```
5. 一维前缀和
```java
class Main {
    
    void solve(int[] arr, int l, int r) {
        int n = arr.length;
        int[] s = new int[n+1];
        // 构建前缀和数组
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + arr[i - 1];
        }
        int sum = sumRange(s, l, r);
    }
    // 查询区间和[l, r]
    int sumRange(int[] s, int l, int r) {
        return s[r] - s[l-1];
    }
}
```
6. 二维前缀和
```java
class Main {
    
    void solve(int[][] mat, int x1, int y1, int x2, int y2) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] s = new int[m+10][n+10];
        // 构建前缀和数组
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = i + 1, y = j + 1;
                s[x][y] = mat[i][j];
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                s[i][j] += s[i-1][j] + s[i][j-1] - s[i-1][j-1];
            }
        }
    }
    // (x1, y1)是左上角 (x2,y2)是右下角
    int sumRange(int[][] s, int x1, int y1, int x2, int y2) {
        return s[x2+1][y2+1] - s[x1][y2+1] - s[x2+1][y1] + s[x1][y1];
    }
}
```
7. 一维差分
```java
class Main {
    void solve(int[] arr, int[][] ops, int c) {
        int n = arr.length;
        // 给区间 [l, r]中每个数加上 c
        int[] diff = new int[n+1];
        for (int[] o : ops) {
            int l = o[0], r = o[1];
            diff[l] += c;
            diff[r + 1] -= c;
        }
    }
}
```
8. 二分差分
```java
class Main {
    // n * n的矩阵
    void solve(int n, int[][] queries) {
        int[][] diff = new int[n+2][n+2];
        for (int[] q : queries) {
            // 区间 + 1
            int x1 = q[0], y1 = q[1], x2 = q[2], y2 = q[3];
            diff[x1+1][y1+1] += 1;
            diff[x2+2][y1+1] -= 1;
            diff[x1+1][y2+2] -= 1;
            diff[x2+2][y2+2] += 1;
        }
        int[][] arr = new int[n][n];
        // 前缀和还原数组
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                arr[i-1][j-1] = (diff[i][j] += diff[i-1][j] + diff[i][j-1] - diff[i-1][j-1]);
            }
        }
    }
}
```
9. 滑动窗口 (元素翻转|替换模板)
```text
这样的题目一般是: 给你一串数字(字符串), 你可以最多翻转k次, 求数组中连续相同字符(数字)的长度
```

```java
class Main {
    int solve(String s, int k) {
        char[] ss = s.toCharArray();
        int[] map = new int[26];
        int maxCnt = 0;
        int i = 0, j = 0, ans = 0;
        while (i < ss.length) {
            map[ss[i]-'a']++;
            maxCnt = Math.max(maxCnt, map[ss[i]-'a']);
            if (i - j + 1 > maxCnt + k) {
                char l = ss[j];
                map[l-'a']--;
                j++;
            }
            ans = Math.max(ans, i - j + 1);
            i++;
        }
        return ans;
    }
}
```
10. 滑动窗口 (恰好k个模板)
```text
这样的题目一般是求:  
给你一个数组, 求出数组中恰好k个满足条件的子数组
问题转换成求 最多满足k个 和 最多满足k-1个子数组的个数 两个作差可求出答案
```

```java
class Main {
    // 求出恰好k个奇数的子数组个数
    int solve(int[] nums, int k) {
        return calc(nums, k) - calc(nums, k - 1);
    }
    
    int calc(int[] nums, int k) {
        int i = 0, j = 0, cnt = 0;
        int ans = 0;
        while (i < nums.length) {
            cnt += (nums[i] & 1);
            while (cnt > k) {
                cnt -= (nums[j] & 1);
                j++;
            }
            ans += i - j + 1;
            i++;
        }
        return ans;
    }
}
```

### 数据结构模板
1. Trie
> 一般用于解决字符串前缀匹配相关问题
```java
class Main {
    Node root = new Node();
    // 插入s
    void insert(String s) {
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
    // 查找前缀
    boolean search(String pf) {
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
        boolean isEnd;
        Node[] son;

        public Node() {
            son = new Node[26];
            isEnd = false;
        }
    }
}
```


### 数论问题模板
1. 筛质数

- 1.1 欧拉线性筛
```java
class Main {
    static final int N = 1000;
    int idx;
    int[] primes = new int[N+1];     // primes[]存储所有素数
    boolean[] st = new boolean[N+1]; // st[x]存储x是否被筛掉
    
    void getPrime() {
        for (int i = 2; i <= N; i++) {
            if (!st[i]) {
                primes[idx++] = i;
            }
            for (int j = 0; primes[j] <= N / i; j++) {
                st[primes[j] * i] = true;
                if (i % primes[j] == 0) {
                    break;
                }
            }
        }
    }
}
```
- 1.2 埃氏筛
```java
class Main {
    static final int N = 1000;
    int idx;
    int[] primes = new int[N+1];     // primes[]存储所有素数
    boolean[] st = new boolean[N+1]; // st[x]存储x是否被筛掉
    
    void getPrime() {
        for (int i = 2; i <= N; i++) {
            if (st[i]) continue;
            primes[idx++] = i;
            for (int j = i + i; j <= N; j += i) {
               st[j] = true; 
            }
        } 
    }
}
```
2. 质因数分解
```java
class Main {
    
    void divide(int x) {
        int tmp = x;
        for (int i = 2; i * i <= tmp; i++) {
            if (tmp % i == 0) {
                while (tmp % i == 0) {
                    tmp /= i;
                }
            }
        }
        if (tmp > 1) {
            // ...
        }
    }
}
```
3. 最大公约数GCD
```java
class Main {
    
    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```
4. 模运算~~快速~~龟速幂
```java
class Main {
    // 求 m^k mod p 时间复杂度 O(logk)
    int qpow(int m, int k, int p) {
        int ans = 1 % p, t = m;
        while (k > 0) {
            if ((k & 1) == 1) {
                ans = (ans * t) % p;
            }
            t = (t * t) % p;
            k >>= 1;
        }
        return ans;
    }
}
```

### RMQ问题模板
1. 单点修改线段树
```java
import java.util.Arrays;

class SegmentTree {

    static int N = 4 * 30001;
    static int[] a = new int[N];
    static int[] f = new int[4 * N];

    public SegmentTree(int[] nums) {
        for (int i = 1; i <= nums.length; i++) {
            a[i] = nums[i - 1];
        }
        buildTree(1, 1, nums.length);
    }

    // 区间查询 [s,t]
    int calc(int k, int l, int r, int s, int t) {
        if (l == s && r == t) {
            return f[k];
        }
        int m = (l + r) >> 1;
        if (t <= m) {
            return calc(k + k, l, m, s, t);
        } else {
            if (s > m) {
                return calc(k + k + 1, m + 1, r, s, t);
            } else {
                return calc(k + k, l, m, s, m) + calc(k + k + 1, m + 1, r, m + 1, t);
            }
        }
    }

    // 单点修改
    void add(int k, int l, int r, int x, int y) {
        f[k] += y;
        if (l == r) return;
        int m = (l + r) >> 1;
        if (x <= m) {
            add(k + k, l, m, x, y);
        } else {
            add(k + k + 1, m + 1, r, x, y);
        }
    }

    void buildTree(int k, int l, int r) {
        if (l == r) {
            f[k] = a[l];
            return;
        }
        int m = (l + r) >> 1;
        buildTree(k + k, l, m);
        buildTree(k + k + 1, m + 1, r);
        f[k] = f[k + k] + f[k + k + 1];
    }
}
```
2. 区间修改线段树
```java
class SegmentTree {
    
    static int N = 100001;
    static int[] a = new int[N];
    static int[] f = new int[4*N];
    static int[] v = new int[4*N];
    
    void buildTree(int k, int l, int r) {
        v[k] = 0;
        if (l == r) {
            f[k] = a[l];
            return;
        }
        int m = (l + r) >> 1;
        buildTree(k + k, l, m);
        buildTree(k + k + 1, l, m + 1, r);
        f[k] = f[k + k] + f[k + k + 1];
    }
    // 在[l,r] 中的[x,y]区间 加上z
    void insert(int k, int l, int r, int x, int y, int z) {
        if (l == x && r == y) {
            v[k] += z;
            return;
        }
        f[k] += (y - x + 1) * z;
        int m = (l + r) >> 1;
        if (y <= m) {
            insert(k + k, l, m, x, y, z);
        } else {
            if (x > m) {
                insert(k + k + 1, m + 1, r, x, y, z);
            } else {
                insert(k + k, l, m, x, m, z);
                insert(k + k + 1, m + 1, r, m + 1, y, z);
            }
        }
    }
    // 区间求和
    int calc(int k, int l, int r, int x, int y, int p) {
        p += v[k];
        if (l == x && r == y) {
            return p * (r - l + 1) + f[k];
        }
        int m = (l + r) >> 1;
        if (y <= m) {
            return calc(k + k, l, m, x, y, p);
        } else {
            if (x > m) {
                return calc(k + k + 1, m + 1, r, x, y, p);
            } else {
                return calc(k + k, l, m, x, m, p) + calc(k + k + 1, m + 1, r, m + 1, y, p);
            }
        }
    }
    
    public SegmentTree(int[] nums) {
        for (int i = 1; i <= nums.length; i++) {
            a[i] = nums[i - 1];
        }
        buildTree(1, 1, nums.length);
        
    }
}
```


### 图论问题模板
**草莓奶昔的图论求环总结**

| 求什么?          | 算法                                  |
|---------------|-------------------------------------|
| 有向带权图最小环      | Dijkstra                            | 
| 有向带权图最大环      | 边变为负数+spfa 求最短路                     | 
| 无向带权图最小环      | Dijkstra                            |
| 无向带权图最大环      | 边变为负数+spfa 求最短路                     |
| 有向无权图最小环      | 拓扑排序找到所有环分组/Tarjan 缩点成拓扑图           |
| 有向无权图最大环      | 拓扑排序找到所有环分组/Tarjan 缩点成拓扑图           |
| 无向无权图最小环      | 拓扑排序找到所有环分组/dfs 找到所有环分组/Tarjan 缩点成树 |
| 无向无权图最大环      | 拓扑排序找到所有环分组/dfs 找到所有环分组/Tarjan 缩点成树 |
| 处理路径上边权/点权的问题 | 离线查询 + 并查集                          |

**树转图小技巧**
```java
class Main {

    boolean[] vis = new boolean[10001];
    
    class Node {
        int val;
        Node left;
        Node right;
        Node fa;

        public Node(int val) {
            this.val = val;
        }
    }

    Node st = null;
    // dfs建图 start选择以 val = start的节点为起点, 参数可选
    Node dfs(TreeNode root, int start) {
        if (root == null) return null;
        var node = new Node(root.val);
        if (node.val == start) st = node;
        node.left = dfs(root.left, start);
        node.right = dfs(root.right, start);
        if (node.left != null) node.left.fa = node;
        if (node.right != null) node.right.fa = node;
        return node;
    }
}
```
1. Tarjan求桥边板子
```java
import java.util.ArrayList;
import java.util.List;

class Tarjan {
    int n;
    int time;
    int[] low;
    int[] dfn;
    List<Integer>[] g;
    List<List<Integer>> ans = new ArrayList<>(); // 存储桥边

    public Tarjan(List<Integer>[] g) {
        this.g = g;
        n = g.length;
        dfn = new int[n];
        low = new int[n];
        for (int i = 0; i < n; i++) {
            if (dfn[i] == 0) {
                dfs(i, -1);
            }
        }
    }

    void dfs(int u, int fa) {
        dfn[u] = low[u] = ++time;
        for (int v : g[u]) {
            if (dfn[v] == 0) {
                dfs(v, u);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > dfn[u]) {  // 找桥边
                    ans.add(List.of(u, v));
                }
            } else if (v != fa) {       // 搜索到父节点
                low[u] = Math.min(low[u], dfn[v]);
            }
        }
    }
}
```
2. Tarjan求环大小板子
```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Tarjan {
    int n;
    int time;
    int[] dfn;
    int[] low;
    boolean[] inStack;
    List<Integer>[] g;
    LinkedList<Integer> st = new LinkedList<>();
    List<List<Integer>> all = new ArrayList<>();

    public Tarjan(List<Integer>[] g) {
        this.g = g;
        n = g.length;
        dfn = new int[n];
        low = new int[n];
        inStack = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (dfn[i] == 0) {
                dfs(i);
            }
        }
    }

    void dfs(int u) {
        st.push(u);
        inStack[u] = true;
        dfn[u] = low[u] = ++time;
        for (int v : g[u]) {
            if (dfn[v] == 0) {
                dfs(v);
                low[u] = Math.min(low[u], low[v]);
            } else if (inStack[v]) {
                low[u] = Math.min(low[u], dfn[v]);
            }
        }
//        当dfn[x]==low[x]，代表着发现一个强联通分量
        if (dfn[u] == low[u]) { //  说明u 与u之后的节点构成SSC [环]
            var list = new ArrayList<Integer>();
            while (st.peek() != u) {
                var p = st.pop();
                list.add(p);
                inStack[p] = false;
            }
            list.add(st.pop());
            all.add(list);
        }
    }
}
```
3. Spfa判断图中是否存在负环板子
```java
import java.util.LinkedList;
import java.util.List;

class Spfa {
    int n;  // 节点数
    int[][] w;
    int[] dist; // dist[x]存储1号点到x的最短距离
    int[] cnt;  // cnt[x]存储1到x的最短路中经过的点数
    boolean[] st; // 存储每个点是否在队列中
    List<Integer>[] g;

    public Spfa(List<Integer>[] g, int[][] w) {
        this.g = g;
        this.w = w;
        n = g.length;
        dist = new int[n];
        cnt = new int[n];
        st = new boolean[n];
    }

    boolean solve() {
        var q = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) {
            q.offer(i);
            st[i] = true;
        }
        while (q.size() > 0) {
            int u = q.poll();
            st[u] = false;
            for (int v : g[u]) {
                if (dist[v] > dist[u] + w[u][v]) {
                    dist[v] = dist[u] + w[u][v];
                    cnt[v] = cnt[u] + 1;
                    if (cnt[v] >= n) return true; // 如果从1号点到x的最短路中包含至少n个点（不包括自己），则说明存在环
                    if (!st[v]) {
                        q.offer(v);
                        st[v] = true;
                    }
                }
            }
        }
        return false;
    }
}
```
4. 并查集板子
```java
class UnionFind {
    int count;
    int[] fa;
    
    public UnionFind(int n) {
        // 根据节点最小编号选择从0 或者 1开始
        for (int i = 0; i < n; i++) {
            fa[i] = i;
        }
        count = n;
    }
    
    int find(int x) {
        while (x != fa[x]) {
            fa[x] = fa[fa[x]];
            x = fa[x];
        }
        return x;
    }
    
    void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        fa[b] = a;
        count--;
    }
    
    boolean isConnected(int a, int b) {
        return find(a) == find(b);
    }
    
    int getCount() {
        return count;
    }
}
```
5. Dijkstra(堆优化)板子
```java
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Dijkstra {
    int n;          // 点的数量
    int[][] w;        // 边权
    int[] dist;     // 存储所有点到1号点的距离
    boolean[] st;   // 存储每个点的最短距离是否已确定
    List<Integer>[] g;
    
    public Dijkstra(List<Integer>[] g, int[][] w) {
        this.g = g;
        this.w = w;
        n = g.length;
        dist = new int[n];
        st = new boolean[n];
        
    }

    void solve() {
        Arrays.fill(dist, 0x3f3f3f);
        dist[1] = 0;
        var pq = new PriorityQueue<int[]>();
        pq.offer(new int[]{0, 1});
        while (pq.size() > 0) {
            var p = pq.poll();
            int u = p[0], dis = t[1];
            if (st[u]) continue;
            st[u] = true;
            for (int v : g[u]) {
                if (dist[v] > d + w[u][v]) {
                    dist[v] = d + w[u][v];
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }
    }
}
```
6. bfs求最小环
```java
class Main {
    
    List<Integer>[] g;
    int[] dis;
    
    public int findShortestCycle(int n, int[][] edges) {
        g = new ArrayList[n];
        dis = new int[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        int ans = 0x3f3f3f;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, bfs(i));
        }
        return ans;
    }

    int bfs(int start) {
        int ans = 0x3f3f3f;
        Arrays.fill(dis, -1);
        dis[start] = 0;
        var q = new LinkedList<int[]>();
        q.offer(new int[]{start, -1});
        while (q.size() > 0) {
            var p = q.poll();
            int x = p[0], fa = p[1];
            for (int y : g[x]) {
                if (dis[y] < 0) {
                    dis[y] = dis[x] + 1;
                    q.offer(new int[]{y, x});
                } else if (y != fa) {
                    ans = Math.min(ans, dis[x] + dis[y] + 1);
                }
            }
        }
        return ans;
    }
}
```
