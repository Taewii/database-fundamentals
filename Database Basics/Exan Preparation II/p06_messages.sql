select content, sent_on
from messages
where sent_on > '2014-05-12' and content like '%just%'
order by id desc