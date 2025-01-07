# payments-spring-relational
Springboot + Hibernate + MySQL


### Missing tasks
* validar que al agregar una tarjeta que el customer sea cliente del banco, de lo contrario arrojar el errror o agregarlo al banco



## Set up

### 1. Install JDK
Open terminal and run 
```shell
brew install openjdk@17
```

### 2. H2 Database set up
After running the application 
1. Open the H2 terminal http://localhost:8080/h2-console/
2. Connect to the application using the JDBC URL: jdbc:h2:mem:paymentsdb


### 3. Test the Setup

1. Restart your Spring Boot application.
2.	If everything is configured correctly, the application should connect to MySQL and create tables as needed.

## Informe

### 1. Relaciones

Las decisiones de las direcciones, el fetch type, el cascade type fueron basadas en el dominio de la aplicacion.

#### 1a. Relaciones unidireccionales

- BANK - PROMOTION

Dominio: Agregar una nueva PROMOCION de tipo financing a un BANCO

|Caracteristica|Valor|Comentarios|
|:----|:----|:----|
|Owner|Bank| |
|Inverse|Promotions| |
|Relationship|One(Bank)ToMany(Promotions)| |
|Nombre del atributo en el owner|promotions| |
|Fetch Type|Lazy|No siempre es necesario obtener las promociones correspondientes a un banco|
|Cascade Type|Persist|Cuando se guarde un banco, todas sus promociones tambien seran guardadas|
|Orphan Removal|TRUE|Si se elimina una promocion del banco, no tiene sentido seguir teniendo la promocion porque ya se invalida|

---

- CARD - BANK

|Caracteristica|Valor|Comentarios|
|:----|:----|:----|
|Owner|Card| |
|Inverse|Bank| |
|Relationship|Many(Card)ToOne(Bank)| |
|Nombre del atributo en el owner|bank| |
|Fetch Type|Eager|Solo se esta trayendo un banco por tarjeta, por eso es que computacionalmente no es caro. Ademas, saber el banco por el cual la tarjeta fue emitida, es una informacion crucial.|
|Cascade Type|None|Para evitar cascadeos innecesarios, se utiliza None. Ya que los bancos pueden existir independientemente y las tarjetas solo hacen una referencia hacia el banco|

---

- CARD - CUSTOMER

|Caracteristica|Valor|Comentarios|
|:----|:----|:----|
|Owner|Card| |
|Inverse|Customer| |
|Relationship|Many(Card)ToOne(Customer)| |
|Nombre del atributo en el owner|cardHolder|Se respeta el nombre que aparece en la consigna del trabajo practico|
|Fetch Type|Eager|Solo se esta trayendo un cliente por tarjeta, por eso es que computacionalmente no es caro. Ademas, saber a quien le pertenece cierta tarjeta, es una informacion importante.|
|Cascade Type|None|Para evitar cascadeos innecesarios, se utiliza None. Ya que los clientes pueden existir independientemente y las tarjetas solo hacen una referencia hacia el cliente|

---

- PAYMENT SUMMARY - CARD

|Caracteristica|Valor|Comentarios|
|:----|:----|:----|
|Owner|Payment Summary| |
|Inverse|Card| |
|Relationship|Many(PaymentSummary)ToOne(Card)| |
|Nombre del atributo en el owner|card| |
|Fetch Type|Eager|Cuando un cliente pide el resumen, es normal que aparezca la tarjeta con la que fue realizada el pago en el mismo resumen|
|Cascade Type|None|Para evitar cascadeos innecesarios, se utiliza None. Ya que las tarjetas pueden existir independientemente y los resumenes solo hacen una referencia hacia la tarjeta|

---

- PAYMENT SUMMARY - QUOTA

Dominio: Generar el total de pago de un mes dado, informando las compras correspondientes




- PAYMENT SUMMARY - PURCHASE SINGLE PAYMENT

Dominio: Generar el total de pago de un mes dado, informando las compras correspondientes



### 1b. Relaciones bidireccionales

- BANK - CUSTOMER

Dominio: Obtener un listado con el numero de clientes de cada banco

- PURCHASE - CARD

Dominio: Obtener la informacion de las 10 tarjetas con mas compras.

- PUCHASE - PROMOTION

Dominio 1: Eliminar una promocion a traves de su codigo tener en cuenta que esta puede haber sido aplicada a alguna compra

Dominio 2: Obtener la promocion mas utilizada en las compras registradas

- PURCHASE MONTHLY PAYMENTS - QUOTA

Dominio: Obtener la informacion de una compra, incluyendo el listado de cuotas si esta posee


### 1c. Relaciones parent-children

- Financing y Discount son hijos de Promotion.
- Purchase Monthly Payments y Purchase Single Payment son hijos de Purchase.

### 2. Entidades

### 3. Transacciones

3

## Promotions
- *InheritanceType.SINGLE_TABLE* Fue seleccionada para evitar joins a la hora de recuperar las entidades
-  Relations:
  - Promotion -> Bank
    - Type: Many to One, fetchtype: yo escogeri lazy, por perfomance ya que no siempre que se haga fetch del banco se necita inmediatamente la información de las promociones, Ownership: Bank, 


