import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        /** 11726_Algorithm flow: 점화식. Dynamic Programming,
         * 나머지 정리 : A = B * Q + R일 때
         * A * x + y = (B * Q + R) * x + y = B * Q * x + (R * x + y) = B * Q * x + (B * Q1 + R1) = B * () + R1
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] tiles = new int[1001];
        tiles[1] = 1;
        tiles[2] = 2;

        for (int i = 3; i <= N; i++) {
            tiles[i] = (tiles[i - 1] + tiles[i - 2]) % 10007;
        }

        System.out.println(tiles[N]);
    }

}