import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, c;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());


        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        dfs(0, n / 2 - 1, 0, left);
        dfs(n / 2, n - 1, 0, right);

        Collections.sort(left);
        Collections.sort(right);   // 투포인터 전제 : 정렬


        int ans = 0;
        int e = right.size() - 1;
        for (Integer num : left) {
            while (e >= 0 && num + right.get(e) > c)
                e--;
            ans += e + 1;
        }

        System.out.println(ans);
    }

    public static void dfs(int s, int e, int sum, ArrayList<Integer> list) {
        if (sum > c) return;
        if (s > e) {
            list.add(sum);
            return;
        }

        dfs(s + 1, e, sum, list);
        dfs(s + 1, e, sum + arr[s], list);
    }

}