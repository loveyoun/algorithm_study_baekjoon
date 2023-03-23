import java.io.*;
import java.util.*;

public class Main{
    //ArrayList[] -> Edge[]
    static ArrayList<Edge>[] A;
    static boolean visited[];
    static int[] distance;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        A = new ArrayList[N+1];
        for(int i=1;i<N+1;i++) A[i] = new ArrayList<Edge>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            while(true){
                int E = Integer.parseInt(st.nextToken());
                if(E == -1) break;
                int V = Integer.parseInt(st.nextToken());
                A[S].add(new Edge(E,V));
            }
        }
        //index = 1 기준으로 가장 거리가 큰 값의 index구하기.
        distance = new int[N+1];
        visited = new boolean[N+1];
        BFS(1);
        int max = 1;
        for(int i=2;i<N+1;i++) max = distance[max] > distance[i] ? max : i;
        
        distance = new int[N+1];
        visited = new boolean[N+1];
        BFS(max);
        Arrays.sort(distance);
        
        System.out.println(distance[N]);
    }
    
    
    static void BFS(int index){
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(index);
        visited[index] = true;
        
        while(!q.isEmpty()){
            int now = q.poll();
            for (Edge i : A[now]){
                int e = i.e;
                int v = i.value;
                if(!visited[e]){
                    visited[e] = true;
                    q.add(e);
                    distance[e] = distance[now] + v;
                }
            }
        }
    }
    
    static class Edge{
        int e;
        int value;
        public Edge(int e, int value){
            this.e = e;
            this.value = value;
        }
    }
}