import java.io.*;

public class Main{
    static final int MAX_VALUE=1000000;
        
    public static void main(String[] args){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            
            /**체 실행해서 미리 배열 생성**/
            long[] fy = new long[MAX_VALUE+1];
            //Arrays.fill(array, 1);

            for (int i = 2; i <= MAX_VALUE; i++) {
                //i <= Math.sqrt(MAX_VALUE)
                for (int j = 1; i * j <= MAX_VALUE; j++) {
                    fy[i*j] += i;
                }
                fy[i] = fy[i - 1] + fy[i]; //최적화 불가능
            }
            
            /**조건 수행**/
            //테스트 개수
            int N = Integer.parseInt(br.readLine());
            int n;
            for(int i=0;i<N;i++) {
                n = Integer.parseInt(br.readLine());
                bw.write((fy[n]+n) + "\n");
            }
            //while(n-- > 0) bw.write(fy[Integer.parseInt(br.readLine())] + "\n");
            
            bw.flush();
            bw.close();
        }catch(IOException e) {return;}
    }
    
}