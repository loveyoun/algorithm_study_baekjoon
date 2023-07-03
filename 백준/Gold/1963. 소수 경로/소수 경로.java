import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int MAX = 10000;
    static StringBuilder sb = new StringBuilder();
    static boolean[] prime = new boolean[MAX];
    static boolean[] visited;
    static int[] count;
    static int n, before, after;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 2; i < MAX; i++) prime[i] = true;
        eratos();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            before = Integer.parseInt(st.nextToken());
            after = Integer.parseInt(st.nextToken());

            count = new int[MAX]; // ~ 9999
            visited = new boolean[MAX];

            if (bfs(before, after) || before == after) sb.append(count[after]).append("\n");
            else sb.append("Impossible").append("\n");
        }


        System.out.print(sb);
    }

    static boolean bfs(int s, int e) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        visited[s] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            visited[now] = true;

            for (int n = 1; n <= 4; n++) {
                int cipher = (int) Math.pow(10, n);
                int limit = now % cipher;
                cipher /= 10;
                limit = limit / cipher;
                int tmp = now - limit * cipher;

                for (int k = 0; k <= 9; k++) {
                    int next = tmp + k * cipher;
                    if (next == now || next < 1000 || next > 9999 || visited[next] || !prime[next]) continue;

                    count[next] = count[now] + 1;

                    if (next == e) return true;

                    queue.add(next);
                    visited[next] = true;
                }
            }
        }

        return false;
    }

    static void eratos() {
        for (int i = 2; i <= Math.sqrt(MAX); i++) {
            if (!prime[i]) continue;
            for (int j = i + i; j < MAX; j += i) prime[j] = false;
        }
    }

}