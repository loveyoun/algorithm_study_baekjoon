import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, L;
    static int[] rest_locs, locs_diff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 현재
        M = Integer.parseInt(st.nextToken()); // 미래
        L = Integer.parseInt(st.nextToken()); // 총 거리
        rest_locs = new int[N + 1];
        locs_diff = new int[N + 1];

        /* 극단 케이스 핸들링_1
        if (N == 0) {
            // 1 2 3 4 ~ L
            //최댓값이 아니라, 최댓값 중 최솟값이다
            System.out.println(L - M + 1);
            return;
        }
        */

        if(N>0)st = new StringTokenizer(br.readLine());
        rest_locs[0] = L;
        for (int i = 1; i <= N; i++)
            rest_locs[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(rest_locs);

        /*locs_diff[0] = rest_locs[0] - 1;*/
        locs_diff[0] = rest_locs[0];
        for (int i = 1; i <= N; i++)
            locs_diff[i] = rest_locs[i] - rest_locs[i - 1];
        Arrays.sort(locs_diff);
        

        int result = binarySearch(1, locs_diff[N]); //
        /*while (last_loc + result == L) result--;*/
        System.out.println(result);
    }

    static int binarySearch(int low, int high) {
        while (low < high) {
            int mid = (low + high) / 2;

            int count = 0;
            for (int i = N; i >= 0; i--) {
                int tmp = locs_diff[i] / mid;
                if (locs_diff[i] % mid == 0) tmp--;
                count += tmp;
                if (tmp == 0) break;
            }

            // ooo? xxxx lower_bound 최소값
            // xxx? oooo upper_bound 최대값
            // 코드 자체가 최대가 가능하니, 줄여주어야함
            if (count <= M) high = mid; // mid 를 줄여야함
            else low = mid + 1; // count > M, mid 키워야 함
        }

        return low;
    }

}