import java.io.*;
import java.util.*;

public class Main{
    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static int[] check;
    static boolean isEven;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            A = new ArrayList[V+1];
            visited = new boolean[V+1];
            //그룹핑 배열
            //처음 값 : 0
            //check[1] = 0;
            check = new int[V+1];
            for(int j=1;j<V+1;j++) A[j] = new ArrayList<Integer>();
            for(int j=0;j<E;j++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                A[start].add(end);
                A[end].add(start);
            }
            isEven = true;
            //여기까지 초기화
            
            for(int j=1;j<V+1;j++){
                if(isEven) DFS(j);
                else break;
            }
            
            //YES NO 구분 할 변수 필요.
            if(isEven) System.out.println("YES");
            else System.out.println("NO");
        }
    }
    
    
    static void DFS(int node){
        visited[node] = true;
        
        for(int i : A[node]){
            if(!visited[i]){
                //아직 방문 안 했으면
                //check update
                check[i] = (check[node] + 1) % 2;
                DFS(i);
            }
            
            //방문했는데, 연결 노드랑 값이 같으면 
            //순환구조일 때
            //엣지를 일방향으로만 저장해도 될까?
            //1(0) -> 2(1) -> 5(0)
            //4(0) -> 5(0)
            //새롭게 시작하는 엣지가 없어야 함. check값 충돌.
            //양방향으로 엣지 저장.
            else if(check[node] == check[i]) isEven = false;
        }
    }
}