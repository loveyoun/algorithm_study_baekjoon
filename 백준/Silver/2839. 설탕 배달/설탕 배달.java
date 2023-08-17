import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());


        System.out.println(divide35());
    }


    static int dp() {
        int[] bong = new int[n + 1];
        Arrays.fill(bong, Integer.MAX_VALUE);
        bong[0] = -1;
        bong[1] = -1;
        bong[2] = -1;
        bong[3] = 1;
        /*bong[4] = -1; bong[5] = 1; ArrayIndexOutOfBounds*/

        for (int i = 4; i <= n; i++) {
            int div = i / 5;
            for (int j = 1; j <= div; j++) {
                if (bong[j * 5] != -1 && bong[i - j * 5] != -1)
                    bong[i] = Math.min(bong[i], bong[j * 5] + bong[i - j * 5]);
            }
            if (i % 3 == 0) bong[i] = Math.min(bong[i], i / 3);
            if (i % 5 == 0) bong[i] = Math.min(bong[i], i / 5);

            if (bong[i] == Integer.MAX_VALUE) bong[i] = -1;
        }

        return bong[n];
    }

    static int dp2() {
        int[] dp = new int[n + 1];
        if (n >= 3) dp[3] = 1;
        if (n >= 5) dp[5] = 1;


        for (int i = 6; i <= n; i++) {
            if (i % 5 == 0)
                dp[i] = dp[i - 5] + 1;
            else if (i % 3 == 0)
                dp[i] = dp[i - 3] + 1;
            else {
                if (dp[i - 3] != 0 && dp[i - 5] != 0)
                    dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
            }

        }


        return dp[n];
    }


    static int divide() {
        int cnt = 0;

        while (n > 0) {
            if (n % 5 == 0) {
                cnt++;
                n -= 5;
            } else {
                cnt++;
                n -= 3;
            }

            if (n < 0) return -1;
        }

        return cnt;
    }

    static int divide35() {
        int bag3 = 0;

        if (n % 5 == 0) return n / 5;

        while ((n / 3) > 0) {
            n -= 3;
            bag3++;
            if ((n % 5) == 0)
                return n / 5 + bag3;

        }

        if (n == 0) return bag3;
        else return -1;
    }

//    static int divide3() {
//        if (n % 3 == 0 && n < 15) {
//            System.out.print(n / 3);
//            return;
//        } else {
//            int n = n / 3;
//            int r = n % 3;
//            while (n > 0) {
//                if ((3 * n + r) % 5 == 0) {
//                    System.out.print(n / 3 - n + (3 * n + r) / 5);
//                    return;
//                }
//                n--;
//            }
//            System.out.print(-1);
//            return;
//        }
//    }

    static int divide5() {
        int q = n / 5;
        int r = n % 5;

        if (r == 1) {
            if (q >= 1) return q += -1 + 2;
            else q = -1;
        } else if (r == 2) {
            if (q >= 2) return q += -2 + 4;
            else q = -1;
        } else if (r == 3) return q += 1;
        else if (r == 4) {
            if (q >= 1) return q += -1 + 3;
            else q = -1;
        }

        return q;
    }

    static int divide53() {   // == n - 5, - 10...
        for (int i = n / 5; i >= 0; i--) {
            if ((n - (5 * i)) % 3 == 0)
                return i + ((n - (5 * i)) / 3);
        }

        return -1;
    }

}