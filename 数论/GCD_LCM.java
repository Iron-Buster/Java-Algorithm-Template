package 数论;

public class GCD_LCM {


    //==============================数论========================================
    // GCD: 最大公约数
    // LCM: 最小公倍数
    //=========================================================================


    static int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }

    static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public static void main(String[] args) {
        int x = 4;
        int y = 6;
        System.out.println(gcd(x, y)); // 2
        System.out.println(lcm(x, y)); // 12
    }
}
