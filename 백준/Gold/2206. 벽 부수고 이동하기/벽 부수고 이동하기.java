import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[2][N][M]; // 0: not_broken, 1: broken

        for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();

        System.out.println(BFS());
    }

    private static int BFS() {
        Queue<Point> q = new LinkedList<>();

        visited[0][0][0] = true;
        q.add(new Point(0, 0, 1, 0));

        while (!q.isEmpty()) {
            Point now = q.poll();
            int now_x = now.x;
            int now_y = now.y;
            int path = now.path;
            if (now_x == N - 1 && now_y == M - 1) return path;

            for (int i = 0; i < 4; i++) {
                int new_x = now_x + dx[i];
                int new_y = now_y + dy[i];
                int isBroken = now.isBroken;
                if (new_x < 0 || new_x >= N || new_y < 0 || new_y >= M) continue;

                // visited 여부, 벽_'1', 정_'1'
                if (visited[isBroken][new_x][new_y] || (map[new_x][new_y] == '1' && isBroken == 1)) continue;
                if (map[new_x][new_y] == '1' && isBroken == 0) isBroken = 1;

                // visited 처리, 벽_'0', 정_'0' 
                visited[isBroken][new_x][new_y] = true;
                q.add(new Point(new_x, new_y, path + 1, isBroken));
            }
        }

        return -1;
    }

    static class Point {
        int x, y, path, isBroken;

        public Point(int x, int y, int path, int isBroken) {
            this.x = x;
            this.y = y;
            this.path = path;
            this.isBroken = isBroken;
        }
    }
}