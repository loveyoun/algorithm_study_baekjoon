import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int n;
    static int[][] p = {{0, 2, 2, 2, 2},
            {0, 1, 3, 4, 3},
            {0, 3, 1, 3, 4},
            {0, 4, 3, 1, 3},
            {2, 3, 4, 3, 1}};
    static int[] arr;
    static long[][][] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[100001];
        for (int i = 0; i <= 100000; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if (tmp == 0) {
                n = i;
                break;
            }
            arr[i] = tmp;
        }


        dp = new long[n][5][5];  // 0 ~ 4
        System.out.println(dfs(0, 0, 0));
    }


    private static long dfs(int seq, int L, int R) {
        // Base case
        if (seq == n) return 0;
        // return point
        if (dp[seq][L][R] != 0)
            return dp[seq][L][R];

        int i = arr[seq];

        // L != R
        if (i == L)  // R -> i == L => L -> i: [i][R]
            dp[seq][L][R] = dfs(seq + 1, i, R) + p[L][i];
        else if (i == R)
            dp[seq][L][R] = dfs(seq + 1, L, i) + p[R][i];
        else dp[seq][L][R] =
                    Math.min(dfs(seq + 1, i, R) + p[L][i], dfs(seq + 1, L, i) + p[R][i]);


        // dp[seq][L][R]: seq+1 ~ n 번 수열을 수행해서 [L][R] 에 있을때 최소 누적 힘
        return dp[seq][L][R];
    }


}