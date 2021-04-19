### Aggregate functions

常见的聚集函数：

```mysql
avg()
count( (distinct)xx ) ##如果xx是某一项的子类，如xx.yy 那么distinct作用的是yy
count(*) ##有的数据一共有多少行，记录的条数

```

---

### Group By

```mysql
group by xxx
```

1. 标签xxx相同的被分为一组，而再使用聚集函数的时候，聚集函数的作用域就是在组内

3. Attributes in select clause outside of aggregate functions **must** appear in **group by** list，否则编译的时候就无法通过

   ```mysql
   select dept_name, ID, avg(salary)
   from instructor
   group by dept_name
   ##因为一个dept_name中可能有很多ID，但是avg按组输出。
   ```

   

### Having clause

---

```mysql
select dept_name, ID, avg(salary)
from instructor
group by dept_name
having avg(salary)>42000

##类似于where，但是这里不能使用。having专门用于group by，留下特定条件的分组
```



#### where和having的区别

```mysql
select id, count(distinct course_id)
from takes
where year=2019##找到2019年的学生
group by id##将找到的2019年的学生根据学号分组（也就是将上过的课根据学生分组）
having count(distinct course_id) < 3
```

1. where内如果有聚集函数，基本就错误了，因为这时候还没有分组

2. having内常见聚集函数，因为已经分好组了
3. where一定是在having之前做的

**程序中的执行顺序：**

1. from 从数据库中拿到数据
2. where 在拿原数据带条件拿
3. group by 对拿到的原数据分组
4. select/having 取出和分类基本同时进行





### NULL  &   Aggregates

---

1. 聚集函数中，NULL一般是忽略掉的（这也是为什么要用count(*)，因为count会忽略掉null
2. 如果聚集函数计算无对象，那么就还是返回null



### Subqueries

---

```mysql
select distinct course_id
from section 
where semester='Fall'and year=2009 and 
	course_id in (select course_id
                  from section
                  where semester="Spring"
                  and year = 2010);
 
```

1. 嵌套子查寻，据此来判断一个成员是不是在某个集合中

2. 上述代码属于： 
   不相关子查寻——内层和外层之间没有参数传递，两者之间相互独立
3. 相关子查寻——效率低





### Comparison

---

some using: **> some** clauses

#### about some clause

> F <comp> some r$\leftrightarrow\exist t\in r$ such that (F <comp>t) where <comp> can be: <,<=,>,>=,<>

**=some**(== **in**)

**!=some **(!= **not in**)



#### about all clause

> F<comp> all $r\leftrightarrow \any $





### Test for Empty Relations

---

**exists** ： r不是空集

**not exists**：r是空集

```mysql
select course)id
from section as S
where semester='Fall' and year=2009 and 
	exists(select *
          from section as T
          where semester='Spring' and year=2010
          and S.course_id=T.course_id);
```



**判断是不是子集**：可以转换为except 再判断是不是空集

