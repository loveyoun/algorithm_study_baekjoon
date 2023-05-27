import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, max;
    static int[][] paper, sums;
    static boolean[][] visited;

    // 동(0), 서(1), 남(2), 북(3)
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    /*
    북, 동, 서
    북, 동, 남
    동, 서, 남
    서, 남, 북
     */
    static int[][] fuck_dir = {
            {3, 0, 1},
            {3, 0, 2},
            {0, 1, 2},
            {1, 2, 3}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        paper = new int[N][M];
        sums = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                paper[i][j] = Integer.parseInt(st.nextToken());
        }


        max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                DFS(i, j, paper[i][j],1);
                setFuck(i, j);
            }
        }

        System.out.println(max);
    }

    static void DFS(int x, int y, int sum, int block) {
        if (visited[x][y]) return;
        if (block == 4) {
            /* 이거 웨 않됌?
             max = Math.max(max, sums[x][y] + paper[x][y]);
             */
            max = Math.max(max, sum);
            return;
        }

        visited[x][y] = true;
        for (int l = 0; l < 4; l++) {
            int new_x = x + dx[l];
            int new_y = y + dy[l];

            if (new_x >= 0 && new_x < N && new_y >= 0 && new_y < M) {
                //sums[new_x][new_y] = sums[x][y] + paper[x][y];

                DFS(new_x, new_y, sum + paper[new_x][new_y], block + 1);

                //sums[new_x][new_y] = 0;
            }
        }

        visited[x][y] = false;
    }

    static void setFuck(int x, int y) {
        for (int k = 0; k < 4; k++) {
            int sum = paper[x][y];
            boolean impossible = false;

            for (int l = 0; l < 3; l++) {
                int new_x = x + dx[fuck_dir[k][l]];
                int new_y = y + dy[fuck_dir[k][l]];

                if (new_x >= 0 && new_x < N && new_y >= 0 && new_y < M)
                    sum += paper[new_x][new_y];
                else {
                    impossible = true;
                    break;
                }
            }

            if (!impossible) max = Math.max(max, sum);
        }
    }

}