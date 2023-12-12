import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> plusPq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minusPq = new PriorityQueue<>();
        int zero = 0, one = 0;
        for (int i = 0; i < N; i++) {
            int data = Integer.parseInt(br.readLine());

            if (data > 1) plusPq.add(data);
            else if (data == 1) one++;
            else if (data == 0) zero++;
            else minusPq.add(data);
        }


        int sum = 0;
        // 1) 양수
        while (plusPq.size() > 1) {
            int first = plusPq.remove();
            int second = plusPq.remove();

            sum += first * second;
        }
        // 1-1) 양수가 하나일 때
        if (!plusPq.isEmpty()) sum += plusPq.remove();

        // 2) 음수
        while (minusPq.size() > 1) {
            int first = minusPq.remove();
            int second = minusPq.remove();

            sum += first * second;
        }
        // 2-2) 음수가 하나일 때
        if (!minusPq.isEmpty())
            if (zero == 0) sum += minusPq.remove();
        // else: 0으로 곱해졌다고 생각해서, 안 더해줘도 된다.

        // 3) 1
        sum = sum + one;


        System.out.println(sum);
    }


}