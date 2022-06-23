MovieDataRepo
This is a repo which is a basic API Design using the Kaggle Dataset for Movies. It answers the following questions:

Production Company Details: The endpoint should be able to take a production_id as a query param and a year as a query param budget per year revenue per year
Movie Genre Details: The endpoint should be able to take a year as a query param most popular genre by year
Deploying the App
Requirements: **MySQL, JAVA 8, Python Version 3+, Gradle 7+, IntelliJ or similar IDE( to view files/project). Steps:

Simply import the project into IDE/ clone the repo
Go to the folder on the local machine of the repo via terminal/command line.
Run gradle build
To execute and get the application up and running, run the following command which uses a JAR File: java -jar MoviesDataRepo\build\libs\MovieData-0.0.1-SNAPSHOT.jar
For the Dataset, use the following command on the Terminal: Python MoviesDataRepo\scripts\csv_extractor.py for MAC/Linux or Py MoviesDataRepo\scripts\csv_extractor.py for Windows
If needed, add dummy Data to the MySQL database instance to validate the behavior of API Call.
Use a REST Api Client to execute the 2 api calls. Examples given as follows:
For Getting Production Details: http://localhost:8080/api/v1/movies/getProductionFinances/10/2009
For getting Popular Genre http://localhost:8080/api/v1/movies/getPopularGenre/2009