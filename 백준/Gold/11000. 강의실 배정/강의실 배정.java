import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] queue = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            queue[i][0] = Integer.parseInt(st.nextToken());
            queue[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(queue, ((x, y) -> x[0] - y[0]));    // 시작 시간 오름차순. 넣을 때마다(PriorityQueue)가 아닌, 한 번에 정렬

        PriorityQueue<int[]> rooms = new PriorityQueue<>((x, y) -> x[1] - y[1]);   // 끝나는 시간 오름차순


        rooms.offer(queue[0]);
        for (int i = 1; i < N; i++) {
            if (!rooms.isEmpty() && rooms.peek()[1] <= queue[i][0]) rooms.poll();

            rooms.offer(queue[i]);
        }


        System.out.println(rooms.size());
    }

}