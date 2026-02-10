import java.io.*;
import java.util.*;

/**
 * 트리 (Tree) - 순회
 *
 * [입력] - 노드 왼쪽자식 오른쪽자식 (.이면 없음)
 * 7
 * A B C
 * B D E
 * C F G
 * D . .
 * E . .
 * F . .
 * G . .
 *
 * [출력]
 * 전위: A B D E C F G
 * 중위: D B E A F C G
 * 후위: D E B F G C A
 */
public class TreeTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int[] left = new int[26];
    static int[] right = new int[26];

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine().trim());
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = st.nextToken().charAt(0) - 'A';
            String l = st.nextToken();
            String r = st.nextToken();
            if (!l.equals(".")) left[node] = l.charAt(0) - 'A';
            if (!r.equals(".")) right[node] = r.charAt(0) - 'A';
        }

        sb.append("전위: "); preOrder(0); sb.append('\n');
        sb.append("중위: "); inOrder(0); sb.append('\n');
        sb.append("후위: "); postOrder(0); sb.append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void preOrder(int node) {
        if (node == -1) return;
        sb.append((char)(node + 'A')).append(' ');
        preOrder(left[node]);
        preOrder(right[node]);
    }

    public static void inOrder(int node) {
        if (node == -1) return;
        inOrder(left[node]);
        sb.append((char)(node + 'A')).append(' ');
        inOrder(right[node]);
    }

    public static void postOrder(int node) {
        if (node == -1) return;
        postOrder(left[node]);
        postOrder(right[node]);
        sb.append((char)(node + 'A')).append(' ');
    }

    // ===== 트리 높이 =====
    public static int height(int node) {
        if (node == -1) return 0;
        return 1 + Math.max(height(left[node]), height(right[node]));
    }
}
