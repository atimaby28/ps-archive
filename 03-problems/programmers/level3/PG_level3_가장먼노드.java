package programmers.level3;

import java.io.*;
import java.util.*;

public class PG_level3_가장먼노드 {

    static boolean[] visited;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] edge = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            edge[i][0] = Integer.parseInt(st.nextToken());
            edge[i][1] = Integer.parseInt(st.nextToken());
        }

        int result = solution(n, edge);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    public static int solution(int n, int[][] edge) {
        graph = new ArrayList<>();
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] e : edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        return bfs(1);
    }

    private static int bfs(int k) {
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(k);
        visited[k] = true;

        int lastLevelCount = 0;

        while (!queue.isEmpty()) {
            lastLevelCount = queue.size();

            for (int i = 0; i < lastLevelCount; i++) {
                int cur = queue.poll();

                for (int next : graph.get(cur)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }

        }

        return lastLevelCount;
    }

}
