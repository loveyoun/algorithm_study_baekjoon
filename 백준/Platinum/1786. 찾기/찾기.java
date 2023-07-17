import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] table;
    static String text, ptr;
    static StringBuilder sb = new StringBuilder(), tmp = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        text = br.readLine();
        ptr = br.readLine();


        int len = ptr.length();
        table = new int[len];
        setTable();

        sb.append(kmp(len)).append("\n");
        sb.append(tmp);
        System.out.print(sb);
    }

    public static void setTable() {
        int i = 0;

        // i: 움직(앞), j: 뒤
        for (int j = 1; j < ptr.length(); j++) {
            while (i > 0 && ptr.charAt(j) != ptr.charAt(i))
                i = table[i - 1];

            if (ptr.charAt(j) == ptr.charAt(i))
                table[j] = ++i;
        }

    }

    public static int kmp(int len) {
        int count = 0;
        int j = 0;

        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != ptr.charAt(j))
                j = table[j - 1];

            if (text.charAt(i) == ptr.charAt(j)) {
                if (j == ptr.length() - 1) {
                    tmp.append(i + 2 - len).append(" ");
                    count++;
                    j = table[j];
                } else j++;
            }
        }

        return count;
    }

}