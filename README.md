# payments-spring-relational
Springboot + Hibernate + MySQL


### Missing tasks
* validar que al agregar una tarjeta que el customer sea cliente del banco, de lo contrario arrojar el errror o agregarlo al banco



## Install JDK
Open terminal and run 
```shell
brew install openjdk@17
```


## H2 Database set up
1. Download H2 Database as https://www.h2database.com/
2. Extract the ZIP file.
   1. Locate the downloaded .zip file (e.g., h2-<version>.zip). 
   2. Extract the ZIP file to a directory of your choice (e.g., /Users/your-username/h2).
3. Start the H2 Database 
   1. Open the Terminal on macOS.
   2. Navigate to the extracted H2 directory: cd /Users/your-username/h2/bin
   3. Start the H2 Console:
```shell
java -jar h2-*.jar
```
Replace h2-*.jar with the actual JAR file name, such as h2-2.2.220.jar.
4. Create the H2 Database 
   1. In the H2 Console login page:
      1. Saved Settings: Generic H2 (Embedded)
      2. Driver Class: org.h2.Driver
      2. JDBC URL: jdbc:h2:~/test
      3. User Name: sa (default).
      4. Password: Leave blank (default).
   2. Click Connect. (This creates an H2 database at the specified path. If you used jdbc:h2:~/test, a testdb.mv.db will appear in your home directory)
5. Access the database 
   1. You can now run SQL commands in the H2 Console interface to interact with the database.
   2. To verify the database file, check the path (e.g., ~/testdb.mv.db).


## MySQL Database set up --- Deprecated

1. Open terminal and run
```shell
brew install mysql
```

2. Start MySQL server
```shell
brew services start mysql
```
Verify the installation by checking the version
```shell
mysql --version
```

3. Connect to MySQL
```shell
mysql -u root
```

4. Set a Password for the Root User

While logged in as the root user in the MySQL prompt, set a password:
```shell
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'yourpassword';
FLUSH PRIVILEGES;
EXIT;
```
Replace 'yourpassword' with a secure password.

5. Create a Database for Your Application:

Log back into MySQL:

```shell
mysql -u root -p
```

6. Create a new database for your Spring Boot app:

```shell
CREATE DATABASE payments_db;
EXIT;
```

## Test the Setup

1. Restart your Spring Boot application.
2.	If everything is configured correctly, the application should connect to MySQL and create tables as needed.



## Promotions
- *InheritanceType.SINGLE_TABLE* Fue seleccionada para evitar joins a la hora de recuperar las entidades
-  Relations:
  - Promotion -> Bank
    - Type: Many to One, fetchtype: yo escogeri lazy, por perfomance ya que no siempre que se haga fetch del banco se necita inmediatamente la información de las promociones, Ownership: Bank, 