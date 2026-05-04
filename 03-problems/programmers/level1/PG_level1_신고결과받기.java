package programmers.level1;

import java.io.*;
import java.util.*;

public class PG_level1_신고결과받기 {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        String[] id_list = new String[N];
        String[] report = new String[M];

        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++) {
            id_list[i] = st.nextToken();
        }

        for (int i = 0; i < M; i++) {
            report[i] = br.readLine();
        }

        int k = Integer.parseInt(br.readLine());

        int[] result = solution(id_list, report, k);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        Map<String, Integer> index = new HashMap<>();
        Map<String, Set<String>> map = new HashMap<>();

        for (int i = 0; i < id_list.length; i++) {
            index.put(id_list[i], i);
        }

        for (String content : report) {
            String[] c = content.split(" ");

            String from = c[0];
            String to = c[1];

            map.computeIfAbsent(to, s -> new HashSet<>()).add(from);
        }

        for (Map.Entry<String, Set<String>> e : map.entrySet()) {
            // k 이상인 값을 가지고 있는 키에서 계산
            if (e.getValue().size() >= k) {
                for (String key : e.getValue()) {
                    answer[index.get(key)]++;
                }
            }
        }

        return answer;
    }
}
