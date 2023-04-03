import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        /** 1931_Algorithm_flow_회의실 배정 : 그리디
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] meetings = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            //시작시간
            meetings[i][0] = Integer.parseInt(st.nextToken());
            //끝나는시간
            meetings[i][1] = Integer.parseInt(st.nextToken());
        }
        /** Arrays.sort(arr, new Comparator<>() {compare()} ); **/
        Arrays.sort(meetings, new Comparator<int[]>() {
            public int compare(int[] M1, int[] M2) {
                // 종료 시간이 같으면, 시작 시간 기준 오름차순 정렬(1시 시작 -> 2시 시작)
                if (M1[1] == M2[1]) return M1[0] - M2[0];
                // 종료 시간 기준으로 오름차순 정렬
                return M1[1] - M2[1];
            }
        });

        /** 빨리 끝나는 회의가 중요하다
         *  회의 선정 기준 : '전에 끝나는 시간 이상에 시작하는 회의 + 그 다음 빨리 끝나는 회의'
         **/
        int count = 0;
        int end = -1;
        for (int i = 0; i < N; i++) {
            if (meetings[i][0] >= end) {
                end = meetings[i][1];
                count++;
            }
        }

        System.out.println(count);
    }

}