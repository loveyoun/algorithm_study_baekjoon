import java.util.Scanner;

public class Main{
    
    public static void main(String[] args){
        int N; 
        int bag3 = 0, bag5 = 0;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        
        if(N%5 == 0) {System.out.print(N/5); return;}
        while((N/3) > 0){
            N = N - 3; bag3++;
            if((N%5) == 0) {System.out.print(N/5 + bag3); return;}
        }
        if(N == 0) System.out.print(bag3);
        else System.out.print(-1);
    }
    
}

