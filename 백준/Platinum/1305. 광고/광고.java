import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int l;
    static int[] table;
    static String text;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        l = Integer.parseInt(br.readLine());
        text = br.readLine();


        table = new int[l];
        setTable();

        
        //StringBuilder sb = new StringBuilder();
        //for (int i = 0; i < l - table[l - 1]; i++) sb.append(text.charAt(i));
        //System.out.print(sb.toString());

        System.out.println(l - table[l - 1]);
    }

    public static void setTable() {
        int i = 0;

        // i: 움직(앞), j: 뒤
        for (int j = 1; j < text.length(); j++) {
            while (i > 0 && text.charAt(j) != text.charAt(i))
                i = table[i - 1];

            if (text.charAt(j) == text.charAt(i))
                table[j] = ++i;
        }

    }

}