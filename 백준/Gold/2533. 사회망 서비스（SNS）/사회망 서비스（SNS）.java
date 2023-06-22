import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) tree[i] = new ArrayList<>();
        
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].add(v);
            tree[v].add(u);
        }

        Queue<Integer> leaves = new LinkedList<>();
        for (int i = 1; i <= N; i++)
            if (tree[i].size() == 1) leaves.add(i); // 리프 노드


        boolean[] adapted = new boolean[N + 1];
        int result = 0;
        while (!leaves.isEmpty()) {
            int l = leaves.poll();
            if (adapted[l] || tree[l].size() == 0) continue; // 이미 큐에 들어갔는데, g에서 빼지는 경우 생각
            adapted[l] = true;

            int f = tree[l].get(0); // 친 ~ 구
            if (adapted[f]) continue;
            adapted[f] = true;
            result++;
            
            //tree[l].remove(f);
            int tmp = tree[f].indexOf(l);
            tree[f].remove(tmp);
            
            for (int i = 0; i < tree[f].size(); i++) {
                int g = tree[f].get(i); // 친구의 친 ~ 구
                if (adapted[g]) continue;

                tmp = tree[g].indexOf(f);
                tree[g].remove(tmp);
                if (tree[g].size() == 1) leaves.add(g);
            }

            //if (tree[f].size() == 1) leaves.add(f);
        }


        System.out.println(result);
    }

}