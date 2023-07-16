import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n;
    public static int[] table;
    public static String text, ptr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        ptr = br.readLine().replaceAll(" ", "");
        char[] tmp = br.readLine().replaceAll(" ", "").toCharArray();
        char[] txt = new char[2 * n - 1];
        for (int i = 0; i < 2 * n - 1; i++) txt[i] = tmp[i % n];
        text = String.valueOf(txt);


        table = new int[n];
        setTable();


        int result = kmp();

        StringBuilder sb = new StringBuilder();
//        int divisor = gcd(n, result);
//        sb.append(result / divisor);
//        sb.append('/');
//        sb.append(n / divisor);
        

        sb.append("1/" + n / result);
        System.out.print(sb);
    }

    public static void setTable() {
        int j = 0;

        // j: 움직(앞), i: 기준(뒤)
        for (int i = 1; i < ptr.length(); i++) {
            while (j > 0 && ptr.charAt(i) != ptr.charAt(j))
                j = table[j - 1];

            if (ptr.charAt(i) == ptr.charAt(j))
                table[i] = ++j;
        }

    }

    public static int kmp() {
        int count = 0;
        int j = 0;

        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != ptr.charAt(j))
                j = table[j - 1];

            if (text.charAt(i) == ptr.charAt(j)) {
                if (j == ptr.length() - 1) {
                    count++;
                    j = table[j];
                } else j++;
            }
        }

        return count;
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

}