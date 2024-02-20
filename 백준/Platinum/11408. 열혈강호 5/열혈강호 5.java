import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
 
class Main{
     
    final int INF =987654321,S=0,SINK=1;
    int[][] cap,flow,cost;
    int n,m,MAX;
    Scanner sc = new Scanner(System.in);
     
    void solve(){
        n = sc.nextInt();
        m = sc.nextInt();
        MAX = 2+n+m;
        cap = new int[MAX][MAX];
        flow = new int[MAX][MAX];
        cost = new int[MAX][MAX];
        for(int i=0;i<n;i++){
            cap[0][2+i] = 1;
            int num = sc.nextInt();
            for(int j=0;j<num;j++){
                int a = sc.nextInt();
                int c = sc.nextInt();
                a--;
                cap[2+i][2+n+a] = 1;
                cost[2+i][2+n+a] = c;
                cost[2+n+a][2+i] = -c;
                cap[2+n+a][1] = 1;
            }
        }
        Point ans = mcmf();
        System.out.println((int)ans.getX()+" "+(int)ans.getY());
    }
    Point mcmf(){
        int totalCost=0;
        int totalFlow=0;
        LinkedList<Integer> q = new LinkedList<Integer>();
        int[] dist = new int[MAX];
        int[] parent = new int[MAX];
        boolean[] chk = new boolean[MAX];
        while(true){
            Arrays.fill(dist, INF);
            Arrays.fill(parent, -1);
            Arrays.fill(chk, false);
            dist[S]=0;
            parent[S]=S;
            q.add(S);
            while(!q.isEmpty()){
                int u = q.poll();
                chk[u]=false;
                for(int i=0;i<MAX;i++){
                    if(cap[u][i]-flow[u][i]>0 && dist[u]+cost[u][i]<dist[i]){
                        dist[i] = dist[u]+cost[u][i];
                        parent[i]=u;
                        if(!chk[i]) q.add(i);
                        chk[i]=true;
                    }
                }
            }
            if(parent[SINK]==-1) break;
            if(dist[SINK]<0) break;
             
            for(int p=SINK;p!=S;p=parent[p]){
                flow[parent[p]][p] +=1;
                flow[p][parent[p]] -=1;
            }
             
            totalFlow += 1;
            totalCost += dist[SINK];
        }
        return new Point(totalFlow,totalCost);
    }
    public static void main(String[] args) {
        new Main().solve();
    }
}
