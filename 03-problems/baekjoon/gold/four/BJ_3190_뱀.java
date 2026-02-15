package baekjoon.gold.four;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3190_뱀 {

    static int N, K, L;
    static int[][] map;
    static boolean[][] isSnake;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static Queue<TurnQueue> turnQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        isSnake = new boolean[N + 1][N + 1];
        turnQueue = new ArrayDeque<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            map[y][x] = 2;
        }

        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);

            turnQueue.add(new TurnQueue(time, direction));
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();
        bw.close();
    }

    private static int solution() {
        int time = 0;

        Deque<int[]> snake = new ArrayDeque<>();

        snake.add(new int[]{1, 1});
        isSnake[1][1] = true;

        int direction = 0;

        while (true) {
            int[] cur = snake.peekFirst();
            int ny = cur[0] + dy[direction];
            int nx = cur[1] + dx[direction];

            time++;

            if (nx < 1 || nx > N || ny < 1 || ny > N) break;

            if (isSnake[ny][nx]) break;

            if (map[ny][nx] != 2) {
                int[] tail = snake.pollLast();
                isSnake[tail[0]][tail[1]] = false;
            } else map[ny][nx] = 0;

            snake.addFirst(new int[]{ny, nx});
            isSnake[ny][nx] = true;

            if (!turnQueue.isEmpty() && turnQueue.peek().time == time) {
                if (turnQueue.peek().direction == 'D') direction = (direction + 1) % 4;
                else direction = (direction + 3) % 4;

                turnQueue.poll();
            }
        }

        return time;
    }

    static class TurnQueue {
        int time;
        char direction;

        public TurnQueue(int time, char direction) {
            this.time = time;
            this.direction = direction;
        }
    }
}
