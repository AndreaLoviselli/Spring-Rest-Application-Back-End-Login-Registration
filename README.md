# # Spring Rest Application Back-End // Login & Registration

    
## Descrizione
Applicazione Back-End basata su Spring Boot per la gestione di utenti.   
Include operazioni CRUD, funzionalità di Login e Registratione in relazione ad un Database MySQL.  
Le password vengono criptate tramite Spring Security mentre le chiamate accessibili in relazione al ruolo (ADMIN || USER).    
  
## Framework e Librerie utilizzate
-SpringBoot / Spring  
-Spring Security  
-Spring Web  
-MySQL Driver / MySQL / Spring Data JPA  
-gradle, DBNavigator  
-PostMan  
-JDK 17  
  
## Installazione, configurazione 

Una volta scaricato il progetto è necessario modificare il file **application.properties** nella directory "resources". Li si trovano le seguenti righe:  

spring.jpa.hibernate.ddl-auto=update  
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/users_db                                             1️⃣ **sostituire 3306 con la propria porta del DB MySQL e indicarne il nome**      
spring.datasource.username=root                                                                                      2️⃣ **sostituire root con il proprio username MySQL**     
spring.datasource.password=password                                                                                  3️⃣ **sostituire la password del proprio database MySQL**     
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver  
spring.jpa.show-sql: true  

4️⃣ Connettete con DBNavigator Intellij al Database MySQL

5️⃣ Potete inserire ora su MySQL i seguenti comandi in ordine

CREATE database users_db;
use users_db;
create table users(
                        username varchar(50) not null primary key,
                        password varchar(500) not null,
                        enabled boolean not null
);

create table authorities (
                              username varchar(50) not null,
                              authority varchar(50) not null,
                              constraint fk_authorities_users foreign key(username) references users(username)
);

create unique index ix_auth_username on authorities (username, authority);

INSERT INTO users (id, username, password, enabled)
VALUES (1, 'admin', '$2a$10$ZWYGqb56wF3sduxLNd/qmO.ywhQW.2tKqmNXjeaUyEEAjcHJUgmES', true);

INSERT INTO authorities (username, authority)
VALUES ('admin', 'ROLE_ADMIN');

6️⃣Potete avviare l'applicazione😊

## Utilizzo
Una volta configurato il progetto è possibile testarlo utilizzando PostMan adattando le chiamate in base alle informazioni presenti nella classe UserController.

**PER EFFETTUARE OGNI CHIAMATA USARE LE CREDENZIALI PRIMA INSERITE IN MYSQL "username" : "admin" / "password" : "admin"**
    
Per velocizzare il testing, al seguente link è possible accedere alle chiamate PostMan che ho utilizzato per testare l'applicazione:  
  
##POSTMAN  
LIBRERIA POSTMAN ->   https://progettosbloviselliandrea.postman.co/workspace/New-Team-Workspace~b2d02203-2a9a-47ed-867e-79b9a8e2c608/collection/27730869-eee52046-9ec5-4363-9210-a4e0c0f5f41f?action=share&creator=27730869
