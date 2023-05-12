---- Insert Users ----
insert into users values(100,'Alla',1234);
insert into users values(200,'Akaki',1111);
----- Insert Users Accounts ----
insert into accounts values(101,'by1',100,100);
insert into accounts values(102,'by2',200,100);
insert into accounts values(103,'by3',300,100);
insert into accounts values(104,'by4',400,200);
insert into accounts values(105,'by5',500,200);
insert into accounts values(106,'by6',600,200);
---- Insert Account Transactions ----
insert into transactions values(100,TO_DATE('03/05/2023 10:00', 'DD/MM/YYYY HH:MM'),-100,101);
insert into transactions values(101,TO_DATE('03/05/2023 10:15', 'DD/MM/YYYY HH:MM'),-200,101);
insert into transactions values(102,TO_DATE('03/05/2023 10:30', 'DD/MM/YYYY HH:MM'),500,101);
insert into transactions values(103,TO_DATE('03/05/2023 10:00', 'DD/MM/YYYY HH:MM'),-1000,102);
insert into transactions values(104,TO_DATE('03/05/2023 10:15', 'DD/MM/YYYY HH:MM'),-2000,102);
insert into transactions values(105,TO_DATE('03/05/2023 10:30', 'DD/MM/YYYY HH:MM'),5000,102);
insert into transactions values(106,TO_DATE('03/05/2023 10:00', 'DD/MM/YYYY HH:MM'),-3100,103);
insert into transactions values(107,TO_DATE('03/05/2023 10:15', 'DD/MM/YYYY HH:MM'),-3200,103);
insert into transactions values(108,TO_DATE('03/05/2023 10:30', 'DD/MM/YYYY HH:MM'),5030,103);
insert into transactions values(109,TO_DATE('03/05/2023 10:00', 'DD/MM/YYYY HH:MM'),-10,104);
insert into transactions values(111,TO_DATE('03/05/2023 10:15', 'DD/MM/YYYY HH:MM'),-20,104);
insert into transactions values(112,TO_DATE('03/05/2023 10:30', 'DD/MM/YYYY HH:MM'),700,104);
insert into transactions values(113,TO_DATE('03/05/2023 10:00', 'DD/MM/YYYY HH:MM'),-100,105);
insert into transactions values(114,TO_DATE('03/05/2023 10:15', 'DD/MM/YYYY HH:MM'),-200,105);
insert into transactions values(115,TO_DATE('03/05/2023 10:30', 'DD/MM/YYYY HH:MM'),500,105);
insert into transactions values(116,TO_DATE('03/05/2023 10:00', 'DD/MM/YYYY HH:MM'),-4100,106);
insert into transactions values(117,TO_DATE('03/05/2023 10:15', 'DD/MM/YYYY HH:MM'),-4200,106);
insert into transactions values(118,TO_DATE('03/05/2023 10:30', 'DD/MM/YYYY HH:MM'),5040,106);