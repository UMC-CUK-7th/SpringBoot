// 진행중인 미션
SELECT * FROM mission as m
JOIN user_mission AS um ON u.id = um.user_id
WHERE m.status = 0
ORDER BY um.updated_at DESC LIMIT 10;

// 진행완료한 미션
SELECT * FROM mission AS m
JOIN user_mission AS um ON u.id = um.user_id
WHERE um.status = 1
ORDER BY um.updated_at DESC LIMIT 10;