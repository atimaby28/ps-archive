import java.io.*;
import java.util.*;

/**
 * HashMap / HashSet - 빈도수 카운팅 & Two Sum
 *
 * [빈도수 카운팅 - 입력]
 * 7
 * 1 2 3 1 2 1 3
 *
 * [빈도수 카운팅 - 출력] - 가장 많이 나온 수
 * 1
 *
 * [Two Sum - 입력]
 * 4
 * 2 7 11 15
 * 9               ← target
 *
 * [Two Sum - 출력] - 합이 target인 두 인덱스
 * 0 1
 */
public class HashMapSetTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        // --- 빈도수 카운팅 ---
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) freq.put(num, freq.getOrDefault(num, 0) + 1);

        // 가장 많이 나온 수
        int maxCount = 0, maxNum = 0;
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            if (e.getValue() > maxCount) {
                maxCount = e.getValue();
                maxNum = e.getKey();
            }
        }
        sb.append(maxNum).append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // ===== Two Sum =====
    public static int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (map.containsKey(complement)) return new int[]{map.get(complement), i};
            map.put(arr[i], i);
        }
        return new int[]{-1, -1};
    }

    // ===== 중복 제거 (순서 유지) =====
    public static List<Integer> removeDuplicates(int[] arr) {
        Set<Integer> seen = new LinkedHashSet<>();
        for (int num : arr) seen.add(num);
        return new ArrayList<>(seen);
    }

    // ===== 애너그램 그룹핑 =====
    public static List<List<String>> groupAnagrams(String[] words) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(map.values());
    }
}
