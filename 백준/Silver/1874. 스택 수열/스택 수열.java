import java.util.Scanner;
import java.util.Stack;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringBuffer bf = new StringBuffer();
        int N = sc.nextInt();
        int[] A = new int[N];
        for(int i=0;i<N;i++) A[i] = sc.nextInt();
        Stack<Integer> stack = new Stack<>();
        int num = 1;
        boolean result = true;
        
        //기작
        //스택에는 num = 1부터 push 후 증가
        //A[i] 4 == num4까지 push 후 num++(중복되면 안되니까) 후 pop 후 ---1
        //A[i] 6 > num5이면 ---2==1
        //A[i] 3 < num5이면 pop() == A[i]이면 ---3
        //NO일 때 : '3'에서 pop()이 더 크면 나오면 수열 값이 아니므로
        for(int i=0;i<A.length;i++){
            int su = A[i];
            if(su >= num){
                while(su >= num){
                    stack.push(num++);
                    bf.append("+\n");
                }
                stack.pop();
                bf.append("-\n");
            }else{
                int n = stack.pop();
                if(n != su){
                    System.out.println("NO");
                    result = false;
                    break;
                }else bf.append("-\n");
            }
        }
        
        if(result) System.out.println(bf.toString());
    }
}