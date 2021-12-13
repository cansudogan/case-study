# readingIsGood

## Endpoints
It consists of four services:
* **Auth Service**: It contains two endpoints:
    * **[POST]**  ***/api/auth/signup*** is used to sign up and returns a message if you succeeded or not.
    * **[POST]**  ***/api/auth/signin*** is used to sign in and returns a JWT Bearer token (accessToken and tokenType).

![authsignin](https://user-images.githubusercontent.com/21179912/127791131-fab2467d-37b7-41b9-a1a9-8a9ff414edc1.png)

* **Book Service**: It contains four endpoints:
    * **[POST] ***/api/book*** creates a book and it checks for user authorization.
    * **[GET] ***/api/book/{id}*** returns book for given bookId.
    * **[PUT] ***/api/book*** updates book stock information and it checks for user authorization.
    * **[DELETE] ***/api/book*** deletes book and it checks for user authorization.

* **Order Service**: It contains four endpoints:
    * **[POST]  ***/api/order*** creates a new order.
    * **[GET] ***/api/order/{orderId}*** returns order detail for given orderId and it checks for user authorization.
      **Note:** User data is obtained from JWT token. (*Key : Authorization, Value : Bearer generateJWTToken*)
    * **[POST] ***/api/order/date*** returns the order detail for given date range.
    * **[POST] ***/api/order/getAllOrders*** returns the all user orders.

* **Statistics Service**: It contains one endpoints:
    * **[GET] ***/api/statistics*** returns user statistics.

**Note:** Except the auth endpoints, all the endpoints is secure. You have to have a valid JWT token.

**Note:** Application uses MongoDB.

**Note:** You can use the following users to login.

**Username** : admin    **Password** : 123456

**Username** : customer **Password** : 123456


**Note:** 
"create book", "update book" and "delete book" operations can be done with only the **ADMIN** role.
"create order" and "get order all user order" operations can be done with only the **CUSTOMER** role.

## Swagger
http://localhost:8080/swagger-ui.html#/

<img width="995" alt="Screen Shot 2021-12-13 at 03 32 57" src="https://user-images.githubusercontent.com/21179912/145736229-c8666248-2cf9-4d7d-8232-cf7ea3a9df78.png">

<img width="1004" alt="Screen Shot 2021-12-13 at 03 34 03" src="https://user-images.githubusercontent.com/21179912/145736249-d9421233-9d68-4dec-9b94-0318733614d5.png">

**Note:** After signup and signin operations, press the "Authorize" button located above the right. Fill the "value" with "Bearer generateJWTToken" for authorize user with given role.

## Postman
You can find the Postman export in Postman folder.

## Dockerize
For running application execute these line `mvn clean install` and `docker-compose up --build`
Application will start at port 8080

