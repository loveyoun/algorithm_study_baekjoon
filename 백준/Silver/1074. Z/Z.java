import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long r, c;
    static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        r = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());

        result = 0;
        
        // 1 사분면 : 시작점, 3 사분면 : 중심점 반복.
        findPlane(0, 0, N - 1);

        System.out.println(result);
    }

    public static void findPlane(long start_r, long start_c, int level) {
        /** Base Case **/
        if (level == -1) return;

        /** Cast to Long **/
        long sqrt = 1L << level;
        long row = start_r + sqrt; //(long) (start_r + Math.pow(2, level));
        long col = start_c + sqrt;
        
        int plane;
        // 차례로 1, 2, 3, 4 사분면
        if (r < row && c < col) {
            plane = 1;
            row = start_r;
            col = start_c;
        } else if (r < row) { //&& c >= col
            plane = 2;
            row = start_r;
        } else if (c < col) { //&& r >= row
            plane = 3;
            col = start_c;
        } else {
            /*if (r == row && c == col) return;*/
            plane = 4;
        }

        //result += (long) (Math.pow(2, level) * Math.pow(2, level) * (plane - 1));
        result += sqrt * sqrt * (plane - 1);
        if (r == row && c == col) return;

        findPlane(row, col, level - 1);
    }

}