import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        /** 1744_Algorithm_flow_수 묶기 : 그리디, 우선순위 큐(or 정렬)
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        //1 (4 6) (7 10)
        PriorityQueue<Integer> pluspq = new PriorityQueue<>(Collections.reverseOrder());
        //-1 (-8 -9)
        PriorityQueue<Integer> minuspq = new PriorityQueue<>();
        int zero = 0, one = 0;
        for (int i = 0; i < N; i++) {
            int data = Integer.parseInt(br.readLine());

            if (data > 1) pluspq.add(data);
            else if (data == 1) one++;
            else if (data == 0) zero++;
            else minuspq.add(data);
        }

        int sum = 0;
        //1) 양수
        while (pluspq.size() > 1) {
            int first = pluspq.remove();
            int second = pluspq.remove();

            //()
            sum += first * second;
        }
        //1-1) 양수가 하나 남았을 때
        if (!pluspq.isEmpty()) sum += pluspq.remove();

        //2) 음수
        while (minuspq.size() > 1) {
            int first = minuspq.remove();
            int second = minuspq.remove();

            //()
            sum += first * second;
        }
        //2-2) 음수가 하나 남았을 때
        if (!minuspq.isEmpty())
            if (zero == 0) sum += minuspq.remove();
            // else : 0으로 곱해졌다고 생각해서, 안 더해줘도 된다.

        //3) 1 처리
        sum = sum + one;
        System.out.println(sum);
    }

}