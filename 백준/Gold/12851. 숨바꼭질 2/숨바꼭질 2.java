import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int N, K;
    static int counts;
    static int[] locs;
    static int[] dx = {1, -1};
    static int new_x;

    public static void main(String[] args) throws IOException {
        /** 12851_Algorithm flow_숨바꼭질 2 : kind of 다익스트라 + 추가적인 조건 변형
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] stmp = br.readLine().split(" ");
        N = Integer.parseInt(stmp[0]);
        K = Integer.parseInt(stmp[1]);

        locs = new int[100001];
        for (int i = 0; i < 100001; i++) locs[i] = Integer.MAX_VALUE;
        counts = 0;

        StringBuilder sb = new StringBuilder();
        if (N == K) sb.append("0" + "\n" + "1");
        else {
            BFS(N, 0);
            sb.append(locs[K] + "\n" + counts);
        }

        System.out.println(sb);
    }

    static void BFS(int unni, int count) {
        PriorityQueue<Node> pqueue = new PriorityQueue<>(((o1, o2) -> {
            /* 4, 6 반례 처리 
            (x, sec) : (3, 4) (4, 4) (2, 5)
            */
            if (o1.sec == o2.sec) return o1.x - o2.x;
            else return o1.sec - o2.sec; // 오름차순
        }));

        pqueue.add(new Node(unni, count));

        while (pqueue.size() > 0) {
            Node node = pqueue.poll();

            for (int i = 0; i < 3; i++) {
                if (i == 2) new_x = node.x * 2;
                else new_x = node.x + dx[i];

                if (new_x >= 0 && new_x <= 100000) {
                    int new_sec = node.sec + 1;
                    // new_sec = 4, 2, 4 이런 식으로 들어올 수는 없다. 우선순위 큐를 해줬는데?

                    if (new_x == K) {
                        if (new_sec < locs[new_x]) {
                            locs[new_x] = new_sec;
                            counts = 1;
                        } else if (new_sec == locs[new_x]) counts++;
                    }
                    
                    /* 오류 핸들링
                    1 (+1)2 (*2) 4
                      (*2)2 (*2) 4
                    */
                    if (new_sec <= locs[new_x]) {
                        locs[new_x] = new_sec;
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