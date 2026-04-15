package baekjoon.gold.five;

import java.io.*;
import java.util.*;

public class BJ_13549_숨바꼭질3 {

    static int N, K;
    static int[] positions;

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
        positions = new int[100_001];
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

            int[] positions = {position - 1, position + 1, position * 2};

            for (int p : positions) {
                if (p < 0 || p > 100_000 || visited[p]) continue;
                visited[p] = true;
                queue.addLast(new int[]{positions[0], time + 1});
                queue.addLast(new int[]{positions[1], time + 1});
                queue.addFirst(new int[]{positions[2], time});
            }

        }

        return -1;
    }
}
