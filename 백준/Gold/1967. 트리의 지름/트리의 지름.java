/** 연습용 손코딩 **/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    static int max_index, max;
    static ArrayList<Node>[] tree;
    // BFS
    static int[] distance;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 트리 저장
        tree = new ArrayList[N+1];
        for(int i=0;i<=N;i++) tree[i] = new ArrayList<>();
        for(int i=0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[s].add(new Node(e, v));
            tree[e].add(new Node(s, v));
        }
        
        max_index = 0;
        max = 0;
        distance = new int[N+1];
        visited = new boolean[N+1];
        BFS(1);
        
        max = 0;
        distance = new int[N+1];
        visited = new boolean[N+1];
        BFS(max_index);
        
        System.out.println(max);
    }
    
    static void BFS(int start_index){
        // 큐 
        Queue<Integer> queue = new LinkedList<>();
        
        visited[start_index] = true;
        queue.add(start_index);
        
        while(queue.size()>0){
            int now = queue.poll();
           
            for(Node node:tree[now]){
                int next = node.next;
                if(!visited[next]){
                    int value = node.value;
                    visited[next] = true;
                    queue.add(next);
                    distance[next] = distance[now] + value;
                    if(distance[next] > max){
                        max = distance[next];
                        max_index = next;
                    }
                }
            }
        }
    }
    
    static class Node{
        int next, value;
        Node(int next, int value){
            this.next = next;
            this.value = value;
        }
    }
}