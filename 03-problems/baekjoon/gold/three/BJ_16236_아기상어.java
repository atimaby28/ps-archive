package baekjoon.gold.three;

import java.io.*;
import java.util.*;

public class BJ_16236_아기상어 {

    static int N;
    static int[][] map;
    static boolean[][] visited;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        BabyShark babyShark = null;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 9) {
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

        // 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다.
        // 물고기를 먹으면, 그 칸은 빈 칸이 된다.
        // 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지

        // 더 이상 먹을 수 있는 물고기가 공간에 없다면 종료
        // 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
        // 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
        //   거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
        //   거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
        return bfs(babyShark);
    }

    private static int bfs(BabyShark babyShark) {
        int totalTime = 0;

        while(true) {
            Queue<int[]> queue = new ArrayDeque<>();
            List<int[]> fishList = new ArrayList<>();

            queue.offer(new int[]{babyShark.row, babyShark.col});
            visited[babyShark.row][babyShark.col] = true;

            int distance = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();

                for (int k = 0; k < size; k++) {
                    int[] cur = queue.poll();

                    int curR = cur[0];
                    int curC = cur[1];

                    for (int index = 0; index < 4; index++) {
                        int newR = curR + dr[index];
                        int newC = curC + dc[index];

                        if (newR < 0 || newR >= N || newC < 0 || newC >= N) continue;
                        if (!babyShark.canPass(map[newR][newC]) || visited[newR][newC]) continue;

                        visited[newR][newC] = true;
                        queue.offer(new int[]{newR, newC});

                        if (babyShark.canEat(map[newR][newC])) {
                            fishList.add(new int[]{newR, newC});
                        }
                    }
                }
                distance++;
                if (!fishList.isEmpty()) break;
            }
            if (fishList.isEmpty()) break;

            totalTime += distance;
            fishList.sort(Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt(a -> a[1]));

            int[] fish = fishList.get(0);

            babyShark.row = fish[0];
            babyShark.col = fish[1];

            babyShark.eat();
            map[babyShark.row][babyShark.col] = 0;
            visited = new boolean[N][N];
        }

        return totalTime;
    }

    static class BabyShark {
        int row, col;
        int size;
        int eatCount;

        BabyShark(int row, int col, int size) {
            this.row = row;
            this.col = col;
            this.size = size;
            this.eatCount = 0;
        }

        boolean canEat(int fish) {
            return fish > 0 && fish < size;
        }

        boolean canPass(int cell) {
            return cell <= size;
        }

        void eat() {
            eatCount++;
            if (eatCount == size) {
                size++;
                eatCount = 0;
            }
        }
    }
}
