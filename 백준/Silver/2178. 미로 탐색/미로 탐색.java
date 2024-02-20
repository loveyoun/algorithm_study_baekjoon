import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] graph, visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new boolean[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String row = br.readLine();
            for (int j = 1; j <= M; j++) {
                if (row.charAt(j - 1) == '1') graph[i][j] = true;
                else graph[i][j] = false;
            }

        }


        visited = new boolean[N + 1][M + 1];
        System.out.println(bfs(1, 1));
    }

    static int bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y, 1));
        visited[x][y] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = p.x + dx[k];
                int ny = p.y + dy[k];
                int nl = p.len + 1;
                if (nx == N && ny == M) {
                    cnt = nl;
                    break;
                }

                if (nx < 1 || nx > N || ny < 1 || ny > M || visited[nx][ny] || !graph[nx][ny]) continue;
                q.add(new Point(nx, ny, nl));
                visited[nx][ny] = true;
            }

            if (cnt > 0) break;
        }

        return cnt;
    }


    static class Point {

        int x, y, len;

        Point(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }

    }


}