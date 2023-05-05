package 数论;

public class FilterPrime {

    //=======================数论============================
    // 筛质数
    // 欧拉线性筛
    // 埃氏筛
    //======================================================


    static final int N = 100001;
    static int idx = 0;
    static boolean[] st = new boolean[N];
    static int[] primes = new int[N];

    // 埃氏筛
    static void method1(int N) {
        for (int i = 2; i < N; i++) {
            if (st[i]) continue;
            primes[idx++] = i;
            for (int j = i + i; j < N; j += i) { // 划掉i的倍数
                st[j] = true;
            }
        }
        for (int i = 0; i < N; i++) {
            if (primes[i] != 0) {
                System.out.println(primes[i]);
            }
        }
    }

    // 欧拉线性筛
    static void method2(int N) {
        for (int i = 2; i < N; i++) {
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
        for (int i = 0; i < N; i++) {
            if (primes[i] != 0) {
                System.out.println(primes[i]);
            }
        }
    }


    public static void main(String[] args) {
        method1(1000);
    }
}
