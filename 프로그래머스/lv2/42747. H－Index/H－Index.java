import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);

        int arr_length = citations.length;
        for (int i = 0; i < arr_length; i++) {
            int h = arr_length - i; //i = 0 ~ 부터라서, 논문 개수

            if(citations[i] >= h){
                answer = h;
                //h가 내림차순으로 내려가니까, break 가능.
                break;
            }
        }

        return answer;
    }
}