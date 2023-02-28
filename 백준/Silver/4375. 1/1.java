import java.io.*;

public class Main{
    
    public static void main(String[] args){
        InputStreamReader st = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(st);
		String s;
        int n;
        try{
            while((s=br.readLine())!= null){
                n = Integer.parseInt(s);
                int ones=1;
                int fig=1;
                ones = ones%n;    //n==1일 때 유의
                while(ones != 0){    
                    ones = (ones*10+1)%n; //나머지 값 넣어주기
                    fig++;
                }
                System.out.println(fig);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}