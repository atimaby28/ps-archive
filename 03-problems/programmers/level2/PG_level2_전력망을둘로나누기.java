package programmers.level2;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class PG_level2_전력망을둘로나누기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;

        int[][] wires = new int[n - 1][2];

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            wires[i][0] = start;
            wires[i][1] = end;
        }

        int result = solution(n, wires);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    public static int solution(int n, int[][] wires) {
        int answer = n;

        boolean[][] adjMatrix = new boolean[n + 1][n + 1];

        for (int[] wire : wires) {
            adjMatrix[wire[0]][wire[1]] = true;
            adjMatrix[wire[1]][wire[0]] = true;
        }

        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];

            // 1. 끊기
            adjMatrix[v1][v2] = false;
            adjMatrix[v2][v1] = false;

            // 2. BFS로 한쪽 개수 세기
            int count = bfs(n, v1, adjMatrix);

            // 3. 차이 계산 (전체 n에서 count를 빼면 나머지 한쪽 개수)
            int diff = Math.abs(count - (n - count));
            answer = Math.min(answer, diff);

            // 4. 복구하기
            adjMatrix[v1][v2] = true;
            adjMatrix[v2][v1] = true;
        }

        return answer;
    }

    private static int bfs(int n, int start, boolean[][] adjMatrix) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];

        queue.offer(start);
        visited[start] = true;

        int count = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            // 인접 행렬 탐색
            for (int i = 1; i <= n; i++) {
                if (adjMatrix[cur][i] && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                    count++;
                }
            }
        }

        return count;
    }

}
