import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) parent[i] = i;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            if (q == 0) union(n1, n2);
            else {
                if (isSame(n1, n2))
                    sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }

        System.out.print(sb);
//        for (int e : parent) System.out.print(e + " ");
    }

    static void union(int a, int b) {
        int ca = a; //pa = find(a);
        int cb = b; //pa = find(b);
        while (a != parent[a]) a = find(a);
        while (b != parent[b]) b = find(b);
        if (a != b) parent[b] = a;
        parent[cb] = a;
    }

    static boolean isSame(int a, int b) {
        while (a != parent[a]) a = find(a);
        while (b != parent[b]) b = find(b);

        return a == b; //find(a) == find(b);
    }

    static int find(int i) {
        return parent[i];
    }

}