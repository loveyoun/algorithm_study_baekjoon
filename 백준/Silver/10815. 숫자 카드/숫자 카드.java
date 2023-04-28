import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        /** 10815_Algorithm_flow_숫자 카드: 이분 탐색(lower bound)
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] sangen = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) sangen[i] = Integer.parseInt(st.nextToken());
        /*
         * index: 1~N으로 하면, index==0 때문에 이상해짐
         * [0] = Integer.MIN_VALUE 로 놓으면 되긴 하겠다.
         */
        Arrays.sort(sangen);

        int M = Integer.parseInt(br.readLine());
        int[] cards = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++)
            cards[i] = Integer.parseInt(st.nextToken());


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cards.length; i++) {
            int start = 0, end = N - 1;

            /** LOWER BOUND **/
            while (start < end) {
                int mid_idx = (start + end) / 2;
                int MID = sangen[mid_idx];

                if (cards[i] <= MID) end = mid_idx;
                else start = mid_idx + 1;
            }
            if (cards[i] == sangen[start]) sb.append("1 ");
            else sb.append("0 ");
        }

        System.out.println(sb);
    }

}