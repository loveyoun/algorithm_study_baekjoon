import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static long[] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 입국 심사대
        m = Integer.parseInt(st.nextToken());   // 상근이 칭구들
        time = new long[n];
        long max = 0;
        for (int i = 0; i < n; i++) {
            time[i] = Long.parseLong(br.readLine());
            max = Math.max(max, time[i]);
        }
        //Arrays.sort(immigration_time);


        long share = m % n == 0 ? m / n : m / n + 1;   /* 2 3 \n 9 \n 10 */
        if (share == 0) share = 1;   // M = 2, N = 4인 경우


        System.out.println(binarySearch(0, max * share));
    }

    static long binarySearch(long l, long r) {
        while (l < r) {
            long mid = (l + r) / 2;
            long total = 0;
            for (int i = 0; i < n; i++)
                total += mid / time[i];

            if (total >= m) r = mid;
            else l = mid + 1;

        }

        return l;
    }

}