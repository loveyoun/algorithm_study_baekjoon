import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int[][] paper;
    public static int[][] sums;
    public static int[][] xs, ys;
    public static boolean[][] visited;
    public static int N, M; // 행, 열
    public static int count, sum, result;

    public static void main(String[] args) throws IOException {
        /** 세로 1, 2, .. 가로 1, 2, .. '마다' 재귀 (바로 다음 인덱스 세로 1, 2.. 가로 1, 2..(...)) **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] sarr = br.readLine().split(" ");
        N = Integer.parseInt(sarr[0]);
        M = Integer.parseInt(sarr[1]);
        paper = new int[N][M];

        sums = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) paper[i][j] = s.charAt(j) - '0';
        }

        count = 0;
        sum = 0;
        result = 0;
        right_down(0, 0); // 오른쪽으로 자르고, 아래쪽으로 자르고

        System.out.println(result);
    }


    public static void right_down(int n, int m) {
        // BASE CASE
        if (count == N * M) {
            iterCheck();
            return;
        }

        if (n >= 0 && n < N && m >= 0 && m < M) {
            if (!visited[n][m]) {
                int v, h; // 각 케이스에서 어디까지 탐색했나 확인 변수

                for (int i = 1; i < N - n; i++) { // ex> N = 4, n = 2 -> 2, 3
                    // 세로탐색
                    for (v = 0; v <= i; v++) { // [n][m] ~ [n+i][m] 까지 더하기
                        if (!visited[n + v][m]) {
                            sums[n][m] = sums[n][m] * 10 + paper[n + v][m];
                            count++;
                            visited[n + v][m] = true;
                        } else break;
                    }

                    // 2. 재귀
                    if ((m + 1) >= M) right_down(n + 1, 0);
                    else right_down(n, m + 1);

                    // 3. 이 단계에서 해준거 고대로 리와인드
                    sums[n][m] = 0;
                    for (int l = 0; l < v; l++) {
                        count--;
                        visited[n + l][m] = false;
                    }
                }

                // 단독 or 가로탐색
                for (int j = 0; j < M - m; j++) {
                    for (h = 0; h <= j; h++) { // [n][m] ~ [n][m+j] 까지 더하기
                        if (!visited[n][m + h]) {
                            sums[n][m] = sums[n][m] * 10 + paper[n][m + h];
                            count++;
                            visited[n][m + h] = true;
                        } else break;
                    }

                    // 2. 재귀
                    if ((m + 1) >= M) right_down(n + 1, 0);
                    else right_down(n, m + 1);

                    // 3. 리와인드
                    sums[n][m] = 0;
                    for (int l = 0; l < h; l++) {
                        count--;
                        visited[n][m + l] = false;
                    }
                }

            } else { // if (visited)
                if ((m + 1) >= M) right_down(n + 1, 0);
                else right_down(n, m + 1);
            }
        }

    }

    public static void iterCheck() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                sum += sums[i][j];

        result = Math.max(result, sum);

        sum = 0;
    }

}