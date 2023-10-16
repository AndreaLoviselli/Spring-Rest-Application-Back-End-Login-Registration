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
  
## Installazione, configurazione e utilizzo
Una volta scaricato il progetto è necessario modificare il file application.properties nella directory "resources". Li si trovano le seguenti righe:  

spring.jpa.hibernate.ddl-auto=update  
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/users_db                                            <-sostituire la porta 3306 con la propria porta del Database MySQL e indicarne il nome  
spring.datasource.username=root                                                                                     <-sostituire root con il proprio username MySQL  
spring.datasource.password=password                                                                                 <-sostituire la password del proprio database MySQL  
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver  
spring.jpa.show-sql: true  
  
-------------------------------------------------------------------------  
  
Una volta configurato il file è possibile avviare il progetto e testarlo utilizzando PostMan adattando le chiamate in base alle informazioni presenti nella classe UserController.  
  
Al seguente link è possible accedere alle chiamate PostMan che ho utilizzato per testare l'applicazione:  
  
##POSTMAN  
LIBRERIA POSTMAN ->   
