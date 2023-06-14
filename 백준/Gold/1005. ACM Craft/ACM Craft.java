import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static int N;
    static int[] time;
    static boolean[] visited;
    static ArrayList<Integer>[] orderLst;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int tc = read();

        for (int t = 0; t < tc; t++) {
            N = read();
            int K = read(); // 건설순서 규칙 개수

            time = new int[N + 1];
            visited = new boolean[N + 1];
            for (int i = 1; i <= N; i++) time[i] = read();

            orderLst = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) orderLst[i] = new ArrayList<>();
            for (int i = 0; i < K; i++) {
                int pre = read();
                int pst = read();

                orderLst[pst].add(pre); // 나중 건물 -> {먼저, 먼저, ... }
            }


            int toWin = read();
            build(toWin);
            sb.append(time[toWin] + "\n");
        }

        System.out.println(sb);
    }

    static void build(int num) {
        if (visited[num]) return;

        visited[num] = true;

        int max = 0;
        for (int i = 0; i < orderLst[num].size(); i++) {
            int pre_num = orderLst[num].get(i);

            // 전 단계 꺼 업데이트 다 한 후에,
            if (orderLst[pre_num].size() > 0) build(pre_num);

            /** 위상정렬 **/
            max = Math.max(max, time[num] + time[pre_num]);
        }

        time[num] = Math.max(max, time[num]); /** 모두 0 일 때 조심 **/
    }

    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

}