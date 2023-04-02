import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        /** 1715_Algorithm_flow_카드 정렬하기: 그리디, 우선순위 큐
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) pq.add(Integer.parseInt(br.readLine()));

        int sum = 0;
        while (pq.size() != 1) {
            int data1 = pq.remove();
            int data2 = pq.remove();

            sum += data1 + data2;
            pq.add(data1 + data2);
        }

        System.out.println(sum);
    }

}