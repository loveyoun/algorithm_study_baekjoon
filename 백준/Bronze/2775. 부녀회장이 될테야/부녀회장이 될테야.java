import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            int k = Integer.parseInt(br.readLine());

            dp = new int[n + 1][k + 1];
            for (int j = 1; j <= k; j++) dp[0][j] = j;
            for (int i = 1; i <= n; i++) dp[i][1] = 1;

//            for (int i = 1; i <= n; i++) {  // 층
//                for (int j = 2; j <= k; j++) {  // 호
//                    for (int x = 1; x <= j; x++) dp[i][j] += dp[i - 1][x];
//                }
//            }

            for (int i = 1; i <= n; i++) {  // 층
                for (int j = 1; j <= k; j++) {  // 호
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }


            sb.append(dp[n][k]).append("\n");
        }


        System.out.print(sb);
    }

    static int dfs(int i, int j) {
        // return point
        if (i == 0) return dp[i][j];

        // base case
        if (dp[i][j] != -1) return dp[i][j];

        return dp[i][j] = dfs(i - 1, j) + dfs(i - 1, j - 1);
    }


}