import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int answer;
    public static int[] table;
    public static String all, ptr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        all = br.readLine();
        ptr = br.readLine();

        table = new int[ptr.length()];
        setTable();


        if (KMP() == 1) System.out.println(1);
        else System.out.println(0);
    }

    public static void setTable() {
        int j = 0;

        for (int i = 1; i < ptr.length(); i++) {
            while (j > 0 && ptr.charAt(i) != ptr.charAt(j))
                j = table[j - 1];

            if (ptr.charAt(i) == ptr.charAt(j))
                table[i] = ++j;
        }

    }

    public static int KMP() {
        int j = 0;

        for (int i = 0; i < all.length(); i++) {
            while (j > 0 && all.charAt(i) != ptr.charAt(j))
                j = table[j - 1];


            if (all.charAt(i) == ptr.charAt(j)) {
                if (j == ptr.length() - 1) {
                    return 1;
                } else j++;
            }
        }
        
        return 0;
    }

}