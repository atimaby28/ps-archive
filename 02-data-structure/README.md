# 자료구조 (Data Structure)

라이브 코테에서 자주 사용하는 자료구조 8개 정리

---

## 01. 배열 / 리스트

**Java 컬렉션**
- `int[]`: 고정 크기, 기본형
- `ArrayList<>`: 가변 크기, 랜덤 접근 O(1)
- `LinkedList<>`: 삽입/삭제 O(1), 접근 O(n)

**코테 포인트**
- 대부분 `ArrayList` 사용
- 배열 복사: `Arrays.copyOf()`, `System.arraycopy()`
- 정렬: `Arrays.sort()`, `Collections.sort()`

---

## 02. 스택 (Stack)

**Java 사용법**
- `Deque<Integer> stack = new ArrayDeque<>();` (Stack 클래스보다 권장)
- `push()`, `pop()`, `peek()`, `isEmpty()`

**대표 유형**
- 괄호 검사
- 후위 표기식
- 모노톤 스택 (NGE, 히스토그램)

---

## 03. 큐 / 덱 (Queue / Deque)

**Java 사용법**
- `Queue<Integer> q = new LinkedList<>();` → `offer()`, `poll()`, `peek()`
- `Deque<Integer> dq = new ArrayDeque<>();` → `offerFirst/Last()`, `pollFirst/Last()`

**대표 유형**
- BFS 탐색
- 슬라이딩 윈도우 최댓값 (덱)
- 프로세스 스케줄링

---

## 04. HashMap / HashSet

```java
Map<String, Integer> map = new HashMap<>();
map.put(key, value);
map.getOrDefault(key, 0);
map.containsKey(key);
map.entrySet(); // 순회

Set<Integer> set = new HashSet<>();
set.add(value);
set.contains(value);
```

**코테 포인트**
- 빈도수 카운팅 → `getOrDefault(key, 0) + 1`
- 중복 제거 → `HashSet`
- 순서 필요 → `LinkedHashMap`, `TreeMap`

---

## 05. 우선순위 큐 (Priority Queue / Heap)

```java
// 최소힙 (기본)
PriorityQueue<Integer> minPQ = new PriorityQueue<>();

// 최대힙
PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

// 커스텀 정렬
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
```

**코테 포인트**
- 다익스트라 최단경로
- K번째 최솟값/최댓값
- 중앙값 구하기 (최소힙 + 최대힙)

---

## 06. 트리 (Tree)

**순회 방식**
- 전위 (Pre): 루트 → 왼쪽 → 오른쪽
- 중위 (In): 왼쪽 → 루트 → 오른쪽
- 후위 (Post): 왼쪽 → 오른쪽 → 루트
- 레벨 (Level): BFS

**코테 포인트**
- 이진 트리 높이, LCA (최소 공통 조상)
- 세그먼트 트리 (구간 합/최솟값)

---

## 07. 그래프 (Graph)

```java
// 인접 리스트 (대부분 이것 사용)
List<List<Integer>> adj = new ArrayList<>();
for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

// 인접 행렬 (N ≤ 500 이하일 때)
int[][] adj = new int[n + 1][n + 1];

// 간선 리스트 (크루스칼 등)
int[][] edges = new int[m][3]; // {from, to, weight}
```

**코테 포인트**
- 희소 그래프 → 인접 리스트
- 밀집 그래프 → 인접 행렬
- 방향/무방향, 가중치 유무 확인

---

## 08. 트라이 (Trie)

**핵심 개념**
- 문자열 집합을 트리 형태로 저장
- 삽입/검색 O(문자열 길이)

**코테 포인트**
- 자동완성, 문자열 검색
- 카카오 기출 빈출
