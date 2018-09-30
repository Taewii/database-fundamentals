update chats as c
join messages as m on m.chat_id = c.id
set start_date = 
					(
						select sent_on 
						from messages 
						where chat_id = c.id 
						order by sent_on asc 
						limit 1
					)
where start_date > m.sent_on