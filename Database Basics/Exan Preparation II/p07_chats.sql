select title, is_active
from chats
where (is_active = 0 and length(title) < 5) or title like '__tl%'
order by title desc