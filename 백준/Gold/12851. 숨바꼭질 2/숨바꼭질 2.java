import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int N, K;
    static boolean found;
    static int secs, counts;
    static int[] dx = {1, -1};
    static int new_x;

    static int[] loc;

    public static void main(String[] args) throws IOException {
        /** 13549_Algorithm flow_숨바꼭질 3: 다익스트라
         *  ≒ 1697 숨바꼭질
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] stmp = br.readLine().split(" ");
        N = Integer.parseInt(stmp[0]);
        K = Integer.parseInt(stmp[1]);

        loc = new int[100001];
        for (int i = 0; i < 100001; i++) loc[i] = Integer.MAX_VALUE;
        counts = 0;

        StringBuilder sb = new StringBuilder();
        if (N == K) sb.append("0" + "\n" + "1");
        else {
            BFS(N, 0);
            sb.append(loc[K] + "\n" + counts);
        }

        System.out.println(sb);
    }

    static void BFS(int unni, int count) {
        PriorityQueue<Node> pqueue = new PriorityQueue<>(((o1, o2) -> {
            /* 4, 6 반례 처리 */
            if (o1.sec == o2.sec) return o1.x - o2.x;
            else return o1.sec - o2.sec; // 오름차순
        }));

        pqueue.add(new Node(unni, count));

        while (pqueue.size() > 0) {
            Node node = pqueue.poll();
            // == 인접 노드 탐색
            for (int i = 2; i >= 0; i--) {
                if (i == 2) new_x = node.x * 2;
                else new_x = node.x + dx[i];

                if (new_x >= 0 && new_x <= 100000) {
                    int new_sec = node.sec + 1;

                    // new_sec = 4, 2, 4 이런 식으로 들어올 수는 없다. 우선순위 큐를 해줬는데?
                    /*
                    1 (+1)2 (*2) 4
                      (*2)2 (*2) 4
                    */
                    if (new_x == K) {
                        if (new_sec < loc[new_x]) {
                            loc[new_x] = new_sec;
                            counts = 1;
                        } else if (new_sec == loc[new_x]) {
                            counts++;
                        }
                    }

                    if (new_sec <= loc[new_x]) {
                        loc[new_x] = new_sec;
                        pqueue.add(new Node(new_x, new_sec));
                    }
                }
            }
        }
    }

    static class Node {
        int x, sec;

        public Node(int x, int sec) {
            this.x = x;
            this.sec = sec;
        }
    }

}