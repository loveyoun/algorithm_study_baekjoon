import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static int N, result;
    static boolean found;
    //static int[] monitor;
    //static boolean[] visited;
    static boolean[] clip_board;

    public static void main(String[] args) throws IOException {
        /** 14226_Algorithm_이모티콘 : 다익스트라 (no pure BFS)
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        clip_board = new boolean[2001];
        found = false;
        result = 0;
        Di_BFS(1, 0, 0);

        System.out.println(result);
    }

    static void Di_BFS(int start_len, int copied, int sec) {
        /*PriorityQueue<Clip> queue = new PriorityQueue<>(((o1, o2) -> {
            /* 4, 6 처리 _ 숨바꼭질3 
            4 (3,1) 6 > 4 (5,1) 6
             
            if (o1.sec == o2.sec) return o1.len - o2.len;
            else return o1.sec - o2.sec; // 오름차순
        }));*/
        Queue<Clip> queue = new LinkedList<>();

        queue.add(new Clip(start_len, copied, sec));

        while (queue.size() > 0) {
            Clip now = queue.poll();
            int now_len = now.len;
            int now_copied = now.copied;
            int now_sec = now.sec;

            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    // 1) 복사하기
                    // (1, 1, 1) -> (1, 1, 2) 되지 않게 clip_board[1] 조건
                    if (!clip_board[now_len] && now_len > 0) {
                        queue.add(new Clip(now_len, now_len, now_sec + 1));
                        /* 우선순위 큐로 해결 됨.
                        clip_board[now_len] = Math.min(clip_board[now_len], monitor[now_len] + 1);
                        */
                        clip_board[now_len] = true;
                    }
                } else if (i == 1) {
                    /** 2) 붙여넣기
                     1 2 4 (2) 6 12 (6) 18 -> 8
                     6을 복사했는데, 다음에 2를 붙여넣을 수 없지 않냐? 를 생각할 의미는 없다.
                     1)에서 새로운 복붙을 갱신해주기 때문에. 유남생?
                     붙여넣기는 붙여넣기만 해주고, 복사는 새로운 클립보드를 업데이트 하기 때문에.
                     **/
                    int new_len = now_len + now_copied;
                    int new_sec = now_sec + 1;

                    // 1개 이상 있을 때만 복붙 가능.
                    if (now_copied > 0 && new_len <= 2000) {
                        if (new_len == N) {
                            result = new_sec;
                            found = true;
                            break;
                        }
                        //pqueue.add(new Clip(new_len, now_copied, now_count + clip_board[now_copied] + 1));
                        queue.add(new Clip(new_len, now_copied, new_sec));
                    }
                } else {
                    // 3) 1개 지우기
                    int new_len = now_len - 1;
                    int new_sec = now_sec + 1;

                    if (new_len > 0) {
                        if (new_len == N) {
                            result = new_sec;
                            found = true;
                            break;
                        }
                        queue.add(new Clip(new_len, now_copied, new_sec));
                    }
                }
            }

            if (found) break;
        }
    }

    static class Clip {
        int len, copied, sec;
        //boolean ispasted;

        public Clip(int len, int copied, int sec) {
            this.len = len;
            this.copied = copied;
            this.sec = sec;
        }
    }

}