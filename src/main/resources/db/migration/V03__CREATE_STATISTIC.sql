CREATE VIEW APISTARTER.VW_STATISTIC AS Select ROW_NUMBER() OVER (order by country) as "CODE", c.country as "COUNTRY", c.city as "CITY", count(c.id) as "TIMES_SEARCHED" 
from apistarter.city c 
group by c.id, c.country, c.city;