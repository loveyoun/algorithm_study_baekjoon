import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb;
    static int n, k, level, k_idx, k_level, k_group, k_siblings, p;
    static int[] tree, level_count, level_group, parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) break;
            k = Integer.parseInt(st.nextToken());

            tree = new int[n + 1];
            parent = new int[n + 1];
            level_count = new int[n + 1];
            level_group = new int[n + 1];

            level_count[1] = 1;
            level_group[1] = 1;
            p = 0;
            parent[1] = p; // root

            st = new StringTokenizer(br.readLine());

            level = 1;
            k_idx = 0;
            k_level = 0;
            k_group = 0;
            findCousins();
        }

        System.out.println(sb);
    }

    static void findCousins() {
        tree[1] = Integer.parseInt(st.nextToken());

        for (int i = 2; i <= n; i++) {
            tree[i] = Integer.parseInt(st.nextToken());

            // 자식이나 사촌
            if (tree[i] > tree[i - 1] + 1) {
                // 자식
                if (level_count[level - 1] < level_group[level] + 1) level++;

                // 사촌
                p++;
                level_count[level]++;
                level_group[level]++;
            }
            // 형제
            else level_count[level]++;

            parent[i] = p;
            if (tree[i] == k) k_idx = i;
        }

        k_siblings = 0;
        for (int i = 2; i <= n; i++) {
            int p1 = parent[i];
            int p2 = parent[k_idx];
            if (p1 != p2 && parent[p1] == parent[p2]) k_siblings++;
        }

        sb.append(k_siblings + "\n");
    }

}