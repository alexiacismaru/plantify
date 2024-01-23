# Plantify
### Team Members:
- Mocanu Paul-Cristian
- Nohra Firas
- Etman Max
- Cismaru Alexia
- Hermans Elliot

## Configuration instructions
A PostgreSQL database needs to be created with the name plantify.

The schema.sql and the mock.sql files need to be run onto the database


In the application.properties file you need to put the username of your database in the spring.datasouce.username variable.
In the same file, also put the password to the database in the spring.datasource.password variable.



## Dependencies

Lombok - used for getters and setters \
Gson - ease of use for Json files for the communication with the arduino \
Thymeleaf - framework used for implementing the webapp \
Jdbc - driver used for making it possible to communicate with the database from the Java application \
Jpa - ease of use for communicating with the database by using hibernate and object relational mapping 

## Interaction with other systems


# Wifi Module

The webapp communicates with the arduino through the wifi module. \
This is a stand-alone board which can connect to the internet by wifi and to the arduino by jumper cables.
The two boards use UART to communicate between themselves.

# Arduino

The webapp and the physical product work independently of each other when there is no internet connection for the physical product. \
When there is internet, the wifi module requests the readings from the arduino. \
These readings are formatted as a Json and send through a Post request to the backend using an Octet-Stream as the format for the data. \
In the Java backend we are using a REST api PostMapping method to handle that request and process the octet stream.

# Postgres

Postgres is the DBMS we are using for this project. We use JPA and queries to make requests to the database.

Documentation of the interface with other systems (Arduino, ...?)

### Application.properties file

