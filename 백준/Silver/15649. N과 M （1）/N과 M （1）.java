import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    //public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb;
    public static boolean[] flag;
    public static int[] tmp;
    public static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]); // 1 ~ N
        M = Integer.parseInt(s[1]); // M 열
        flag = new boolean[N + 1];
        tmp = new int[M];


        /** 재 귀 함 수 (한 칸을 채우는데, 해당 iteration 에서 1 ~ N 중 썼는지 안 썼는지 계속 확인) 호출 **/
        recur(M);
        System.out.println(sb);
    }


    static void recur(int m) { // m : 남은 자리
        /** Base case : M 열 다 채웠을 때**/
        if (m == 0) {
            for (int i = 0; i < M; i++) sb.append(tmp[i] + " "); //bw.write("\n"); bw.flush();
            sb.append("\n");

            return;
        }

        for (int i = 1; i <= N; i++) {
            if (flag[i]) continue;

            flag[i] = true;  // 방명록: 퍼가요~
            tmp[M - m] = i;  // tmp[0] 부터

            recur(m - 1);

            flag[i] = false; // 안 읽음으로 처리 (백트래킹)
        }
    }

}