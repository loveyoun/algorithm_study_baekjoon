import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        long dp[][][] = new long[101][101][101];
        dp[1][1][1] = 1;  // 빌딩이 1개: (1,1)
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= L; j++) {
                for (int k = 1; k <= R; k++)
                    dp[i][j][k] =
                            (dp[i - 1][j][k] * (i - 2) + (dp[i - 1][j][k - 1] + dp[i - 1][j - 1][k])) % MOD;
            }

        }

        System.out.println(dp[N][L][R]);
    }


}