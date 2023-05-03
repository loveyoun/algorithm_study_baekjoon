import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());

        final int MAX = 10000;
        int[] dp = new int[MAX + 1];

        // 최악의 상황 : 1 복사(1) -> 1 복붙(1) -> dp[i] == i
        for (int i = 2; i <= MAX; ++i) dp[i] = i;
 
        for (int start = 2; start < MAX; ++start) {
            if (dp[start + 1] + 1 < dp[start]) {
                dp[start] = dp[start + 1] + 1;
            }
            
            int time = dp[start];
            for (int i = start * 2; i <= MAX; i *= 2) {
                time += 2;
                /* 1) 복사하기
                i = 4(2에서 이미 복사 붙여넣기 완료)
                */
                int clip = i / 2;
                if (time < dp[i]) dp[i] = time;
                    
                /* 2) 붙여넣기
                start = 2 일 때, 
                a. i = 4 일 때, 2 계속 붙여넣기
                b. i = 8 일 때, 2 계속 붙여넣기는?
                -> 4 붙여넣고 2 붙여넣을 수 없잖아. a에서 이미 카운트 됨. 
                c. i = 8 일 때, 4 계속 붙여넣기
                */
                int temp = time;
                for (int j = i + clip; j <= MAX; j += clip) {
                    ++temp;
                    if (temp < dp[j]) dp[j] = temp;
                }
            }
        }

        // 3) -1 할 때가 더 빠르면 그걸로.
        for (int i = 2; i < MAX; ++i) {
            if (dp[i + 1] + 1 < dp[i]) {
                dp[i] = dp[i + 1] + 1;
            }
        }

        System.out.println(dp[S]);
    }
}
