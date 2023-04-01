import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        /** 10815_Algorithm_flow_숫자 카드: 이분 탐색
         * 최적화: customized_sort()
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

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
        //int[] arr = new int[M];
        Cards[] arr = new Cards[M];
        int[] answer = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            arr[i] = new Cards(Integer.parseInt(st.nextToken()), i);
        }
        Arrays.sort(arr, new Comparator<Cards>() {
            @Override
            public int compare(Cards o1, Cards o2) {
                return o1.value - o2.value;
            }
        });


        /** 변수 재사용 **/
        int start = 0;
        for (int i = 0; i < M; i++) {
            int end = N - 1;
            boolean flag = false;

            while (start <= end) {
                int mid_idx = (start + end) / 2;
                int MID = sangen[mid_idx];

                if (MID < arr[i].value) {
                    start = mid_idx + 1;
                } else if (MID > arr[i].value) {
                    /*
                     * IndexOutOfBoundsException 일어날일 없나?
                     * !!!없다!!!
                     * arr ... start(mid) end -> arr ... end start 되면 while 문 탈출해서
                     */
                    end = mid_idx - 1;
                } else { // 찾았을 때
                    start = mid_idx;
                    flag = true;
                    break;
                }
            }

            if (flag) answer[arr[i].index] = 1;
            else answer[arr[i].index] = 0;
        }

        for(int i=0;i<M;i++) sb.append(answer[i] + " ");
        System.out.println(sb);
    }

    static class Cards {
        int value;
        int index;

        Cards(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

}