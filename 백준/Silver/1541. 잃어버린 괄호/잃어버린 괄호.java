import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "-");
        
        int answer = 0;
        answer += mySum(st.nextToken());
        while(st.hasMoreElements()){
            int temp = mySum(st.nextToken());
            answer -= temp;
        }
        
        System.out.println(answer);
    }
    
    
    static int mySum(String a){
        int sum = 0;
        String temp[] = a.split("\\+");
        for(int i=0;i<temp.length;i++) sum += Integer.parseInt(temp[i]);
        
        return sum;
    }
}