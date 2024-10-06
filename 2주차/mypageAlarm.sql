SELECT * FROM member_agree as ma
JOIN member as m ON ma.member_id=m.id
JOIN terms as t ON ma.terms_id=t.id
WHERE m.id={회원 id}
