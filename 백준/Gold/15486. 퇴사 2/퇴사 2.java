import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 2];
        int[] dur = new int[n + 1];
        int[] pay = new int[n + 1];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            dur[i] = Integer.parseInt(st.nextToken());
            pay[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = n; i >= 0; i--) {
            if (i + dur[i] > n) dp[i] = dp[i + 1];
            else dp[i] = Math.max(dp[i + dur[i]] + pay[i], dp[i + 1]);
        }

        System.out.println(dp[0]);
    }

}