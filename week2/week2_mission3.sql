// 지역 이름 조회
SELECT name FROM region AS r
WHERE r.id = ?;

// 달성한 미션 개수 조회
SELECT count(*) FROM user_mission AS um
JOIN mission AS m ON um.mission_id = m.id
JOIN store AS s ON m.store_id = s.id
WHERE um.user_id = ? AND um.status = 1;

// MY MISSION 조회
SELECT s.name, s.food_id, m.* FROM user AS u
JOIN user_mission AS um ON um.user_id = u.id
JOIN mission AS m ON m.id = um.mission_id
JOIN store AS s ON s.id = m.store_id
WHERE u.id = ? AND um.status = 0 AND region.id = ?
ORDER BY m.deadlline, um.updated_at DESC limit 10;