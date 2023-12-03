import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            for (int j = k; j >= w; j--) { // j = 5, w = 2, j - w = 3
                if (dp[j] >= dp[j - w] + v) continue;
                
                if (dp[j - w] > 0 || (j - w) == 0)
                    dp[j] = dp[j - w] + v;
            }

        }


        int max = 0;
        for (int i = 1; i <= k; i++) max = Math.max(max, dp[i]);

        System.out.print(max);
    }


}