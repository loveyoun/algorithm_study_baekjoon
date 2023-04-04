import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int count = 0;
        PriorityQueue<Candidate> candidates = new PriorityQueue<>((o1, o2) -> {
            //votes 기준 내림차순
            //votes가 같으면, number 기준 오름차순 : 안 해도 될 듯
            if(o1.votes == o2.votes) return o1.number - o2.number;
            return o2.votes - o1.votes;
        });

        int dasom = Integer.parseInt(br.readLine());
        if(N >= 2) {
            for (int i = 2; i <= N; i++)
                candidates.add(new Candidate(i, Integer.parseInt(br.readLine())));
            

            while (candidates.peek().votes >= dasom){
                Candidate tmp = candidates.poll();
                tmp.decreaseVotes();
                dasom++;
                count++;
                candidates.add(tmp);
            }
        }

        System.out.println(count);
    }

    static class Candidate{
        int number;
        int votes;

        Candidate(int number, int votes) {
            this.number = number;
            this.votes = votes;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public void decreaseVotes() {
            this.votes--;
        }
    }

}