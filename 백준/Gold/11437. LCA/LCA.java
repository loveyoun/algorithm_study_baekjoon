import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static ArrayList<Integer>[] tree;
    static int[] parent;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1]; // 1 ~ n 번 노드
        for (int i = 0; i <= n; i++) tree[i] = new ArrayList<>();

        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        parent = new int[n + 1];
        visited = new boolean[n + 1];
        findParent(1);


        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            sb.append(findAnc(u, v)).append("\n");
        }


        System.out.print(sb);
    }

    static int findAnc(int u, int v) {
        visited = new boolean[n + 1];

        while (u != 0) {
            visited[u] = true;
            u = parent[u];
        }
        while (!visited[v]) v = parent[v];

        return v;
    }

    static void findParent(int v) {
        visited[v] = true;

        for (int child : tree[v]) {
            if (visited[child]) continue;
            parent[child] = v;
            findParent(child);
        }
    }

}