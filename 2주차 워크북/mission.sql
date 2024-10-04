
SELECT u.name AS user_name, m.mission_explanation, um.created_at, um.updated_at
FROM user_mission AS um
JOIN user AS u ON um.user_id = u.id
JOIN mission AS m ON um.mission_id = m.id
WHERE u.id = {user_id}  -- 특정 사용자의 미션 정보 조회
ORDER BY um.updated_at DESC  -- 최신순 정렬
LIMIT 15 OFFSET (n - 1) * 15;  -- 페이징 적용 (한 페이지당 15개씩)
