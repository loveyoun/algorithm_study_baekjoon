import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static ArrayList<Ingredient>[] ratio_pair;
    //static int[][] ratios;
    static int[] august14;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ratio_pair = new ArrayList[N];
        for (int i = 0; i < N; i++) ratio_pair[i] = new ArrayList<>();

        int divisor = 1;
        int lcm = 1;
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int origin = Integer.parseInt(st.nextToken());
            int pair = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            divisor = gcd(p, q);
            /* p = p / divisor; q = q / divisor; */

            divisor = gcd(p, q);
            int small_lcm = p / divisor * q;
            divisor = gcd(lcm, small_lcm);
            lcm = small_lcm / divisor * lcm;

            ratio_pair[origin].add(new Ingredient(pair, p, q));
            ratio_pair[pair].add(new Ingredient(origin, q, p));;
        }

        august14 = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) dfs(i);

        for (int i = 0; i < N; i++)
            lcm = gcd(lcm, august14[i]);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(august14[i] / lcm + " ");
        System.out.println(sb);
    }

    static void dfs(int origin) {
        /** 위치 중요 **/
        visited[origin] = true;

        for (int j = 0; j < ratio_pair[origin].size(); j++) {
            int pair = ratio_pair[origin].get(j).pair;
            if (!visited[pair]) {
                int p = ratio_pair[origin].get(j).p;
                int q = ratio_pair[origin].get(j).q;

                if (august14[origin] != 0) {
                    int divisor = gcd(august14[origin], p);
                    int multiplier_4_p = august14[origin] / divisor;
                    int multiplier_4_august14= p / divisor;
                    for (int i = 0; i < N; i++)
                        august14[i] = august14[i] * multiplier_4_august14;

                    p = p * multiplier_4_p;
                    q = q * multiplier_4_p;
                }
                august14[origin] = p;
                august14[pair] = q;

                dfs(pair);
            }
        }
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
            // gcd0 -> gcd1 -> ... -> gcd_base return -> ... -> return to gcd0
        else return gcd(b, a % b);
    }

    static class Ingredient {
        int pair;
        int p, q;

        Ingredient(int pair, int p, int q) {
            this.pair = pair;
            this.p = p;
            this.q = q;
        }
    }

}