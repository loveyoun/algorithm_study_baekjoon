import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Arrays;


public class Main {

    static int[][] apt;
    static boolean[][] visited;
    static int[] complex;

    static int count;
    static int N;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        /** 2667_Algorithm flow: DFS, priority queue
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine()); //테스트 케이스 수
        apt = new int[N][N];
        visited = new boolean[N][N];
        complex = new int[N * N + 1];

        for (int i = 0; i < N; i++) {
            String stmp = br.readLine();
            for (int j = 0; j < N; j++) {
                apt[i][j] = stmp.charAt(j) - '0';
            }
        }

        count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (apt[i][j] == 1) {
                    if (!visited[i][j]) {
                        count++;
                        complex[count]++;
                        DFS(i, j, count);
                    }
                }
            }
        }

        /** complex 갑 자체를 priority queue 로 구현해도 되겠다.**/
        //Collections.sort(apt);
        /** 이차원 배열은, ClassCast 런타임 에러!!! **/
        //Arrays.sort(apt);
        Arrays.sort(complex);

        sb.append(count + "\n");
        for (int i = 1; i <= N*N; i++) {
            if(complex[i]==0) continue;
            else sb.append(complex[i] + "\n");
        }

        System.out.println(sb);
    }

    static void DFS(int x, int y, int num) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int newx = x + dx[i];
            int newy = y + dy[i];
            if (newx < N && newx >= 0 && newy < N && newy >= 0) {
                if (!visited[newx][newy] && apt[newx][newy] == 1) {
                    complex[num]++;
                    DFS(newx, newy, num);
                }
            }
        }
    }

}