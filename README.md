# 3rd Homework of the Java Test Automation Course (2nd block) - Solvd Laba
This repository stores the 3rd homework given by our mentor in the 2nd block of 
the Java Test Automation Course at Solvd Laba. It consists in transferring our
database hierarchy to a Java project in order to make a connection between the
Java application and the MySQL database, applying the MVC design pattern and
using JDBC with a connection pool to have a better control with the connections.

## Explanation

To accomplish the requirements of the homework, I've created firstly the 
config.properties file in the resources folder, to change the values there if is 
necessary instead of modifying the code. Also, there is the sql script to 
initialize the schema in case you don't have it (animals_hierarchy.sql). After,
the three packages of the MVC pattern were created (dao, model, service) and I've
modified some classes from the hierarchy so, they look the same as their respective
table in the database. Then, in the DAO package, I stored all the persistence
files of the project, like the connection pool or the interfaces.
In this case, I selected three entities to work with and made their interfaces in
order to make the code flexible to changes. In each DAO implementation class, 
I overrode the different operations for each entity in the database. Finally,
I used the service package to keep the business logic and to decouple the code 
even more, achieving an only responsibility for each layer in the pattern. Each
service class uses the getFramework static method from the ConnectionPool class 
to get an instance either from the jdbc package or from the mybatis package, 
that has not been implemented yet, but I did this structure to make easier the 
change of framework in the future.

## What did I learn?

Doing this homework I refreshed the MVC pattern concepts and the functionality
of each layer in it. I could understand the need of using interfaces, they
are very useful to make the program more scalable and flexible. This was my
first time working with connection pool, and it seems to be a good way to have
control over our database connections. Regarding JDBC, I already had knowledge
about it but was interesting keep practicing with it. Finally, I can say that
now I know the purpose of the .properties files in the project, because they
save us a lot of time and avoid us to modify the code in case that we want to
change some implementation or configuration.

## Technologies

- Java
- Maven
- MySQL
- JDBC

## Set-Up

To run this project you will need an updated version of Java.
First, clone this repository in a folder of your PC.
You have to put the following command in a terminal:

```bash
  git clone this-repo-url
```
You will need an IDE to open the project folder and, finally, run the 
Main.java file to see the program output.

## Author

- [@Nazareno Bucciarelli](https://github.com/nazabucciarelli)
