import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[][] tree;

    public static void main(String[] args) throws IOException {
        /** 1991_Algorithm flow_트리 순회 : 트리, 전위/중위/후위 순회(재귀)
         * **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tree = new int[26][2]; //왼쪽,오른쪽 자식 노드
        for (int i = 0; i < N; i++) {
            String[] stmp = br.readLine().split(" ");
            int parent = stmp[0].charAt(0) - 'A';
            int left = stmp[1].charAt(0);
            int right = stmp[2].charAt(0);

            if (left == '.') tree[parent][0] = -1;
            else tree[parent][0] = left - 'A';

            if (right == '.') tree[parent][1] = -1;
            else tree[parent][1] = right - 'A';
        }

        //전위
        preOrder(0);
        System.out.println();
        //중위
        inOrder(0);
        System.out.println();
        //후위
        postOrder(0);
        System.out.println();
    }

    
    private static void preOrder(int node) {
        //자식 노드가 -1 였을 때,
        if (node == -1) return;

        System.out.print((char) (node + 'A'));
        preOrder(tree[node][0]);
        preOrder(tree[node][1]);
    }

    private static void inOrder(int node) {
        if (node == -1) return;

        inOrder(tree[node][0]);
        System.out.print((char) (node + 'A'));
        inOrder(tree[node][1]);
    }

    private static void postOrder(int node) {
        if (node == -1) return;

        postOrder(tree[node][0]);
        postOrder(tree[node][1]);
        System.out.print((char) (node + 'A'));
    }

}