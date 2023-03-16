import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{
    
    static ArrayList<Integer>[] arrLst;
    static boolean visited[];
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        String[] tmp = br.readLine().split(" ");
        int N = Integer.parseInt(tmp[0]);
        int E = Integer.parseInt(tmp[1]);
        
        arrLst = new ArrayList[N+1]; //이거 없으면 NullPointErorr
        visited = new boolean[N+1];
        for(int i=1;i<N+1;i++) arrLst[i] = new ArrayList<Integer>();
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());            
            arrLst[s].add(e); arrLst[e].add(s);
        }
        
        int count = 0;
        for(int i=1;i<N+1;i++){    //탐색 쉽게 배열 인덱스 맞춰주기
            if(!visited[i]){
                count++; //연결요소들 다 DFS되니까 visited == true;
                DFS(i);    
            }
        }
        
        System.out.println(count);
    }
    
    public static void DFS(int s){
        if(visited[s]) return; //지나간 곳 가지 않는다.
        visited[s] = true; //지나갔다고 표시한다.
        for(int i : arrLst[s]){ //for(int i=0;i<A[s].length;i++)
            if(!visited[i]) DFS(i);
        }   
    }
    
}