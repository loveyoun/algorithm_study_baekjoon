import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, safety_z, tmp_safe_z, max, wallCount;
    static int[][] lab;
    static boolean[][] vi_visited;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static ArrayList<Point> virus;
    static Queue<Point> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        safety_z = -3; // 벽 미리 카운트

        lab = new int[N][M];
        vi_visited = new boolean[N][M];
        virus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 0) safety_z++; // 한 iteration 후, 안전 지역을 카운트하는 이중 for 문 대신 사용
                if (lab[i][j] == 2) {
                    vi_visited[i][j] = true; // 전염시킨 바이러스 라는 표시
                    virus.add(new Point(i, j));
                }
            }
        }


        max = 0;
        wallCount = 0;
        makeWall();

        System.out.println(max);
    }

    static void makeWall() {
        // 첫 번째 벽
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (lab[i][j] == 0) {
                    lab[i][j] = 1;

                    // 두 번째 벽
                    for (int x = i; x < N; x++) {
                        int y = 0;
                        if (x == i) y = j + 1;

                        while (y < M) {
                            if (lab[x][y] == 0) {
                                lab[x][y] = 1;

                                //세 번째 벽
                                for (int n = x; n < N; n++) {
                                    int m = 0;
                                    if (n == x) m = y + 1;

                                    while (m < M) {
                                        if (lab[n][m] == 0) {
                                            lab[n][m] = 1;

                                            vi_visited = new boolean[N][M];
                                            tmp_safe_z = safety_z;
                                            BFS();
                                            max = Math.max(max, tmp_safe_z);

                                            lab[n][m] = 0;
                                        }
                                        m++;
                                    } /* m = 0; 무한루프 */
                                } // 세 번째 벽
                                lab[x][y] = 0;
                            }
                            y++;
                        } /* y = 0; 무한루프 */
                    } // 두 번째 벽
                    lab[i][j] = 0;
                }
            } // 첫 번째 벽
        }
    }

    static void BFS() {
        queue = new LinkedList<>();

        // 기존 바이러스 구역으로 초기화
        for (Point point : virus) queue.add(point);

        // queue 에 기존 바이러스 구역을 다 넣고, 전염시키면 된다.
        // 어차피 한 iteration 당 BFS 한 번만 도니까
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int x = now.x;
            int y = now.y;

            for (int k = 0; k < 4; k++) {
                int new_x = x + dx[k];
                int new_y = y + dy[k];

                if (new_x >= 0 && new_x < N && new_y >= 0 && new_y < M) {
                    if (lab[new_x][new_y] == 0 && !vi_visited[new_x][new_y]) {
                        vi_visited[new_x][new_y] = true;
                        /* lab[new_x][new_y] = 2; */
                        tmp_safe_z--;

                        queue.add(new Point(new_x, new_y));
                    }
                }
            } // 동서남북 탐색
        }

    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}