import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static Point[] houses_loc, chickens_loc;
    static boolean[] selected;
    static int N, M, chickens, houses, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        houses_loc = new Point[2 * N];
        chickens_loc = new Point[13];

        houses = 0;
        chickens = 0;
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                int tmp = Integer.parseInt(st.nextToken());

                if (tmp == 2) chickens_loc[chickens++] = new Point(r, c);
                else if (tmp == 1) houses_loc[houses++] = new Point(r, c);
            }
        }


        selected = new boolean[chickens];
        result = Integer.MAX_VALUE;

        // 치킨 집 고르기
        for (int i = 0; i < chickens; i++)
            selectChickens(i, 1);


        System.out.println(result);
    }

    static void selectChickens(int idx, int count) {
        if (selected[idx]) return;

        selected[idx] = true;
        if (count == M) {
            int tmp_min = 0;
            for (int j = 0; j < houses; j++) {
                Point house = houses_loc[j];
                tmp_min += fromHouse(house.x, house.y);
            }

            result = Math.min(result, tmp_min);

            selected[idx] = false;
            return;
        }

        for (int i = 1; idx + i < chickens; i++)
            selectChickens(idx + i, count + 1);

        selected[idx] = false;
    }

    static int fromHouse(int x, int y) {
        int min = Integer.MAX_VALUE;

        // 치킨 집들과 한 집의 거리
        for (int i = 0; i < chickens; i++) {
            if (selected[i]) {
                int chic_x = chickens_loc[i].x;
                int chic_y = chickens_loc[i].y;

                int dist = Math.abs(x - chic_x) + Math.abs(y - chic_y);
                min = Math.min(min, dist);
            }
        }

        return min;
    }


    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}