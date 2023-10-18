## CRUD-JDBC
Консольное CRUD приложение, которое взаимодействует с БД и позволяет выполнять все CRUD операции над сущностями:
- Writer(Long id, String firstName, String lastName, List<Post> posts, Region region)
- Post (Long id, String content, Long created, Long updated)
- Region (Long id, String name)

##  Database diagram                                                                                                                                                 
<img src="https://github.com/git4ef/CRUD-JDBC/assets/140438465/41109ff5-f837-4031-ad4d-b1fdd01dcbbb" width="600"> 

## Class diagram
<img src="https://github.com/git4ef/CRUD-JDBC/assets/140438465/4daad626-0e52-45c6-92fc-3ca50933e6a3" width="600">  

## Технологии
Java, Postgresql, JDBC, Maven, Liquibase, JUnit, Mockito

## Использование
Для локального запуска проекта и подключению к БД необходимо указать в консоле следующие параметры: 
1. URL
2. login
3. password
