import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb;
    static int n, m;
    static int[] senior, praise;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 직원 수
        m = Integer.parseInt(st.nextToken()); // 칭찬 횟수
        senior = new int[n + 1];
        praise = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        st.nextToken(); // 1번은 사장
        for (int i = 2; i <= n; i++)
            senior[i] = Integer.parseInt(st.nextToken()); // i 의 직속선배

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int jnr = Integer.parseInt(st.nextToken());
            int pr = Integer.parseInt(st.nextToken());

            praise[jnr] += pr; /** 한 사람이 여러번 칭찬받을 수 있다 **/
        }


        for (int jnr = 0; jnr <= n; jnr++)
            praise[jnr] += praise[senior[jnr]];
        
        sb = new StringBuilder();
        for (int i = 1; i <= n; i++) sb.append(praise[i] + " ");
        System.out.println(sb);
    }

}