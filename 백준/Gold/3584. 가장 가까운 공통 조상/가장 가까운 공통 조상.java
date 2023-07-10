import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static ArrayList<Integer>[] tree;
    static int[] parent, level;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            n = Integer.parseInt(br.readLine());

            tree = new ArrayList[n + 1]; // 1 ~ n 번 노드
            for (int i = 0; i <= n; i++) tree[i] = new ArrayList<>();

            parent = new int[n + 1];
            StringTokenizer st;
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                tree[a].add(b);
                parent[b] = a;  // a는 b의 부모
            }
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());


            int root = 0;
            for (int i = 1; i <= n; i++)
                if (parent[i] == 0) root = i;

            level = new int[n + 1];
            //checkLevel(root, 1);

            //sb.append(findAnc1(u, v)).append("\n");
            visited = new boolean[n + 1];
            sb.append(findAnc2(root, u, v)).append("\n");
        }


        System.out.print(sb);
    }

    static int findAnc1(int u, int v) {
        int dif = level[u] - level[v];

        if (dif > 0) for (int i = 0; i < dif; i++) u = parent[u];
        else if (dif < 0) for (int i = dif; i < 0; i++) v = parent[v];

        while (v != u) {
            u = parent[u];
            v = parent[v];
        }

        return u;
    }

    static int findAnc2(int r, int u, int v) {
        while (u != r) {
            visited[u] = true;
            u = parent[u];
        }
        visited[r] = true;
        while (!visited[v]) {
            visited[v] = true;
            v = parent[v];
        }

        return v;
    }

    static void checkLevel(int r, int l) {
        level[r] = l;

        for (int next : tree[r]) checkLevel(next, l + 1);
    }

}