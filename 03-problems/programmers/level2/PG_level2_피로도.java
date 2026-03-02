package programmers.level2;

import java.io.*;
import java.util.StringTokenizer;

public class PG_level2_피로도 {

    static int clearedDungeons = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        int k = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[][] dungeons = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            dungeons[i][0] = Integer.parseInt(st.nextToken());
            dungeons[i][1] = Integer.parseInt(st.nextToken());
        }

        int result = solution(k, dungeons);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    public static int solution(int k, int[][] dungeons) {

        dfs(0, k, dungeons, new boolean[dungeons.length]);

        return clearedDungeons;
    }

    private static void dfs(int depth, int curFatigue, int[][] dungeons, boolean[] visited) {

        clearedDungeons = Math.max(clearedDungeons, depth);

        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i] || curFatigue < dungeons[i][0]) continue;
            visited[i] = true;

            dfs(depth + 1, curFatigue - dungeons[i][1], dungeons, visited);
            visited[i] = false;
        }
    }

}
