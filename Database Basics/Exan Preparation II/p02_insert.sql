insert into messages(content, sent_on, chat_id, user_id)
select 
concat(u.age, '-', u.gender, '-', l.latitude, '-', l.longitude) as content,
'2016-12-15' as sent_on,
(
	case
		when gender = 'F' then ceil(sqrt(age * 2))
		when gender = 'M' then ceil(pow(age / 18, 3)) 
	end
) as chat_id,
u.id as user_id
from users as u
join locations as l on l.id = u.location_id
where u.id between 10 and 20;