import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static Node[] graph;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        graph = new Node[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Node now = graph[a];
            if (now == null || b < now.v) graph[a] = new Node(b, graph[a]);
            else { // '2' -> 4 -> '5' -> null: 3, 6 삽입
                while (b > now.v) {
                    if (now.next == null || now.next.v > b) break;
                    now = now.next;
                }
                now.next = new Node(b, now.next);
            }

            now = graph[b];
            if (now == null || a < now.v) graph[b] = new Node(a, graph[b]);
            else {
                while (a > now.v) {
                    if (now.next == null || now.next.v > a) break;
                    now = now.next;
                }
                now.next = new Node(a, now.next);
            }
        }
//        for (int i = 1; i <= N; i++) {
//            Node now = graph[i];
//            while (now != null) {
//                System.out.print(now.v + " ");
//                now = now.next;
//            }
//            System.out.println();
//        }

        visited = new boolean[N + 1];
        dfs(V);
        sb.append("\n");

        visited = new boolean[N + 1];
        bfs(V);

        System.out.println(sb);
    }

    static void dfs(int v) {
        visited[v] = true;
        sb.append(v).append(" ");

        for (Node node = graph[v]; node != null; node = node.next) {
            if (!visited[node.v]) {
                dfs(node.v);
            }
        }
    }

    static void bfs(int s) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;

        while (!q.isEmpty()) {
            int v = q.poll();
            sb.append(v).append(" ");

            for (Node node = graph[v]; node != null; node = node.next) {
                int next = node.v;
                if (visited[next]) continue;

                q.add(next);
                visited[next] = true;
            }
        }
    }

    static class Node {
        int v;
        Node next;

        Node(int v, Node next) {
            this.v = v;
            this.next = next;
        }
    }

}