import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int n2 = 2 * N;

        boolean[] occupied = new boolean[N];
        int[] durability = new int[n2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n2; i++) durability[i] = Integer.parseInt(st.nextToken());


        int zero_cnt = 0; // 내구도 0 개수
        int phase = 0; // 단계
        int num = 0;   // (i + num) % n2 : i가 옮겨진 인덱스 -> (i - num + n2) % n2; 나머지 성질;
        while (true) {
            // 벨트 (로봇) 회전
            num = (++phase) % n2;

            // 로봇 옮기기
            if (occupied[N - 2]) occupied[N - 2] = false; // 로봇 내리기
            int origin = N - 1 - num + n2;
            for (int i = N - 3; i >= 0; i--, origin--) {
                if (!occupied[i]) continue;
                occupied[i] = false;

                if (durability[origin % n2] > 0 && !occupied[i + 2]) {
                    if (--durability[origin % n2] == 0) zero_cnt++;
                    if (i != N - 3) occupied[i + 2] = true; // 로봇 내리기
                } else occupied[i + 1] = true;
            }

            // 로봇 올리기
            if (durability[(n2 - num) % n2] > 0) {
                if (--durability[(n2 - num) % n2] == 0) zero_cnt++;
                occupied[0] = true;
            }

            if (zero_cnt >= K) break;
        }


        System.out.println(phase);
    }

}