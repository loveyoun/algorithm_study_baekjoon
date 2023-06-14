import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, Is, min;
    static char is;
    static char[][] nation;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] dist;
    static Queue<int[]> int_que;
    static Queue<Bridge> brg_queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nation = new char[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                nation[i][j] = st.nextToken().charAt(0); /*br.readLine().split(" ").toString().toCharArray();*/
        }

        // 섬 나누기
        Is = 0;
        dist = new int[N][N]; // 한 번만 해줘도 됨.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || nation[i][j] == '0') continue;
                divideIslands(i, j);
            }
        }

        // 다리 놓기 4방향 계속 진행 dfs
        // bfs?   min 값 업데이트
        min = Integer.MAX_VALUE;
        setBridge();
        /* 메모리 초과
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (nation[i][j] == '0') continue;
                //dist = new int[N][N];
                setBridge(i, j, nation[i][j]);
            }
        }
        */
        System.out.println(min);
    }

    static void divideIslands(int x, int y) {
        Is++;
        is = (char) (Is + '0');

        int_que = new LinkedList<>();

        nation[x][y] = is;
        visited[x][y] = true;
        int_que.add(new int[]{x, y});
        brg_queue.add(new Bridge(x, y, 0, is));
        dist[x][y] = is;

        while (!int_que.isEmpty()) {
            int[] node = int_que.poll();
            int now_x = node[0];
            int now_y = node[1];

            for (int k = 0; k < 4; k++) {
                int new_x = now_x + dx[k];
                int new_y = now_y + dy[k];

                if (new_x < 0 || new_x >= N || new_y < 0 || new_y >= N) continue;
                if (visited[new_x][new_y] || nation[new_x][new_y] == '0') continue;

                nation[new_x][new_y] = is;
                visited[new_x][new_y] = true;
                int_que.add(new int[]{new_x, new_y});
                brg_queue.add(new Bridge(new_x, new_y, 0, is));
                dist[new_x][new_y] = is;
            }
        }
    }

    static void setBridge() {
        while (!brg_queue.isEmpty()) {
            Bridge brg = brg_queue.poll();
            int now_x = brg.x;
            int now_y = brg.y;
            char IsNum = brg.IsNum; // 시작 섬
            int path = brg.path;

            for (int k = 0; k < 4; k++) {
                int new_x = now_x + dx[k];
                int new_y = now_y + dy[k];
                if (new_x < 0 || new_x >= N || new_y < 0 || new_y >= N) continue;

                int num = nation[new_x][new_y];

                // 이미 다리놨거나 같은 섬이면
                //if (dist[new_x][new_y] > 0 && dist[new_x][new_y] <= path) continue;
                if (dist[new_x][new_y] == IsNum) continue;
                if (num == IsNum) continue;

                // 바다나 다른 섬 만날 때
                if (num == '0') {
                    dist[new_x][new_y] = IsNum;
                    brg_queue.add(new Bridge(new_x, new_y, path + 1, IsNum));
                } else {
                    min = Math.min(min, path);
                    return;
                }
            }
        }
    }

    static class Bridge {
        int x, y, path;
        char IsNum;

        Bridge(int x, int y, int path, char IsNum) {
            this.x = x;
            this.y = y;
            this.path = path;
            this.IsNum = IsNum;
        }
    }

}