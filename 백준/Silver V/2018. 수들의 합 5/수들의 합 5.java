import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int sum = 1, s = 1, e = 1;
        int count = 1;  // case N
        while (e != N) {
            if (sum == N) {
                count++;
                e++;  // s++; -> e++;
                sum += e;
            } else if (sum > N) {
                sum -= s;
                s++;
            } else { // sum < N
                e++;
                sum += e;
            }
        }

        System.out.println(count);
    }

}