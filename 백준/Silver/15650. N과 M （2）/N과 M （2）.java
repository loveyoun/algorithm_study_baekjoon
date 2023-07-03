// 내 풀이

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static StringBuilder sb = new StringBuilder();
    public static boolean[] visited;
    public static int[] tmp;
    public static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);

        visited = new boolean[N];
        tmp = new int[M];

        /** 재귀함수(한 칸을 채우는데, 해당 iteration 에서 (index+1) ~ N 중에서 썼는지 안 썼는지 계속 확인) **/
        recur(M);

        System.out.print(sb);
    }

    public static void recur(int m) {
        if (m == 0) {
            for (int i = 0; i < M; i++) sb.append(tmp[i]).append(" ");
            sb.append("\n");

            return;
        }

        int i;
        for (i = N; i >= 1; i--) if (visited[i - 1]) break; /** 어디까지 채웠는지 확인 -> 인자로 넘겨줘도 되겠다(dfs, depth) **/
        for (i += 1; i <= N; i++) {
            //if (visited[i - 1]) continue;

            visited[i - 1] = true;
            tmp[M - m] = i; // tmp[0] 부터

            recur(m - 1);

            visited[i - 1] = false;
        }
    }

}