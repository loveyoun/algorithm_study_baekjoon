import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, max, size;
    static int[][] paper;

    /*
     동(0), 서(1), 남(2), 북(3)
     동동(4), 남남(5)
     대_1(6), 대_2(7), 대_3(8), 대_4(9)
     */
    static int[] dx = {0, 0, 1, -1, 0, 2, -1, -1, 1, 1};
    static int[] dy = {1, -1, 0, 0, 2, 0, -1, 1, -1, 1};
    static int[][] tetris = {
            // ㅣ
            {1, 0, 4},
            {3, 2, 5},
            // ㅁ
            {0, 2, 9},
            // ㄴ
            {3, 2, 9},
            {3, 2, 8},
            {1, 0, 7},
            {1, 0, 6},
            {6, 3, 2},
            {7, 3, 2},
            {8, 1, 0},
            {9, 1, 0},
            // ㄹ
            {1, 2, 9},
            {0, 2, 8},
            {3, 1, 8},
            {3, 0, 9},
            // ㅗ
            {3, 0, 1},
            {3, 0, 2},
            {0, 1, 2},
            {1, 2, 3}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        paper = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                paper[i][j] = Integer.parseInt(st.nextToken());
        }


        max = 0;
        size = tetris.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                place4Blocks(i, j);
        }

        System.out.println(max);
    }

    static void place4Blocks(int x, int y) {
        for (int k = 0; k < size; k++) {
            int sum = paper[x][y];
            boolean impossible = false;

            for (int l = 0; l < 3; l++) {
                int new_x = x + dx[tetris[k][l]];
                int new_y = y + dy[tetris[k][l]];

                if (new_x >= 0 && new_x < N && new_y >= 0 && new_y < M)
                    sum += paper[new_x][new_y];
                else {
                    impossible = true;
                    break;
                }
            }

            if (!impossible) max = Math.max(max, sum);
        }
    }

}