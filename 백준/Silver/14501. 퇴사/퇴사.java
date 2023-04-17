import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        /** 15486_Algorithm_flow_퇴사 2: DP
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 2];  // 0 ~ N+1
        int[] pay = new int[N + 1]; // 0 ~ N
        ArrayList<Integer>[] can_next = new ArrayList[N + 2]; // 0 ~ N+1
        for (int i = 0; i <= N + 1; i++) can_next[i] = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int next_time = i + Integer.parseInt(st.nextToken());
            pay[i] = Integer.parseInt(st.nextToken());

            //can_next 에 퇴사 가능
            if (next_time <= N + 1)
                can_next[next_time].add(i);
        }


        /* dp[i] : i가 퇴사일 때, 최대 임금
         * i-1 일까지 일할 수 있다.
         */
        int max = 0;
        for (int i = 2; i <= N + 1; i++) {
            int isExists = can_next[i].size();

            //꽉 채울 수 있을 때
            if (isExists > 0) {
                for (int j = 0; j < isExists; j++) {
                    int tmp = can_next[i].get(j);
                    dp[i] = Math.max(dp[i], dp[tmp] + pay[tmp]);
                }
            }

            /* 시간 초과
            꽉 채울 수 없을 때
            for (int j = 2; j < i; j++)
                dp[i] = Math.max(dp[i], dp[j]);
             */
            dp[i] = Math.max(max, dp[i]);
            max = Math.max(max, dp[i]);
        }

        System.out.println(dp[N + 1]);
    }

}