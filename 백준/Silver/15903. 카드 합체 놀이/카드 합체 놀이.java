import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 카드 개수
        int m = Integer.parseInt(st.nextToken());// 합체 수

        PriorityQueue<Long> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) pq.add(Long.parseLong(st.nextToken()));

        for (int i = 0; i < m; i++) { // 시간 복잡도 : O(4 * m)
            long a = pq.poll();
            long b = pq.poll();

            long sum = a + b; /** a a a ... -> a a ... 2a 2a -> int 범위 넘어가버림 **/
            pq.add(sum);
            pq.add(sum);
        }


        long result = 0;
        while (!pq.isEmpty()) result += pq.poll();
        System.out.println(result);
    }

}