
UPDATE member SET name={변경값},
									updated_at = CURRENT_TIMESTAMP
	WHERE id={member의 id값};
