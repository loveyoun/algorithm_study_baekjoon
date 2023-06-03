import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, L;
    static int[] rest_locs, locs_diffs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 현재
        M = Integer.parseInt(st.nextToken()); // 미래
        L = Integer.parseInt(st.nextToken()); // 총 거리
        rest_locs = new int[N + 1];
        locs_diffs = new int[N + 1];

        /* 
        극단 케이스 핸들링_1 : 최댓값이 아니라, 가능한 최댓값 중 최솟값이다
        if (N == 0) {
            // 1 2 3 4 ~ L
            System.out.println(L - M + 1);
            return;
        }
        */

        if (N > 0) st = new StringTokenizer(br.readLine());
        rest_locs[0] = L;
        for (int i = 1; i <= N; i++)
            rest_locs[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(rest_locs);

        /*
         locs_diff[0] = rest_locs[0] - 1;
         locs_diff[0] = 7 -> mid 가 7일 때 7 / 7 = 1 - 1 로 안 되고, 6일 때 1, locs_diff[0] 으로 가능하다. 
         
         마찬가지로, locs_diff[N] = L - rest_locs[N-1] - 1 이 아닌 이유이다.
         L = 10, rest_locs[N-1] = 5 일 때, 5 / 5 = 1 - 1 로 안된다.
         L 에 놓이면 안 되므로, 거리가 4인 9 지점에 놓여야 한다.
         */
        locs_diffs[0] = rest_locs[0];
        for (int i = 1; i <= N; i++)
            locs_diffs[i] = rest_locs[i] - rest_locs[i - 1];
        Arrays.sort(locs_diffs);


        /* 
         low = 0이면 에러
         거리가 1과 locs_diffs[N] 일때를 포함한다.
         (하지만 locs_diffs[N] == L 일 때는 L-1 까지 고려되게 이분 탐색을 구성하여야 한다.)
         */
        int result = binarySearch(1, locs_diffs[N]);
        /*
         극단 케이스 핸들링_2 : 여기서 해주면 안 된다. 방식도 틀렸다.
         while (last_loc + result == L) result--;
         */
        System.out.println(result);
    }

    static int binarySearch(int low, int high) {
        while (low < high) {
            int mid = (low + high) / 2;

            int count = 0;
            for (int i = N; i >= 0; i--) {
                int tmp = locs_diffs[i] / mid;
                if (locs_diffs[i] % mid == 0) tmp--;
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