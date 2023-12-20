import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static final int MOD = 1_000_000_007;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());


        long[][][] dp = new long[101][L + 2][R + 2];

        // Base case 조심
//        if (N > 1)
            dp[1][1][1] = 1;

        for (int i = 1; i < N; i++) {
            for (int x = 1; x <= L; x++) {
                for (int y = 1; y <= R; y++) {
                    if (dp[i][x][y] == 0) continue;

                    dp[i + 1][x + 1][y] = (dp[i + 1][x + 1][y] + dp[i][x][y]) % MOD;
                    dp[i + 1][x][y + 1] = (dp[i + 1][x][y + 1] + dp[i][x][y]) % MOD;

                    if (i - 1 > 0)
                        dp[i + 1][x][y] = (dp[i + 1][x][y] + dp[i][x][y] * (i - 1)) % MOD;
                }

            }

        }


//        for (int i = 1; i <= N; i++) {
//            for (int x = 1; x <= L; x++) {
//                for (int y = 1; y <= R; y++) {
//                    System.out.print(dp[i][x][y] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }

        System.out.println(dp[N][L][R]);
    }


}