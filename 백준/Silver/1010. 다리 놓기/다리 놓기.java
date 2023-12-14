import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[][] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        dp = new long[30][30];
        for (int i = 0; i < 30; i++) {
            dp[i][0] = 1;
            dp[i][1] = i;
            dp[i][i] = 1;
        }

        for (int i = 3; i < 30; i++) {
            for (int j = 2; j < i; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            sb.append(dp[m][n]).append("\n");
        }


        System.out.print(sb);
    }

    static long dfs(int i, int j) {
        // return point
        if (i == 0) return dp[i][j];

        // base case
        if (dp[i][j] != -1) return dp[i][j];

        return dp[i][j] = dfs(i - 1, j) + dfs(i - 1, j - 1);
    }


}