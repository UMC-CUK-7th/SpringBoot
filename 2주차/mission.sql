SELECT * FROM member as m
JOIN member_mission as mm ON m.id = mm.member_id
JOIN mission as ms ON mm.mission_id = ms.id
WHERE m.id = 'llin'
  AND mm.status = '진행중';   -- 혹은 진행완료
  AND mm.created_at < {현재 커서 값}.created_at
ORDER BY mm.created_at DESC
LIMIT 15;
