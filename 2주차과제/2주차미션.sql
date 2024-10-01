SELECT m.points,us.point,s.name
FROM mission m
JOIN user_mission us ON m.id=um.id
JOIN store s ON m.store_id=s.id
WHERE us.status IN ('ING','COMPLETED')
ORDER BY us.created_at DESC
LIMIT 10;

INSERT INTO review (user_id,store_id,star,test)
VALUES(123,456,5,'행복');
INSERT INTO review_content (review_image)
VALUES('image_url.jpg');

SELECT m.points, us.status,us.completed_mission_count
FROM mission m,user_mission
JOIN store s ON m.store_id
WHERE s.region='역곡동' and us.status='ING'
order by m.created_at DESC
LIMIT 10 OFFSET 0;

SELECT u.name,u.point,u.email,r.id,r.test
FROM user u
LEFT JOIN review r
ON u.id=r.user_id
WHERE u.id= [누군가];
