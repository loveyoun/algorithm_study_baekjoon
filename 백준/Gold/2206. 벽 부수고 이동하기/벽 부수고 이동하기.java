import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map, nb_path, yb_path; // no_broken_path & yes_broken_path
    static boolean[][] nb_visited, yb_visited;
    static boolean isPossible;
    static int result;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        /** 2206_Algorithm_벽 부수고 이동하기 :
         **/
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nb_visited = new boolean[N][M];
        yb_visited = new boolean[N][M];
        map = new int[N][M];
        nb_path = new int[N][M];
        yb_path = new int[N][M];
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++)
                map[i][j] = tmp.charAt(j) - 48; // '0'
        }


        isPossible = false;
        nb_path[0][0] = 1;
        result = 1;

        BFS(0, 0, result, false);
        if (N == 1 && M == 1) isPossible = true;

        if (yb_path[N - 1][M - 1] == 0) result = nb_path[N - 1][M - 1];
        else if (nb_path[N - 1][M - 1] == 0) result = yb_path[N - 1][M - 1];
        else result = Math.min(nb_path[N - 1][M - 1], yb_path[N - 1][M - 1]);

        if (isPossible) System.out.println(result);
        else System.out.println(-1);
    }

    static void BFS(int x, int y, int path, boolean broke) {
        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(x, y, path, broke));
        nb_visited[x][y] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int cur_x = now.x;
            int cur_y = now.y;
            int cur_path = now.path;

            for (int i = 0; i < 4; i++) {
                int new_x = cur_x + dx[i];
                int new_y = cur_y + dy[i];
                boolean isBroken = now.broke;

                if (new_x >= 0 && new_x < N && new_y >= 0 && new_y < M) {
                    if (isBroken) {
                        if (!yb_visited[new_x][new_y]) {
                            yb_visited[new_x][new_y] = true;

                            if (new_x == N - 1 && new_y == M - 1) {
                                isPossible = true;
                                yb_path[new_x][new_y] = cur_path + 1;
                                //break;
                            }

                            if (map[new_x][new_y] == 1)
                                if (isBroken) continue;

                            yb_path[new_x][new_y] = cur_path + 1;
                            queue.add(new Point(new_x, new_y, cur_path + 1, isBroken));
                        }
                    } else {
                        if (!nb_visited[new_x][new_y]) {
                            //visited[new_x][new_y] = true;
                            nb_visited[new_x][new_y] = true;
                            yb_visited[new_x][new_y] = true;

                            /*
                             끝에 오기도 전에 무한루프에 빠질 수 있다. 경로가 없는 경우에도 무조건 무한루프.
                             이게 break point가 되지는 않는다.
                            */
                            if (new_x == N - 1 && new_y == M - 1) {
                                isPossible = true;
                                nb_path[new_x][new_y] = cur_path + 1;
                            }

                            if (map[new_x][new_y] == 1) {
                                if (isBroken) {
                                    //visited[new_x][new_y] = true;
                                    continue;
                                } else isBroken = true;
                            }

                            //if (!isBroken) nb_visited[new_x][new_y] = true;

                            //if (cur_path + 1 < nb_path[new_x][new_y]) {
                            nb_path[new_x][new_y] = cur_path + 1;
                            queue.add(new Point(new_x, new_y, cur_path + 1, isBroken));
                        }
                    }
                }
            }

            if (isPossible) break;
        }
    }

    static class Point {
        int x, y, path;
        boolean broke;

        Point(int x, int y, int path, boolean broke) {
            this.x = x;
            this.y = y;
            this.path = path;
            this.broke = broke;
        }
    }

}