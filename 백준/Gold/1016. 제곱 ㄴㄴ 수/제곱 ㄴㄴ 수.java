import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        /**1016_Algorithm_flow_제곱 ㄴㄴ 수: 정수론(에라토스테네스의 체)
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());
        boolean[] isPow = new boolean[(int) (max - min + 1)];

        // 에라토스테네스의 체
        for (long i = 2; i * i <= max; i++) {
            long pow = i * i; // pow = 3 * 3 = 9
            long start_index = min / pow; // min <= pow * j <= max
            if (min % pow != 0) start_index++;

            for (long j = start_index; pow * j <= max; j++)
                isPow[(int) ((j * pow) - min)] = true;
        }


        int count = 0;
        for (int i = 0; i <= max - min; i++) {
            if (!isPow[i]) count++;
        }

        System.out.println(count);
    }

}