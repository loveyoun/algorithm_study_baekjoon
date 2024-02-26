import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr); // 오름차순

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int tar = Integer.parseInt(st.nextToken());
            if (binarySearch(tar) == tar) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }

        System.out.print(sb);
    }

    static int binarySearch(int tar) {
        int s = 0, e = N - 1;
        while (s < e) {
            int m = (s + e) >> 1;
            if (tar <= arr[m]) e = m; // s e tar tar
            else s = m + 1;
        }

        return arr[s];
    }

}