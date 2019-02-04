Things to do before deploying:
- Setup application.properties correctly
    * spring.jpa.hibernate.ddl-auto=update
- In mysql server:
    * mysql> revoke all on db_example.* from 'springuser'@'localhost';
    * mysql> grant select, insert, delete, update on db_example.* to 'springuser'@'localhost';
    