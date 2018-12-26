# Description
 
 The package contains two rest end points with the capabilities  as described below 
 - GET - api/interest-rates
 - POST -api/mortgage-check
 - Detailed description - available in the last section
 
 # Getting Started
 
 - Take a pull or download the code and open in your favourite IDE. 
 - requires at least maven 3.3.3 and Java 8 
 
 # Running Application
 
 To run the application on default port 8090-
 - navigate to package com.recmort.mortgages.config
 - run the file - MortgagesApiApplication.java  as spring boot app with Run As Command  - 
 - default url of the api should look like - localhost:8080/api/interest-rates
 - use POSTMAN to check /run the post request with mentioned inputs as form data
 
# Change the default server port 
- navigate to file  application.properties at  src/main/resource 
- change the line server.port = your port number
  
 
# Detailed Description - Get (api/interest-rates)

- Gives the current list of mortage interest rates with the maturity period combination. Each rate of interest value indicates the rate based on provided maturity rate.
- The response also contains attribute - lastUpdate - indicates the last published date of interest.
- The values of interest rates, maturity period , and last published date can be added/updated/deleted  with the file data.sql present in resource directory(src/main/resource). 
- There is no code change needed to do the above. The rates will be updated in the H2 repository as soon as application is restarted.

# Detailed Description - POST (api/mortgage-check)
