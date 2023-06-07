import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static int[][] nums;
    public static int[][] sums;
    public static int[][] xs;
    public static int[][] ys;
    public static boolean[][] visited;
    public static int N, M;
    public static int count, sum, max;

    public static void main(String[] args) throws IOException {
        /**14391_Algorithm flow: **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] sarr = br.readLine().split(" ");
        N = Integer.parseInt(sarr[0]); //행
        M = Integer.parseInt(sarr[1]); //열

        nums = new int[N][M];
        sums = new int[N][M];
        xs = new int[N][M];
        ys = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                nums[i][j] = s.charAt(j) - '0';
            }
        }

        count = 0;
        sum = 0;
        max = 0;
        rightdown(0, 0);

        System.out.println(max);
    }


    public static void rightdown(int n, int m) {
        if (!visited[n][m]) { //visited 안 한 점이면
            for (int i = 0; i < N - n; i++) {
                for (int j = 0; j < M - m; j++) {
                    if (i != 0) { //세로탐색
                        for (int l = 0; l <= i; l++) { //[n][m] ~ [n+i][m]까지 더하기
                            /**!!!visited[n+l][m]도 false여야 함!!!**/
                            if(!visited[n+l][m]){
                                sums[n][m] = sums[n][m] * 10 + nums[n + l][m];
                                count++;
                                visited[n + l][m] = true;
                                xs[n][m]=l;
                            }else break;
                        }
                    } else { //i==0일 때. 가로탐색
                        for (int l = 0; l <= j; l++) { //[n][m] ~ [n][m+j]까지 더하기
                            if(!visited[n][m+l]){
                                sums[n][m] = sums[n][m] * 10 + nums[n][m + l];
                                count++;
                                visited[n][m + l] = true;
                                ys[n][m]=l;
                            } else break;
                        }
                    }

                    if (count == N * M) countcheck(); //count==16 점검
                    //countcheck()했으면, count==16이고, 나머지 다 visited니까 재귀함수 시작 안할 것.

                    /**!!!!!근데, 이건 처음 visited 상관없이 실행되어야하는데!!!!!**/
                    if ((m + 1) >= M) {
                        if ((n + 1) < N) rightdown(n + 1, 0);
                    } else rightdown(n, m + 1);

                    /**재귀 끝나면 리셋해줄 코드**/
                    sums[n][m] = 0;
                    if (i != 0) { //세로탐색
                        for (int l = 0; l <= xs[n][m]; l++) {
                            count--;
                            visited[n + l][m] = false;
                        }
                    } else { //i==0일 때. 가로탐색
                        for (int l = 0; l <= ys[n][m]; l++) {
                            count--;
                            visited[n][m + l] = false;
                        }
                    }
                }//안쪽 for
            }//바깥 for
        }//if문
        else { //visited 한 점이면, 다음 인덱스로...
            /**if~else 중첩일 때 조심...**/
            if ((m + 1) >= M) {//열 끝났는데
                //행안 끝났으면, 다음 행 0열로 가야함.
                if ((n + 1) < N) rightdown(n + 1, 0);
            } else rightdown(n, m + 1);
        }
    }

    //(N * M)개 index 다 돌았는지
    public static void countcheck() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sum += sums[i][j];
                //System.out.print(sums[i][j] + " ");
            }
            //System.out.println("");
        }
        max = Math.max(max, sum);
        //System.out.println(max+"\n");
        sum = 0;
    }

}