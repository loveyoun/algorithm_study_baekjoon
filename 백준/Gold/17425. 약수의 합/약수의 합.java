import java.io.*;

public class Main {
    static final int MAX_VALUE = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /** 에라토스테네스의 체로 미리 배열 생성 **/
        long[] divisors_sum = new long[MAX_VALUE + 1];
        //Arrays.fill(divisors_sum, 1); 개미눈만큼 느림

        for (int i = 2; i <= MAX_VALUE; i++) { //i <= Math.sqrt(MAX_VALUE) 최적화
            // i의 배수들 다 걸러주기. 약수 구하는 거랑 똑같음.
            for (int j = 1; i * j <= MAX_VALUE; j++)
                divisors_sum[i * j] += i;

            // f(y) -> g(x)
            divisors_sum[i] = divisors_sum[i - 1] + divisors_sum[i]; // 최적화 불가능
        }


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 테스트 개수
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int test_num = Integer.parseInt(br.readLine());
            bw.write((divisors_sum[test_num] + test_num) + "\n"); // 1 ~ test_num 까지 약수 1 더해주기.
        }
        /* 개미손만큼 느림
        while(n-- > 0) bw.write(divisors_sum[Integer.parseInt(br.readLine())] + "\n");
         */

        bw.flush();
        bw.close();
    }

}