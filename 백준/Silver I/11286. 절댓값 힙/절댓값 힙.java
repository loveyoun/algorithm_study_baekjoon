import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        /** 11286_Algorithm flow : Priority Queue
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        /** new PriorityQueue<>((파라미터들) -> {실행문}); **/
        PriorityQueue<Integer> myQueue = new PriorityQueue<>((o1, o2) -> {
            int first_abs = Math.abs(o1);
            int second_abs = Math.abs(o2);

            if (first_abs == second_abs)
                // 1 > (-1) : 1 => rear 1 -1 front : 오름차순이 default 이다.
                return o1 > o2 ? 1 : -1;
            else
                // |-2| - |1| > 0 : rear |-2| |1| front : 양수이면 그대로 오름차순.
                return first_abs - second_abs;
        });
        

        for (int i = 0; i < N; i++) {
            int request = Integer.parseInt(br.readLine());

            if (request == 0) {
                if (myQueue.isEmpty())
                    sb.append("0\n");
                else
                    sb.append(myQueue.poll() + "\n");
            } else myQueue.add(request);
        }

        System.out.println(sb);
    }

}