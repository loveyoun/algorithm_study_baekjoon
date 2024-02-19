import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        //String[] tmp = br.readLine().split(" ");

        graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++)
            graph[i] = new ArrayList<>(); /***/

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph[s].add(e);
            graph[e].add(s);
        }


        visited = new boolean[N + 1]; /***/
        int cc = 0;
        for (int i = 1; i < N + 1; i++) {
            if (visited[i]) continue;
            cc++;
            dfs(i);
        }

        System.out.println(cc);
    }

    static void dfs(int v) {
//        if (visited[v]) return;
        visited[v] = true; /***/

        for (int i = 0; i < graph[v].size(); i++) {
            //for (int next : graph[v])
            int next = graph[v].get(i);

            if (visited[next]) continue;
            dfs(next);
        }

    }


}