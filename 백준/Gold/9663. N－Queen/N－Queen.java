import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, count, result;
    static int[][] chess;

    public static void main(String[] args) throws IOException {
        /** 9663_Algorithm_N_QUEEN :
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        chess = new int[N][N];

        count = 0;
        result = 0;
        Play(0);

        System.out.println(result);
    }

    static void Play(int row) {
        if (row == N) {
            if (count == N) result++;
            return;
        }

        for (int c = 0; c < N; c++) {
            if (chess[row][c] == 0) {
                // row == 0 인 경우 때문에 오류.
                chess[row][c] = row + 1;
                count++;

                for (int r = row + 1; r < N; r++) {
                    int diff = r - row;
                    if (chess[r][c] == 0) chess[r][c] = row + 1;
                    if (c - diff >= 0)
                        if (chess[r][c - diff] == 0) chess[r][c - diff] = row + 1;
                    if (c + diff < N)
                        if (chess[r][c + diff] == 0) chess[r][c + diff] = row + 1;
                }

                Play(row + 1);

                /*
                chess를 boolean으로 하면, 기존에 true라고 했던 것들 false로 바꿔서 오류.
                내가 바꿨다는 걸 알아야 함.
                다른 방법 1) int 배열로
                다른 방법 2)
                 */
                for (int r = row + 1; r < N; r++) {
                    int diff = r - row;
                    if (chess[r][c] == row + 1) chess[r][c] = 0;
                    if (c - diff >= 0)
                        if (chess[r][c - diff] == row + 1) chess[r][c - diff] = 0;
                    if (c + diff < N)
                        if (chess[r][c + diff] == row + 1) chess[r][c + diff] = 0;
                }
                
                chess[row][c] = 0;
                count--;
            }
        }
    }

}