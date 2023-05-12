import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, stabled, stable;
    static int[][] lab, infected;
    static boolean[][] visited;
    static int max;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        /** 14502_Algorithm_연구소 :
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        stabled = 0;

        lab = new int[N][M];
        infected = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 0) stabled++;
            }
        }
        stabled -= 3;


        max = 0;
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

                                            visited = new boolean[N][M];
                                            infected = new int[N][M];
                                            stable = stabled;
                                            BFS();
                                            max = Math.max(max, stable);

                                            lab[n][m] = 0;
                                        }
                                        m++;
                                    } //m = 0; 무한루프
                                } // 세 번째 벽
                                lab[x][y] = 0;
                            }
                            y++;
                        } //y = 0; 무한루프
                    } // 두 번째 벽
                    lab[i][j] = 0;
                }
            } // 첫 번째 벽
        }
    }

    static void BFS() {
        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (lab[i][j] == 2 || infected[i][j] == 2) {
                    if (!visited[i][j]) {
                        infected[i][j] = 2;
                        visited[i][j] = true;
                        queue.add(new Point(i, j));

                        while (!queue.isEmpty()) {
                            Point now = queue.poll();
                            int x = now.x;
                            int y = now.y;

                            for (int k = 0; k < 4; k++) {
                                int new_x = x + dx[k];
                                int new_y = y + dy[k];

                                if (new_x >= 0 && new_x < N && new_y >= 0 && new_y < M) {
                                    // lab: 기존 바이러스(2), 기존 안전(0), 기존 벽 + 새로운 벽(1)
                                    // infected: 새로운 바이러스(2)
                                    if (lab[new_x][new_y] == 0 && infected[new_x][new_y] == 0) {
                                        infected[new_x][new_y] = 2;
                                        visited[new_x][new_y] = true;
                                        stable--;
                                        queue.add(new Point(new_x, new_y));
                                    }
                                }
                            }
                        } //while (!queue.isEmpty())
                    } //if (!visited[i][j])
                } //if (infected[i][j] == 2 && !visited[i][j])
            }
        }
    }

    /*
    시간이 너무 걸린다. 갯수로 해결해보자 -> int stable
    static void findStable(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
            }
        }
    }
     */

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}