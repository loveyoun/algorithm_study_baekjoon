import java.io.*;
import java.util.*;

public class Main{
    static int[] parent;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        for(int i=0;i<N+1;i++) parent[i] = i;
        
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int question = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            
            if(question == 0) union(n1, n2);
            else{
                if(isSib(n1, n2)) System.out.println("YES");
                else System.out.println("NO");
            }
        }
    }
    
    
    static int find(int index){
        // 1 3 5 7
        // 1 1 3 5
        if(index != parent[index]){
            //index = parent[index]. parent바꿀 수 없음.
            //내 부모들을 업데이트 해주고 싶음.
            return parent[index] = find(parent[index]);
            //parent[7] = find(5) = find(parent[5]==3) ... = 1
        }else return index;
    }
    
    static void union(int a, int b){
        //parent[find(b)] = parent[a];
        //parent를 깊숙히 탐색해야함.
        //중간에 바뀐 parent 고려.
        a = find(a); b = find(b);
        if(a != b) parent[b] = a; //b가 대표노드였으면, 자녀노드들은 각자 find하면 그 위에가 바뀜.
    }
    
    static boolean isSib(int a, int b){
        a = find(a); b = find(b);
        if(a == b) return true;
        else return false;
    }
}