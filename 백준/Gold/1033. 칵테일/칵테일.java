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

        int divisor;
        int lcm = 1;
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int origin = Integer.parseInt(st.nextToken());
            int pair = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            /* p = p / divisor; q = q / divisor; */

            // 전체 lcm 구하기
            divisor = gcd(p, q);
            int small_lcm = p / divisor * q;
            divisor = gcd(lcm, small_lcm);
            lcm = small_lcm / divisor * lcm;

            ratio_pair[origin].add(new Ingredient(pair, p, q));
            ratio_pair[pair].add(new Ingredient(origin, q, p));
        }

        august14 = new int[N];
        visited = new boolean[N];
        // 끊어진 노드를 고려하여
        for (int i = 0; i < N; i++) dfs(i);

        // 전체 최소공배수를 이용하여, 전체 최대공약수 구하기
        for (int i = 0; i < N; i++)
            lcm = gcd(lcm, august14[i]);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(august14[i] / lcm + " ");
        System.out.println(sb);
    }

    static void dfs(int idx) {
        /** visited 중요 **/
        visited[idx] = true;

        int size = ratio_pair[idx].size();
        for (int j = 0; j < size; j++) {
            Ingredient origin = ratio_pair[idx].get(j);

            int pair = origin.pair;
            if (!visited[pair]) {
                int p = origin.p;
                int q = origin.q;

                /* (a, b)이랑 (a, c) 재료 비율 맞춰주기
                  a  b   d
                  a'   c 
                  a*a' b*a' c*a d*a'
                 */
                if (august14[idx] != 0) {
                    int divisor = gcd(august14[idx], p);
                    int multiplier_4_p = august14[idx] / divisor;
                    int multiplier_4_august14 = p / divisor;
                    
                    for (int i = 0; i < N; i++)
                        august14[i] = august14[i] * multiplier_4_august14;

                    p = p * multiplier_4_p;
                    q = q * multiplier_4_p;
                }
                august14[idx] = p;
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