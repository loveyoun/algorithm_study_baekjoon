import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        /** 10816_Algorithm_숫자 카드 2: 이분 탐색, 매개변수 탐색
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            int key = Integer.parseInt(st.nextToken());

            sb.append(upperBound(key) - lowerBound(key)).append(' ');
        }

        System.out.println(sb);
    }

    private static int lowerBound(int key) {
        int start = 0;
        /** 틀렸습니다.
         포함하는 거니까(포함 안 해도), 없는 거면 0이 되어야 하니까. 
         arr.length - 1
         **/
        int end = arr.length;

        // lo가 hi랑 같아질 때 까지 반복
        while (start < end) {
            int mid = (start + end) / 2;

            /*
            1 2 2(start, end) 4 4 4 6 7 7 9
            if (key > arr[mid]) start = mid;
            else end = mid - 1;
            */
            if (key <= arr[mid]) end = mid;
            else start = mid + 1;
        }

        return start;
    }

    private static int upperBound(int key) {
        int start = 0;
        int end = arr.length;

        while (start < end) {
            int mid = (start + end) / 2;

            /* 시간 초과
            1 2 2 4 4(start) 4(end) 6 7 7 9
            if (key >= arr[mid]) start = mid;
            // 중복원소의 경우 else 에서 처리된다.
            else end = mid - 1;
            */

            if (key < arr[mid]) end = mid;
            else start = mid + 1;
        }

        return start;
    }

}