import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] fake_LIS = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        // 초기화
        fake_LIS[0] = arr[0];
        int lengthOfLIS = 1;

        for (int i = 1; i < N; i++) {
            int new_element = arr[i];

            // fake_LIS 에 마지막 값보다 클 경우, 최장 길이 늘어나고 값 추가
            if (fake_LIS[lengthOfLIS - 1] < new_element) {
                lengthOfLIS++;
                fake_LIS[lengthOfLIS - 1] = new_element;
            } else {
                /*
                10 15 20 30 1 2
                10 15 20 30(원래 답) -> 1 15 20 30 -> 1 2 15 30(변형 fake 답)
                
                10 20 30 1 2 3
                10 20 30 -> 1 20 30 -> 1 2 30 -> 1 2 (30 -> 3)
                */

                /** 삽입할 곳 이분 탐색 **/
                int s = 0;
                int e = lengthOfLIS;

                while (s < e) {
                    // mid = (s + e) / 2;
                    int mid = (s + e) >>> 1;

                    // fake_LIS[mid] >= new_element : lower bound
                    if (fake_LIS[mid] < new_element) s = mid + 1;
                    else e = mid;
                }

                /*  
                 new_element <= fake_LIS[s]
                 fake_LIS fake_LIS[s]  fake_LIS -> fake_LIS  new element  fake_LIS
                 */
                // 치환
                fake_LIS[s] = new_element;
            }
        }

        System.out.println(lengthOfLIS);
    }

}