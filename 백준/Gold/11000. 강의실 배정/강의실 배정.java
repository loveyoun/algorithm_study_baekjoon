import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> classes = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];   // 시작 시간 오름차순
        });
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            classes.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        PriorityQueue<int[]> rooms = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];   // 끝나는 시간 오름차순
        });

        int result = 0;
        rooms.add(classes.poll());
        while (!classes.isEmpty()) {
            int[] c = classes.poll();
            if (!rooms.isEmpty() && rooms.peek()[1] <= c[0]) rooms.poll();
            rooms.add(c);

            result = Math.max(result, rooms.size());
        }


        System.out.println(result);
    }

}