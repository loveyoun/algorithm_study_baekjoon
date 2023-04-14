import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        /**1850_Algorithm_flow_최대공약수: 유클리드 호제법(정수론)
         **/

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        long a = Long.parseLong(tmp[0]);
        long b = Long.parseLong(tmp[1]);

        /**
         a -> 1...1로 안바꾸어도 되는 이유:
         1) 11...11의 특징. 1111(4) = 11(2) * 100 + 11(2)
         2) 출력 값의 특징.
         **/
        long result = gcd(a, b);

        for (int i = 0; i < result; i++) sb.append(1);

        System.out.println(sb);
    }

    static long gcd(long a, long b) {
        // a > b
        if (b == 0) return a;
        else return gcd(b, a % b);
    }

}