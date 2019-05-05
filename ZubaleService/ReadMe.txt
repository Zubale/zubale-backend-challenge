Zubale Service

The service allows users to post quotes and vote on individual quotes. The display of the quotes is sorted 
in the order of most votes.

Technical details:
The system is coded in java using Spring Boot. For persistence it uses H2 embedded database and JPA layer in java
to save data. Helper methods in the JPA are used to sort the data for votes. The database creation uses flywayDB.

Packages and classes

com.zubale
ZubaleServiceApplication.java
	- The main application class to start and configure the spring boot application

com.zubale.config
PersistenceConfiguration.java
	- Database configurations : depends on application.properties in the resources directory


com.zubale.controller
HomeController.java
	- this is a test controller to verify application is up and running. Gives a test message in the browser. 
	Details on how to run the application are provides in the Compiling and Executing section.
QuoteController.java
	- this is the class which provides the REST interfaces for the 3 endpoints.
		1. list() method is used to find all the quotes and sort them
		2. create() method saves the quotes to the repository(database)
		2. update() method gets the quote based on the quote id and increments its vote count and saves it back to repo
		
com.zubale.model
Quote.java
	- JPA entity which represents the Quote. The id for the quote is auto incremented based on a database sequence

com.zubale.repo
QuoteRepo.java
	- this interface is used by the spring framework to initialize the repository and provide basic CRUD methods. 
		Any custom DAO methods can be added to this interface and a service class can be created to support those
		and add appropriate transactional attributes.
		For the purposes of this service basic crud functions have been used so there is not additional service.

src/main/resources/
	db/migrationV1__create_quote.sql
		- flywayDB database creation script
	application.properties
		- various properties to setup server port, debug level, database etc

*** to be noted this is a reference implementation. NO validation, paging, transactions, same user voting to his own quotes, unit testing etc have been added. 

Requirements:

Maven is required to build.

***********
Compiling and Executing:

1. cd <project directory>/ZubaleService
2. mvn packaging
3. This will create a traget directory with executable jar file
4. Run the command below to start the service
	cd <project directory>/target
	java -jar ZubaleService-0.0.1-SNAPSHOT.jar
5. A reference build has been added to the executable directory in the main project folder.
	In case there is a problem building use the reference implementation to test.
	cd <project directory>/executable
	java -jar ZubaleService-0.0.1-SNAPSHOT.jar
	


The springboot application with the embedded web server will be launched on port 8080

To verify if the system is deployed correctly go to the below URL in a browser
http://localhost:8080

You should see the below output:
	"Zubale Quote and Vote service!"

To go to the H2 database console

http://localhost:8080/h2

Make sure the JDBC URL points to the correct database: jdbc:h2:~/quotedb 
Currently it has a single table with quotes and votes

The application provided 3 endpoints.

REST Endpoints:
Please use tools like Postman, soapui, curl etc to run the endpoints
1. Add quotes
	This end point will add a new quote. The vote count defaults to 0 for a new quote
	POST http://localhost:8080/api/v1/quotes
	headers:
	Content-Type: application/json
	Request JSON:
	{
		"quoteString": "You must be the change you want to see in the world. – Mahatma Gandhi"
	}
	
2. Get Quotes

	The response is sorted for votes in descending order. 

	GET http://localhost:8080/api/v1/quotes
	
	Sample output
	[
	    {
	        "id": 4,
	        "quoteString": "You can get everything in life you want if you will just help enough other people get what they want. – Zig Ziglar",
	        "votes": 3
	    },
	    {
	        "id": 3,
	        "quoteString": "Desire is the starting point of all achievement, not a hope, not a wish, but a keen pulsating desire which transcends everything. – Napoleon Hill",
	        "votes": 2
	    },
	    {
	        "id": 2,
	        "quoteString": "Failure is the condiment that gives success its flavor. – Truman Capote",
	        "votes": 1
	    },
	    {
	        "id": 5,
	        "quoteString": "I’ve missed more than 9000 shots in my career. I’ve lost almost 300 games. 26 times, I’ve been trusted to take the game winning shot and missed. I’ve failed over and over and over again in my life. And that is why I succeed. – Michael Jordan",
	        "votes": 0
	    },
	    {
	        "id": 6,
	        "quoteString": "You must be the change you want to see in the world. – Mahatma Gandhi",
	        "votes": 0
	    }
	]
	
3. Vote for quoted

	This endpoint is used to add vote to a single quote. The id of the quote is required for the vote. The id should be retrieved using the get quotes.
	PUT http://localhost:8080/api/v1/vote/3
	
	Sample response:
	{
	    "id": 3,
	    "quoteString": "Desire is the starting point of all achievement, not a hope, not a wish, but a keen pulsating desire which transcends everything. – Napoleon Hill",
	    "votes": 2
	}
	
	