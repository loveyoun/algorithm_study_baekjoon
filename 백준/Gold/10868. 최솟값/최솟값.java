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
        int m = Integer.parseInt(st.nextToken());  // 연산 횟수

        int level = 0;
        while ((1 << level) < n) level++;
        int treeSize = (int) Math.pow(2, level + 1);   // 8개면 4층까지 (2 ^ 0 ~ 2 ^ 3) : 15개 -> 2 ^ 4
        int indexing = treeSize / 2 - 1;   // + 7 인덱싱

        tree = new long[treeSize];   // 1 ~ 15 번 사용
        for (int i = indexing + 1; i <= indexing + n; i++)
            tree[i] = Long.parseLong(br.readLine());


        setTree(treeSize - 1);


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) + indexing;
            int e = Integer.parseInt(st.nextToken()) + indexing;

            sb.append(getMin(s, e)).append("\n");
        }

        System.out.println(sb);
    }


    private static long getMin(int s, int e) {
        long min = tree[s];

        while (s <= e) {
            if (s % 2 == 1)   // 포함
                min = Math.min(min, tree[s++]);

            if (e % 2 == 0)   // 포함
                min = Math.min(min, tree[e--]);
            
            s /= 2;
            e /= 2;
        }

        return min;
    }


    private static void setTree(int i) {
        while (i != 1) {   // 루트 노드 빼고
            tree[i / 2] = Math.min(tree[i], tree[i - 1]);  // 8, 9 -> 4
            i -= 2;
        }

    }

}