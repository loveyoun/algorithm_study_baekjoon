import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());


        System.out.println(divide5());
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

//    static int divide35() {
//        int bag3 = 0, bag5 = 0;
//
//        if (n % 5 == 0) {
//            System.out.print(n / 5);
//            return;
//        }
//        while ((n / 3) > 0) {
//            n = n - 3;
//            bag3++;
//            if ((n % 5) == 0) {
//                System.out.print(n / 5 + bag3);
//                return;
//            }
//        }
//        if (n == 0) System.out.print(bag3);
//        else System.out.print(-1);
//    }
//
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

}