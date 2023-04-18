import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int nodes, edges, start;
    static int[] distance;
    static boolean[] visited;
    static ArrayList<Edge>[] graph;
    static PriorityQueue<Edge> pqueue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        nodes = Integer.parseInt(st.nextToken());
        edges = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());

        visited = new boolean[nodes + 1];
        distance = new int[nodes + 1];
        for (int i = 1; i <= nodes; i++) distance[i] = Integer.MAX_VALUE;

        graph = new ArrayList[nodes + 1];
        for (int i = 1; i <= nodes; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 일방향
            graph[s].add(new Edge(e, v));
        }


        dijkstra(start);


        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= nodes; i++) {
            if (visited[i]) sb.append(distance[i] + "\n");
            else sb.append("INF\n");
        }
        System.out.println(sb);
    }

    public static void dijkstra(int start) {
        pqueue.add(new Edge(start, 0));
        distance[start] = 0;

        while (!pqueue.isEmpty()) {
            Edge now = pqueue.poll();
            int now_node = now.node;
            if (!visited[now_node]) {
                visited[now_node] = true;

                for (Edge e : graph[now_node]) {
                    int next_node = e.node;
                    int btw_value = e.value;
                    // 다른 곳에서 온 거리 vs 지금 곳에서 온 거리
                    if (distance[next_node] > distance[now_node] + btw_value) {
                        distance[next_node] = distance[now_node] + btw_value;
                        pqueue.add(new Edge(next_node, distance[next_node]));
                    }
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int node, value;

        Edge(int node, int value) {
            this.node = node;
            this.value = value;
        }

        @Override
        public int compareTo(Edge e) {
            return this.value - e.value;
        }
    }

}