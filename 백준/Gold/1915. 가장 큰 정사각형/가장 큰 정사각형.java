import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] arr = new char[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String tmp = br.readLine();
            for (int j = 1; j <= m; j++)
                arr[i][j] = tmp.charAt(j - 1);
        }


        int[][] dp = new int[n + 1][m + 1];
        long max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr[i][j] == '0') continue;

                boolean impossible = arr[i - 1][j - 1] == '0' || arr[i - 1][j] == '0' || arr[i][j - 1] == '0';

                if (impossible) dp[i][j] = 1;
                else {
                    int min = dp[i - 1][j - 1];
                    min = Math.min(min, dp[i - 1][j]);
                    min = Math.min(min, dp[i][j - 1]);
                    dp[i][j] = min + 1;
                }

                max = Math.max(max, dp[i][j]);
            }

        }


        System.out.println(max * max);
    }


}