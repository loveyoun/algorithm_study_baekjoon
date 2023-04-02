import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        /**2018_Algorithm_flow_수들의 합 5: 투 포인터
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /*Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();*/
        int N = Integer.parseInt(br.readLine());

        int sum = 1, start_index = 1, end_index = 1;
        int count = 1;

        while (end_index != N) {
            if (sum == N) {
                count++;
                end_index++;
                sum = sum + end_index;
            } else if (sum > N) {   // 삭제
                sum -= start_index;
                start_index++;
            } else {    /** 위와 순서 조심 **/
                end_index++;        // 확장
                sum += end_index;
            }
        }

        System.out.println(count);
    }

}