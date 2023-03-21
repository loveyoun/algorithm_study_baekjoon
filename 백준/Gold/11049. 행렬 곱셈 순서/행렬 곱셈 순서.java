import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        /** 11049_Algorithm flow: DP
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Matrix[] matrices = new Matrix[N + 1];
        int[][] dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            matrices[i] = new Matrix(row, col); /* 생성자로 넣어주어야 함. 아니면 NullPointerException */
        }

        /** 창문 닦기 **/
        /* i++씩, j=i+0, 1, 2, 3..*/
        //for (int j = 1; j <= N; j++) {          //j = 5    4
        //for (int i = 1; i <= N; i++) {
        for (int l = 0; l < N; l++) {
            //for (int i = 1; i <= j; i++) {      //i = 1~5  1~4
            //for (int l = 0; i + l <= N; l++) {
            for (int i = 1; i <= N; i++) {
                int j = i + l;
                if (j <= N){
                    /** 조 심 **/
                    if (i == j) dp[i][j] = 0;
                    else dp[i][j] = Integer.MAX_VALUE;

                    /* i j 1 2 3 4 5
                     *  1  1)2)3)4)5)
                     *  2    7)8)9)10)
                     *  3      11)12)13)
                     *  4
                     *  5
                     *
                     * 1)->7)->11)->.. -> 2)->8)->...
                     * i = 1~5{
                     *  j = i+0, 1, 2, 3, 4
                     * } 값! 이 아니라 차이를
                     * */
                    for (int k = i; k < j; k++) {  //k = 1~5   1~4
                        /* Math.min 쓸 때 항상 뜻하지 않는 값(0)이 없는지 주의 */
                        int min = dp[i][k] + dp[k + 1][j] + matrices[i].row * matrices[k].col * matrices[j].col;
                        dp[i][j] = Math.min(dp[i][j], min);
                    }
                }
            }
        }

        System.out.println(dp[1][N]);
    }

    /* 반례 예시
    *  4
       1 7
       7 2
       2 5
       5 6
    * */
    static class Matrix {
        int row;
        int col;

        Matrix(int row, int col) {
            this.row = row; //행
            this.col = col; //렬
        }
    }

}