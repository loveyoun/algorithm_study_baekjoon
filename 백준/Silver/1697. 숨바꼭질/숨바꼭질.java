import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


public class Main {

    static int N, K;
    static boolean found;
    static int secs;
    static int[] dx = {-1, 1};
    static int new_x;

    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        /** 1697_Algorithm flow: 최소, 최단은 BFS로 해야 시간초과 안 나잖슴...
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] stmp = br.readLine().split(" ");
        N = Integer.parseInt(stmp[0]);
        K = Integer.parseInt(stmp[1]);

        visited = new boolean[100001];
        secs = 0;
        found = false;

        /** 이거 떄매... 젭알 조건... **/
        if (N == K) System.out.println(0);
        else {
            BFS(N, 0);

            if (found) System.out.println(secs);
        }
    }

    static void BFS(int v, int count) {
        Queue<Node> queue = new LinkedList<>();

        visited[v] = true;
        queue.add(new Node(v, count));

        while (queue.size() > 0) {
            Node node = queue.poll();
            //== 인접 노드 탐색
            for (int i = 2; i >= 0; i--) {
                if (i == 2) new_x = node.x * 2;
                else new_x = node.x + dx[i];

                /** index check 반듯이 **/
                if (new_x >= 0 && new_x <= 100000) {
                    if (!visited[new_x]) {
                        /* 밖으로 빼는게 조건 확인 한 번 더 안 해서 빠르긴 함 
                        * 0일 때 계속 0이라서 ㄱㅊ? 했는데*/
                        if (new_x == K) {
                            found = true;
                            secs = node.count + 1;
                            break;
                        }

                        visited[new_x] = true;
                        queue.add(new Node(new_x, node.count + 1)); /* ++n.count n.count 도 올라감*/
                    }
                } else continue;
            }

            if (found) break;
        }
    }

    static class Node {
        int x;
        int count;

        public Node(int x, int count) {
            this.x = x;
            this.count = count;
        }
    }

}