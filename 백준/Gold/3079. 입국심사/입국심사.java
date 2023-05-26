import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static long[] immigration_time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 입국 심사대
        M = Integer.parseInt(st.nextToken()); // 상근이 칭구들
        immigration_time = new long[N];
        long max = 0;
        for (int i = 0; i < N; i++) {
            immigration_time[i] = Long.parseLong(br.readLine());
            max = Math.max(max, immigration_time[i]);
        }
        //Arrays.sort(immigration_time);

        long share = M / N;
        // M = 2, N = 4인 경우 한 번에 다 받을 수 있어서
        if (share == 0) share = 1;

        long result = binarySearch(0, max * share);
        System.out.println(result);
    }

    static long binarySearch(long left, long right) {
        while (left < right) {
            long mid = (left + right) / 2;
            long total = 0;
            for (int i = 0; i < N; i++)
                total += mid / immigration_time[i];

            // 심사를 다 마칠 수 없으니, 시간을 늘려야됨
            if (total < M) left = mid + 1;
            else right = mid;
        }

        return left;
    }

}