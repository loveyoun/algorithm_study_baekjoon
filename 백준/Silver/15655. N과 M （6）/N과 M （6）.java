import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static StringBuilder sb = new StringBuilder();
    public static int[] arr, tmp;
    public static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()); // m 줄

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);


        //flag = new boolean[n];
        tmp = new int[m + 1];
        recur(m, 0);
        System.out.println(sb);
    }


    static void recur(int depth, int idx) { // 해당 depth 에서 idx 부터
        // Base case
        if (depth == 0) {
            for (int i = m; i >= 1; i--) sb.append(tmp[i]).append(" ");
            sb.append("\n");

            return;
        }

        for (int i = idx; i < n; i++) {
            tmp[depth] = arr[i];

            recur(depth - 1, i + 1); /* idx + 1 이 아니야 */
        }
    }

}