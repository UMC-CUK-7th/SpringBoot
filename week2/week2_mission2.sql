// 가게에 작성된 리뷰 보여주기
SELECT u.nickname, review.* FROM review AS r
JOIN user AS u ON r.user_id = u.id
JOIN store AS s ON r.store_id = s.id
WHERE u.status = 1
ORDER BY r.updated_at, u.created_at DESC LIMIT 10;

// 가게에 리뷰 작성하기
INSERT INTO review (user_id, store_id, content, rating)
VALUES (?, ?, ?, ?);