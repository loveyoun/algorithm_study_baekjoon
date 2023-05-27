import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] bamboos;
    static int[][] dp;
    static int N, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        bamboos = new int[N][N];
        dp = new int[N][N];
        for (int r = 0; r < N; ++r) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; ++c)
                bamboos[r][c] = Integer.parseInt(st.nextToken());
        }

        
        // DP[][]: 거꾸로 올 때, 해당 지역까지 이동할 수 있는 최댓값
        max = 0;
        for (int r = 0; r < N; ++r) {
            for (int c = 0; c < N; ++c) {
                int path_len = dfs(r, c);
                max = path_len > max ? path_len : max;
            }
        }

        System.out.println(max);
    }

    private static int dfs(int r, int c) {
        /*
         역방향 : 이미 갱신된 지역은 바로 그 값을 리턴한다.
         정방향 : 자기 까지 올 수 있는 루트가 또 있다. 그래서 visited 처리 해주면 안 된다.
         그럼, 자기 시작이 아니라, 자기가 마지막일 때 갈 수 있는 곳을 다음으로 가는 방향으로 생각하자.
         */
        if (dp[r][c] != 0) return dp[r][c];

        dp[r][c] = 1;

        for (int k = 0; k < 4; k++) {
            int new_r = r + dir[k][0];
            int new_c = c + dir[k][1];
            if (new_r >= N || new_r < 0 || new_c >= N || new_c < 0) continue;

            if (bamboos[new_r][new_c] > bamboos[r][c])
                // !!!!! 재귀함수 이용해서 돌아오면서 갱신 !!!!!
                dp[r][c] = Math.max(dp[r][c], dfs(new_r, new_c) + 1);
        }

        return dp[r][c];
    }

}