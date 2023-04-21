import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 16000000 + 1;
    static int N;
    static int[][] costs;
    static int[][] DP;

    public static void main(String[] args) throws IOException {
        /** 2098_Algorithm_flow_외판원 순회: DP, 비트마스킹, TSP
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        costs = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++)
                costs[i][j] = Integer.parseInt(st.nextToken());
        }

        DP = new int[N][(1 << N) - 1];
        /*for (int i = 0; i < N; i++) {
            for (int j = 0; j < (1 << N) - 1; j++)
                DP[i][j] = INF;
        } //Arrays.fill(DP[i], INF);*/

        System.out.println(TSP(0, 1));
    }

    static int TSP(int city, int visited) {
        // 1) 다 방문했을 때 (BASE CASE)
        if (visited == (1 << N) - 1)
            return costs[city][0] == 0 ? INF : costs[city][0];
        // 2) 이미 방문한 노드
        //if (DP[city][visited] != INF) 
        if (DP[city][visited] != 0)
            return DP[city][visited];
        
        // 3) 방문 가능 도시 (not visited, cost 있으면)
        DP[city][visited] = INF;
        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) == 0 && costs[city][i] != 0)
                DP[city][visited] = Math.min(DP[city][visited], TSP(i, (visited | (1 << i))) + costs[city][i]);
        }

        return DP[city][visited];
    }

}