import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int city, bus;
    static int[] distance;
    static boolean[] visited;
    static ArrayList<Edge>[] graph;

    static PriorityQueue<Edge> pqueue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        /** 1916_Algorithm flow_최소비용 구하기: 다익스트라 (사실 BFS + 우선순위 큐 해서 응용)
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        city = Integer.parseInt(br.readLine());   // 노드 개수
        bus = Integer.parseInt(br.readLine());   // 엣지 개수

        visited = new boolean[city + 1];
        distance = new int[city + 1];
        for (int i = 1; i < city + 1; i++) distance[i] = Integer.MAX_VALUE;

        graph = new ArrayList[city + 1];
        for (int i = 1; i < city + 1; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < bus; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[s].add(new Edge(e, v));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());


        // 출발 노드
        pqueue.add(new Edge(start, 0));
        distance[start] = 0;
        while (!pqueue.isEmpty()) {
            Edge now = pqueue.poll();
            int now_vertex = now.vertex;
            if (visited[now_vertex]) continue;

            // 안 해주면 시간초과
            visited[now_vertex] = true;

            // 새로운 출발 노드에 연결된 노드들의, 거리 업데이트
            for (int i = 0; i < graph[now_vertex].size(); i++) {
                Edge next = graph[now_vertex].get(i);
                int next_vertex = next.vertex;
                int next_value = next.value;

                // 기존 distance 값 + (now->new).value 값이 더 작으면, 업데이트
                if (distance[next_vertex] > distance[now_vertex] + next_value) {
                    distance[next_vertex] = distance[now_vertex] + next_value;
                    pqueue.add(new Edge(next_vertex, distance[next_vertex]));
                }
            }
        }

        System.out.println(distance[end]);
    }


    static class Edge implements Comparable<Edge> {
        int vertex, value;

        Edge(int vertex, int value) {
            this.vertex = vertex;
            this.value = value;
        }

        public int compareTo(Edge e) {
            return this.value - e.value;
        }
    }

}