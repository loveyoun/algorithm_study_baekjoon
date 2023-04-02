import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        /**17298_Algorithm_flow_오큰수: 스택
         * **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] NGEs = new int[N];
        String[] str = br.readLine().split(" ");
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(str[i]);

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        /** 기작
         *  index 1 2 3 4 
         *  if 9 1 2 5 -> -1 5 5 -1
         *  index 2까지만 빼주어야 함 == 스택 기작
         **/
        /* 스택 값 : index 값들
         * if 스택에 i값 넣고 arr[i++]와 비교하면 전수비교 하는 거
         * 버블비교와 스택 이용
         * 스택에 오큰수 대기 index 들 넣기
         * 다음거와 비교 -> 작으면 push : 스택에는 내림차순으로 쌓이고 있음
         * 비교했을 때 크면 작은 거 나올 때까지 계속 pop
         * ex) 9 5 4 3 8
         * 1 2 3 4 5 -> 1 5
         */
        for (int i = 1; i < N; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i])
                NGEs[stack.pop()] = arr[i];
            stack.push(i);
        }
        //-1인 인덱스들
        while (!stack.empty()) NGEs[stack.pop()] = -1;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++)
            bw.write(NGEs[i] + " ");
        bw.write("\n");
        bw.flush();
    }

}