import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static StringBuilder sb;
    public static int[] tmp;
    public static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]); // 1 ~ N
        M = Integer.parseInt(s[1]); // M 열
        tmp = new int[M + 1];

        recur(M);

        System.out.print(sb);
    }


    static void recur(int m) { // m : 남은 자리
        /** Base case : M 열 다 채웠을 때 **/
        if (m == 0) {
            for (int i = M; i >= 1; i--) sb.append(tmp[i] + " ");
            sb.append("\n");

            return;
        }

        for (int i = 1; i <= N; i++) {
            if (m != M && i < tmp[m + 1]) continue;
            
            tmp[m] = i;

            recur(m - 1);
        }
    }

}