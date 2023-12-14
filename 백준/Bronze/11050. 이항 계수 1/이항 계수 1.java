import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        // 1. 구현
//        int answer = 1;
//        for (int i = N; i > N - K; i--) // 5Ｃ2, 5Ｃ5
//            answer *= i;
//        for (int i = 2; i <= K; i++) answer /= i;
//
//        System.out.println(answer);


        // 2. 조합
        int[][] dp = new int[N + 1][N + 1];

        // 5Ｃ0=1, 5Ｃ1=5, 5Ｃ5=1
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
            dp[i][1] = i;
            dp[i][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= i - 1; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }

        System.out.println(dp[N][K]);
    }

    static int factorial(int n) {
        if (n <= 1) return 1;

        int ret = 1;
        for (int i = n; i >= 2; i--)
            ret *= i;

        return ret;
    }


}