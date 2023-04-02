import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws NumberFormatException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long A[] = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) A[i] = Long.parseLong(st.nextToken());
        Arrays.sort(A);
        
        int Result = 0;
        for(int k=0;k<N;k++){
            long find = A[k];
            int i=0; int j = N-1;
            while(i<j){
                if(A[i] + A[j] == find){
                   //서로 다른 두 수의 합. 본인 자신이 되면 안 됨.
                   if(i != k && j != k){
                       Result++; break;
                   }else if(i == k) i++;    //A[K]과 0
                   else if(j == k) j--;     //0과 A[K]
                }else if(A[i] + A[j] < find) i++;
                else j--;
            }
        }
        
        System.out.println(Result);
        br.close();
    }
}