Things to do before deploying:
- Set up mysql db
- Implement Security feature (login - https://www.baeldung.com/spring-security-login )
- Setup application.properties correctly
    * spring.jpa.hibernate.ddl-auto=update
- In mysql server:
    * mysql> revoke all on db_example.* from 'springuser'@'localhost';
    * mysql> grant select, insert, delete, update on db_example.* to 'springuser'@'localhost';
    