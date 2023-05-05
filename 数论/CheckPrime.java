package 数论;

public class CheckPrime {

    //=========================数论=========================
    // 判断质数
    //=====================================================


    private boolean check(int x) {
        if (x <= 1) return false;
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 26;
        var cp = new CheckPrime();
        for (int i = 2; i <= n; i++) {
            System.out.println(i + " " + cp.check(i));
        }
    }
}
