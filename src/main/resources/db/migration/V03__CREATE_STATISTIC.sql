CREATE VIEW vw_statistic AS Select ROW_NUMBER() OVER (order by COUNTRY) as "CODE", c.country as "COUNTRY", c.city as "CITY", count(c.id) as "TIMES_SEARCHED" 
from city c 
group by c.id, c.country, c.city;