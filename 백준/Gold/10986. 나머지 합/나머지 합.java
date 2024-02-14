import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] rArr = new long[M];
        long sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sum += Integer.parseInt(st.nextToken());
            rArr[(int) (sum % M)]++; // 나머지
        }

        long answer = 0;
        for (int i = 0; i < M; i++) {
            if (rArr[i] > 1)
                answer += rArr[i] * (rArr[i] - 1) / 2; // nC₂
        }

        System.out.println(answer + rArr[0]);
    }

}