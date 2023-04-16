import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        /**1929_Algorithm_flow_소수 구하기: 정수론(에라토스테네스의 체)
         **/
        
        /*Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();*/
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        /*String nm[] = br.readLine().split(" ");       
        int M = Integer.parseInt(nm[0]);
        int N = Integer.parseInt(nm[1]);*/
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        /*int[] A = new int[N+1];
        for(int i=2;i<=N;i++) A[i] = i;*/
        boolean[] nonprime = new boolean[N + 1]; // 초기값: false

        for (int i = 2; i <= N; i++) {
            /** 에라토스테네스의 체 최적화 
             i <= Math.sqrt(N)
             **/
            // 배수이면, 
            if (nonprime[i]) continue;
            // 소수이면,
            if (i >= M) sb.append(i + "\n"); // 최적화하면 이 코드 못 씀
            for (int j = i + i; j <= N; j += i) nonprime[j] = true; // 소수 아닌 거(소수들의 배수) 지우기
        }
        
        System.out.println(sb);
        /**출력초과**/ //for(int i=2;i<=N;i++) if(!nonprime[i]) System.out.print(i+"\n");
    }

}