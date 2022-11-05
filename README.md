For the designed database, implement a Back-end project using the Spring Boot ecosystem (Spring Data JPA) in the form of a Maven project with a connection to MySQL.

The structure of the project should look something like this:

#### Controllers should process requests for CRUD operations and return the necessary data in the form of DTO objects with links (web addresses), according to HATEOAS recommendations.
#### Services must contain basic business logic for working with data.
#### If necessary, repositories can contain additional methods (automatically generated or with JPQL queries) for working with data.
#### Implement client work with data through Swagger:
* data output from tables;
* inserting data into the table;
* updating data in tables.
* deleting data from the table;
* data output from the side of the M:1 connection, that is, for example, for each city, output the people who live in it;
* data output from the connection table of the M:M connection, that is, to output for each subject from one table all the subjects of the second table that are connected to it.