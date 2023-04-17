import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        /**11689_Algorithm_flow_GCD(n, k) = 1: 정수론(오일러 피), 소인수 분해
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        // 소인수분해
        long result = n;
        for (long pf = 2; pf <= Math.sqrt(n); pf++) {
            if (n % pf == 0) {
                // 오일러 피
                result = result - result / pf;
                while (n % pf == 0) n /= pf;
            }
        }

        // n * n 일 때, (n+k : 소수) * (n+l : 소수) 일 수 없다.
        if (n > 1) result -= result / n;

        System.out.println(result);
    }

}