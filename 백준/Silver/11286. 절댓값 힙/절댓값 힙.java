import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> myQueue = new PriorityQueue<>((o1, o2) -> {
            int o1_abs = Math.abs(o1);
            int o2_abs = Math.abs(o2);

            if (o1_abs == o2_abs) // 오름차순
                // 원래1 > 새로운(-1) : 정렬 true
                return o1 > o2 ? 1 : -1;
            else
                // 원래|-2| - 새로운|1| : true
                return o1_abs - o2_abs;
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) {
                if (myQueue.isEmpty()) sb.append("0\n");
                else sb.append(myQueue.poll()).append("\n");
            } else myQueue.add(n);
        }

        System.out.println(sb);
    }

}