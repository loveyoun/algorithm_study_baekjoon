import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[][] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int M = Integer.parseInt(br.readLine());
        int N = 0;
        int[] color = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            color[i] = Integer.parseInt(st.nextToken());
            N += color[i];
        }
        int K = Integer.parseInt(br.readLine());


        // 1. 조합 (실패)
//        dp = new long[N + 1][N + 1];
//        for (int i = 0; i <= N; i++) {
//            dp[i][0] = 1;
//            dp[i][1] = i;
//            dp[i][i] = 1;
//        }
//
//        for (int i = 3; i <= N; i++) {
//            for (int j = 2; j < i; j++) {
//                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
//            }
//        }
//
//        /* double issue? */
//        long mo = dp[N][K];
//        double ja = 0;
//        for (int i = 0; i < M; i++) {
//            if (color[i] < K) continue;
//            ja += (double) dp[color[i]][K] / mo;
//        }
//
//        System.out.print(ja);


        // 2. 구현
        double answer = 0;
        for (int i = 0; i < M; i++) {
            if (color[i] < K) continue;
            double tmp = 1;
            int num = color[i];
            for (int j = 0; j < K; j++) tmp *= (double) (num - j) / (N - j);

            answer += tmp;
        }

        System.out.println(answer);
    }


}