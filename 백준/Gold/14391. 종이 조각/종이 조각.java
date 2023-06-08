import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int[][] paper;
    public static int[][] sums;
    public static int[][] xs;
    public static int[][] ys;
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
        xs = new int[N][M];
        ys = new int[N][M];
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
        if (!visited[n][m]) {
            int v = 0, h = 0;
            for (int i = 0; i < N - n; i++) { // ex> N = 4, n = 2 -> 2, 3
                for (int j = 0; j < M - m; j++) {
                    // 1. 아래, 오른쪽 탐색
                    if (i != 0) { // 세로탐색
                        for (v = 0; v <= i; v++) { // [n][m] ~ [n+i][m] 까지 더하기
                            if (!visited[n + v][m]) {
                                sums[n][m] = sums[n][m] * 10 + paper[n + v][m];
                                count++;
                                visited[n + v][m] = true;
                                xs[n][m] = v;
                            } else break;
                        }
                    } else { // i == 0 일 때. 단독 or 가로탐색
                        for (h = 0; h <= j; h++) { // [n][m] ~ [n][m+j] 까지 더하기
                            if (!visited[n][m + h]) {
                                sums[n][m] = sums[n][m] * 10 + paper[n][m + h];
                                count++;
                                visited[n][m + h] = true;
                                ys[n][m] = h;
                            } else break;
                        }
                    }

                    // 2. 전체 종이조각 돌았을 때 max 값 업데이트
                    if (count == N * M) iterCheck();

                    // 3. 그 다음 인덱스 (오른쪽으로 한 칸씩) 재귀
                    if ((m + 1) >= M) {
                        if ((n + 1) < N) right_down(n + 1, 0);
                    } else right_down(n, m + 1);

                    // 4. 이 단계에서 해준거 고대로 리와인드
                    sums[n][m] = 0;
                    if (i != 0) { // 세로탐색
                        for (int l = 0; l < v; l++) {
                            count--;
                            visited[n + l][m] = false;
                        }
                    } else { // 가로탐색
                        for (int l = 0; l < h; l++) {
                            count--;
                            visited[n][m + l] = false;
                        }
                    }
                } // 안쪽 for
            } // 바깥 for
        } else { // if (visited)
            // 행 다 탐색 후, 다음 열로 돌아갈 때 : ex> (1, M - 1) ->  (2, 0)
            if ((m + 1) >= M) {
                if ((n + 1) < N) right_down(n + 1, 0);
            } else right_down(n, m + 1);
        }
    }

    public static void iterCheck() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) sum += sums[i][j];

        result = Math.max(result, sum);

        sum = 0;
    }

}