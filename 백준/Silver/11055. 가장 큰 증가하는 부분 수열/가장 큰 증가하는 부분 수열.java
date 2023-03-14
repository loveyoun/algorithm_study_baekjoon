import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        /** 11055_Algorithm flow: **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        int[] sums = new int[N];
        String[] tmp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(tmp[i]);

        int max=0;
        for (int n = 0; n < N; n++) {
            //sums[n] = nums[n];
            for (int i = 0; i < n; i++) {
                /**n==i일 때 조심하기**/
                if (nums[i] < nums[n]) sums[n] = Math.max(sums[n], sums[i]);
                //sums[n] = sums[i]; 가장 큰 값이 멀리있을 수도 있다.
                /*본인 보다 작은 게 없을 때, 본인 값 넣어야 함.
                 * flag 로 해도 되고, 비교연산을 다르게 해도 되고
                 * */
            }
            /**변수 업데이트할 때 주의하기**/
            max = Math.max(max, sums[n]+=nums[n]);
            //max = Math.max(max, sums[n]);
        }

        System.out.println(max);
    }

}