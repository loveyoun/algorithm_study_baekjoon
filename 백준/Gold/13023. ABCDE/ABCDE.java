import java.io.*;
import java.util.*;

public class Main{
    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static boolean arrive;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        A = new ArrayList[N];
        visited = new boolean[N];
        for(int i=0;i<N;i++) A[i] = new ArrayList<Integer>();
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            A[s].add(e); A[e].add(s);
        }//초기화 끝
        
        //DFS(0);
        //연결요소가 하나만 아닐 수 있으니까
        //대신 visited[] 관리 잘 해주기
        //몇 명 친구인지를 어떻게 알지? 인자로 받아서 바꿔주자
        for(int i=0;i<N;i++){
            DFS(i, 1);
            //하나라도 있으면 되는 거니까
            if(arrive) break;
        }
        
        if(arrive) System.out.println("1");
        else System.out.println("0");
    }
    
   
    public static void DFS(int v, int dep){
        //5명 친구인거 확인되었을 때
        if(dep == 5){
            arrive = true;
            return;
        }
        visited[v] = true;
        for(int i : A[v]){
            if(!visited[i]) DFS(i, dep + 1);
        }
      /*1-5,2
        2-3
        3-4
        4-5,6*/
        //1-2-3-4-5 arrive = true;
        //1-2-3-4-6 굳이 돌 필요 없음.
        visited[v] = false;
    }
}