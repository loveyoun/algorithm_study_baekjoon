import java.io.IOException;
import java.util.Arrays;

public class Main {

    static int n, k, result = 0;
    static int[][] dp;
    static Item[] its;

    public static void main(String[] args) throws IOException {
        n = read();   // 물건 개수
        k = read();   // 감당 무게

        dp = new int[k + 1][n];  // [weight][index]

        its = new Item[n];
        for (int i = 0; i < n; i++) {
            int w = read();
            int v = read();

            its[i] = new Item(w, v);
            /*dp[w] = Math.max(dp[w], v);*/
        }

        Arrays.sort(its); /***/


        System.out.println(DP(n - 1, k));

        //System.out.println(result);
    }


    static int DP(int idx, int bag) {
        // 물건 없어도 물건들 조합으로 dp는 생길 수 있다.

        // Base case
        if (idx == -1) return 0;

        /* 이미 업데이트 된 경우 */
        if (dp[bag][idx] != 0) return dp[bag][idx];


        for (int i = idx; i >= 0; i--) {
            int w = its[i].w;
            int v = its[i].v;
            if (w > bag) continue; /*|| bag - w > w*/

            dp[bag][idx] = Math.max(dp[bag][idx], DP(i - 1, bag - w) + v);  /*dp[bag]*/
        }

        return dp[bag][idx];
    }


//    static void DP() {  //System.out.println(dp[k]);
//        for (int i = 0; i < n; i++) {
//            int w = read(), v = read();
//
//            for (int j = k; j >= w; j--)   // k ~ weight 배낭
//                dp[j] = Math.max(dp[j], dp[j - w] + v);  // 해당 물건 넣었을때 vs 안 넣었을 때. `누적된다`
//        }
//
//    }

    /**
     * 1) Bottom - up
     **/
//    static void BT_DP(int idx, int w) {  //its[n] = new Item(0, 0);
//        int v = dp[w][idx];
//
//        for (int i = idx + 1; i <= n; i++) {  // 1 ~ n 번 인덱스
//            int bag = w + its[i].w;
//            if (bag > k) break;
//
//            int new_v = v + its[i].v;
//
//            // 3개 비교
//            if (dp[bag][i] >= new_v) continue;
//            if (dp[bag][i - 1] >= new_v) {
//                dp[bag][i] = dp[bag][i - 1];
//                continue;
//            }
//
//            dp[bag][i] = new_v;
//            result = Math.max(result, new_v);
//
//            BT_DP(i, bag);
//        }
//
//    }
//    static void BT_DP(int idx, int weight) {   //its[n] = new Item(0, 0);
//        for (int i = idx + 1; i <= n; i++) {
//            int sum = weight + its[i].w;
//            if (sum > k) break;
//
//            if (dp[sum][i] >= dp[weight][idx] + its[i].v) continue;
//
//            dp[sum][i] = dp[weight][idx] + its[i].v;
//            result = Math.max(result, dp[sum][i]);
//
//            BT_DP(i, sum);
//        }
//
//    }
//    static void BT_DP(int idx, int weight) {  //its[0] = new Item(0, 0);
//        for (int i = idx + 1; i <= n; i++) {
//            int sum = weight + its[i].w;
//            if (sum > k) continue;  // 정렬을 하지 않으면 뒤에 것들은 다 크지 않다
//
//            if (dp[sum][i] >= dp[weight][idx] + its[i].v) continue;
//
//            dp[sum][i] = dp[weight][idx] + its[i].v;
//            result = Math.max(result, dp[sum][i]);
//
//            BT_DP(i, sum);
//        }
//
//    }


//    static void fail(int idx, int weight) {  //its[n] = new Item(0, 0);
//        for (int i = idx + 1; i <= n; i++) {
//            int sum = weight + its[i].w;
//            if (sum > k) break;      // 어차피 뒤에 것들은 다 크니까
//
//            /* 실패 원 인 : l 까지 더했을 때, l + 2 까지 더했을 때, 이미 확정되어서
//               그렇다고 없으면 시 간 초 과
//             */
//            if (dp[sum] >= dp[weight] + its[i].v) continue;
//
//            dp[sum] = dp[weight] + its[i].v;
//            result = Math.max(result, dp[sum]);
//
//            dfs(i, sum);
//        }
//
//    }
//
//    static int fail(int idx) { /* 1 + 2 + 1 */
//        // 물건 없어도 dp는 생길 수 있다.
//
//        int w = its[idx].w;
//
//        if (visited[idx]) return dp[w];  // 이미 업데이트 된 경우
//        visited[idx] = true;
//
//        for (int i = idx + 1; i < n; i++) {
//            int new_w = w + its[i].w;
//            if (new_w > k) break;
//
//            dp[new_w] = Math.max(dp[new_w], dp[w] + dfs(i));
//        }
//
//        return dp[w];
//    }
//
//    static int fail(int w) {  //its[n] = new Item(k, 0); /* 같은 값 2개 불가능, 1 + 2 + 1 */

//    }


    static class Item implements Comparable<Item> {
        int w, v;

        Item(int w, int v) {
            this.w = w;
            this.v = v;
        }

        @Override
        public int compareTo(Item o) { /***/
            return this.w - o.w; // 가벼운 거
        }

    }


    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

}