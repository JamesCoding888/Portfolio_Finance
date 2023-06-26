CREATE SCHEMA `portfolio` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci ;

select * from portfolio.classify;
select * from portfolio.investor;
select * from portfolio.portfolio;
select * from portfolio.tstock;
select * from portfolio.watch;
select * from portfolio.watch_tstock;

update portfolio.investor set balance=888888  where id=2;
insert into portfolio.tstock (name, symbol) values ('台積電', '2350.TW');
delete from portfolio.tstock where id = 3;
