import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 보려는 채널
        int M = Integer.parseInt(br.readLine()); // 고장난 버튼 개수 M
        int min = Math.abs(100 - N);

        /* Null Pointer */
        if (M == 0) {
            System.out.println(Math.min(min, Integer.toString(N).length()));
            return;
        }
        String[] tmp = br.readLine().split(" ");
        boolean[] broken = new boolean[10]; // 0 ~ 9
        for (int i = 0; i < M; i++) broken[Integer.parseInt(tmp[i])] = true;


        for (int num = 0; num < 1000000; num++) {
            boolean cantReach = false;

            String snum = Integer.toString(num); //String.valueOf(num);
            int len = snum.length();

            for (int i = 0; i < len; i++) {
                if (broken[snum.charAt(i) - '0']) {
                    cantReach = true;
                    break;
                }
            }

            if (!cantReach) min = Math.min(min, (len + Math.abs(N - num)));
        }


        System.out.println(min);
    }

}