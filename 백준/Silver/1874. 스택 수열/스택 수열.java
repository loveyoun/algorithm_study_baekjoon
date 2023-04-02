import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        /**1874_Algorithm_flow_스택 수열: 스택 구현
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine()); //sc.nextInt();
        Stack<Integer> stack = new Stack<>();
        int num = 1;
        boolean result = true;

        /* 기작
         * 스택에는 num = 1부터 push -> 증가
         * arr[i] 4 == num 4까지 push -> num++(중복되면 안되니까) -> pop
         * arr[i] 6 > num 5이면 push (...)
         * arr[i] 3 < num 5이면 pop() 5 == arr[i] 3이면
         * 1 2 3 4(x) 5 6(x)
         * NO일 때 : 위에서 pop()이 더 크거나 작으면, 나오면 수열 값이 아니므로
         * 작은 수가 나올 수는 없다. 이미 그럼 arr[i]가 나갔다는 뜻이니까.
         * 결론: 높은 수 -> 낮은 수로 갈 때, 그 중간 값이 있어서는 안 된다.
         */
        for (int i = 0; i < arr.length; i++) {
            int object = arr[i];
            if (object >= num) { //채워주기만 하면 됨.
                while (object >= num) {
                    stack.push(num++);
                    sb.append("+\n");
                }
                stack.pop();
                sb.append("-\n");
            } else { //뺄 수 있는지 크기 비교 해주어야 함.
                int top = stack.pop();

                /* top > object 되긴 함 */
                if (top != object) {
                    System.out.println("NO");
                    result = false;
                    break;
                } else sb.append("-\n");
            }
        }

        if (result) System.out.println(sb.toString());
    }

}