import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        /**12015_Algorithm_flow: 그리디(이진 탐색) + 구현
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] LIS = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        /** 0부터 넣는 다면, 코드 통일성을 위한 것이다 **/
        LIS[0] = arr[0];
        int lengthOfLIS = 1;

        for (int i = 1; i < N; i++) {

            int new_element = arr[i];

            // LIS 에 마지막 값보다 클 경우, 추가
            if (LIS[lengthOfLIS - 1] < new_element) {
                lengthOfLIS++;
                LIS[lengthOfLIS - 1] = new_element;
            } else {
                /** 삽입할 곳 이분탐색 **/
                int start = 0;
                int end = lengthOfLIS;

                while (start < end) {
                    int mid = (start + end) >>> 1;

                    // LIS[start] LIS[mid] new_element(포함) LIS[end]
                    if (LIS[mid] < new_element) start = mid + 1;
                        // LIS[start] new_element==LIS[mid](포함) LIS[end]
                    else end = mid;
                }
                //start mid end -> start==mid end -> start == end
                /** 치환 **/
                LIS[start] = new_element;
            }
        }

        System.out.println(lengthOfLIS);
    }

}