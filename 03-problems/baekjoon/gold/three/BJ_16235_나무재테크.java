package baekjoon.gold.three;

import java.io.*;
import java.util.*;

public class BJ_16235_나무재테크 {

    // N: 땅 크기, M: 나무 수, K: 년 수
    static int N, M, K;
    // 현재 땅 양분 (초기 5)
    static Cell[][] grid;
    // 겨울에 추가될 양분 A
    static int[][] fertilizer;

    static final int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grid = new Cell[N + 1][N + 1];
        fertilizer = new int[N + 1][N + 1];

        // 겨울 추가 양분 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                grid[i][j] = new Cell();
                fertilizer[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 나무 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            grid[r][c].trees.addLast(age);
        }

        // 칸별 초기 정렬
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                Deque<Integer> dq = grid[r][c].trees;
                if (dq.size() <= 1) continue;
                List<Integer> tmp = new ArrayList<>(dq);
                Collections.sort(tmp);
                dq.clear();
                for (int a : tmp) dq.addLast(a);
            }
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        int trees = 0;

        while (K--> 0) {
            // 봄, 나무는 자신의 나이만큼 양분을 먹고, 나이가 1 증가
            // 1x1 칸에 있는 양분만 먹을 수 있고, 하나의 칸에 여러 개의 나무가 있다면, 어린 나무부터 양분을 먹는다.
            // 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 즉시 죽는다.
            // 여름, 죽은 나무가 양분으로 변하게 된다.
            // 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가, 소수점 아래는 버림
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    Deque<Integer> alive = new ArrayDeque<>();
                    int deadNutrient = 0;

                    while (!grid[i][j].trees.isEmpty()) {
                        int age = grid[i][j].trees.pollFirst();

                        if (grid[i][j].nutrient >= age) {
                            grid[i][j].nutrient -= age;
                            alive.addLast(age + 1);
                        } else {
                            deadNutrient += age / 2;
                        }
                    }

                    grid[i][j].trees = alive;
                    grid[i][j].nutrient += deadNutrient;
                }
            }

            // 가을, 나무가 번식한다.
            // 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개 칸에 나이가 1인 나무가 생긴다.
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    for (int age : grid[r][c].trees) {
                        if (age % 5 == 0) {
                            for (int i = 0; i < 8; i++) {
                                int nr = r + dr[i];
                                int nc = c + dc[i];
                                if (nr < 1 || nr > N || nc < 1 || nc > N) continue;
                                grid[nr][nc].trees.addFirst(1);
                            }
                        }
                    }
                }
            }

            // 겨울, 양분을 추가한다.
            // 각 칸에 추가되는 양분의 양은 map[r][c]
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    grid[r][c].nutrient += fertilizer[r][c];
                }
            }
        }

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (!grid[r][c].trees.isEmpty()) trees += grid[r][c].trees.size();
            }
        }

        return trees;
    }

    static class Cell {
        Deque<Integer> trees = new ArrayDeque<>();
        int nutrient = 5;  // 초기 양분
    }

}