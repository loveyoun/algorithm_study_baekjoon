import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        /**11758_Algorithm_flow_CCW: 외적(기하)
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] xs = new int[4];
        int[] ys = new int[4];
        for (int i = 1; i <= 3; i++) {
            String[] tmp = br.readLine().split(" ");
            xs[i] = Integer.parseInt(tmp[0]);
            ys[i] = Integer.parseInt(tmp[1]);
        }
        ys[0] = ys[3];

        int result = 0;
        for (int i = 1; i <= 3; i++) result += xs[i] * ys[(i + 1) % 3];
        for (int i = 1; i <= 3; i++) result -= xs[i] * ys[(i - 1) % 3];

        int answer = result > 0 ? 1 : -1;
        if (result == 0) answer = 0;

        System.out.println(answer);
    }

}