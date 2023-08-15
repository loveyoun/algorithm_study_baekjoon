import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int[] arr, tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        tmp = new int[n + 1];
        for (int i = 1; i <= n; i++) arr[i] = Integer.parseInt(br.readLine());


        mergeSort(1, n);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) sb.append(arr[i]).append("\n");

        System.out.print(sb);
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

        // s ... m  m+1 ... e
        while (l <= m && r <= e) {   // 두 그룹을 Merge 해주는 로직 : 투포인터
            if (tmp[l] > tmp[r])
                arr[p++] = tmp[r++];
            else
                arr[p++] = tmp[l++];

        }

        // 남은 값 처리
        while (l <= m) arr[p++] = tmp[l++];
        while (r <= e) arr[p++] = tmp[r++];

    }

}