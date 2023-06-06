import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb;
    static int n, k, p, k_parent, result, level;
    static int[] tree, parent, level_count, level_group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) break;
            k = Integer.parseInt(st.nextToken());

            tree = new int[n + 1]; // 루트 노드(1) ~ n번 까지, 모두 n 명
            parent = new int[n + 1];
            /* group + level => parent 정보로 한 번에
             level_count = new int[n + 1]; // 같은 레벨에 있는 친척 사람 수
             level_group = new int[n + 1]; // 같은 레벨에 있는 형제 그룹 수

             level_count[1] = 1;
             level_group[1] = 1;
             level = 1;
             */
            p = 0;
            parent[1] = p; // root

            k_parent = 0;

            st = new StringTokenizer(br.readLine());
            findCousins();
        }

        System.out.println(sb);
    }

    static void findCousins() {
        tree[1] = Integer.parseInt(st.nextToken());

        for (int i = 2; i <= n; i++) {
            tree[i] = Integer.parseInt(st.nextToken());

            // 다음 세대나 사촌
            if (tree[i] > tree[i - 1] + 1) {
                // 다음 세대로 넘어갈 때
                /* if (level_count[level - 1] < level_group[level] + 1) level++; */

                // 사촌
                /*
                 p++;
                 level_count[level]++;
                 level_group[level]++;
                 */

                // 레벨 사이의 경계인지 확인
                /*if (parent[i - 1] != parent[parent[i - 1] + 1])*/
                p++;
            }
            /* 형제 수 필요 없어짐
            else level_count[level]++;
             */

            parent[i] = p;

            if (tree[i] == k) k_parent = p;
        }


        result = 0;
        for (int i = 2; i <= n; i++) {
            int p1 = parent[i];
            
            if (p1 != k_parent && parent[p1] == parent[k_parent]) result++;
        }

        sb.append(result).append("\n");
    }

}