---- Create Tables ----
create table USERS(
                      ID long not null AUTO_INCREMENT,
                      NAME varchar(100) not null,
                      PIN int not null,
                      PRIMARY KEY ( ID )
);

create table ACCOUNTS(
                         ID long not null AUTO_INCREMENT,
                         ACCNUMBER varchar(100) not null,
                         ACCVALUE bigint,
                         USERS_ID long not null,
                         PRIMARY KEY ( ID ),
                         FOREIGN KEY (USERS_ID) REFERENCES USERS(ID)
);

create table TRANSACTIONS(
                             ID long not null AUTO_INCREMENT,
                             TIME date not null,
                             TRVALUE bigint,
                             ACCOUNTS_ID long not null,
                             PRIMARY KEY ( ID ),
                             FOREIGN KEY (ACCOUNTS_ID) REFERENCES ACCOUNTS(ID)
);
---- Set additional script ----
drop ALIAS if exists TO_DATE;
CREATE ALIAS TO_DATE as '
import java.text.*;
@CODE
java.util.Date toDate(String s, String dateFormat) throws Exception {
  return new SimpleDateFormat(dateFormat).parse(s);
}
';

