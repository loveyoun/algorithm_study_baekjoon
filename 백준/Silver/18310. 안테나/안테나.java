import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long[] houses = new long[N];
        long[] sums = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            houses[i] = Long.parseLong(st.nextToken());
        Arrays.sort(houses);

        sums[0] = houses[0];
        for (int i = 1; i < N; i++)
            sums[i] = sums[i - 1] + houses[i];
        
        /* 반례
        1, 90 일 때 답은 90 근데 0 나옴(초기 설정 answer 값)
        answer = 0 -> answer = houses[0]
        if 조건문 대신에
         */
        long min = sums[N - 1], answer = houses[0];
        for (int i = 1; i < N; i++) {
            int smaller = (i + 1) - 1; // Math.min(i - 1, 0); 삼항연산자 대체
            int bigger = N - (i + 1);
            long antenna = houses[i];
            long sum = antenna * smaller - sums[i - 1] + sums[N - 1] - sums[i] - antenna * bigger;
            if (sum < min) {
                min = sum;
                answer = antenna;
            }
        }

        bw.write(answer + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

}