import java.io.IOException;
import java.util.Arrays;

public class Main {

    static int N, M, max_limit, total, result;
    static boolean isPossible;
    static int[] limit, weight, boxes;

    public static void main(String[] args) throws IOException {
        N = read(); // 크레인 수
        limit = new int[N];
        max_limit = 0;
        for (int i = 0; i < N; i++) limit[i] = read();

        M = read(); // 박스 수
        weight = new int[M];
        for (int i = 0; i < M; i++) weight[i] = read();


        Arrays.sort(weight);
        Arrays.sort(limit);

        boxes = new int[N];
        int j = 0;
        for (int i = 0; i < N; i++) {
            int count = 0;
            while (j < M) {
                if (limit[i] < weight[j]) break;
                j++;
                count++;
            }
            boxes[i] = count;
            total += count;
            if (j == M) break;
        }

        if (total != M) System.out.println(-1);
        else {
            result = 0;
            convey();
            System.out.println(result);
        }
    }

    static void convey() {
        int queue = 0;
        while (total > 0) {
            for (int i = N - 1; i >= 0; i--) {
                int container = boxes[i];
                queue++;
                if (container > 0) {
                    if (container >= queue) { // 다 넣어줄 수 있을 때
                        total -= queue;
                        boxes[i] -= queue; // 대기 큐에 없음
                        queue = 0;
                    } else {
                        total -= container;
                        queue -= container;
                        boxes[i] = 0;
                    }
                }
            }
            result++;
            queue = 0; // boxes[0] == 0 일 때
        }
    }

    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

}