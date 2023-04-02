import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[][] A = new int[N][2];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            //시작시간
            A[i][0] = Integer.parseInt(st.nextToken());
            //끝나는시간
            A[i][1] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(A, new Comparator<int[]>(){
            public int compare(int[] S, int[] E){
                if(S[1] == E[1]) return S[0] - E[0];
                return S[1] - E[1];
            }
        });
        
        int count = 0;
        int end = -1;
        for(int i=0;i<N;i++){
            if(A[i][0] >= end){
                end = A[i][1];
                count ++;
            }
        }
        
        System.out.println(count);
    }
}