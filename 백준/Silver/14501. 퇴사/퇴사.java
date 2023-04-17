import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        /** 14501_Algorithm_flow_퇴사: DP
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 2];  // index: 0 ~ N+1
        int[] pay = new int[N + 1]; // 0 ~ N
        ArrayList<Integer>[] can_quit = new ArrayList[N + 2]; // 0 ~ N+1
        for (int i = 0; i <= N + 1; i++) can_quit[i] = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int duration = Integer.parseInt(st.nextToken());
            int before_quit = i + duration;
            pay[i] = Integer.parseInt(st.nextToken());

            // i 일 부터 일해서, can_quit 일에 퇴사 가능
            if (before_quit <= N + 1)
                can_quit[before_quit].add(i);
        }
        
        /* 
         dp[i] : i 일에 퇴사일 때, 최대 임금
         i-1 일까지 일할 수 있다.
         */
        int max = 0;
        for (int i = 2; i <= N + 1; i++) {
            int isExist = can_quit[i].size();

            // 꽉 채울 수 있을 때. i-1 일까지 일할 수 있을 때
            if (isExist > 0) {
                for (int j = 0; j < isExist; j++) {
                    int tmp = can_quit[i].get(j);
                    dp[i] = Math.max(dp[i], dp[tmp] + pay[tmp]);
                }
            }

            // 꽉 채울 수 없을 때. i-1 일까지는 일하는 게 아닐 때.
            /* 시간 초과
            for (int j = 2; j < i; j++)
                dp[i] = Math.max(dp[i], dp[j]);
             */
            dp[i] = Math.max(max, dp[i]);
            max = Math.max(max, dp[i]);
        }

        System.out.println(dp[N + 1]);
    }

}