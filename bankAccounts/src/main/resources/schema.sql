create table USERS(
    ID int not null AUTO_INCREMENT,
    NAME varchar(100) not null,
    PIN int,
    PRIMARY KEY ( ID )
);

create table ACCOUNTS(
    ID int not null AUTO_INCREMENT,
    ACCNUMBER varchar(100) not null,
    ACCVALUE int,
    USERS_ID int,
    PRIMARY KEY ( ID ),
FOREIGN KEY (USERS_ID) REFERENCES USERS(ID)
);

create table TRANSACTIONS(
    ID int not null AUTO_INCREMENT,
    TIME date,
    TRVALUE int,
    ACCOUNTS_ID int,
    PRIMARY KEY ( ID ),
    FOREIGN KEY (ACCOUNTS_ID) REFERENCES ACCOUNTS(ID)
);
-------------------------------------------
drop ALIAS if exists TO_DATE;
CREATE ALIAS TO_DATE as '
import java.text.*;
@CODE
java.util.Date toDate(String s, String dateFormat) throws Exception {
  return new SimpleDateFormat(dateFormat).parse(s);
}
'