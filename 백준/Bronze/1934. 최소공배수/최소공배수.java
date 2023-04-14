import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        /**1934_Algorithm_flow_최소공배수: 유클리드 호제법(정수론)
         **/

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] tmp = br.readLine().split(" ");
            int b = Integer.parseInt(tmp[0]);
            int a = Integer.parseInt(tmp[1]);
            //a > b
            int gcd_ab = a * b / gcd(a, b);
            sb.append(gcd_ab + "\n");
        }

        System.out.println(sb);
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }

}