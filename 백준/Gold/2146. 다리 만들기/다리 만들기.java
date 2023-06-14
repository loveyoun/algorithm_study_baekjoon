import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, Is, min;
    static int[][] nation;
    //static boolean[][] visited;
    //static int[][] occupied;
    static int[][] dist;
    static Queue<int[]> int_que;
    static Queue<Bridge> brg_queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        N = read();
        nation = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                nation[i][j] = read(); //st.nextToken().charAt(0); /*br.readLine().split(" ").toString().toCharArray();*/
        }

        // 섬 나누기
        Is = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (nation[i][j] != 1) continue;
                divideIslands(i, j);
            }
        }

        // 다리 놓기 4방향 계속 진행 dfs
        // bfs? min 값 업데이트
        min = Integer.MAX_VALUE;
        dist = new int[N][N];
        setBridge();

        System.out.println(min);
    }

    static void divideIslands(int x, int y) {
        int_que = new LinkedList<>();

        nation[x][y] = ++Is;
        int_que.add(new int[]{x, y});
        brg_queue.add(new Bridge(x, y,  Is));

        while (!int_que.isEmpty()) {
            int[] node = int_que.poll();
            int now_x = node[0];
            int now_y = node[1];

            for (int k = 0; k < 4; k++) {
                int new_x = now_x + dx[k];
                int new_y = now_y + dy[k];

                if (new_x < 0 || new_x >= N || new_y < 0 || new_y >= N) continue;
                if (nation[new_x][new_y] != 1) continue;  // 0 이거나 섬 번호 지정되었으면

                nation[new_x][new_y] = Is;
                int_que.add(new int[]{new_x, new_y});
                brg_queue.add(new Bridge(new_x, new_y,  Is));
            }
        }
    }

    static void setBridge() {
        while (!brg_queue.isEmpty()) {
            Bridge brg = brg_queue.poll();
            int now_x = brg.x;
            int now_y = brg.y;
            int IsNum = brg.IsNum; // 시작 섬 번호
            int path = dist[now_x][now_y];

            for (int k = 0; k < 4; k++) {
                int new_x = now_x + dx[k];
                int new_y = now_y + dy[k];
                if (new_x < 0 || new_x >= N || new_y < 0 || new_y >= N) continue;
                int num = nation[new_x][new_y];

                // 이미 다리 놨거나 같은 섬이면
                if (num == IsNum) continue;

                // 바다나 다른 섬 만날 때
                if (num == 0) {
                    dist[new_x][new_y] = path + 1;
                    nation[new_x][new_y] = IsNum; /****/
                    brg_queue.add(new Bridge(new_x, new_y, IsNum));
                } else {
                    min = Math.min(min, path + dist[new_x][new_y]);
                    /* 시작점이 다른 BFS 주의
                    return;
                     */
                }
            }
        }
    }

    static class Bridge {
        int x, y;
        int IsNum;

        Bridge(int x, int y, int IsNum) {
            this.x = x;
            this.y = y;
            this.IsNum = IsNum;
        }
    }

    static int read() throws IOException {
        // System.in.read() : 수 하나 씩
        // ex> 31 -> 51, 49 -> 3 * 10 + 1
        int c, n = System.in.read() & 15; // 1111 & 110000(48) ~ 111010(57),
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15); // 스페이스(32), LF(10), CR(13)
        return n;
    }

}