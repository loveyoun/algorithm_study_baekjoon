import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static char[][] map;
    static int[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();
        visited = new int[N][M]; // 0 : 정상, 1 ~ K 번 깨짐
        for (int i = 0; i < N; i++) Arrays.fill(visited[i], 11);

        if (N == 1 && M == 1) System.out.println(1);
        else System.out.println(BFS());
    }

    static int BFS() {
        //PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.path));
        Queue<Point> queue = new LinkedList<>();

        visited[0][0] = 0;
        queue.add(new Point(0, 0, 1, 0));

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int cur_x = now.x;
            int cur_y = now.y;
            int path = now.path + 1;
            // Break Point
            /*if (cur_x == N - 1 && cur_y == M - 1) return path;*/

            for (int k = 0; k < 4; k++) {
                int new_x = cur_x + dx[k];
                int new_y = cur_y + dy[k];
                int wallCount = now.brokeCount;
                if (new_x < 0 || new_x >= N || new_y < 0 || new_y >= M) continue;

                // 벽'1' 만났을 때 하나 더 꺨 수 있으니까 깨고
                if (map[new_x][new_y] == '1') wallCount++;
                if (wallCount > K) continue;

                /* '0'
                 3번째 벽 깰 차례인데 이미 1 or 2에서 깨버렸으면 : x
                 -> 1, 2가 틀리면 3으로 가야함. 0이면 괜찮음 : x
                 -> 1, 2 가 안되면 어차피 3도 안될 것. 더 꺨 수 있는 적은 수로 업데이트
                 벽을 던 깬걸로 업데이트 해주는 게 . 그 루트가 실패할 수도 있고
                 실패하면 더 벽을 깰 수 있는 걸로 대체되어야함. 더 안전한 루트
                 if (visited[new_x][new_y] <= wallCount) continue;
                 */
                if (visited[new_x][new_y] > wallCount) visited[new_x][new_y] = wallCount; // wallCount 번 깬 루트이다
                else continue; // <=

                if (new_x == N - 1 && new_y == M - 1) return path;
                queue.add(new Point(new_x, new_y, path, wallCount));
            }
        }

        return -1;
    }

    static class Point {
        int x, y, path, brokeCount;

        Point(int x, int y, int path, int brokeCount) {
            this.x = x;
            this.y = y;
            this.path = path;
            this.brokeCount = brokeCount;
        }
    }

}