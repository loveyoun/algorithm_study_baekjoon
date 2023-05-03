import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Character[] std_str = new Character[2 * N + 1];
        int std_len = std_str.length;
        int[] std_count = new int[std_len];
        for (int i = 0; i < std_len - 1; i++) {
            if (i % 2 == 0) std_str[i] = 'I';
            else std_str[i] = 'O';
            std_count[i] = i + 1;
        }
        std_str[std_len - 1] = 'I';
        std_count[std_len - 1] = std_len;

        int M = Integer.parseInt(br.readLine());
        Character[] test = new Character[M];
        String test_tmp = br.readLine();
        for (int i = 0; i < M; i++)
            test[i] = test_tmp.charAt(i);

        int count = 0;
        int index = 0;
        for (int i = 0; i < M; i++) {
            if (test[i] == std_str[index]) {
                //if (std_count[index] == std_len) count++;
                index += 1;
            } else {
                /* 다를 떄
                IOI -> IOIIOI 일 때
                4번 째 I에서 넘어가면 안됨.
                i--;
                그럼, OOIO일 때 처음에서 계속 iteration 도는데, 처리는?
                 */
                index = 0;
                if (std_str[index] == test[i]) i--;
            }

            if (index == std_len) {
                index = std_len - 2;
                count++;
            }
        }

        bw.write(count + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

}