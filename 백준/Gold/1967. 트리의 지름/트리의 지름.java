import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    //<Integer> -> <Edge>
    static ArrayList<Node>[] arrLst;
    static boolean visited[];
    static int[] distance;

    public static void main(String[] args) throws IOException {
        /** 1967_Algorithm_트리의 지름 : 트리(BFS, 큐), 스택
         == 1067
         **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        arrLst = new ArrayList[N + 1];

        for (int i = 1; i < N + 1; i++) arrLst[i] = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            arrLst[S].add(new Node(E, V));
            arrLst[E].add(new Node(S, V));
        }

        /**
         임의의 노드(1) 기준으로 가장 거리가 큰 값을 가진 노드 구하기.
         : 임의의 노드를 루트 노드로 생각 -> 가장 거리가 큰 값을 가진 노드는 가장 꼬래비 일 것.
         (가장 큰 노드를 가지거나, 가장 많은 엣지를 가진 노드)
         왼쪽(오른쪽) 꼬래비 -> 오른쪽(왼쪽) 꼬래비 로 다시 거리 탐색.
         == 루트 노드를 새롭게 생각하는 것.
         **/
        distance = new int[N + 1];
        visited = new boolean[N + 1];
        BFS(1);
        int max_idx = 1;
        for (int i = 2; i < N + 1; i++) max_idx = distance[max_idx] > distance[i] ? max_idx : i;

        distance = new int[N + 1];
        visited = new boolean[N + 1];
        BFS(max_idx);

        Arrays.sort(distance);

        System.out.println(distance[N]);
    }

    static void BFS(int index) {
        Queue<Integer> queue = new LinkedList<>();

        visited[index] = true;
        queue.add(index);

        /** 여기서 계속 돌아감. **/
        while (!queue.isEmpty()) {
            int now = queue.poll();

            // 단방향으로 하면 안되나?
            for (Node node : arrLst[now]) {
                int k = node.kid;
                int v = node.value;
                if (!visited[k]) {
                    visited[k] = true;
                    queue.add(k);
                    distance[k] = distance[now] + v;
                }
            }
        }
    }

    static class Node {
        int kid, value;

        public Node(int kid, int value) {
            this.kid = kid;
            this.value = value;
        }
    }

}