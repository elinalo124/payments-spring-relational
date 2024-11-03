# payments-spring-relational
Springboot + Hibernate + MySQL


### Missing tasks
* validar que al agregar una tarjeta que el customer sea cliente del banco, de lo contrario arrojar el errror o agregarlo al banco



## Install JDK
Open terminal and run 
```shell
brew install openjdk@17
```

## Database set up

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
