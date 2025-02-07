# payments-spring-relational
Springboot + Hibernate + MySQL

## Autores

- Carolina Chavez
- Elina Lo

## Set up

### 1. Instalar JDK
Abrir la terminal y correr el siguiente comando
```shell
brew install openjdk@17
```

### 2. Configurar la base de datos H2
Luego de correr la aplicacion:
1. Abrir la terminal de H2 http://localhost:8080/h2-console/
2. Conectarse a la base de datos de la aplicacion utilizando el JDBC URL: jdbc:h2:mem:paymentsdb


### 3. Test the Setup

1. Reiniciar la aplicacion
2. Si la configuracion fue correcta, la aplicacion deberia conectarse a la base de datos H2 y crear las tablas necesarias.

## Informe

Las decisiones de las direcciones, el fetch type, el cascade type fueron basadas en el dominio de la aplicacion.

### 1 Relaciones unidireccionales

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

|Caracteristica|Valor|Comentarios|
|:----|:----|:----|
|Owner|Payment Summary| |
|Inverse|Quota| |
|Relationship|One(PaymentSummary)ToMany(Quota)| |
|Nombre del atributo en el owner|quotasPayments|Se respeta el nombre que aparece en la consigna del trabajo practico|
|Fetch Type|Eager|Debido a que el dominio require de la obtencion de las compras correspondientes a un resumen.|
|Cascade Type|None|No es necesario que las operaciones sobre PaymentSummary afecten a Quota|
|Dominio|Generar el total de pago de un mes dado, informando las compras correspondientes| |

---

- PAYMENT SUMMARY - PURCHASE SINGLE PAYMENT

Dominio: Generar el total de pago de un mes dado, informando las compras correspondientes

|Caracteristica|Valor|Comentarios|
|:----|:----|:----|
|Owner|Payment Summary| |
|Inverse|Purchase Single Payment| |
|Relationship|One(PaymentSummary)ToMany(PuchaseSinglePayment)| |
|Nombre del atributo en el owner|cashPayments|Se respeta el nombre que aparece en la consigna del trabajo practico|
|Fetch Type|Eager|Debido a que el dominio require de la obtencion de las compras correspondientes a un resumen.|
|Cascade Type|None|No es necesario que las operaciones sobre PaymentSummary afecten a Quota|
|Dominio|Generar el total de pago de un mes dado, informando las compras correspondientes| |

---

### 2. Relaciones bidireccionales

- BANK - CUSTOMER

Dominio: Obtener un listado con el numero de clientes de cada banco

|Caracteristica|Valor|Comentarios|
|:----|:----|:----|
|Owner|Bank| |
|Inverse|Customer| |
|Relationship|Many(Bank)ToMany(Customer)| |
|Nombre del atributo en el owner|members|Se respeta el nombre que aparece en la consigna del trabajo practico|
|Nombre del atributo en el inverse|banks| |
|Fetch Type|Lazy|Para evitar cargar datos innecesarios cuando lo unico que se require es contar los clientes de cada banco|
|Cascade Type|Merge|Se evita la utilizacion de CascadeType.REMOVE porque en una relacion @ManyToMany podria eliminar a los clientes de otros bancos. Ambas entitdades tienen ciclos de vida independientes y se podria usar PERSIST o MERGE para simplificar operaciones. Como muy rara vez se crean bancos con sus customers a la misma vez, utilizamos MERGE para evitar tener que actualizar los cambios en el Customer en el caso de que haya un cambio en el Bank|


---

- PURCHASE - CARD

Dominio: Obtener la informacion de las 10 tarjetas con mas compras.

|Caracteristica|Valor|Comentarios|
|:----|:----|:----|
|Owner|Purchase| |
|Inverse|Card| |
|Relationship|Many(Purchase)ToOne(Card)| |
|Nombre del atributo en el owner|card| |
|Nombre del atributo en el inverse|purchases| |
|Fetch Type|None (Eager)|Es comun obtener la informacion de la tarjeta cuando se quiere acceder a la infomacion de una compra|
|Cascade Type|None|Se evita CascadeType.REMOVE para no borrar accidentalmente tarjetas al eliminar compras. Tampoco tiene sentido que las otras operaciones como MERGE, REFRESH, PERSIST o DETACH se cascadeen a las tarjetas. Por eso es que se elije None|

---

- PUCHASE - PROMOTION

Dominio 1: Eliminar una promocion a traves de su codigo tener en cuenta que esta puede haber sido aplicada a alguna compra

Dominio 2: Obtener la promocion mas utilizada en las compras registradas

|Caracteristica|Valor|Comentarios|
|:----|:----|:----|
|Owner|Purchase| |
|Inverse|Promotion| |
|Relationship|Many(Purchase)ToOne(Promotion)| |
|Nombre del atributo en el owner|validPromotion|Se respeta el nombre que aparece en la consigna del trabajo practico|
|Nombre del atributo en el inverse|purchases| |
|Fetch Type|Eager|Por defecto, las relaciones @ManyToOne suelen ser EAGER, lo cual esta bien en este caso, porque generalmente se necesita cargar la promocion asociada a una compra|
|Cascade Type|Merge|No se debe elejir REMOVE, ya que el requerimiento implica eliminar una compra por su codigo, no mediante las compras asociadas. Lo mas adecuado es MERGE o PERSIST, para que los cambios en Promotion se reflejen correctamente en las compras al actualizar las entidades|


---

- PURCHASE MONTHLY PAYMENTS - QUOTA

Dominio: Obtener la informacion de una compra, incluyendo el listado de cuotas si esta posee

|Caracteristica|Valor|Comentarios|
|:----|:----|:----|
|Owner|Purchase Monthly Payments| |
|Inverse|Quota| |
|Relationship|One(PurchaseMonthlyPayments)ToMany(Quota)| |
|Nombre del atributo en el owner|quotas| |
|Nombre del atributo en el inverse|purchase| |
|Fetch Type|Eager|En relaciones @OneToMany es mejor utiizar LAZY para evitar cargar todas las cuotas en el caso de que sean numerosas. Sin embargo, debido a que el dominio pide que se pueda obtener la informacion de una compra e incluir sus cuotas si esta posee, se termino eligiendo EAGER|
|Cascade Type|All|Las cuotas estan directamente relacionadas al PurchaseMonthlyPayments, y es por eso que todas las operaciones realizadas sobre una compra con cuotas (PERSIST, MERGE, REMOVE, REFRESH, DETACH), se deben reflejar automaticamente en las cuotas asociadas.|
|Orphan removal|TRUE|Esto asegura que las cuotas se eliminen automaticamente cuando sean eliminadas de la lista de cuotas de una compra.|



---

### 3. Relaciones parent-children

- Financing y Discount son hijos de Promotion.
- Purchase Monthly Payments y Purchase Single Payment son hijos de Purchase.

### 4. Transacciones

De las operaciones requeridas en el trabajo practico, solo 3 de ellas escriben en la base de datos, el resto son solo lecturas.

- Agregar una nueva promocion de tipo Financing a un banco dado.
  - Es necesario utilizar la anotacion @Transactional debido a que se estan afectando 2 entidades (Promotion y Bank) a la misma vez. Si no se utiliza la anotacion, es posible que solo se guarde el cambio en Promotion, presentando una inconsistencia en los datos. Ademas, no se puede aprovechar las definiciones de Cascade, ya que el owner de la relacion es Bank, y no Promotion
- Extender el tiempo de validez de una promocion.
  - No es necesario utiizar la anotacion @Transactional debido a que solo se esta cambiando el valor de una propiedad de la entidad.
- Eliminar una promocion a traves de su codigo.
  - Es necesario utilizar la anotacion @Transactional debido a que se estan modificando 2 entidades (Purchase y Promotion) a la misma vez. Si no se utiliza la anotacion, es posible que solo se guarde el cambio en los Purchases, presentando una inconsistencia en los datos. Ademas, no se puede aprovechar las definiciones de Cascade, ya que el owner de la relacion es Purchase, y no Promotion

### 5. Prueba de endpoints
Para probar los endpoints que muestran los mapeos se compartira la collecion *Payments* utilizdas en postman.

[Postman Payments](https://cloudy-capsule-99776.postman.co/workspace/Team-Workspace~1899ec8f-24d7-4ca6-8daa-0eff5d03605e/collection/27249426-c3b68fbd-1365-46bb-b9fc-9e461ca9208c?action=share&creator=40503936&active-environment=40503936-96262f85-5524-42ca-8f10-2bff25f80ff3)


