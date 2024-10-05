SELECT * FROM mission as ms
JOIN store as st ON ms.store_id=st.id
JOIN region as r ON st.region_id=r.id
LEFT JOIN
		member_mission as mm ON ms.id=mm.mission_id AND mm.member_id='lin'
WHERE r.id='서울'
		AND ms.status='도전가능'
		AND (mm.status != '진행중')
		AND created_at < {현재커서값}.created_at
ORDER BY mm.created_at DESC;
LIMIT 15;
