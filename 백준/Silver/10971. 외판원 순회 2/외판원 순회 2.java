import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, min = Integer.MAX_VALUE;
    static int[][] city;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        city = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++)
                city[i][j] = Integer.parseInt(st.nextToken());
        }


        visited = new boolean[n + 1];

        // 순회 : 어디에서 출발해서 n개 다 돈 후 최소값 비교
        TSP(1, 0, 0);

        System.out.println(min);
    }

    static void TSP(int via, int cost, int count) {
        // BASE CASE
        if (count == n - 1) {
            if (city[via][1] != 0) min = Math.min(min, cost + city[via][1]);
            return;
        }

        // DFS: 꼬리잡기, BFS: 어깨동무
        /* 오류
           1 -> 2 -> 1 일 때
         */
        for (int to = 2; to <= n; to++) {
            if (visited[to] || city[via][to] == 0) continue;

            visited[to] = true;

            TSP(to, cost + city[via][to], count + 1);

            visited[to] = false;
        }

    }

}