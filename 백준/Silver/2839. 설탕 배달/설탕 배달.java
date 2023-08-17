import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());


        System.out.println(dp());
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

    static int divide5() {
        return 0;
    }

    static int divide3() {
        return 0;
    }

}