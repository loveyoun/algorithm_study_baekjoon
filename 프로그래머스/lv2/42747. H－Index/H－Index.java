import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        
        for(int h=0;h<=Math.max(citations.length, citations[citations.length-1]);h++){
            int count = 0;
            
            for(int j=0;j<citations.length;j++)
                if(citations[j] >= h) count++;
            
            if(count >= h) answer = Math.max(answer, h);
        }
        
        return answer;
    }
}