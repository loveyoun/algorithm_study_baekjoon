import java.io.*;
import java.util.*;

public class Main{
    static int[][] A;
    static int N, M;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for(int j=0;j<M;j++) A[i][j] = Integer.parseInt(line.substring(j, j+1));           
        }
        
        BFS(0,0);
        
        System.out.println(A[N-1][M-1]);
    }
    
   
    static void BFS(int i, int j){
        //초기 설정
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {i,j});
        visited[i][j] = true;
        
        while(!q.isEmpty()){
            int now[] = q.poll();
            for(int k=0;k<4;k++){
                //좌표 움직이고
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];
                //좌표 유효성 검사하고
                if(x >= 0 && y >= 0 && x < N && y < M){
                    //좌표 상태 확인(갈 수 있는 곳인지, 이미 간 곳은 아닌지)
                    if(A[x][y] != 0 && !visited[x][y]){
                        visited[x][y] = true;
                        //깊이 업데이트
                        A[x][y] = A[now[0]][now[1]] + 1;
                        //큐에 넣어주기 rear add->2 4 0->poll front 
                        q.add(new int[] {x,y});
                    }
                }
            }
        }
    }
}
