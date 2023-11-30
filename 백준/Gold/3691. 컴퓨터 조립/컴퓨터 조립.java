import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
    // Java heap space. OutOfMemoryError
//    static final int LIMIT = 1_000_000_000;
//    static boolean[] hasQ;
    static int m, arrLen;  // 부품 종류, 성능 종류 <= 1,000
    static ArrayList<Integer> arr;
    static ArrayList<Item>[] items;
    static long[] oldPrice, newPrice;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < tc; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            long b = Integer.parseInt(st.nextToken());  // 예산


            items = new ArrayList[n];
            for (int i = 0; i < n; i++) items[i] = new ArrayList<>();
            arr = new ArrayList<>();

            String pre = "";
            m = -1;   // 부품 종류
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String t = st.nextToken();
                String name = st.nextToken();
                long p = Integer.parseInt(st.nextToken());
                int q = Integer.parseInt(st.nextToken());

                if (!arr.contains(q)) arr.add(q);
                if (!pre.equals(t)) {
                    m++;
                    pre = t;
                }

                items[m].add(new Item(p, q));
            }


            Collections.sort(arr);
            arrLen = arr.size();
            oldPrice = new long[arrLen];
            newPrice = new long[arrLen];

            Arrays.fill(oldPrice, Integer.MAX_VALUE);  // 가격 >= 0
            Arrays.fill(newPrice, Integer.MAX_VALUE);
            purchase();


            int ans = 0;
            for (int i = 0; i < arrLen; i++)
                if (oldPrice[i] <= b) ans = i;

            sb.append(arr.get(ans)).append("\n");
        }


        System.out.print(sb);
    }


    static void purchase() {
        int len = items[0].size();
        for (int j = 0; j < len; j++) {
            Item o = items[0].get(j);
            int index = binarySearch(o.q, arrLen);
            oldPrice[index] = o.p;
        }

        for (int i = 1; i <= m; i++) {
            len = items[i].size();
//            Collections.sort(items[i]);

            for (int j = 0; j < len; j++) {
                Item o = items[i].get(j);
                int index = binarySearch(o.q, arrLen);
//                System.out.print(arr.get(index) + " ");
                long p = o.p;

                // 1. 작은 거
                for (int s = 0; s < index; s++) {
                    if (oldPrice[s] == Integer.MAX_VALUE) continue;

                    newPrice[s] = Math.min(newPrice[s], oldPrice[s] + p);
                    // 무조건 하나 들어가야 한다.
                }
                // 2. 큰 거
                long min = newPrice[index];  //Integer.MAX_VALUE;
                for (int s = index; s < arrLen; s++) {
                    if (oldPrice[s] == Integer.MAX_VALUE) continue;

                    min = Math.min(min, oldPrice[s] + p);

//                    newPrice[s] = Integer.MAX_VALUE; // 흡수된 성능
                }
                newPrice[index] = min;

//                for (int l = 0; l < arrLen; l++)
//                    System.out.print(newPrice[l] + " ");
//                System.out.println();
            }
//            System.out.println();


            for (int l = 0; l < arrLen; l++) {
                oldPrice[l] = newPrice[l];
                newPrice[l] = Integer.MAX_VALUE;
            }

        }

    }

    static int binarySearch(int num, int e) {
        int s = 0;
        while (s <= e) {
            int m = (s + e) >> 1;

            if (num <= arr.get(m)) e = m - 1;
            else s = m + 1;
        }

        return s;
    }


    static class Item implements Comparable<Item> {

        long p;  // 가격
        int q;   // 성능

        Item(long p, int q) {
            this.p = p;
            this.q = q;
        }

        @Override
        public int compareTo(Item o) {
            return this.q - o.q;  // 오름차순
        }

    }


}