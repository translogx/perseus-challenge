# Perseus Take Home Code Test

### How to run this  project

- clone the code base from [Github](https://github.com/translogx/perseus-challenge).
- change directory into project root in your terminal. ``cd perseus-challenge``
- run ``docker-compose up -d --remove-orphans --build``

### How to view data in h2 Database

- Visit ``http://localhost:7001/h2-console``
- on the web form set the following accordingly
    - JDBC url to ``jdbc:h2:mem:testdb``
    - User Name ``root``
    - Password ``root``
  

### How to test endpoints

- open postman collection in the project resource folder using postman to test endpoints

### How to stop the container

- In your terminal, run ``docker-compose down --remove-orphans`` 
