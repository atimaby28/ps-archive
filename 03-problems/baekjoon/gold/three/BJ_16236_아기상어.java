package baekjoon.gold.three;

import java.io.*;
import java.util.*;

public class BJ_16236_아기상어 {

    static int N;
    static int[][] map;
    static final int BABY_SHARK = 9;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        BabyShark babyShark = null;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == BABY_SHARK) {
                    babyShark = new BabyShark(i, j, 2);
                    map[i][j] = 0;
                }
            }
        }

        int result = solution(babyShark);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution(BabyShark babyShark) {
        int time = 0;

        while (true) {
            // BFS로 먹을 수 있는 물고기 후보 전부 탐색
            List<Fish> fishes = searchFish(babyShark, new boolean[N][N]);
            // 후보가 없으면 종료
            if (fishes.isEmpty()) break;

            // 후보가 있으면 정렬
            Collections.sort(fishes);
            // 정렬 후 가장 가까운 물고기 한마리 먹고 다시 물고기 탐색
            Fish nearestFish = fishes.get(0);

            babyShark.eat(nearestFish.row, nearestFish.col);

            time += nearestFish.distance;
        }

        return time;
    }

    private static List<Fish> searchFish(BabyShark babyShark, boolean[][] visited) {
        Queue<int[]> queue = new ArrayDeque<>();
        List<Fish> fishes = new ArrayList<>();

        queue.offer(new int[]{babyShark.row, babyShark.col, 0});
        visited[babyShark.row][babyShark.col] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int curRow = cur[0];
            int curCol = cur[1];
            int curDistance = cur[2];

            for (int d = 0; d < 4; d++) {
                int newRow = curRow + dr[d];
                int newCol = curCol + dc[d];

                if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) continue;
                if (visited[newRow][newCol]) continue;
                if (!babyShark.isPassed(map[newRow][newCol])) continue;

                if (babyShark.canEat(map[newRow][newCol])) fishes.add(new Fish(newRow, newCol, curDistance + 1));

                visited[newRow][newCol] = true;
                queue.offer(new int[]{newRow, newCol, curDistance + 1});
            }
        }

        return fishes;
    }

    // 자기보다 작은 물고기만 먹을수 있다.
    // 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없다.
    // 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다.
    static class BabyShark {
        int row, col;
        int size, eatCount;

        public BabyShark(int row, int col, int size) {
            this.row = row;
            this.col = col;
            this.size = size;
            this.eatCount = 0;
        }

        // 지나갈 수 있는지
        public boolean isPassed (int fishSize) {
            return fishSize <= this.size;
        }

        // 먹을 수 있는지
        public boolean canEat(int fishSize) {
            return fishSize > 0 && fishSize < this.size;
        }

        // 먹는 동작
        public void eat(int row, int col) {
            eatCount++;

            this.row = row;
            this.col = col;

            map[row][col] = 0;

            if (eatCount == size) {
                size++;
                eatCount = 0;
            }
        }
    }

    static class Fish implements Comparable<Fish>{
        int row, col;
        int distance;

        public Fish (int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }

        // 거리, 행, 열 순
        @Override
        public int compareTo(Fish f) {
            if (this.distance != f.distance) return Integer.compare(this.distance, f.distance);
            if (this.row != f.row) return Integer.compare(this.row, f.row);
            return Integer.compare(this.col, f.col);
        }
    }
}
