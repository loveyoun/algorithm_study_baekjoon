import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, L, result;
    static int[][] high;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        high = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) high[i][j] = Integer.parseInt(st.nextToken());
        }


        result = 0;
        for (int k = 0; k < N; k++) {
            if (checkRow(k)) result++;
            if (checkCol(k)) result++;
        }

        System.out.println(result);
    }

    static boolean checkRow(int r) { // 행 →
        int len = 1;
        boolean isPossible = true;
        boolean down = false;

        for (int j = 0; j < N - 1; j++) {
            int b = high[r][j];
            int a = high[r][j + 1];
            if (Math.abs(b - a) > 1) {
                isPossible = false;
                break;
            }

            if (b == a) {
                len++;

                if (down && len == L) {
                    down = false;
                    len = 0;
                }
            } else if (b > a) {
                if (down && len < L) {
                    isPossible = false;
                    break;
                }

                down = true;
                len = 1;

                // L == len == 1 일 때
                if (len == L) {
                    down = false;
                    len = 0;
                }
            } else {
                if (down || len < L) {
                    isPossible = false;
                    break;
                }

                len = 1;
            }
        }
        if (down && len < L) isPossible = false; // 3 2 2.

        return isPossible;
    }

    static boolean checkCol(int c) { // 열 ↓
        int len = 1;
        boolean isPossible = true;
        boolean down = false;

        for (int i = 0; i < N - 1; i++) {
            int b = high[i][c];
            int a = high[i + 1][c];
            if (Math.abs(b - a) > 1) {
                isPossible = false;
                break;
            }

            if (b == a) {
                len++;

                if (down && len == L) {
                    down = false;
                    len = 0;
                }
            } else if (b > a) {
                if (down && len < L) {
                    isPossible = false;
                    break;
                }

                down = true;
                len = 1;

                if (len == L) {
                    down = false;
                    len = 0;
                }
            } else {
                if (down || len < L) {
                    isPossible = false;
                    break;
                }

                len = 1;
            }
        }

        if (down && len < L) isPossible = false;
        return isPossible;
    }

}