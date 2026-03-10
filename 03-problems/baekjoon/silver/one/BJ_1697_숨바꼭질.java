package baekjoon.silver.one;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1697_숨바꼭질 {
    static int N, K;
    static boolean[] visited;

    static final int MAX_VAL = 100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[MAX_VAL + 1];

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
        Queue<Position> queue = new ArrayDeque<>();

        queue.offer(new Position(N, 0));

        while (!queue.isEmpty()) {
            Position position = queue.poll();

            if (position.index < 0 || position.index > MAX_VAL) continue;
            if (visited[position.index]) continue;

            visited[position.index] = true;

            if (position.index == K) return position.time;

            queue.offer(new Position(position.index * 2, position.time + 1));
            queue.offer(new Position(position.index + 1, position.time + 1));
            queue.offer(new Position(position.index - 1, position.time + 1));
        }

        return -1;
    }

    static class Position {
        int index, time;

        public Position(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }
}
