package baekjoon.gold.five;

import java.io.*;
import java.util.*;

public class BJ_13549_숨바꼭질3 {

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        return bfs();
    }

    private static int bfs() {
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[100_001];

        queue.offer(new int[]{N, 0});
        visited[N] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.pollFirst();

            int position = cur[0];
            int time = cur[1];

            if (position == K) return time;

            int tp = position * 2;
            if (tp >= 0 && tp <= 100_000 && !visited[tp]) {
                visited[tp] = true;
                queue.addFirst(new int[]{tp, time});
            }

            int left = position - 1;
            if (left >= 0 && left <= 100_000 && !visited[left]) {
                visited[left] = true;
                queue.addLast(new int[]{left, time + 1});
            }

            int right = position + 1;
            if (right >= 0 && right <= 100_000 && !visited[right]) {
                visited[right] = true;
                queue.addLast(new int[]{right, time + 1});
            }

        }

        return -1;
    }
}
