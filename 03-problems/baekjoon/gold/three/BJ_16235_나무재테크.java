package baekjoon.gold.three;

import java.io.*;
import java.util.*;

public class BJ_16235_나무재테크 {

    // N: 땅 크기, M: 나무 수, K: 년 수
    static int N, M, K;

    // 현재 땅 양분 (초기 5)
    static int[][] grid;

    // 겨울에 추가될 양분 A
    static int[][] fertilizer;

    // 칸별 나무 나이(오름차순 유지)
    static List<List<Deque<Integer>>> treeGrid;

    static final int INIT = 5;

    static final int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grid = new int[N + 1][N + 1];
        fertilizer = new int[N + 1][N + 1];
        treeGrid = new ArrayList<>(N + 1);

        // 겨울 추가 양분 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                fertilizer[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기화
        treeGrid.add(new ArrayList<>()); // zero dummy

        for (int i = 1; i <= N; i++) {
            treeGrid.add(new ArrayList<>(N + 1));
            treeGrid.get(i).add(new ArrayDeque<>()); // 0번 더미
            for (int j = 1; j <= N; j++) {
                grid[i][j] = INIT;
                treeGrid.get(i).add(new ArrayDeque<>());
            }
        }

        // 나무 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            treeGrid.get(r).get(c).addLast(age);
        }

        // 칸별 초기 정렬
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                Deque<Integer> dq = treeGrid.get(r).get(c);
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

            // 가을, 나무가 번식한다.
            // 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개 칸에 나이가 1인 나무가 생긴다.

            // 겨울, 양분을 추가한다.
            // 각 칸에 추가되는 양분의 양은 map[r][c]
            
            // K년이 지난 후 살아남은 나무의 수 출력
        }

        return trees;
    }
}