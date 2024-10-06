
SELECT * FROM member as m
JOIN review as r ON me.id = r.member_id
WHERE m.id = {회원 id}
  AND r.created_at < {현재 커서 값}.created_at   --created_at 존재한다고 가정..
ORDER BY r.created_at DESC
LIMIT 15;
