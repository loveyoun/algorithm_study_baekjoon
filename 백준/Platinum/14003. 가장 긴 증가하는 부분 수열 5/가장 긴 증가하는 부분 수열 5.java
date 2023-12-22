import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_LENGTH = 1_000_000 + 1;
    static int N;
    static int[] arr = new int[MAX_LENGTH];
    static int[] fakeLIS = new int[MAX_LENGTH];
    static int[] LISLen = new int[MAX_LENGTH];
    static int[] realLIS = new int[MAX_LENGTH];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());


        int size = 1;
        fakeLIS[size] = arr[1];
        LISLen[1] = 1;
        for (int i = 2; i <= N; i++) {
            if (fakeLIS[size] < arr[i]) { // 10 + 20
                fakeLIS[++size] = arr[i];
                LISLen[i] = size;
            } else {
                int idx = binarySearch(1, size, arr[i]);

                fakeLIS[idx] = arr[i]; // 10 -> 1
                LISLen[i] = idx;
            }

        }
        System.out.println(size);


        int idx = size;
        int pre = fakeLIS[size] + 1;
        for (int i = N; i >= 1; i--) {
            if (LISLen[i] == idx && arr[i] < pre) {
                realLIS[idx] = arr[i];

                pre = arr[i];
                idx--;
            }

        }


        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= size; i++)
            sb.append(realLIS[i]).append(" ");

        System.out.println(sb);
    }


    static private int binarySearch(int s, int e, int num) {
        while (s < e) {
            int mid = (s + e) / 2;

            if (fakeLIS[mid] < num)
                s = mid + 1;
            else e = mid;  // >= num
        }

        // x x x o o o
        // s e -> s(e)
        // 처음으로 num <= [s]
        return s;
    }


}