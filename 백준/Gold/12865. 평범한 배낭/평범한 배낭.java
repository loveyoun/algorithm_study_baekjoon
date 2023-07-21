/**
 * Top - down
 **/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] dp;
    static int[] w, v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        w = new int[n];
        v = new int[n];
        dp = new int[n][k + 1];  // [n개 물건][가방 무게 합]

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }


        System.out.println(knapsack(n - 1, k));
    }

    static int knapsack(int i, int k) {
        // Base case
        if (i < 0) return 0;

        // 업데이트 되었다면
        if (dp[i][k] != 0) return dp[i][k];  /****/

        // 현재 물건(i)을 추가로 못담는 경우 (이전 i값 탐색)
        if (w[i] > k) dp[i][k] = knapsack(i - 1, k);
        else dp[i][k] = Math.max(knapsack(i - 1, k), knapsack(i - 1, k - w[i]) + v[i]);


        return dp[i][k];
    }

}