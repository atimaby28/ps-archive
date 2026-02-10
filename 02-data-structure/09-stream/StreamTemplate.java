import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * 스트림 (Stream) - 코테에서 자주 쓰는 패턴
 *
 * [입력]
 * 7
 * 5 3 1 4 2 3 5
 *
 * [출력]
 * 정렬: [1, 2, 3, 3, 4, 5, 5]
 * 중복제거 정렬: [1, 2, 3, 4, 5]
 * 합계: 23
 * 최댓값: 5
 * 최솟값: 1
 * 빈도수: {1=1, 2=1, 3=2, 4=1, 5=2}
 * 짝수만: [4, 2]
 * 문자열 변환: 5,3,1,4,2,3,5
 */
public class StreamTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        // --- int[] → List<Integer> ---
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

        // --- 정렬 ---
        List<Integer> sorted = list.stream().sorted().collect(Collectors.toList());
        sb.append("정렬: ").append(sorted).append('\n');

        // --- 중복 제거 + 정렬 ---
        List<Integer> distinct = list.stream().distinct().sorted().collect(Collectors.toList());
        sb.append("중복제거 정렬: ").append(distinct).append('\n');

        // --- 합계 / 최대 / 최소 ---
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        int max = list.stream().mapToInt(Integer::intValue).max().orElse(0);
        int min = list.stream().mapToInt(Integer::intValue).min().orElse(0);
        sb.append("합계: ").append(sum).append('\n');
        sb.append("최댓값: ").append(max).append('\n');
        sb.append("최솟값: ").append(min).append('\n');

        // --- 빈도수 카운팅 ---
        Map<Integer, Long> freq = list.stream()
            .collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        sb.append("빈도수: ").append(freq).append('\n');

        // --- 필터링 ---
        List<Integer> evens = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        sb.append("짝수만: ").append(evens).append('\n');

        // --- 문자열 변환 (joining) ---
        String joined = list.stream().map(String::valueOf).collect(Collectors.joining(","));
        sb.append("문자열 변환: ").append(joined).append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

/*
 * ============================================
 * 코테에서 자주 쓰는 스트림 치트시트
 * ============================================
 *
 * ■ 배열 변환
 *   int[] → List<Integer>   : Arrays.stream(arr).boxed().collect(Collectors.toList())
 *   List<Integer> → int[]   : list.stream().mapToInt(Integer::intValue).toArray()
 *   int[] → Integer[]       : Arrays.stream(arr).boxed().toArray(Integer[]::new)
 *   String[] → List<String> : Arrays.asList(arr) 또는 List.of(arr)
 *
 * ■ 정렬
 *   오름차순  : list.stream().sorted()
 *   내림차순  : list.stream().sorted(Comparator.reverseOrder())
 *   커스텀    : list.stream().sorted((a, b) -> a.length() - b.length())
 *
 * ■ 필터 / 변환
 *   필터     : .filter(x -> x > 0)
 *   변환     : .map(x -> x * 2)
 *   int변환  : .mapToInt(Integer::intValue)
 *   평탄화   : .flatMap(Collection::stream)
 *
 * ■ 집계
 *   합계     : .mapToInt(Integer::intValue).sum()
 *   평균     : .mapToInt(Integer::intValue).average().orElse(0)
 *   개수     : .count()
 *   최대     : .max(Comparator.naturalOrder()).orElse(null)
 *   최소     : .min(Comparator.naturalOrder()).orElse(null)
 *
 * ■ 수집
 *   리스트   : .collect(Collectors.toList())
 *   셋       : .collect(Collectors.toSet())
 *   맵       : .collect(Collectors.toMap(k -> k, v -> v))
 *   그룹핑   : .collect(Collectors.groupingBy(x -> x))
 *   조인     : .collect(Collectors.joining(", "))
 *
 * ■ 프로그래머스에서 자주 쓰는 패턴
 *
 *   // String → char 스트림 처리
 *   str.chars().mapToObj(c -> (char) c).collect(Collectors.toList())
 *
 *   // 2차원 배열 각 행 정렬
 *   Arrays.sort(arr, (a, b) -> a[0] - b[0])
 *
 *   // Map에서 최대 빈도 키 찾기
 *   freq.entrySet().stream()
 *       .max(Map.Entry.comparingByValue())
 *       .map(Map.Entry::getKey).orElse(null)
 *
 *   // 조건 만족하는 첫 번째 원소
 *   list.stream().filter(x -> x > 10).findFirst().orElse(-1)
 *
 *   // 모두 만족 / 하나라도 만족
 *   list.stream().allMatch(x -> x > 0)
 *   list.stream().anyMatch(x -> x > 0)
 */
