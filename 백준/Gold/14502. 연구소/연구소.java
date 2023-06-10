import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int lab[][];
    static int height, width, safety_zone, tmp_safety, border, answer = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        lab = new int[height][width];

        safety_zone = -3;
        for (int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < width; j++) {
                int type = Integer.parseInt(st.nextToken());
                if (type == 0) safety_zone++;
                lab[i][j] = type;
            }
        }

        border = 3;
        makeWall();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(answer + "");
        bw.flush();
        bw.close();
    }

    private static void makeWall() {
        int size = height * width;

        for (int fst = 0; fst < size - 2; fst++) {
            int fst_w = lab[fst / width][fst % width];
            if ((fst_w == 0) || ((fst_w > 2) && (fst_w < border)))
                lab[fst / width][fst % width] = 1;
            else continue;

            for (int snd = fst + 1; snd < size - 1; snd++) {
                int snd_w = lab[snd / width][snd % width];
                if ((snd_w == 0) || ((snd_w > 2) && (snd_w < border)))
                    lab[snd / width][snd % width] = 1;
                else continue;

                for (int trd = snd + 1; trd < size; trd++) {
                    int trd_w = lab[trd / width][trd % width];
                    if ((trd_w == 0) || ((trd_w > 2) && (trd_w < border)))
                        lab[trd / width][trd % width] = 1;
                    else continue;

                    tmp_safety = safety_zone;

                    infect();

                    border++;
                    lab[trd / width][trd % width] = 0;
                }
                lab[snd / width][snd % width] = 0;
            }
            lab[fst / width][fst % width] = 0;
        }
    }

    private static void infect() {
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++)
                if (lab[x][y] == 2) DFS(x, y);
        }
        
        /**
        int result = 0;
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (lab[x][y] == 0) result++;
                else if (lab[x][y] == 3) lab[x][y] = 0;
            }
        }
         **/

        answer = Math.max(answer, tmp_safety);
    }

    private static void DFS(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (isVaildPos(nx, ny)) {
                int zone = lab[nx][ny];
                if ((zone == 0) || ((zone > 2) && (zone < border))) {
                    lab[nx][ny] = border;
                    tmp_safety--;

                    DFS(nx, ny);

                    /*lab[nx][ny] = 0;*/
                }
            }
        }
    }

    private static boolean isVaildPos(int x, int y) {
        return (x >= 0 && x < height && y >= 0 && y < width);
    }

}