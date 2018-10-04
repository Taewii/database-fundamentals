update problems as p
join contests as c on c.id = p.contest_id
join categories as cg on cg.id = c.category_id
set tests = (
	case
		when p.id % 3 = 0 then length(cg.name)
		when p.id % 3 = 1 then (select sum(s.id) from submissions as s where s.problem_id = p.id)
		when p.id % 3 = 2 then length(c.name) 
	end
)
where p.tests = 0 