import java.io.IOException;
import java.util.Arrays;

public class Main {

    static int n, k, result = 0;
    static int[][] dp;
    static int[] values;
    static Item[] its;

    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine()); Integer.parseInt(st.nextToken());
        n = read(); // 물건 개수 
        k = read();// 감당 무게

        dp = new int[100001][n + 1];  // [weight][index]
        //Arrays.fill(dp, -1);

        its = new Item[n + 1];
        values = new int[100001];
        for (int i = 0; i < n; i++) {
            //st = new StringTokenizer(br.readLine()); Integer.parseInt(st.nextToken());
            int w = read();
            int v = read();

            its[i] = new Item(w, v);
            values[w] = v;
            //dp[w] = Math.max(dp[w], v);
        }
        its[n] = new Item(0, 0);
        Arrays.sort(its); /***/
        //for (int i = 0; i <= n; i++) System.out.println(its[i].w + " " + its[i].v);


        dfs(0, 0);
        //for (int k = 0; k <= n; k++) System.out.print(dp[304][k] + " ");
        System.out.println(result);
    }

    static void dfs(int idx, int weight) {
        for (int i = idx + 1; i <= n; i++) {
            int sum = weight + its[i].w;
            if (sum > k) break;

            if (dp[sum][i] >= dp[weight][idx] + its[i].v) continue;

            dp[sum][i] = dp[weight][idx] + its[i].v;
            result = Math.max(result, dp[sum][i]);

            dfs(i, sum);
        }

    }


//    static boolean dfs(int idx, int weight) {
//        // 물건 없어도 dp는 생길 수 있다.
//        // dp 기본값 : value or -1 (물건 있을 때 없을 떄)
//        if (dp[weight] == -1) return false;
//
//        // 이미 업데이트
//        if (dp[weight] != -1) return true;
//
//
//        // 원래 물건 가치 넣어주기
//        dp[weight] = values[weight];
//
//        for (int i = idx; i >= 0; i--) {
//            if (its[i].w > weight) continue;
//
//            int remain = weight - its[i].w;
//
//            dfs(i - 1, remain);
//            dp[weight] = Math.max(dp[weight], dp[remain] + its[i].v);
//
//            result = Math.max(result, dp[weight]);
//        }
//
//    }


//    static void dfs(int idx, int weight) { /* 시 간 초 과 */
//        for (int i = idx + 1; i <= n; i++) {
//            int sum = weight + its[i].w;
//            if (sum > k) break;
//
//            result = Math.max(result, dp[weight] + its[i].v);
//
//            dfs(i, sum);
//        }
//
//    }
//
//    static void dfs(int idx, int weight) {  //its[n] = new Item(0, 0);
//        for (int i = idx + 1; i <= n; i++) {
//            int sum = weight + its[i].w;
//            System.out.print(sum + " ");
//            if (sum > k) break;      // 어차피 뒤에 것들은 다 크니까
//
//            if (dp[sum] >= dp[weight] + its[i].v) continue; /* 원 인 : l 까지 더했을 때, l + 2 까지 더했을 때, 이미 확정되어서 */
//
//            dp[sum] = dp[weight] + its[i].v;
//            result = Math.max(result, dp[sum]);
//
//            dfs(i, sum);
//        }
//
//    }


//    static int dfs(int idx) { /* 1 + 2 + 1 */  //its[n] = new Item(0, 0);
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
//            System.out.println(new_w + " = " + w + " + " + its[i].w);
//            dp[new_w] = Math.max(dp[new_w], dp[w] + dfs(i));
//        }
//
//        return dp[w];
//    }


//    static int dfs(int w) {  //its[n] = new Item(k, 0); /* 같은 값 2개 불가능, 1 + 2 + 1 */
//        // 물건 없어도 dp는 생길 수 있다.
//
//        // 이미 업데이트 된 경우
//        if (visited[w]) return dp[w];
//
//        visited[w] = true;
//        // dp 기본값 : value or -1 (물건 있을 때 없을 떄)
//
//        for (int i = 0; i < n; i++) {
//            int new_w = its[i].w;
//            if (w <= new_w) continue;
//
//            int plus = w - new_w;
//            if (dfs(plus) == -1) continue;
//
//            dp[w] = Math.max(dp[w], dfs(new_w) + dp[plus]);
//        }
//
//        return dp[w];
//    }


    static class Item implements Comparable<Item> {
        int w, v;

        Item(int w, int v) {
            this.w = w;
            this.v = v;
        }

        @Override
        public int compareTo(Item o) { /***/
            if (this.w == o.w) return this.v - o.v;
            else return this.w - o.w; // 가벼운 거
        }

    }


    static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

}