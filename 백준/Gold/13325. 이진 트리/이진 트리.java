import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int k, n;
    static long[] edge, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        n = (int) (Math.pow(2, k + 1) - 2);

        edge = new long[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) edge[i] = Long.parseLong(st.nextToken());


        dp = new long[n + 1];
        System.out.println(dfs(0));
    }

    static long dfs(int e) {
        // Base case : Leaf edge
        if (e >= (1 << k) - 1 && e <= (1 << (k + 1)) - 2)
            return 0;


        long left = dfs(e * 2 + 1);
        long right = dfs(e * 2 + 2);

        long diff = dp[e * 2 + 1] - dp[e * 2 + 2];
        long fill = edge[e * 2 + 1] - edge[e * 2 + 2];
        long half = edge[e * 2 + 1] + edge[e * 2 + 2] + Math.abs(diff + fill);
        dp[e] = (half+ dp[e * 2 + 1] + dp[e * 2 + 2]) / 2;  // 루트에서 리프 노드까지 거리


        return left + right + half;  // 루트에서 리프 노드까지 거리 합 리턴
    }

}
