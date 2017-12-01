#�·������ֶ�
alter table dsj_new_house_directory_auth add image varchar(255);

alter table dsj_agent modify column skill varchar(200);
#�·����÷����ֶ�
UPDATE dsj_new_house_directory_auth a
SET a.image = (
	SELECT
		p.picture_url
	FROM
		(
			SELECT
				picture_url,
				object_id AS houseId
			FROM
				dsj_new_house_picture_auth
			WHERE
				delete_flag = 2
			ORDER BY
				picture_status,
				picture_frist
		) p
	WHERE
		p.houseId = a.id
	GROUP BY
		p.houseId
); 

##### executed ####


