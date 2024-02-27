import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int L = (int) Math.pow(2, N); // 한 변 길이
        int[][] arr = new int[L][L];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < L; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        Tmp.fun(arr, L);

        for (int t = 0; t < R; t++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken()); // k번 연산
            int l = Integer.parseInt(st.nextToken()); // 2 ^ l
            int c = (int) Math.pow(2, N - l); // 부분배열 묶음. 2^N / 2^l

            switch (k) {
                case 1:
                    arr = cal1(L, l, c, arr);
                    break;
                case 2:
                    arr = cal2(L, l, c, arr);
                    break;
                case 3:
                    arr = cal3(L, l, c, arr);
                    break;
                case 4:
                    arr = cal4(L, l, c, arr);
                    break;
                case 5:
                    arr = cal5(L, l, arr);
                    break;
                case 6:
                    arr = cal6(L, l, arr);
                    break;
                case 7:
                    arr = cal7(L, l, c, arr);
                    break;
                case 8:
                    arr = cal8(L, l, c, arr);
                    break;
            }

//            Tmp.cal(k, l, L);
        }

        StringBuilder sb = new StringBuilder();
//        Boolean flag = true;
//        int[][] tmp = Tmp.getArr();
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
//                if (tmp[i][j] != arr[i][j]) flag = false;

                if (j == L - 1) sb.append(arr[i][j]).append("\n");
                else sb.append(arr[i][j]).append(" ");
            }
        }

//        if (flag) System.out.println("맞음");
//        else System.out.println("틀림");
        System.out.print(sb);
    }

    private static int[][] cal1(int L, int l, int c, int[][] arr) { // 30분...?
        if (l == 0) return arr;

        int s = (int) Math.pow(2, l); // size
        int[][] fin = new int[L][L];

        for (int i = 0; i < c; i++) { // 부분배열 개수만큼 반복횟수
            int x = i * s; // 시작점 x, y
            for (int j = 0; j < c; j++) {
                int y = j * s;

                // 부분 배열
                for (int p = x; p < x + s; p++) { // x  p   x+s-1-(p-x) x+s-1
                    for (int q = y; q < y + s; q++) {
//                        if (y + s - y >= 0) System.arraycopy(arr[p], y, fin[x + s - 1 - p], y, y + s - y);
                        fin[2 * x + s - 1 - p][q] = arr[p][q];
                    }
                }
            }
        }

        return fin;
    }

    private static int[][] cal2(int L, int l, int c, int[][] arr) {
        if (l == 0) return arr;

        int s = (int) Math.pow(2, l);
        int[][] fin = new int[L][L];

        for (int i = 0; i < c; i++) {
            int x = i * s; // 시작점 x, y
            for (int j = 0; j < c; j++) {
                int y = j * s;

                for (int p = x; p < x + s; p++) {
                    for (int q = y; q < y + s; q++) {
                        fin[p][2 * y + s - 1 - q] = arr[p][q];
                    }
                }
            }
        }

        return fin;
    }

    private static int[][] cal3(int L, int l, int c, int[][] arr) {
        if (l == 0) return arr;

        int s = (int) Math.pow(2, l); // size
        int[][] fin = new int[L][L];

        for (int i = 0; i < c; i++) { // 묶음 수
            int x = i * s; // 시작점 x, y
            for (int j = 0; j < c; j++) {
                int y = j * s;

                // L=8, l=0 s=1
                for (int k = s, m = 0, n = 0; k > 0; k -= 2, m++, n++) { // m, n: 새 기준점
                    int d = k - 1; // 8, 6, 4, 2

                    for (int t = 0, p = m, q = n; t < d; t++, q++)
                        fin[x + q][y + s - 1 - p] = arr[x + p][y + q];
                    for (int t = 0, p = m, q = n + d; t < d; t++, p++)
                        fin[x + q][y + s - 1 - p] = arr[x + p][y + q];
                    for (int t = 0, p = m + d, q = n + d; t < d; t++, q--)
                        fin[x + q][y + s - 1 - p] = arr[x + p][y + q];
                    for (int t = 0, p = m + d, q = n; t < d; t++, p--)
                        fin[x + q][y + s - 1 - p] = arr[x + p][y + q];
                }
            }
        }

        return fin;
    }

    private static int[][] cal4(int L, int l, int c, int[][] arr) {
        if (l == 0) return arr;

        int s = (int) Math.pow(2, l); // size. 8
        int[][] fin = new int[L][L];

        for (int i = 0; i < c; i++) {
            int x = i * s; // 시작점 x, y
            for (int j = 0; j < c; j++) {
                int y = j * s;

                for (int k = s, m = 0, n = 0; k > 0; k -= 2, m++, n++) {
                    int d = k - 1;
                    for (int t = 0, p = m, q = n; t < d; t++, q++)
                        fin[x + s - 1 - q][y + p] = arr[x + p][y + q];
                    for (int t = 0, p = m, q = n + d; t < d; t++, p++)
                        fin[x + s - 1 - q][y + p] = arr[x + p][y + q];
                    for (int t = 0, p = m + d, q = n + d; t < d; t++, q--)
                        fin[x + s - 1 - q][y + p] = arr[x + p][y + q];
                    for (int t = 0, p = m + d, q = n; t < d; t++, p--)
                        fin[x + s - 1 - q][y + p] = arr[x + p][y + q];
                }
            }
        }

        return fin;
    }

    private static int[][] cal5(int L, int l, int[][] arr) {
        int s = (int) Math.pow(2, l); // size
        int[][] fin = new int[L][L];

        // 전체 배열 -> 부분 배열 한 개
        for (int x = 0; x < L; x += s) { // x, y: 시작 점
            for (int y = 0; y < L; y += s) {
                for (int m = 0; m < s; m++) { // x  L: L-x-s
                    int k = L - x - s;
                    for (int n = 0; n < s; n++) {
                        fin[k + m][y + n] = arr[x + m][y + n];
                    }
                }
            }
        }

        return fin;
    }

    private static int[][] cal6(int L, int l, int[][] arr) {
        int s = (int) Math.pow(2, l); // size
        int[][] fin = new int[L][L];

        for (int x = 0; x < L; x += s) { // x, y: 시작 점
            for (int y = 0; y < L; y += s) {
                for (int m = 0; m < s; m++) { // y  L: L-y-s
                    int k = L - y - s;
                    for (int n = 0; n < s; n++) {
                        fin[x + m][y + n] = arr[x + m][k + n];
                    }
                }
            }
        }

        return fin;
    }

    private static int[][] cal7(int L, int l, int c, int[][] arr) {
        int s = (int) Math.pow(2, l);
        int[][] fin = new int[L][L];

        // L=8, 길이2 부분배열
        int n = c / 2; // 4쪽, 반 2쪽
        for (int k = 0; k < n; k++) { // 0, 1
            int x = k * s, y = k * s; // 기준점
            int d = 2 * (n - k) - 1;  // 2*2, 2*1 바람개비 날개 크기

            for (int t = 0, p = x, q = y; t < d; t++, q += s) {
                // x, y: 0,2 -> 2,6
                for (int i = 0; i < s; i++) {
                    for (int j = 0; j < s; j++)
                        fin[q + i][L - s - p + j] = arr[p + i][q + j];
                }
            }
            for (int t = 0, p = x, q = y + d * s; t < d; t++, p += s) {
                for (int i = 0; i < s; i++) {
                    for (int j = 0; j < s; j++)
                        fin[q + i][L - s - p + j] = arr[p + i][q + j];
                }
            }
            for (int t = 0, p = x + d * s, q = y + d * s; t < d; t++, q -= s) {
                for (int i = 0; i < s; i++) {
                    for (int j = 0; j < s; j++)
                        fin[q + i][L - s - p + j] = arr[p + i][q + j];
                }
            }
            for (int t = 0, p = x + d * s, q = y; t < d; t++, p -= s) {
                for (int i = 0; i < s; i++) {
                    for (int j = 0; j < s; j++)
                        fin[q + i][L - s - p + j] = arr[p + i][q + j];
                }
            }
        }

        return fin;
    }

    private static int[][] cal8(int L, int l, int c, int[][] arr) {
        int s = (int) Math.pow(2, l);
        int[][] fin = new int[L][L];

        // L=8, 길이4 부분배열
        int n = c / 2; // 2쪽, 반 1쪽
        for (int k = 0; k < n; k++) {
            int x = k * s, y = k * s; // 기준점
            int d = 2 * (n - k) - 1;  // 2*1 바람개비 날개 크기

            for (int t = 0, p = x, q = y; t < d; t++, q += s) {
                // x, y: 0,4 -> 4,4
                for (int i = 0; i < s; i++) {
                    for (int j = 0; j < s; j++)
                        fin[L - s - q + i][p + j] = arr[p + i][q + j];
                }
            }
            for (int t = 0, p = x, q = y + d * s; t < d; t++, p += s) {
                for (int i = 0; i < s; i++) {
                    for (int j = 0; j < s; j++)
                        fin[L - s - q + i][p + j] = arr[p + i][q + j];
                }
            }
            for (int t = 0, p = x + d * s, q = y + d * s; t < d; t++, q -= s) {
                for (int i = 0; i < s; i++) {
                    for (int j = 0; j < s; j++)
                        fin[L - s - q + i][p + j] = arr[p + i][q + j];
                }
            }
            for (int t = 0, p = x + d * s, q = y; t < d; t++, p -= s) {
                for (int i = 0; i < s; i++) {
                    for (int j = 0; j < s; j++)
                        fin[L - s - q + i][p + j] = arr[p + i][q + j];
                }
            }
        }

        return fin;
    }

}