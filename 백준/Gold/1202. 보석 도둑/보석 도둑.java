import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    /*static int[] weights;
    static int[] prices;
    static int[] bags;*/


    public static void main(String[] args) throws IOException {
        /**1202_Algorithm flow_보석 도둑 : 그리디(...억지 알고리즘 명명... 사실 구현이잖아), 우선순위 큐
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //* 무게-> 가격 기준 내림차순(그리디에 더 적합할 듯)
        /*PriorityQueue<Jewel> jewelries = new PriorityQueue<>((o1, o2) -> {
            //가격 같으면, 무게 작은 거 먼저(오름차순)
            if (o1.price == o2.price) return o1.weight - o2.weight;
            else return o2.price - o1.price;
        });*/
        Jewel[] jewelries = new Jewel[N];
        Jewel[] tmp = new Jewel[300000];
        /*PriorityQueue<Jewel> tmp = new PriorityQueue<>((o1, o2) -> {
            if (o1.price == o2.price) return o1.weight - o2.weight;
            else return o2.price - o1.price;
        });*/
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            //* weight과 price 어떻게 연결해주냐... 클래스로 비교?
            //jewelries.add(new Jewel(weight, price));
            jewelries[i] = new Jewel(weight, price);
        }
        Arrays.sort(jewelries, new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                /* if (o1.price == o2.price) return o1.weight - o2.weight;
                else return o2.price - o1.price;*/
                if (o1.weight == o2.weight) return o2.price - o1.price;
                else return o1.weight - o2.weight;
            }
        });

        /*for (int i = 0; i < N; i++) {
            System.out.print(jewelries[i].price);
            System.out.println(jewelries[i].weight);
        }*/

        /* 내림차순
        bags = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });*/
        //PriorityQueue<Integer> bags = new PriorityQueue<>();
        //Queue<Integer> bags = new LinkedList<>();
        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
            //bags.add(Integer.parseInt(br.readLine()));
        }
        //오름차순
        Arrays.sort(bags);

        /* N==K 일 때, 누락이 되면 어떻게 하지??
         * 그럼 가방 무게 가장 작은 것부터 넣어야 하나?
         * 1
         * 2    2
         * 5    10
         * 2 -> 1, 2 중 큰 거 고르기 => bags: 2, 3, 10일 때 오류?
         * 아님 일단 넣고봐?
         * 너무 고려할 것이 많다 -> 고려할 것이 적은 방법을 찾아보자.
         * 처음 방법이 맞았다. 우선순위 큐로 해서 하면 된다.
         * 1) 무게 오름차순, 가방 오름차순 => 가격 내림차순
         * 2) 가격 내림차순, 가방 오름차순 : 시간 초과
         * */

        /* long!!! */
        long result = 0;
        /** 1) 번 방법 **/
        PriorityQueue<Integer> pqueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0, j = 0; i < K; i++) {
            while (j < N && jewelries[j].weight <= bags[i])
                pqueue.add(jewelries[j++].price);
            if (pqueue.size() > 0) result += pqueue.poll();
        }

        /**
         *  2)번 방법 : 시간 초과
         *
         //while (bags.size() > 0) {
         /** O(K * log(N)) : 300000 * 500 = 150,000,000 시간초과 ...
         *  PriorityQueue tmp 해서 하는 것도, log(N)이니까 시간초과 ...
         for (int i = 0; i < K; i++) {
         /*int b = bags.poll();
         boolean fit = false;
         int count = 0;

         for (int l = 0; l < N; l++) {
         Jewel j = jewelries[l];

         if (j.weight >= 0) {
         if (bags[i] >= j.weight) {
         result += j.price;
         jewelries[l].setPrice(-1);
         jewelries[l].setWeight(-1);

         //* 조 심
         break;
         } else continue;
         } else continue;
         }

         /*while (!fit) {
         /** NullPointException!!! 가방은 최대 1개까지 가능하다. 비어있을 수도 있다.
         * 보석 무게보다 작으면 들어갈 수 없는거지 머...

         if (jewelries.size() > 0) {
         Jewel j = jewelries.poll();
         /* 바로 접근하는 것보다, get()으로 하는 게 더 안전
         int p = j.price;
         int w = j.weight;

         //가방에 들어가지면,
         if (bags[i] >= w) {
         result += p;
         fit = true;
         //while (tmp.size() > 0) jewelries.add(tmp.poll());
         for (int k = 0; k < count; k++) jewelries.add(tmp[k]);
         } else {
         tmp[count++] = j;
         //tmp.add(j);
         /* jewelries.add(j); //5 6 7 -> 7 5 6 -> 7 5 우선순위 큐 니까 다시 정렬되는구나...
         }
         } else {
         /* 찾은 것 없을 때, 이미 지나간 것들 넣어주기
         for (int k = 0; k < count; k++) jewelries.add(tmp[k]);
         break;
         }
         } **/

        System.out.println(result);
    }

    /*private static void DFS(int node) {
    }*/

    static class Jewel {
        int weight;
        int price;

        Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }

}