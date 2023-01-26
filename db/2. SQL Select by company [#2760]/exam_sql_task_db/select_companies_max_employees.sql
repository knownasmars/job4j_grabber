select c.name, count(*) 
from person p 
left join company c 
on p.company_id = c.id
group by c.name
having c.count = (select max(t.count) from 
					(select c.name, count(*)
						from person p 
						left join company c 
						on p.company_id = c.id
						group by c.name 
						having count(p.name) = 3) as t);