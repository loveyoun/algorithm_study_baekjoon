import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long ans;
    public static int[] arr, tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken());


        tmp = new int[n + 1];
        ans = 0;
        mergeSort(1, n);


        System.out.print(ans);
    }

    private static void mergeSort(int s, int e) {
        if (e == s)
            return;

        int m = (s + e) >> 1;
        mergeSort(s, m);
        mergeSort(m + 1, e);

        //for (int i = s; i <= e; i++) tmp[i] = A[i];
        if (e - s + 1 >= 0) System.arraycopy(arr, s, tmp, s, e - s + 1);

        int p = s;
        int l = s;
        int r = m + 1;

        long cnt = 0;
        // s ... m  m+1 ... e
        while (l <= m && r <= e) {   // 두 그룹을 Merge 해주는 로직 : 투포인터
            if (tmp[l] > tmp[r]) {
                cnt += Math.abs(p - r);
                arr[p++] = tmp[r++];
            } else {
                cnt += Math.abs(p - l);
                arr[p++] = tmp[l++];
            }
        }

        // 남은 값 처리
        while (l <= m) {
            cnt += Math.abs(p - l);
            arr[p++] = tmp[l++];
        }
        while (r <= e) {
            cnt += Math.abs(p - r);
            arr[p++] = tmp[r++];
        }


        ans += cnt / 2;
    }

}