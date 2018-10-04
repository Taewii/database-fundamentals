insert into submissions(passed_tests, problem_id, user_id)
select 
ceil(sqrt(pow(length(name), 3)) - length(name)) as passed_tests,
id as problem_id,
ceil((id * 3) / 2) as user_id
from problems
where id between 1 and 10; 