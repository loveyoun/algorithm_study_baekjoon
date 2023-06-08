import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] words = new int[N][9];
        AlphaNum[] compare = new AlphaNum[26];
        for (int i = 0; i < 26; i++)
            compare[i] = new AlphaNum(i, 0);

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            int len = s.length(); // ex> 4 : 5 6 7 8, 8 : 1 ~ 8
            for (int j = 0; j < len; j++) {
                int tmp = s.charAt(j);
                words[i][9 - len + j] = tmp; // A == 65, 0 == 48 // cipher = len - j - 1
                compare[tmp - 65].setNumber((int) Math.pow(10, len - j - 1));
            }
        }
        Arrays.sort(compare, (o1, o2) -> {
            return o2.number - o1.number;
        });

        int[] alphabet = new int[26];
        int coefficient = 9;
        //for (int i = 0; i < 26; i++) alphabet[i] = -1;
        for (int i = 0; i < 26; i++) {
            int alpha = compare[i].alphabet; // A == 0
            alphabet[alpha] = coefficient--;
        }


        int answer = 0;
        for (int j = 1; j < 9; j++) { // 9 - i 째 자리
            for (int i = 0; i < N; i++) {
                int a = words[i][j];
                if (a != 0) answer += Math.pow(10, 8 - j) * alphabet[a-65];

                /*
                if (a != 0) {
                    if (alphabet[a - 65] == -1) { // 지정되지 않았을 때
                        answer += Math.pow(10, 8 - j) * coefficient;
                        alphabet[a - 65] = coefficient--;
                    } else answer += Math.pow(10, 8 - j) * alphabet[a - 65];
                }
                 */
            }
        }

        System.out.println(answer);
    }

    static class AlphaNum {
        int alphabet;
        int number;

        AlphaNum(int alphabet, int number) {
            this.alphabet = alphabet;
            this.number = number;
        }

        public void setNumber(int number) {
            this.number += number;
        }
    }

}