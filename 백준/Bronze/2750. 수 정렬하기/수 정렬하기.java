import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        for(int i=0;i<N;i++) A[i] = sc.nextInt();
        
        //버블 정렬        
        for(int i=0;i<N-1;i++){            //N-1번 반복
            for(int j=0;j<N-1-i;j++){    //j : 0 ~ N-1까지 (0,1), (1,2) ... (N-2, N-1)비교
                if(A[j] > A[j+1]){    //그 다음에는 0 ~ N-2까지 (0,1), (1,2) ... (N-3, N-2) 비교
                    int temp = A[j];    //... 0 ~ 1까지 => 총 N-1번 반복
                    A[j] = A[j+1];
                    A[j+1] = temp;
                }
            }
        }
        
        for(int i=0;i<N;i++) System.out.println(A[i]);
    }
}