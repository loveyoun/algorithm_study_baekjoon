import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int n = 1;
        boolean isPos = true;
        for (int out : arr) { // arr.length
            if (out >= n) {
                while (out >= n) {
                    stack.push(n++);
                    sb.append("+\n");
                }
                stack.pop();
                sb.append("-\n");
            } else { //뺄 수 있는지 크기 비교 해주어야 함.
                int top = stack.pop();

                // top = 4 > out = 3 일 때
                if (top != out) {
                    System.out.println("NO");
                    isPos = false;
                    break;
                } else sb.append("-\n");
            }

        }

        if (isPos) System.out.println(sb);
    }

}