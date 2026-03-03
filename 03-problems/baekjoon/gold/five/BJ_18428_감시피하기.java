package baekjoon.gold.five;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_18428_감시피하기 {

    static int N;
    static char[][] map;
    static boolean[][] visited;
    static String answer = "NO";

    static final int limit = 3;

    static List<int[]> teacherPositions;
    static List<int[]> emptyPositions;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        visited = new boolean[N][N];

        emptyPositions = new ArrayList<>();
        teacherPositions = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);

                if (map[i][j] == 'T') {
                    teacherPositions.add(new int[]{i, j});
                } else if (map[i][j] == 'X') {
                    emptyPositions.add(new int[]{i, j});
                }
            }
        }

        String result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static String solution() {
        search(0, 0, new ArrayList<>());

        return answer;
    }

    private static void search(int count, int index, List<int[]> results) {
        if (answer.equals("YES")) return;

        if (count == limit) {
            if (isPossible(results)) answer = "YES";
            return;
        }

        for (int i = index; i < emptyPositions.size(); i++) {
            results.add(emptyPositions.get(i));
            search(count + 1, i + 1, results);
            results.remove(results.size() - 1);
        }

    }

    public static boolean isPossible(List<int[]> results) {

        for (int[] result : results) {
            visited[result[0]][result[1]] = true;
        }

        for (int[] teacherPosition : teacherPositions) {
            int cx = teacherPosition[1];
            int cy = teacherPosition[0];

            for (int d = 0; d < 4; d++) {
                for (int i = 1; i < N; i++) {
                    int nx = cx + dx[d] * i;
                    int ny = cy + dy[d] * i;

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) break;
                    if (visited[ny][nx]) break;

                    if (map[ny][nx] == 'S') {
                        for (int[] result : results) {
                            visited[result[0]][result[1]] = false;
                        }
                        return false;
                    }
                }
            }
        }


        for (int[] result : results) {
            visited[result[0]][result[1]] = false;
        }

        return true;
    }
}
