import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Character[] ptr = new Character[2 * n + 1];
        int ptrlen = ptr.length;
        for (int i = 0; i < ptrlen - 1; i++) {
            if (i % 2 == 0) ptr[i] = 'I';
            else ptr[i] = 'O';
        }
        ptr[ptrlen - 1] = 'I';

        int m = Integer.parseInt(br.readLine());
        String text = br.readLine();
        //for (int i = 0; i < m; i++) Character[] ptr[i] = text.charAt(i);


        int result = 0, j = 0;
        for (int i = 0; i < m; i++) {
            if (ptr[j] == text.charAt(i)) { // 일치
                if (++j == ptrlen) {
                    j = ptrlen - 2;
                    result++;
                }
            } else { // 불일치
                j = 0;
                
                /* 
                IOI'I'OI 일 때, 4번째 I에서 무시하면 안 됨. 새 시작점이 될 수 있다.
                  I'O'I
                   'I'OI
                그럼, OOIO일 때 처음에서 계속 iteration 도는데, 처리는?
                 */
                //if (ptr[j] == text.charAt(i)) i--;
                if (text.charAt(i) == 'I') i--;
            }

        }


        bw.write(result + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

}