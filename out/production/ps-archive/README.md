# SQL 코딩테스트 정리

프로그래머스 SQL 고득점 Kit 기준 정리

---

## 01. SELECT / WHERE / ORDER BY

```sql
-- 기본 조회
SELECT column1, column2
FROM table
WHERE condition
ORDER BY column1 ASC, column2 DESC;

-- NULL 처리
SELECT IFNULL(column, '대체값') FROM table;
SELECT COALESCE(col1, col2, '기본값') FROM table;

-- LIKE 패턴
WHERE name LIKE '김%'      -- 김으로 시작
WHERE name LIKE '%호'      -- 호로 끝남
WHERE name LIKE '%민%'     -- 민 포함

-- IN / BETWEEN
WHERE age IN (20, 25, 30)
WHERE age BETWEEN 20 AND 30

-- CASE WHEN
SELECT
    CASE
        WHEN score >= 90 THEN 'A'
        WHEN score >= 80 THEN 'B'
        ELSE 'C'
    END AS grade
FROM scores;

-- LIMIT / OFFSET
SELECT * FROM table LIMIT 10 OFFSET 5;

-- DISTINCT
SELECT DISTINCT city FROM users;
```

---

## 02. JOIN

```sql
-- INNER JOIN (교집합)
SELECT a.*, b.*
FROM table_a a
INNER JOIN table_b b ON a.id = b.a_id;

-- LEFT JOIN (왼쪽 기준, 오른쪽 없으면 NULL)
SELECT a.*, b.*
FROM table_a a
LEFT JOIN table_b b ON a.id = b.a_id;

-- RIGHT JOIN
SELECT a.*, b.*
FROM table_a a
RIGHT JOIN table_b b ON a.id = b.a_id;

-- SELF JOIN (같은 테이블 조인)
SELECT e.name AS employee, m.name AS manager
FROM employees e
LEFT JOIN employees m ON e.manager_id = m.id;

-- 다중 JOIN
SELECT *
FROM orders o
JOIN customers c ON o.customer_id = c.id
JOIN products p ON o.product_id = p.id;
```

---

## 03. GROUP BY / HAVING / 집계함수

```sql
-- 집계함수
SELECT
    COUNT(*),           -- 전체 행 수
    COUNT(column),      -- NULL 제외 행 수
    SUM(amount),
    AVG(score),
    MAX(price),
    MIN(price)
FROM table;

-- GROUP BY
SELECT department, COUNT(*) AS cnt
FROM employees
GROUP BY department
HAVING COUNT(*) >= 5
ORDER BY cnt DESC;

-- GROUP_CONCAT (MySQL)
SELECT department, GROUP_CONCAT(name ORDER BY name SEPARATOR ', ')
FROM employees
GROUP BY department;
```

---

## 04. 서브쿼리 (Subquery)

```sql
-- WHERE 절 서브쿼리
SELECT * FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees);

-- IN 서브쿼리
SELECT * FROM orders
WHERE customer_id IN (SELECT id FROM customers WHERE city = '서울');

-- EXISTS
SELECT * FROM customers c
WHERE EXISTS (
    SELECT 1 FROM orders o WHERE o.customer_id = c.id
);

-- FROM 절 서브쿼리 (인라인 뷰)
SELECT dept, avg_sal
FROM (
    SELECT department AS dept, AVG(salary) AS avg_sal
    FROM employees
    GROUP BY department
) sub
WHERE avg_sal > 50000;

-- 스칼라 서브쿼리 (SELECT 절)
SELECT name,
    (SELECT COUNT(*) FROM orders o WHERE o.customer_id = c.id) AS order_count
FROM customers c;
```

---

## 05. 윈도우 함수 (Window Function)

```sql
-- ROW_NUMBER: 고유 순번
SELECT name, salary,
    ROW_NUMBER() OVER (ORDER BY salary DESC) AS rn
FROM employees;

-- RANK / DENSE_RANK
SELECT name, salary,
    RANK() OVER (ORDER BY salary DESC) AS rnk,         -- 1,2,2,4
    DENSE_RANK() OVER (ORDER BY salary DESC) AS d_rnk   -- 1,2,2,3
FROM employees;

-- PARTITION BY (그룹별 순위)
SELECT department, name, salary,
    ROW_NUMBER() OVER (PARTITION BY department ORDER BY salary DESC) AS dept_rank
FROM employees;

-- LAG / LEAD (이전/다음 행)
SELECT date, sales,
    LAG(sales, 1) OVER (ORDER BY date) AS prev_sales,
    LEAD(sales, 1) OVER (ORDER BY date) AS next_sales
FROM daily_sales;

-- 누적합
SELECT date, amount,
    SUM(amount) OVER (ORDER BY date) AS cumulative_sum
FROM transactions;

-- NTILE (N등분)
SELECT name, salary,
    NTILE(4) OVER (ORDER BY salary DESC) AS quartile
FROM employees;
```

---

## 06. 문자열 / 날짜 함수

```sql
-- 문자열 함수
SELECT
    SUBSTRING(name, 1, 3),           -- 부분 문자열
    CONCAT(first, ' ', last),        -- 문자열 합치기
    UPPER(name), LOWER(name),
    TRIM(name),
    REPLACE(phone, '-', ''),
    LENGTH(name)
FROM table;

-- 날짜 함수 (MySQL)
SELECT
    DATE_FORMAT(created_at, '%Y-%m-%d') AS date,    -- 2025-01-15
    DATE_FORMAT(created_at, '%Y-%m') AS month,      -- 2025-01
    YEAR(created_at),
    MONTH(created_at),
    DAY(created_at),
    HOUR(created_at),
    DATEDIFF(end_date, start_date) AS diff_days,
    DATE_ADD(created_at, INTERVAL 7 DAY) AS week_later
FROM table;

-- 날짜 필터
WHERE created_at BETWEEN '2025-01-01' AND '2025-12-31'
WHERE DATE_FORMAT(created_at, '%Y-%m') = '2025-01'
WHERE YEAR(created_at) = 2025 AND MONTH(created_at) = 1
```
