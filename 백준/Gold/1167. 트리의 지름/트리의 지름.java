import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static ArrayList<Node>[] tree;
    static boolean[] visited;
    //    static int[][] weight;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());

        tree = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) tree[i] = new ArrayList<>();
//        weight = new int[V + 1][V + 1];

        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            while (b != -1) {
                int w = Integer.parseInt(st.nextToken());
                tree[a].add(new Node(b, w));
//                weight[a][b] = w;  weight[b][a] = w;

                b = Integer.parseInt(st.nextToken());
            }

        }


        dist = new int[V + 1];
        visited = new boolean[V + 1];
        dfs(1, 0);
//        bfs(1);
        int max_idx = 1;
        for (int i = 2; i <= V; i++) max_idx = dist[max_idx] > dist[i] ? max_idx : i;

        dist = new int[V + 1];
        visited = new boolean[V + 1];
        dfs(max_idx, 0);
//        bfs(max_idx);

        Arrays.sort(dist);
        System.out.println(dist[V]);
    }

    static void dfs(int s, int d) {
        visited[s] = true;

        for (Node next : tree[s]) {
            if (!visited[next.e]) dfs(next.e, d + next.w);
        }
        if (tree[s].size() == 1) dist[s] = d;

        visited[s] = false;
    }

    static void bfs(int s) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;

        while (!q.isEmpty()) {
            int v = q.poll();

            for (Node next : tree[v]) {
                if (visited[next.e]) continue;

                dist[next.e] = dist[v] + next.w;   //weight[next][v];
                q.add(next.e);
                visited[next.e] = true;
            }

        }

    }


    static class Node {

        int e, w;

        Node(int e, int w) {
            this.e = e;
            this.w = w;
        }

    }


}