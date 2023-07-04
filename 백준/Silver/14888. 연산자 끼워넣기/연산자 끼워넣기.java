import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[] num, opr, formula;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        num = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) num[i] = Integer.parseInt(st.nextToken());

        opr = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) opr[i] = Integer.parseInt(st.nextToken());


        formula = new int[n - 1];
        makeFormula(0);

        System.out.println(max + "\n" + min);
    }

    static void makeFormula(int idx) {
        if (idx == n - 1) { /** Base case **/
            int result = num[0];

            for (int i = 0; i < n - 1; i++) {
                int tmp = formula[i];

                if (tmp == 0) result += num[i + 1];
                else if (tmp == 1) result -= num[i + 1];
                else if (tmp == 2) result *= num[i + 1];
                else {
                    boolean flag = false;
                    if (result < 0) flag = true;
                    result = Math.abs(result) / num[i + 1];
                    if (flag) result = -result;
                }
            }

            max = Math.max(max, result);
            min = Math.min(min, result);

            return;
        }

        for (int i = 0; i < 4; i++) {
            if (opr[i] == 0) continue;

            formula[idx] = i;
            opr[i]--;

            makeFormula(idx + 1);

            opr[i]++;
        }
    }

}