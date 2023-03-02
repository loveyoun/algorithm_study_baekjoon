import java.io.*;

public class Main{
    
    public static void main(String[] args){
        try{
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();

            String nm[] = input.readLine().split(" ");       
            int M = Integer.parseInt(nm[0]);
            int N = Integer.parseInt(nm[1]);
            int[] A = new int[N+1];
            for(int i=2;i<=N;i++) A[i] = i;

            for(int i=2;i<=N;i++){
                if(A[i] == 0) continue; //배수이면, 
                else{ //소수이면,
                    if(i >= M) sb.append(i+"\n");
                    for(int j=i+i;j<=N;j+=i) A[j] = 0; //소수 아닌 거(소수들의 배수) 지우기
                }
            }

            System.out.println(sb);
        }catch(IOException e){return;}
    }
    
}