import java.io.*;
import java.util.*;

public class Main{
    static boolean[] nonprime;
    static int N;
    
    public static void main(String[] args){
        eratos(1000000);
        
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            
            N = Integer.parseInt(br.readLine());
            while(N != 0){
                //eratos(N);
                boolean flag=false;
               
                for(int i=2;i<=N/2;i++){ 
                    if(!nonprime[i] && !nonprime[N-i]){ //if 중첩 필요 없음
                        //sb.append(N +" = "+ i +" + "+ (N-i));
                        System.out.println(N +" = "+ i +" + "+ (N-i));
                        flag=true;
                        break;
                    }
                    //ㅇㅈㄹ...if(!flag) System.out.println("Goldbach's conjecture is wrong.");
                }
                if(!flag) System.out.println("Goldbach's conjecture is wrong.");
                
                N = Integer.parseInt(br.readLine());
            }
        }catch(IOException e){return;}
    }
    
    static void eratos(int N){
        nonprime = new boolean[N+1]; //false로 초기화

        for(int i=2;i<=Math.sqrt(N);i++){ //에라토스테네스의 체 최적화
            if(nonprime[i]) continue; //배수이면, 
            //소수이면,
            for(int j=i+i;j<=N;j+=i) nonprime[j] = true; //소수 아닌 거(소수들의 배수) 지우기          
        }
    }
    
}