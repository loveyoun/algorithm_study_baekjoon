import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];

        int n = 0, top = 0;
        while (N-- > 0) {
            int out = Integer.parseInt(br.readLine());

            // out(7) n(8) top(7를 가리키는 idx)
            if (out > n) { // out 까지 push
                for (int i = n + 1; i <= out; i++) {
                    array[top++] = i;
                    sb.append('+').append('\n');
                }
                n = out;
            } else if (array[top - 1] != out) { // pop
                System.out.println("NO");
                return;
            }
            top--; // pop
            sb.append('-').append('\n');
        }

        System.out.println(sb);
    }

}
