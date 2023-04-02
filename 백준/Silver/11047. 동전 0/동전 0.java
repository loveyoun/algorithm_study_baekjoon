import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        /** 11047_Algorithm_flow_동전 0: 그리디
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coins = new int[N];
        for (int i = 0; i < N; i++) coins[i] = Integer.parseInt(br.readLine());

        int count = 0;
        for (int i = N - 1; i >= 0; i--) {
            //4200인데, 10000으로 나눌 수 없고, 5000으로 나눌 수 없고, 1000으로 나눌 수 있다
            if (K >= coins[i]) {
                count += (K / coins[i]);
                K %= coins[i];
            } 
            /*
            나머지가 0이 될 때까지.
            근데 1원까지 있으니, 조건으로 따질 필요 없음.
            else break;
             */
        }

        System.out.println(count);
    }

}