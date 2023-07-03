// 내 풀이

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static char[] alpha, arr;
    static boolean[] visited;
    static int l, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());  // 문자열 길이
        c = Integer.parseInt(st.nextToken());  // 가진 문자들

        alpha = new char[c + 1]; // 1 ~ c
        visited = new boolean[c + 1];
        arr = new char[l];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= c; i++) alpha[i] = st.nextToken().charAt(0);  // 'a' == 97
        Arrays.sort(alpha);


        recur(0, 1, 0, 0);

        System.out.print(sb);
    }

    public static void recur(int len, int idx, int vowel, int con) { // (길이, 알파벳, 모음, 자음)
        if (len == l) {
            if (vowel >= 1 && con >= 2) sb.append(arr).append("\n");
            return;
        }

        for (int asc = idx; asc <= c; asc++) {
            char tmp = alpha[asc];
            int v = vowel;
            int k = con;

            if (tmp == 'a' || tmp == 'e' || tmp == 'i' || tmp == 'o' || tmp == 'u') v++;
            else k++;

            arr[len] = tmp;
            recur(len + 1, asc + 1, v, k);
        }

    }

}