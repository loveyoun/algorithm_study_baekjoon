import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 보석
        int K = Integer.parseInt(st.nextToken()); // 가방

        Jewel[] jewelries = new Jewel[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            jewelries[i] = new Jewel(weight, price);
        }
        Arrays.sort(jewelries, Comparator.comparingInt(o -> o.weight));
        /**
        Arrays.sort(jewelries, (o1, o2) -> {
            return o1.weight - o2.weight;
        });
        Arrays.sort(jewelries, new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                // 무게 3 == 3일 때, 99 > 50 (가격 내림차순)
                if (o1.weight == o2.weight) return o2.price - o1.price;
                    // 무게 2 < 3 (무게 오름차순)
                else return o1.weight - o2.weight;
            }
        });
        **/

        int[] bags = new int[K];
        for (int i = 0; i < K; i++)
            bags[i] = Integer.parseInt(br.readLine());
        Arrays.sort(bags);


        /* long!!! */
        long result = 0;
        PriorityQueue<Integer> pqueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0, j = 0; i < K; i++) {
            // 가방 무게 내로 넣을 수 있는 `가장 큰 가격`
            while (j < N && jewelries[j].weight <= bags[i])
                pqueue.add(jewelries[j++].price);

            /** 시간복잡도 : O(K)
             전 가방 무게 내로 넣을 수 있는 보석들이 가격 순으로 담겨 있다. 다시 안 돌려도 됨. 시간 초과 안 남.
             **/
            if (pqueue.size() > 0) result += pqueue.poll();
        }

        System.out.println(result);
    }

    static class Jewel {
        int weight, price;

        Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }

}