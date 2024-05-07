Получить всех студентов, возраст которых находится между 10 и 20 (можно подставить любые числа, главное, чтобы нижняя граница была меньше верхней).
select *
from students
where age between 10 and 20;

Получить всех студентов, но отобразить только список их имен.
select name from students;

Получить всех студентов, у которых в имени присутствует буква «О» (или любая другая).
select * from students where name like '%a%';

Получить всех студентов, у которых возраст меньше идентификатора.
select * from students where age < id;

Получить всех студентов упорядоченных по возрасту.
select * from students order by age;