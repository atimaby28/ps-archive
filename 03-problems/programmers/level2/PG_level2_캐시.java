package programmers.level2;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class PG_level2_캐시 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        int cacheSize = Integer.parseInt(br.readLine());
        int cityCount = Integer.parseInt(br.readLine());

        String[] cities = new String[cityCount];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cityCount; i++) {
            cities[i] = st.nextToken();
        }

        int result = solution(cacheSize, cities);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;

        Queue<String> cache = new ArrayDeque<>();

        // hit: 1, miss: 5
        for (String city : cities) {
            city = city.toUpperCase();
            // city가 캐시에 있다면 + 1, 캐시에서 city를 맨뒤로
            if (cache.contains(city)) {
                answer += 1;
                if (cacheSize == 0) continue;  // edge case
                cache.remove(city);
                cache.offer(city);
            } else { // city가 캐시에 없다면. + 5, 맨 뒤에 city 삽입
                answer += 5;
                if (cacheSize == 0) continue; // edge case
                if (cache.size() == cacheSize) cache.poll();
                cache.offer(city);
            }
        }
        return answer;
    }
}
