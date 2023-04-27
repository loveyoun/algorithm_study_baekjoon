import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        /** 1300_Algorithm_K번째 수 : 이진 탐색, 매개변수 탐색
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        long start = 1, end = K;

        while (start < end) {
            long middle = (start + end) / 2;
            long cnt = 0;
            for (int i = 1; i <= N; i++) cnt += Math.min(middle / i, N);

            // lower bound
            if (cnt < K) start = middle + 1;
            else end = middle;

        }

        /* upper bound 하면 안되는 이유.
        start - 1한 값이 배열 값이 아닐 수도 있어서.
        System.out.println(start - 1);
        */
        System.out.println(start);
    }

}