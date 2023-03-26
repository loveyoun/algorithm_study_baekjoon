import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V, E, K;
    static int[] distance;
    static boolean[] visited;
    static ArrayList<Edge>[] graph;
    static PriorityQueue<Edge> pqueue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        /** 1753_Algorithm flow: 다익스트라 (사실 BFS 응용)
         * **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); //노드개수
        E = Integer.parseInt(st.nextToken()); //엣지개수
        K = Integer.parseInt(br.readLine()); //출발노드번호
        distance = new int[V + 1];
        visited = new boolean[V + 1];
        graph = new ArrayList[V + 1];
        for (int i = 1; i < V + 1; i++) graph[i] = new ArrayList<>();
        for (int i = 1; i < V + 1; i++) distance[i] = Integer.MAX_VALUE;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[s].add(new Edge(e, v));
        }

        //출발 노드
        pqueue.add(new Edge(K, 0));
        distance[K] = 0;

        while (!pqueue.isEmpty()) {
            Edge current = pqueue.poll();
            int c_v = current.vertex;
            if (visited[c_v]) continue;

            visited[c_v] = true;
            for (int i = 0; i < graph[c_v].size(); i++) {
                Edge tmp = graph[c_v].get(i);
                int next = tmp.vertex;
                int value = tmp.value;
                //전에 값 + 엣지 값 이 작으면
                if (distance[next] > distance[c_v] + value) {
                    distance[next] = distance[c_v] + value;
                    pqueue.add(new Edge(next, distance[next]));
                }
            }
        }

        for (int i = 1; i < V + 1; i++) {
            if (visited[i]) System.out.println(distance[i]);

            //경로 없을 때
            else System.out.println("INF");
        }
    }


    static class Edge implements Comparable<Edge> {
        int vertex, value;

        Edge(int vertex, int value) {
            this.vertex = vertex;
            this.value = value;
        }

        public int compareTo(Edge e) {
            if (this.value > e.value) return 1; // 오름차순
            else return -1;
        }
    }

}