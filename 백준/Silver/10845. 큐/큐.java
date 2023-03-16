import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        /**Algorithm flow: **/

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> queue = new LinkedList<>();

        int N = Integer.parseInt(br.readLine());
        int x=0;
        for(int i=0;i<N;i++){
            String[] s = br.readLine().split(" ");
            String op = s[0]; //push, pop, size, empty, front, back

            switch(op){
                case "push":
                    x = Integer.parseInt(s[1]);
                    queue.add(x);
                    break;
                case "pop":
                    if(queue.size()>0) sb.append(queue.poll() + "\n");
                    else sb.append(-1 + "\n");
                    break;
                case "size":
                    sb.append(queue.size() + "\n");
                    break;
                case "empty":
                    if(queue.size()>0) sb.append(0 + "\n");
                    else sb.append(1 + "\n");
                    break;
                case "front":
                    if(queue.size()>0) sb.append(queue.peek() + "\n");
                    else sb.append(-1 + "\n");
                    break;
                case "back":
                    if(queue.size()>0) {
                        //queue.add(queue.poll());
                        for(int y=1;y<queue.size();y++){
                            queue.add(queue.poll());
                        }
                        int last = queue.poll();
                        sb.append(last + "\n");
                        queue.add(last);
                    } /*else if (queue.size()==1) {
                        sb.append(queue.poll() + "\n");
                        break;
                    } */
                    else sb.append(-1 + "\n");
                    break;
                default: break;
            }
        }

        System.out.println(sb);
    }

}