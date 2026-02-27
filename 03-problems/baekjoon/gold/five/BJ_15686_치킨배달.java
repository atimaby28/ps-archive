package baekjoon.gold.five;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_15686_치킨배달 {
    
    static int N, M;
    static int[][] map;

    static int chickenDistanceOfCities = Integer.MAX_VALUE;
    
    static List<Position> chickenHouses;
    static List<Position> houses;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        
        chickenHouses = new ArrayList<>();
        houses = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) chickenHouses.add(new Position(i, j));
                else if (map[i][j] == 1) houses.add(new Position(i, j));
            }
        }
        
        int result = solution();
            
        bw.write(result + "\n");
        
        bw.flush();
            
        bw.close();
        br.close();
    }

    private static int solution() {
        combination(0, 0, new ArrayList<>());
        return chickenDistanceOfCities;
    }

    private static void combination(int depth, int index, List<Position> result) {
        if (depth == M) {
            chickenDistanceOfCities = Math.min(chickenDistanceOfCities, getDistance(result));
            return;
        }

        for (int i = index; i < chickenHouses.size(); i++) {
            result.add(chickenHouses.get(i));
            combination(depth + 1, i + 1, result);
            result.remove(result.size() - 1);
        }
    }

    private static int getDistance(List<Position> result) {
        int chickenDistance = 0;

        for (Position house : houses) {
            int minDistance = Integer.MAX_VALUE;
            for (Position chicken : result) {
                minDistance = Math.min(minDistance, Math.abs(chicken.x - house.x) + Math.abs(chicken.y - house.y));
            }
            chickenDistance += minDistance;
        }

        return chickenDistance;
    }

    static class Position {
        int x, y;
        
        public Position(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }
}
