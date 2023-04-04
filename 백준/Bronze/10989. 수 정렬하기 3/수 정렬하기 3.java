import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] arr;

    /* 시간초과
     * static PriorityQueue<Integer>[] pqueue;
     */

    public static void main(String[] args) throws IOException {
        /** 10989_Algorithm_flow_수 정렬하기 3: 기수 정렬
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        /*pqueue = new PriorityQueue[10]; //0 ~ 9
        for (int i = 0; i < 10; i++) pqueue[i] = new PriorityQueue<>();*/

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        RadixSort();

        for (int i = 0; i < N; i++)
            sb.append(arr[i] + "\n");

        System.out.println(sb);
    }

    static void RadixSort() {
        int cipher = 1;
        int count = 0;
        int[] tmp = new int[N];

        while (count < 5) {
            int[] bucket = new int[10];
            for (int i = 0; i < N; i++) {
                //일의 자리, 십의 자리 수, ... 구하기.
                //pqueue[(arr[i] / cipher) % 10].add(arr[i]);

                bucket[(arr[i] / cipher) % 10]++;
            }

            //for (int i = 0; i < N; i++) {
            //int i = 0;
            for (int j = 1; j < 10; j++) {
                /*while (pqueue[j].size() > 0) {
                    arr[i] = pqueue[j].poll();
                    i++;
                }*/

                //index 합 배열 == 순서 크기
                bucket[j] += bucket[j - 1];
            }

            for (int i = N - 1; i >= 0; i--) {
                tmp[bucket[(arr[i] / cipher) % 10] - 1] = arr[i];
                bucket[(arr[i] / cipher) % 10]--;
            }

            for (int i = 0; i < N; i++)
                arr[i] = tmp[i];


            cipher *= 10;
            count++;
        }
    }

}