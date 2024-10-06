// 마이페이지 개인정보 조회
SELECT nickname, email, phone, point FROM user AS u
WHERE u.id = ?;

// 마이페이지-작성한 리뷰
SELECT * FROM review AS r
WHERE r.user_id = ?
ORDER BY r.updated_at DESC LIMIT 10;