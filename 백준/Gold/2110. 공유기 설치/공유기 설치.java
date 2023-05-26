import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int C;
    static int[] houses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        houses = new int[N];
        for (int i = 0; i < N; i++)
            houses[i] = Integer.parseInt(br.readLine());
        Arrays.sort(houses);

        // 첫 집에 설치해야 최대를 만들 확률이 높아지지 않을까
        int result = binarySearch(1, houses[N - 1] - houses[0] + 1);
        System.out.println(result);
    }

    static int binarySearch(int left, int right) {
        while (left < right) {
            int mid = (left + right) / 2;

            // o o o S(^) ??? E(x) x x x
            if (countRouter(mid) < C) right = mid;
            else left = mid + 1;
        }

        // o o o(S-1) S==E(x) x x x
        return left - 1;
    }

    static int countRouter(int distance) {
        int count = 1;
        int recent_house = houses[0];

        for (int i = 1; i < N; i++) {
            int new_house = houses[i];
            if (new_house - recent_house >= distance) {
                count++;
                recent_house = new_house;
            }
        }

        return count;
    }

}