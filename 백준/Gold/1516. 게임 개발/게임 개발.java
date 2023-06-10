import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> orderLst = new ArrayList<>();
        for (int i = 0; i <= N; i++) orderLst.add(new ArrayList<>()); // 1 ~ N번 건물

        int[] indegree = new int[N + 1]; // 위상 저장 배열
        int[] time = new int[N + 1];
        for (int dpt = 1; dpt <= N; dpt++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[dpt] = Integer.parseInt(st.nextToken());

            while (true) {
                int pre = Integer.parseInt(st.nextToken());
                if (pre == -1) break;

                orderLst.get(pre).add(dpt); // 먼저 건물 -> {나중, 나중 ...}
                indegree[dpt]++;
            }
        }


        // 위상 정렬
        Queue<Integer> que = new LinkedList<>();
        for (int dpt = 1; dpt <= N; dpt++) if (indegree[dpt] == 0) que.offer(dpt); // 사전 건물이 없는 건물

        int[] result = new int[N + 1];
        while (!que.isEmpty()) {
            int now = que.poll();
            for (int next : orderLst.get(now)) {
                result[next] = Math.max(result[next], result[now] + time[now]);

                indegree[next]--;
                if (indegree[next] == 0) que.offer(next); // 업데이트 마무리된 건물
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) sb.append((result[i] + time[i]) + "\n");
        System.out.println(sb);
    }

}