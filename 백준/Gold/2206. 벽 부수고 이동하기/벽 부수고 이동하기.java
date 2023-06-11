import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] org_visited, brk_visited;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        org_visited = new boolean[N][M];
        brk_visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++)
                map[i][j] = tmp.charAt(j) - 48; // '0'
        }


        if (N == 1 && M == 1) System.out.println(1);
        else System.out.println(BFS(0, 0, 1, false));
    }

    static int BFS(int x, int y, int path, boolean broke) {
        /** path 를 인자로 넘기거나, 배열에 저장할 수도 있다 **/

        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(x, y, path, broke));
        org_visited[x][y] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int cur_x = now.x;
            int cur_y = now.y;
            int cur_path = now.path; // 현재까지 거리

            // Break Point
            if (cur_x == N - 1 && cur_y == M - 1)
                return cur_path;

            for (int i = 0; i < 4; i++) {
                int new_x = cur_x + dx[i];
                int new_y = cur_y + dy[i];
                boolean isBroken = now.broken;

                if (new_x < 0 || new_x >= N || new_y < 0 || new_y >= M) continue;

                // 벽 뚫은 경우
                if (isBroken) {
                    if (map[new_x][new_y] == 1) continue;
                    if (brk_visited[new_x][new_y]) continue;
                }
                // 뚫지 않은 루트
                if (!isBroken) {
                    if (org_visited[new_x][new_y]) continue;
                    org_visited[new_x][new_y] = true;
                    if (map[new_x][new_y] == 1) isBroken = true;
                }

                brk_visited[new_x][new_y] = true; // 벽을 안 뚫어도 정상적으로 미리 도착한게 더 빠르니까
                queue.add(new Point(new_x, new_y, cur_path + 1, isBroken)); /** 항상 visited 여부 조심! **/
            } // 4 방위 탐색
        } // while (queue)

        return -1;
    }

    static class Point {
        int x, y, path;
        boolean broken;

        Point(int x, int y, int path, boolean broken) {
            this.x = x;
            this.y = y;
            this.path = path;
            this.broken = broken;
        }
    }

}