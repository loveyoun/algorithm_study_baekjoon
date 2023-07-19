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
        int m = Integer.parseInt(st.nextToken());  // 변경 횟수
        int k = Integer.parseInt(st.nextToken());  // 연산 횟수

        int level = 0;
        while ((1 << level) < n) level++;
        int treeSize = (int) Math.pow(2, level + 1);  // 8개면 4층까지 (2 ^ 0 ~ 2 ^ 3) : 15개 -> 2 ^ 4
        int indexing = treeSize / 2 - 1;  // + 7 인덱싱

        tree = new long[treeSize];  // 1 ~ 15 번 사용
        for (int i = indexing + 1; i <= indexing + n; i++)
            tree[i] = Long.parseLong(br.readLine());


        setTree(treeSize - 1);


        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            long e = Long.parseLong(st.nextToken());

            // 변경
            if (a == 1) changeVal(indexing + s, e);
            // 구간 합
            else if (a == 2) {
                s = s + indexing;
                e = e + indexing;

                sb.append(getSum(s, (int) e)).append("\n");
            } else break;

        }

        System.out.println(sb);
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
            if (s % 2 == 1) {  // 선택
                partSum += tree[s];
                s++;
            }
            if (e % 2 == 0) {  // 선택
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
            tree[i / 2] += tree[i];  // 8, 9 -> 4
            i--;
        }

    }

}