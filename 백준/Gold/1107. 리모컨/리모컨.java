import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //이동하려는 채널 N
        String N = br.readLine();
        int n = Integer.parseInt(N);
        /*int[] narr = new int[N.length()]; //~=null nullPointException
        for(int i=0;i<N.length();i++)
            narr[i] = N.charAt(i)-'0'; //~N[i]^^ N.charAt(i)*/

        //가장 가까운 수 중 큰 수, 작은 수
        int max = n, min = n;
        int maxlen=N.length(), minlen=N.length();
        boolean outoforder = false;

        //고장난 버튼 개수 M
        int M = Integer.parseInt(br.readLine());
        //고장난게 없을 때
        if(M==0) {
            System.out.println(N.length() > Math.abs(100-n) ? Math.abs(100-n) : N.length());
            return;
        }
        //다 고장났을 때
        if(M==10){
            System.out.println(Math.abs(100-n));
            return;
        }

        String[] tmp = br.readLine().split(" ");
        int[] broken = new int[M];
        for(int i=0;i<M;i++) broken[i] = Integer.parseInt(tmp[i]);


        //큰 수 찾기
        while(true){
            N = Integer.toString(max); maxlen=N.length();
            for(int i=0;i<maxlen;i++){
                for(int b : broken){
                    /*System.out.print(b+"\\");
                    System.out.println(N.charAt(i));*/
                    //고장나서 못 누르면
                    if((N.charAt(i)-'0') == b) {
                        outoforder = true;
                        break;
                    }
                }
                if(outoforder) break;
            }
            //안고장난 버튼들 이면
            if(!outoforder) break;
            /*한없이 끝까지 커질 때
            n = 0 -> max = ...99
            500,000 -> 511,111*/
            if((max-n+maxlen)>Math.abs(100-n)) {
                //max = 1000001; // n-100 < max-n+maxlen 이어야 함.
                break;
            }

            max+=1;
            outoforder = false;
        }
        maxlen=N.length();
        outoforder = false;

        /**변수 재활용 조심하기**/
        //작은 수 찾기
        while(true){
            N = Integer.toString(min); minlen=N.length();
            for(int i=0;i<minlen;i++){
                for(int b : broken){
                    /*System.out.print(b+"\\");
                    System.out.println(N.charAt(i));*/
                    //고장나서 못 누르면
                    if((N.charAt(i)-'0') == b) {
                        outoforder = true;
                        break;
                    }
                }
                if(outoforder) break;
            }
            //안고장난 버튼들 이면
            if(!outoforder) break;
            //한없이 작아질 때
            if(min<=0) {
                min = n+minlen-Math.abs(100-n); //n-100 < n-min+minlen 이어야 함.
                break;
            }


            min-=1;
            outoforder = false;
        }
        minlen=N.length();


        int MAX = max-n+maxlen;
        int MIN = n-min+minlen;
        int semi = MAX > MIN ? MIN : MAX;
        int result = semi > Math.abs(100-n) ? Math.abs(100-n) : semi;
        System.out.println(result);
//        bw.write(result);
//        bw.flush();
//        bw.close();
    }

}