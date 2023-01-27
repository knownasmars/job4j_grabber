select c.name, count(*) 
from person p 
left join company c 
on p.company_id = c.id
group by c.name
having count(p.company_id) 
	= (select count(*) as cid
		from person p
		group by p.company_id
		order by cid desc
		limit 1);
		