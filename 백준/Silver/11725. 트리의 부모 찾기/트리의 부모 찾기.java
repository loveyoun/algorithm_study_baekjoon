import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static ArrayList<Integer>[] tree;
    static int[] p;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        p = new int[n + 1];
        tree = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) tree[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].add(v);
            tree[v].add(u); // 양방향 저장
        }


        visited = new boolean[n + 1];
        //dfs(1);
        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) sb.append(p[i]).append("\n");
        System.out.println(sb);
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        visited = new boolean[n + 1];
        q.add(1);

        while (!q.isEmpty()) {
            int now = q.poll();

            //if (visited[now]) continue;
            visited[now] = true;

            for (int next : tree[now]) {
                if (visited[next]) continue;

                p[next] = now;
                q.add(next);
            }

        }

    }

    static void dfs(int now) {
        if (visited[now]) return;
        visited[now] = true;

        for (int next : tree[now]) {
            if (visited[next]) continue; // 부모 노드

            // 위 노드부터 오니까 순서가 정해져 있다
            p[next] = now;
            dfs(next);
        }

    }

}