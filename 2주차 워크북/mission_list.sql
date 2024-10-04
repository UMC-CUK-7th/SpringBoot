
SELECT m.mission_explanation, m.point, s.name AS store_name, r.name AS region_name
FROM mission AS m
JOIN store AS s ON m.store_id = s.id
JOIN region AS r ON s.region_id = r.id
WHERE r.name = '{selected_region}' -- 사용자가 선택한 지역
ORDER BY m.created_at DESC  -- 최신 순 정렬
LIMIT 15 OFFSET (n - 1) * 15;  -- 페이징 적용
