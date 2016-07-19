#Ordering tasking

* ###/products

1. return 201 when POST product  
 estimate: 5 min  
 real: 4 min

2. create product with parameters and find product by product id  
 e: 20 min  
 r: 19 min
  
3. return 201 when POST product with parameters  
 e: 5 min  
 r: 4 min
  

4. return 200 when GET products  
 e: 3 min  
 r: 2 min
  

5. find all products  
 e: 15 min  
 r: 6 min
  
  
6. return a list of products when GET products  
 e: 5 min  
 r: 17 min  
 reason: complete the next task at the same time.
    
    
7. return product json list when GET products  
 e: 5 min  
 r: 0 min

* ###/products/{productId}

1. return product json when GET product by product id  
 e: 10 min  
 r: 13 min  
  
2. return 404 when GET product by product id fails  
 e: 3 min  
 r: 2 min
  
 

* ###/users

1. return 201 when POST user  
 e: 5 min  
 r: 3 min
 
2. create user with parameters and find user by user id  
 e: 15 min  
 r: 13 min
  
3. return 201 when POST user with parameters  
 e: 5 min  
 r: 6 min
 
4. return 200 when GET users  
 e: 3 min  
 r: 2 min
  
 
5. find all users  
 e: 6 min  
 r: 6 min
  
 
6. return user json list when GET users  
 e: 5 min  
 r: 7 min
  
 

* ###/users/{userId}

1. return user json when GET user by user id  
e: 10 min  
r: 9 min
 
 
2. return 404 when GET by user id fails  
 e: 5 min  
 r: 2 min
  


* ###/users/{userId}/orders

1. return 201 when POST order  
 e: 5 min  
 r: 3 min
  

2. create order with parameters and find by user id and order id  
 e: 25 min  
 r: 50 min  
 reason: mysql get last insert id
    

3. return 201 when POST order with parameters  
 e: 8 min  
 r: 6 min
    
   
4. return 200 when GET orders  
 e: 3 min  
 r: 2 min
  
 
5. find orders  
 e: 5 min  
 r: 6 min
  

6. return a list of order when GET orders  
 e: 5 min  
 r: 9 min
    
 

* ###/users/{userId}/orders/{orderId}

1. return order json when GET order by user id and order id  
 e: 10 min  
 r: 12 min

2. return 404 when GET order by user id and order id fails  
 e: 3 min  
 r: 4 min
  
 
* ###/users/{userId}/orders/{orderId}/payment

1. return 201 when POST payment  
 e: 5 min  
 r: 4 min
  
 
2. create payment with parameters and find payment by order id  
 e: 15 min  
  

3. return 201 when POST with parameters  
 e: 8 min  
  

4. return payment when GET payments by order id   
 e: 5 min  
  
 
5. return 404 when GET payments by  order id fails  
 e: 3 min  
  
