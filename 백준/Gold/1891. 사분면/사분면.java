import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static long lr, ud;
    static long x, y, dx, dy, n, sqr; // [x, y] -> [dx, dy]
    static String answer;
    static boolean notPossible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        String before = st.nextToken();

        st = new StringTokenizer(br.readLine());
        lr = Long.parseLong(st.nextToken());
        ud = -Long.parseLong(st.nextToken());

        x = 0;
        y = 0;
        int len = before.length();
        n = 1L << N - 1;
        for (int i = 0; i < len; i++) {
            int plane = before.charAt(i) - '0';
            findCoordinates(plane);
        }


        dx = x + lr;
        dy = y + ud;

        answer = "";
        sqr = 1L << N - 1;
        notPossible = false;
        if (dx < 0 || dx >= 2 * sqr || dy < 0 || dy >= 2 * sqr) notPossible = true;
        else findPlanes(sqr, sqr);

        if (notPossible) System.out.println(-1);
        else System.out.println(answer);
    }

    static void findCoordinates(int plane) {
        if (plane == 1) {
            x += n;
        } else if (plane == 3) {
            y += n;
        } else if (plane == 4) {
            x += n;
            y += n;
        }

        n >>= 1;
    }

    static void findPlanes(long mx, long my) {
        if (sqr == 0) return;

        sqr >>= 1;
        if (dx >= mx && dy < my) {
            mx += sqr;
            my -= sqr;
            answer += "1";
        } else if (dx < mx && dy < my) {
            mx -= sqr;
            my -= sqr;
            answer += "2";
        } else if (dx < mx) {
            mx -= sqr;
            my += sqr;
            answer += "3";
        } else {
            mx += sqr;
            my += sqr;
            answer += "4";
        }

        findPlanes(mx, my);
    }

}