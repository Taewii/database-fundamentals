select c.id, c.title, m.id
from chats as c
join messages as m on m.chat_id = c.id
where m.sent_on < '2012-03-26' and c.title like '%x'
order by c.id asc, m.id asc