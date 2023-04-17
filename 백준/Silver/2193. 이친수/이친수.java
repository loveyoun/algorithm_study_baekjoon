import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        /**2193_Algorithm flow_이친수: 반복되는 기시감 -> 점화식이겠다.**/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] pinary = new long[N + 1]; // index: 0 ~ N

        /* 점화식(or 수학적 귀납법) 초기값 */
        /**예외 처리
         N == 1일 때 **/
        if (N < 3) { // 1, 2
            System.out.println(1);
            return;
        } else {
            for (int i = 0; i < 3; i++) pinary[i] = 1; // i = 0, 1, 2
            pinary[3] = 2;
            for (int i = 4; i <= N; i++) {
                for (int j = 0; j <= i - 2; j++) {
                    pinary[i] += pinary[j];
                }
            }
        }

        System.out.println(pinary[N]);
    }

}