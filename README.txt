Zubale Challenge - Quotes application

This application allows an user create a quote, vote for a quote, and shows a quote list order by 
the most voted quoted. It was build using Spring Boot, Spring MVC and Spring JPA. Maven is required
to executed this application. 

This application used a memory database HSQLDB to store the data.

This application does not include security features, error pages, handle exception. It includes basic unit testing.

It was tested using PostMan tool.

We considered the following endpoints

    Action   |      Endpoint        |                   Comments
    GET      |    /                 | A welcome message
    GET      |   /api/quotes        | Gets a list quotes ordered by the quote with highest votation
    GET      |   /api/quotes/{id}   | Gets the quote indicated by the id value
    POST     |   /api/quotes        | Store a quote in the database
    PUT      |   /api/quotes/{id}   | increases the vote of the selected quote
    
    
 Examples for each endpoint
 *******************
  Endpoint: http://localhost:8080/
 Method: GET
 Data:
    headers: Content-Type:application/json
    output: 
        Welcome to Quota Application a Zubale Challenge
 *******************
 
 Endpoint: http://localhost:8080/api/quotes/
 Method: GET
 Data:
    headers: Content-Type:application/json
    output: 
    [
        {
            "id": 4,
            "quote": "It is hard to fail, but it is worse never to have tried to succeed.",
            "author": "Theodore Roosevelt",
            "votes": 5
        },
        {
            "id": 3,
            "quote": "Those who dare to fail miserably can achieve greatly",
            "author": "John F. Kennedy",
            "votes": 3
        },
        {
            "id": 2,
            "quote": "Get busy living or get busy dying",
            "author": "Stephen King",
            "votes": 1
        },
        {
            "id": 1,
            "quote": "Love is a serious mental disease.",
            "author": "Plato",
            "votes": 0
        }
    ]
 *******************
 
 Endpoint: http://localhost:8080/api/quotes/4
 Method: GET
 Data:
    headers: Content-Type:application/json
    output: 
    {
        "id": 4,
        "quote": "It is hard to fail, but it is worse never to have tried to succeed.",
        "author": "Theodore Roosevelt",
        "votes": 5
    }
    *******************
    
 Endpoint: http://localhost:8080/api/quotes/4
 Method: POST
 Data:
    headers: Content-Type:application/json
    input: 
        {
            "quote": "A friend is someone who gives you total freedom to be yourself.",
            "author": "Jim Morrison",
            "votes": "0"
        }
    output:
         {
            "id": 5,
            "quote": "A friend is someone who gives you total freedom to be yourself.",
            "author": "Jim Morrison",
            "votes": 0
        }  
        *******************
        
  Endpoint: http://localhost:8080/api/quotes/5
 Method: PUT
 Data:
    headers: Content-Type:application/json
    output: 
    {
        "id": 5,
        "quote": "A friend is someone who gives you total freedom to be yourself.",
        "author": "Jim Morrison",
        "votes": 1
    }
         
 
 Steps to execute the Quotes Application
 a) Clone the git repository.
 b) Cd to <project directory>\zubale-backend-challenge
 c) Execute the following command:
      mvn clean package
 d) Execute the following command
      mvn spring-boot:run
 e) Go to: http://localhost:8080/api/quotes
 
 Note: Some quotes are created during application startup.
 
 My Information
 email: rsalinasg@hotmail.com. sgallegosr@gmail.com
 mobile phone: 55 2245 5259
 