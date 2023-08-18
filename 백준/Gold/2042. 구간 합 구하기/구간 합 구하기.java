import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] tree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());   // 변경 횟수
        int k = Integer.parseInt(st.nextToken());   // 연산 횟수

        int lev = 0;
        while ((1 << lev) < n) lev++;
        int treeSize = (int) Math.pow(2, lev + 1);   // 2³개면 4층까지 (1 +...+ 2³) : 15개 -> 0 ~ 2⁴ - 1 : 16개
        int indexing = treeSize / 2;   // + 8 인덱싱

        tree = new long[treeSize];   // 0 ~ 2⁴ - 1
        for (int i = indexing; i < indexing + n; i++)
            tree[i] = Long.parseLong(br.readLine());


        setTree(indexing + n - 1);   // treeSize - 1


        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            long e = Long.parseLong(st.nextToken());

            // 변경
            if (a == 1) changeVal(indexing + s - 1, e);
                // 구간 합
            else if (a == 2) {
                s = s + indexing - 1;
                e = e + indexing - 1;

                sb.append(getSum(s, (int) e)).append("\n");
            } else break;

        }

        System.out.print(sb);
    }

    private static void changeVal(int index, long val) {
        long diff = val - tree[index];
        while (index > 0) {  // 루트 노드까지
            tree[index] += diff;
            index /= 2;
        }

    }

    private static long getSum(int s, int e) {
        long partSum = 0;

        while (s <= e) {
            if (s % 2 == 1) {   // odd 선택
                partSum += tree[s];
                s++;
            }
            if (e % 2 == 0) {   // even 선택
                partSum += tree[e];
                e--;
            }

            s /= 2;
            e /= 2;
        }

        return partSum;
    }


    private static void setTree(int i) {
        while (i != 1) {
            tree[i / 2] += tree[i];   // 8, 9 -> 4
            i--;
        }

    }

}