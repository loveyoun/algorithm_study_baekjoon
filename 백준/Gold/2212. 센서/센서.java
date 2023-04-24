import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        /** 2212_Algorithm_flow_센서: 그리디(구현), 정렬
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[] sensors = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++)
            sensors[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(sensors);

        int[] diffs = new int[N - 1];
        for (int i = 0; i < N - 1; i++)
            diffs[i] = sensors[i + 1] - sensors[i];
        Arrays.sort(diffs);
        //Arrays.sort(diffs, Collections.reverseOrder());


        int answer = 0;
        //N-1개 중에서 K - 1개 빼기 = N - K
        for (int i = 0; i < N - K; i++) //0 ~ N - K - 1
            answer += diffs[i];

        System.out.println(answer);
    }

}