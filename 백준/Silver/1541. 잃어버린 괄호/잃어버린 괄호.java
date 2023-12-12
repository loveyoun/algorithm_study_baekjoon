import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "-");

        int answer = 0;
        /* anwer += Integer.parseInt(st.nextToken());
         * 오류 이유 : 처음이 10+34 - 10+20+30 ...이럴 수 있어서.
         */
        answer += sumPlus(st.nextToken());
        while (st.hasMoreElements()) {
            int tmpSum = sumPlus(st.nextToken());

            answer -= tmpSum;
        }


        System.out.println(answer);
    }


    static int sumPlus(String str) {
        int sum = 0;

        String[] arr = str.split("\\+");
        for (String s : arr) sum += Integer.parseInt(s);

        return sum;
    }


}