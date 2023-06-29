import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, sx, sy, size, count, min, secs, fx, fy;
    static boolean updated;
    static int[][] aqua;
    static boolean[][] eaten;
    static PriorityQueue<FishInfo> fishes;
    static Queue<int[]> ready;
    static PriorityQueue<int[]> possible;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        aqua = new int[N][N];
        eaten = new boolean[N][N];

        fishes = new PriorityQueue<>((o1, o2) -> {
            return o1.info - o2.info;
        }); // 작은 크기부터
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 9) {
                    sx = i;
                    sy = j;
                    tmp = 0; /****/
                }
                aqua[i][j] = tmp;

                if (tmp == 0) continue;

                fishes.add(new FishInfo(i, j, tmp));
            }
        }


        size = 2;
        count = 0;
        secs = 0;
        ready = new LinkedList<>();

        while (true) {
            while (!fishes.isEmpty()) {
                if (fishes.peek().info >= size) break;

                FishInfo fish = fishes.poll();
                int tx = fish.x;
                int ty = fish.y;

                ready.add(new int[]{tx, ty});

                /* 무한루프 오류
                fishes.add(fish);
                */
            }

            if (ready.isEmpty()) break;  // 이제 먹을 고기가 없을 때

            updated = false;
            min = Integer.MAX_VALUE;
            // size-1 인 애들이랑 bfs로 계속 거리 업데이트
            int r_size = ready.size();
            for (int i = 0; i < r_size; i++) {
                int[] tmp = ready.poll();
                int tx = tmp[0];
                int ty = tmp[1];
                if (eaten[tx][ty]) continue;

                findNear(tx, ty);
                ready.add(tmp);
            }

            if (!updated) break; /** 갈 수 없을 때 **/

            sx = fx;
            sy = fy;
            eaten[sx][sy] = true;
            aqua[sx][sy] = 0;

            // 먹기
            secs += min;
            if (++count == size) {
                size++;
                count = 0;
            }
        }


        System.out.println(secs);
    }

    static void findNear(int tx, int ty) {
        Queue<FishInfo> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        visited[sx][sy] = true;
        q.add(new FishInfo(sx, sy, 0));

        while (!q.isEmpty()) {
            FishInfo tmp = q.poll();
            int x = tmp.x;
            int y = tmp.y;
            int d = tmp.info;
            for (int k = 0; k < 4; k++) {
                int new_x = x + dx[k];
                int new_y = y + dy[k];
                if (new_x < 0 || new_x >= N || new_y < 0 || new_y >= N || visited[new_x][new_y] || aqua[new_x][new_y] > size)
                    continue;

                // size 이상 안 되고, == size이면 지나갈 수는 있고
                if (new_x == tx && new_y == ty) {
                    if (d + 1 < min) {
                        updated = true;
                        min = d + 1;
                        fx = new_x; /****/
                        fy = new_y;
                    } else if (d + 1 == min) {
                        if (new_x < fx) {
                            fx = new_x;
                            fy = new_y;
                        } else if (new_x == fx && new_y < fy) {
                            fx = new_x;
                            fy = new_y;
                        }
                    }

                    return;
                }

                visited[new_x][new_y] = true;  /****/
                q.add(new FishInfo(new_x, new_y, d + 1));
            }
        }

    }


    static class FishInfo {
        int x, y, info;

        FishInfo(int x, int y, int info) {
            this.x = x;
            this.y = y;
            this.info = info;
        }
    }

}