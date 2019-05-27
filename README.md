# Zubale Backend Challenge 

At Zubale we are all about raising people up and inspiring people. To do that, we need a great way to track inspirational quotes. So, we need you to write a service that will let people suggest quotes and vote on the ones they like the best.

We hope doing this is fun, but we are also are always looking for the best people to join our team. We will be happy to review your submission and consider you for a possition at Zubale. 

The requirements are:
1. Fork this repo and clone it to start working
2. Build out a service
3. Provide an endpoint to allow quotes to be submitted 
4. Provide an endpoint to allow votes on quotes to be submitted 
5. Provide an endpoint that returns the list of quotes in the order with the most votes
6. Push your code early and often 
7. Provide a readme explaining your work and how to run it
8. Include how to contact you in the readme (WhatsApp & Enail)
9. When you are done, open a PR from your fork to this repo

Your solution does not need a front end, it can be just APIs. You are also free to use any technology or libraries you like, but extra points will be given for Kotlin and Spring. 


#How to Install and Run
1.You should have a mysql installed v8  
2.Create at Mysql a DB for the project  
3.Change your DB login credentials in the application.properties    
4.Run file TechnicalTestApplication 
5.You need to login first against   
/oauth/token (POST with values username : andres, password: 12345, grant_type: password}) you should use x-www-form-urlencoded for login    
Use the access_token generated as Authorization Bearer Token to call de APIS    
6.Call some endpoint at (endpoints requires you to be logged):  
/api/quotes (GET , POST)    
/api/quotes/most-voted (GET )    
/api/users (GET , POST)     
/api/votes (GET , POST)     

Note: Here is a postman collection with a guidance of the requests
https://www.getpostman.com/collections/345ba15dc999c3dde6e4 

To import it you need :     
1.Install postman   
2.Click on file-> Import -> Import from link    

