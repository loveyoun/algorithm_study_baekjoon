import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        /** 2164_Algorithm_flow_카드2: 큐(의 정석)
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> myQueue = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) myQueue.add(i);

        while (myQueue.size() > 1) {
            myQueue.poll();
            myQueue.add(myQueue.poll());
        }

        System.out.println(myQueue.poll());
    }

}