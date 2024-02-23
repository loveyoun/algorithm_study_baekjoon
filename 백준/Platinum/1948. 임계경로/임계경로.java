import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Node>[] dosi, reverse;
    static int[] indegree, dist;
    static boolean[] visited;
    static int n, m, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dosi = new ArrayList[n + 1];
        reverse = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            dosi[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }
        indegree = new int[n + 1];
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            dosi[s].add(new Node(e, t));
            reverse[e].add(new Node(s, t));
            indegree[e]++; // 2, 4, 5, 6 -> 7 = 4
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
        dist = new int[n + 1];
        int max = dfs(start);
//        unionFind(start);

        ans = 0;
        visited = new boolean[n + 1];
        backtrack(start, max);

        System.out.println(max);
        System.out.println(ans);
    }

    private static int dfs(int now) {
        if (visited[now]) return dist[now];
        visited[now] = true;

        for (Node next : dosi[now]) {
            dist[now] = Math.max(dist[now], dfs(next.v) + next.time);
        }

        return dist[now];
    }

    private static void unionFind(int start) { // 위상정렬
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        for (int i = 1; i < n + 1; i++) {
            int now = q.poll();
            for (Node next : dosi[now]) {
                indegree[next.v]--;
                dist[next.v] = Math.max(dist[next.v], dfs(next.v) + next.time);
            }
            if (indegree[now] == 0) ;

        }
    }

    private static void backtrack(int now, int left) {
        if (visited[now]) return;
        visited[now] = true;

        for (Node next : dosi[now]) {
            if (left - next.time != dist[next.v]) continue;
            ans++;
            backtrack(next.v, left - next.time);
        }
    }

    static class Node {
        int v, time;

        Node(int v, int time) {
            this.v = v;
            this.time = time;
        }
    }

}