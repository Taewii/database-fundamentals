update problems as prblms
set tests = (
	case
		when prblms.id % 3 = 0 then (select cat.lngth from (select length(cg.name) as lngth
			from problems as p
			join contests as c on c.id = p.contest_id
			join categories as cg on cg.id = c.category_id
			where p.id = prblms.id) as cat)
		when prblms.id % 3 = 1 then (select sub.idsum from (select sum(s.id) as idsum
			from problems as p
			join submissions as s on s.problem_id = p.id
			where p.id = prblms.id) as sub)
		when prblms.id % 3 = 2 then (select cont.lngth from (select length(c.name) as lngth
			from problems as p
			join contests as c on c.id = p.contest_id
			where p.id = prblms.id) as cont)
	end
)
where prblms.tests = 0