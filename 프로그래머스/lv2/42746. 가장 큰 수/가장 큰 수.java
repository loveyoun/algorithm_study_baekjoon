import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String solution(int[] numbers){
        StringBuilder sb = new StringBuilder();
        int N = numbers.length;
        ArrNum[] arranged = new ArrNum[N];

        int zeros = 0;
        for (int i = 0; i < N; i++) {
            int cipher = 1;
            int first = numbers[i];
            int tmp = 0;

            if (first == 0) zeros++;
            else {
                while (first / 10 != 0) {
                    first /= 10;
                    cipher++;
                }
                /*
                3:
                cipher = 1, *= 10^(4 - 1)
                += first * 10^(0,1,2)
                89:
                cipher = 2, *= 10^(4 - 2)
                += numbers[i]
                287:
                cipher = 3, *= 10^(4 - 3)
                += first * 10^0
                */
                tmp = (int) (numbers[i] * Math.pow(10, (4 - cipher)));
                if (cipher == 2) tmp += numbers[i];
                else
                    for (int j = 0; j < 4 - cipher; j++) tmp += (int) (first * Math.pow(10, j));
            }
            arranged[i] = new ArrNum(tmp, i);
        }


        Arrays.sort(arranged, new Comparator<ArrNum>() {
            @Override
            public int compare(ArrNum o1, ArrNum o2) {
                return o1.value - o2.value;
            }
        });

        /** 특이 케이스 : 11번 TC **/
        if (zeros == N) sb.append(0);
        else {
            for (int i = N - 1; i >= 0; i--) {
                int value = arranged[i].value;
                int origin_index = arranged[i].origin_index;

                if (value == 0) continue;

                sb.append(numbers[origin_index]);
            }

            for (int i = 0; i < zeros; i++) sb.append(0);
        }

        return sb.toString();
    }
}

class ArrNum {
    int value;
    int origin_index;

    ArrNum(int value, int origin_index) {
        this.value = value;
        this.origin_index = origin_index;
    }
}