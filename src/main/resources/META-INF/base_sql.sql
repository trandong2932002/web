select *
from customer;
select *
from employee;
select *
from manager;

select *
from serviceprovider;
select *
from serviceaccount;

select *
from transactionaccount;
select *
from activity;

select *
from transfer;
select *
from savings;

select *
from savingsinterest;

select *
from verification;
select *
from verificationcode;

--
-- BANK
--

-- savings interest
insert into savingsinterest
values (0, 0.05, '1 tháng', 0.008, 0);
insert into savingsinterest
values (1, 0.05, '3 tháng', 0.008, 1);
insert into savingsinterest
values (2, 0.06, '6 tháng', 0.008, 2);
insert into savingsinterest
values (3, 0.06, '12 tháng', 0.008, 3);
insert into savingsinterest
values (4, 0.07, '24 tháng', 0.008, 4);
insert into savingsinterest
values (5, 0.07, '36 tháng', 0.008, 5);

-- bank account for transfer
insert into customer
values (0, '', '', '', '', '', '', '', null);
insert into transactionaccount
values (0, '', 100000000000, 0);

-- bank manager
insert into manager
values (-1, null, null, null, 123, 1234567890, null);


--
-- SERVICES PROVIDER
--

-- service provider
insert into serviceprovider
values (1, 'Điện 1', 0, 100);
insert into serviceprovider
values (2, 'Điện 3', 0, 101);
insert into serviceprovider
values (3, 'Điện 2', 0, 102);
insert into serviceprovider
values (4, 'Nước 1', 1, 103);
insert into serviceprovider
values (5, 'Nước 2', 1, 104);
insert into serviceprovider
values (6, 'Nước 3', 1, 105);
insert into serviceprovider
values (7, 'Internet 1', 2, 106);
insert into serviceprovider
values (8, 'Internet 2', 2, 107);
insert into serviceprovider
values (9, 'Internet 3', 2, 108);

-- customer
insert into customer
values (100, null, 'Điện 1', null, 123, 101, null, null, null);
insert into customer
values (102, null, 'Điện 3', null, 123, 103, null, null, null);
insert into customer
values (101, null, 'Điện 2', null, 123, 102, null, null, null);
insert into customer
values (103, null, 'Nước 1', null, 123, 201, null, null, null);
insert into customer
values (104, null, 'Nước 2', null, 123, 202, null, null, null);
insert into customer
values (105, null, 'Nước 3', null, 123, 203, null, null, null);
insert into customer
values (106, null, 'Internet 1', null, 123, 301, null, null, null);
insert into customer
values (107, null, 'Internet 2', null, 123, 302, null, null, null);
insert into customer
values (108, null, 'Internet 3', null, 123, 303, null, null, null);

-- transaction account
insert into transactionaccount
values (101, 10000001, 0, 100);
insert into transactionaccount
values (102, 10000002, 2, 101);
insert into transactionaccount
values (103, 10000003, 0, 102);
insert into transactionaccount
values (104, 10000004, 0, 103);
insert into transactionaccount
values (105, 10000005, 0, 104);
insert into transactionaccount
values (106, 10000006, 0, 105);
insert into transactionaccount
values (107, 10000007, 0, 106);
insert into transactionaccount
values (108, 10000008, 0, 107);
insert into transactionaccount
values (109, 10000009, 0, 108);


-- service account
-- insert into serviceprovider
-- values (1, 123456, 0, 0, 1, 2);

