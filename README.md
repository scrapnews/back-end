Project based on https://github.com/savicprvoslav/Spring-Boot-starter (using a lot of spring boot third parties (jpa, web, devtools,...)



Lightened for useless tech in our case.

Details below :

- Multiple profiles
- embedded database for spring security
- spring boot actuator

Best to convert with real dependencies but not needed for the moment (hibernate dependency,...) same shit.

Check this page for default applications.properties with spring boot.

https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html

Check this page for spring boot devtools for the feature restart auto like jRockit almost :

http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/html/using-boot-devtools.html

Database (MySQL) is hosted on AWS : 

spring.datasource.url=jdbc:mysql://aapq21lfjp51ba.cpjoj8sdntqf.us-west-2.rds.amazonaws.com:3306/scrapnews_db
spring.datasource.username=scrapnews514
spring.datasource.password=514Montreal



Use case : 

Angular 2 front-end will communicate with firebase authentication to get Json Web Token (JWT), for each request to the back-end, we join a header with JWT retrieved previously. The backend will verify the token and retrieve the security profile linked to it (for example : 

curl -X GET -H "Accept: application/json" -H "X-Authorization-Firebase-ScrapNews: eyJhbGciOiJSUzI1NiIsImtpZCI6ImI3NjkwMWU2NzY4YmU3YTY2MDc2NjE3ZWRmOTc0MzEyYTBjNzNjNWEifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vc2NyYXBuZXdzLWJhNjNkIiwibmFtZSI6Ik5nb2MgQW4gTmd1eWVuIiwicGljdHVyZSI6Imh0dHBzOi8vbGg1Lmdvb2dsZXVzZXJjb250ZW50LmNvbS8tQXBqUVBNYThkX0EvQUFBQUFBQUFBQUkvQUFBQUFBQUFBR3cvMHJPLTFmZmNvbWsvcGhvdG8uanBnIiwiYXVkIjoic2NyYXBuZXdzLWJhNjNkIiwiYXV0aF90aW1lIjoxNDkwNTcxNDEzLCJ1c2VyX2lkIjoid1ZKT2JneVI1Zk1OODBGVlh3eXV6dm1oUVB0MiIsInN1YiI6IndWSk9iZ3lSNWZNTjgwRlZYd3l1enZtaFFQdDIiLCJpYXQiOjE0OTA1NzE0MTMsImV4cCI6MTQ5MDU3NTAxMywiZW1haWwiOiJuZ29jMDJAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxMTcyNjg3NjcwMTc3MTQ2NzI3OTEiXSwiZW1haWwiOlsibmdvYzAyQGdtYWlsLmNvbSJdfSwic2lnbl9pbl9wcm92aWRlciI6Imdvb2dsZS5jb20ifX0.FCRgDZfxtTRJQrqo4d6163QuCcYQNnN00zlnrgNVMCbxPaNDhtVOh-CjHaCWRF2Fz0BJoZBjiEOYYu8HEV8UkXUpGo7QWDc9XKr_5sV4JiLFuSXPzYHhH_0r_Fgevu15sgx6yp_LLxiIRPokzeh61vfS_Aoxc1uixcfSv3HokoikoAPUVnMBrEE4sN58FqayuJolKtQg3H7HRmUDExKmI8y7F0zy-4QkAxxhWsLtW1zMnjSJV8ScyY52wyoZZTgbeDI5x5dmrKPgtgZ3mQMMOME7EPkEQpjnTFlOuowFxfqIWhs7M6Dp_c_-QlttM8FUzi_RaooJaSU12DAPzJZoeA" "http://localhost:8080/api/client/example"

).

If no security profile is linked, 

call this /api/open/firebase/signup 

@RequestMapping(value = "/api/open/firebase/signup", method = RequestMethod.POST)
	public void signUp(@RequestHeader String firebaseToken) {
	
	}
	
Call this with header jwt token, it will created in database a security profile linked to google account with user privilege (maybe later we'll need an entry for admin and guest)
for example to get a list of all news 
curl -X GET -H "Accept: application/json" -H "X-Authorization-Firebase-ScrapNews: eyJhbGciOiJSUzI1NiIsImtpZCI6IjlmMDJlOTY1NzI5ODRiNGIyMzI0YWU1NmRiYzQ1YmE0NmIzYmFlMzAifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vc2NyYXBuZXdzLWJhNjNkIiwibmFtZSI6Ik5nb2MgQW4gTmd1eWVuIiwicGljdHVyZSI6Imh0dHBzOi8vbGg1Lmdvb2dsZXVzZXJjb250ZW50LmNvbS8tQXBqUVBNYThkX0EvQUFBQUFBQUFBQUkvQUFBQUFBQUFBR3cvMHJPLTFmZmNvbWsvcGhvdG8uanBnIiwiYXVkIjoic2NyYXBuZXdzLWJhNjNkIiwiYXV0aF90aW1lIjoxNDkwNjY1MzQ1LCJ1c2VyX2lkIjoid1ZKT2JneVI1Zk1OODBGVlh3eXV6dm1oUVB0MiIsInN1YiI6IndWSk9iZ3lSNWZNTjgwRlZYd3l1enZtaFFQdDIiLCJpYXQiOjE0OTA2NjUzNDUsImV4cCI6MTQ5MDY2ODk0NSwiZW1haWwiOiJuZ29jMDJAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxMTcyNjg3NjcwMTc3MTQ2NzI3OTEiXSwiZW1haWwiOlsibmdvYzAyQGdtYWlsLmNvbSJdfSwic2lnbl9pbl9wcm92aWRlciI6Imdvb2dsZS5jb20ifX0.fkrnRXOpiXBu-5GssbPK-l7zITUR45fnOdk15TEndYE84DNRxwxDBKQUthZoW37TKVmyGxUUmml0pIFwmrU_vjkxFNKXylR70KnAC2O81-f900lNw7Z7B-YS5jkE-RbSgLNYukGjwF1-y3MzwmH6S6_zEMP6d1MH5H6t-78cdIf6ndAi7r_o0JO-Nj4mZqjCD8GUzrooedtsSPsWJn-va8jXwnhH6op50IXiWnFoND3b2nAfgyPC2HiFlt5VbVwZmlSDCe6BdHm-zqfslG8MBuUbmwUKzCRpYLiaR_77ppDOynKg4l3utR6qsZXirN3GfOvRqOaJ5VtukU6_9j2BMg" "http://localhost:8080/api/client/newsAll"


to start server : 

juste launch main class, it will also start application server embedded etc