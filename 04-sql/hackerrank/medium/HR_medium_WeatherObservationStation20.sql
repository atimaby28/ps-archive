SELECT ROUND(LAT_N, 4)
FROM (
         SELECT LAT_N,
                ROW_NUMBER() OVER(ORDER BY LAT_N) AS rn,
             COUNT(*) OVER() AS cnt
         FROM STATION
     ) t
WHERE rn = FLOOR((cnt + 1) / 2)