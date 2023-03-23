import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static Kids[] tree;
    static boolean[] visited;
    static ArrayList<Integer>[] levels;
    static int[] widths;
    //static ArrayList<Integer> result;
    static int count;

    public static void main(String[] args) throws IOException {
        /**2250_Algorithm flow_트리의 높이와 너비 : 트리, 중위순회(재귀)
         * **/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        /** tree : 자신의 높이와 자식(Kids Class)을 가짐
         *  levels : 각 높이에 해당하는 노드개수-> 노드인덱스들
         *  widths : 각 높이에 해당하는 너비-> 너비 위치
         **/
        tree = new Kids[N + 1];
        for (int i = 1; i < N + 1; i++) tree[i] = new Kids();
        visited = new boolean[N + 1];
        //levels = new int[N + 1];
        levels = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) levels[i] = new ArrayList<>();
        widths = new int[N + 1];
        //result = new ArrayList<>();

        /* 루트 노드가 1이 아닐 경우
        tree[1].setLevel(1);
        levels[1].add(1);*/
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            /* levels[tree[parent].level]++; 자식 없는 부모는 안되니까, 부모가 자식을 책임져주자.
             * 뜬금없이 나오는 부모는 없을 거다! 라는 가정.*/

            if (L != -1) {
                tree[parent].setLeft(L);
                visited[L] = true;

                /* 만약에 부모가 먼저 안나온다면...? 그럴리는 없다 */
                //tree[L] = new Kids(tree[parent].level + 1);
                /*tree[L].setLevel(tree[parent].level + 1);
                //levels[tree[parent].level + 1]++;
                levels[tree[parent].level + 1].add(L);*/
            }
            if (R != -1) {
                tree[parent].setRight(R);
                visited[R] = true;

                /*tree[R].setLevel(tree[parent].level + 1);
                levels[tree[parent].level + 1].add(R);*/
            }
        }
        //루트노드 찾기
        int root = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) root = i;
        }

        tree[root].setLevel(1);
        levels[1].add(root);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);

        while (queue.size() > 0) {
            int now = queue.poll();
            int left = tree[now].left;
            int right = tree[now].right;
            
            if (left != -1) {
                queue.add(left);

                tree[left].setLevel(tree[now].level + 1);
                levels[tree[now].level + 1].add(left);
            }
            if (right != -1) {
                queue.add(right);

                tree[right].setLevel(tree[now].level + 1);
                levels[tree[now].level + 1].add(right);
            }
        }


        count = 0;
        //중위 순회
        check_left(root);

        int max = 0, index = 0;
        //for (int i = 1; i <= N; i++) {
        for (int i = N; i > 0; i--) {
            if (levels[i].size() > 0) {
                int width = Math.abs(widths[levels[i].get(0)] - widths[levels[i].get(levels[i].size() - 1)]) + 1;
                if (width >= max) {
                    max = width;
                    index = i;
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(index + " " + max);

        bw.flush();
        bw.close();
    }

    private static void check_left(int node) {
        if (tree[node].left != -1) check_left(tree[node].left);
        widths[node] = count++;
        if (tree[node].right != -1) check_left(tree[node].right);
    }

    static class Kids {
        int left;
        int right;
        int level;

        public Kids() {
            this.left = -1;
            this.right = -1;
            this.level = 0;
        }


        public void setLevel(int level) {
            this.level = level;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public void setRight(int right) {
            this.right = right;
        }
    }

}