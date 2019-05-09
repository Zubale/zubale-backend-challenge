# Zubale Backend Challenge 

This app is runnig on Spring boot.

it's using gradle for the building and downloagind libraries.
it's using only spring libraries.

This is the arquitecture:
	
repository is for the persistence, in this case there is no data base, but repository
heps us to make the persistency of data.

the service has the operacion from validation, this is the second layer.

Controller coming with rest will expose web services with 3 methods:
	
get to retreive the list of elements, sorted by most voted, for this I used a
Comparable interface for making the compare and then I sorted with Collections

put to add a vote to the quote

post to create a new vote

I am using RestController because of is easy to apply and also offers a long suite of
tools that we can combine and makes strong an secure our application.

cell +521 5544556430
email israazul@gmail.com


 
