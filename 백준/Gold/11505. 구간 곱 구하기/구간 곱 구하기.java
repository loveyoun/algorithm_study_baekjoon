import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final long MOD = 1000000007;
    static long[] tree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());  // 변경 횟수
        int k = Integer.parseInt(st.nextToken());  // 연산 횟수

        int level = 0;
        while ((1 << level) < n) level++;
        int treeSize = (int) Math.pow(2, level + 1);  // 8개면 4층까지 (2 ^ 0 ~ 2 ^ 3) : 15개 -> 2 ^ 4
        int indexing = treeSize / 2 - 1;  // + 7 인덱싱

        tree = new long[treeSize];  // 1 ~ 15 번 사용
        Arrays.fill(tree, 1);
        for (int i = indexing + 1; i <= indexing + n; i++)
            tree[i] = Long.parseLong(br.readLine());


        setTree(treeSize - 1);


        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            int s = Integer.parseInt(st.nextToken()) + indexing;
            long e = Long.parseLong(st.nextToken());

            // 변경
            if (a == 1) changeValZero(s, e);
            else if (a == 2) sb.append(getTimes(s, (int) e + indexing)).append("\n");
            else break;

        }

        System.out.print(sb);
    }


    private static void changeVal(int i, long new_val) {
        long origin = tree[i] % MOD;

        while (i > 0) {
            tree[i] = (tree[i] / origin * new_val) % MOD;
            i /= 2;
        }

    }

    private static void changeValZero(int i, long new_val) {
        tree[i] = new_val;

        while (i > 0) {
            if (i % 2 == 1) tree[i / 2] = (tree[i] * tree[i - 1]) % MOD;
            else tree[i / 2] = (tree[i] * tree[i + 1]) % MOD;

            i /= 2;
        }

    }

    private static long getTimes(int s, int e) {
        long partTime = 1;

        while (s <= e) {
            if (s % 2 == 1) {  // 선택
                partTime = (partTime * tree[s]) % MOD;
                s++;
            }
            if (e % 2 == 0) {  // 선택
                partTime = (partTime * tree[e]) % MOD;
                e--;
            }

            s /= 2;
            e /= 2;
        }

        return partTime;
    }


    private static void setTree(int i) {
        while (i != 1) {
            tree[i / 2] = (tree[i / 2] * tree[i]) % MOD;  // MAX 값과 같아서 0이 되어도 어차피 곱하는 것이니 상관 없음
            i--;
        }

    }

}