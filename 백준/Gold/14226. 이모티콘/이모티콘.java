import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int N, result;
    static boolean found;
    static int[] monitor, clip_board;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        /** 14226_Algorithm_이모티콘 : 다익스트라 (no BFS)
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        clip_board = new int[2001];
        found = false;
        result = 0;
        BFS(1, 0, 0);

        System.out.println(result);
    }

    static void BFS(int start_len, int copied, int count) {
        PriorityQueue<Clip> pqueue = new PriorityQueue<>(((o1, o2) -> {
            if (o1.count == o2.count) return o1.len - o2.len;
            else return o1.count - o2.count; // 오름차순
        }));

        pqueue.add(new Clip(start_len, copied, count));

        while (pqueue.size() > 0) {
            Clip now = pqueue.poll();
            int now_len = now.len;
            int now_copied = now.copied;
            int now_count = now.count;

            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    // 1) 복사하기
                    // (1, 1, 1) -> (1, 1, 2) 되지 않게 조건.
                    if (clip_board[now_len] == 0 && now_len > 0) {
                        pqueue.add(new Clip(now_len, now_len, now_count + 1));
                    /* 우선순위 큐로 해결 됨.
                    clip_board[now_len] = Math.min(clip_board[now_len], monitor[now_len] + 1);
                     */
                        clip_board[now_len] = now_count + 1;
                    }

                } else if (i == 1) {
                    /** 2) 붙여넣기
                     1 2 4 (2) 6 12 (6) 18 -> 8
                     6을 복사했는데, 다음에 2를 붙여넣을 수 없지 않냐? 를 생각할 의미는 없다.
                     1)에서 새로운 복붙을 갱신해주기 때문에. 유남생?
                     **/
                    int new_len = now_len + now_copied;
                    if (now_copied > 0 && new_len <= 2000) {
                        if (new_len == N) {
                            result = now_count + 1;
                            found = true;
                            break;
                        }
                        //pqueue.add(new Clip(new_len, now_copied, now_count + clip_board[now_copied] + 1));
                        pqueue.add(new Clip(new_len, now_copied, now_count + 1));
                    }

                } else {
                    // 3) 1개 지우기
                    int new_len = now_len - 1;
                    if (new_len > 0) {
                        if (new_len == N) {
                            result = now_count + 1;
                            found = true;
                            break;
                        }
                        pqueue.add(new Clip(new_len, now_copied, now_count + 1));
                    }
                }
            }

            if (found) break;
        }
    }

    static class Clip {
        int len, copied, count;
        //boolean ispasted;

        public Clip(int len, int copied, int count) {
            this.len = len;
            this.copied = copied;
            this.count = count;
        }
    }

}