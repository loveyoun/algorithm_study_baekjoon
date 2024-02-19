import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        String[] str = br.readLine().split(" ");
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(str[i]);

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int[] NGEs = new int[N];
        for (int i = 1; i < N; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i])
                NGEs[stack.pop()] = arr[i];
            stack.push(i);
        }
        while (!stack.empty()) NGEs[stack.pop()] = -1;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++)
            sb.append(NGEs[i]).append(" ");

        System.out.println(sb);
    }

}