import java.util.Scanner;

public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        // 예시를 봐서도 2, 3, 5, 7로 시작하는 것만 봐도 된다는 걸 알 수 있다.
        DFS(2, 1);
        DFS(3, 1);
        DFS(5, 1);
        DFS(7, 1);

        System.out.println(sb);
    }

    static void DFS(int num, int digit) {
        if (digit == N) {
            if (isPrime(num)) sb.append(num).append("\n");
            return;
        }

        for (int i = 1; i < 10; i++) { // 1 ~ 9
            if (i % 2 == 0) continue;

            int next = num * 10 + i;

            if (isPrime(next)) DFS(next, digit + 1);
        }
    }

    static boolean isPrime(int num) {
        // classic "isPrime". Non-aristostenes
        for (int i = 2; i <= Math.sqrt(num); i++)
            if (num % i == 0) return false;

        return true;
    }

}