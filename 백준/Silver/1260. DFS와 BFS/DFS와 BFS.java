import java.io.*;
import java.util.*;

public class Main{
    static ArrayList<Integer>[] A;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int start= Integer.parseInt(st.nextToken());
        A = new ArrayList[N+1];
        for(int i=1;i<N+1;i++) A[i] = new ArrayList<Integer>();
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            A[S].add(E); A[E].add(S);
        }
        //문제 조건 : 작은 것부터 탐색
        for(int i=1;i<N+1;i++) Collections.sort(A[i]);
        
        visited = new boolean[N+1];
        DFS(start);
        System.out.println();
        
        visited = new boolean[N+1];
        BFS(start);  
    }
    
    
    static void DFS(int v){
        System.out.print(v + " ");
        
        visited[v] = true;
        for(int i : A[v]){
            if(!visited[i]) DFS(i);
        }
    }
    
    static void BFS(int v){
        Queue<Integer> q = new LinkedList<Integer>();
        //큐로 하는 거니까 넣어주고 빼기
        //처음에는 넣고 빼는게 같겠지만, 두번째부터는 다름.
        q.add(v);
        visited[v] = true;
        
        while(!q.isEmpty()){
            int now = q.poll();
            System.out.print(now + " ");
            
            for(int i : A[now]){
                if(!visited[i]){
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }
}