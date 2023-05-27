import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_LENGTH = 1000001;
    static int N, maxLength;
    static int[] arr = new int[MAX_LENGTH];
    static int[] fake_LIS = new int[MAX_LENGTH];
    static int[] max_len = new int[MAX_LENGTH];
    static int[] answer = new int[MAX_LENGTH];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());


        fake_LIS[++maxLength] = arr[1];
        max_len[1] = 1;

        for (int i = 2; i <= N; i++) {
            if (fake_LIS[maxLength] < arr[i]) {
                fake_LIS[++maxLength] = arr[i];
                max_len[i] = maxLength;
            } else {
                int index = binarySearch(1, maxLength, arr[i]);

                // 즉시 치.환.
                fake_LIS[index] = arr[i];

                // index 는 길이이자 내 값이 들어간 인덱스 
                // -> 그래서 0 이 아니라 1 부터 시작했군. 0 이었어도 maxLength만 +1 해주면 됨.
                max_len[i] = index;
            }
        }
        System.out.println(maxLength);


        int tmp = maxLength;
        int the_before = fake_LIS[maxLength] + 1;
        for (int i = N; i >= 1; i--) {
            if (max_len[i] == tmp && arr[i] < the_before) {
                answer[tmp] = arr[i];

                the_before = arr[i];
                tmp--;
            }
        }

        for (int i = 1; i <= maxLength; i++)
            System.out.print(answer[i] + " ");
    }

    // 탐색해야겠다... 정렬되어있네? -> 즉.시. 이분 탐색
    static int binarySearch(int left, int right, int now) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (fake_LIS[mid] < now) left = mid + 1;
            else right = mid;
        }

        // 처음으로 now <= fake_LIS[left]
        return left;
    }

}