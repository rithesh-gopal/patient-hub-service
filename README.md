# patient-hub-service    

## List of APIs   

1. GET /api/patients - Returns all the available patients in JSON format   
    Example: `curl --location 'localhost:8080/api/patients'`   

2. GET /api/patients/{id} - Returns the patient with the patient ID   
    Example: `curl --location 'localhost:8080/api/patients/1'`   

3. POST /api/patients - Creates a patient   
    Example:    
      `curl --location 'localhost:8080/api/patients' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "name": "Rithesh",
   "address": "Bengaluruecho "# patient-hub-service"
   "contactNumber": "9738548249",
   "email": "rithesh@gmail.com",
   "dob": "1994-04-25",
   "hasInsurance": true
   }'`

4. PUT /api/patients/{id} - Updates a patient with patient ID    
    Example:   
        `curl --location --request PUT 'localhost:8080/api/patients/1' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "name":  "Rithesh",
   "address": "Bengaluru",
   "contactNumber": "9738",
   "email": "rithesh@gmail.com",
   "dob": "1994-04-25",
   "hasInsurance": true
   }'`
5. DELETE /api/patients/{id} - Deletes patient with patient ID   
    Example:    
        `curl --location --request DELETE 'localhost:8080/api/patients/1'`

## Steps to Run

### Clean Project
`./gradlew clean`

### Build Project
`./gradlew build jacocoTestReport`

Once the above command is run, the Jacoco Test report containing the code coverage will be generated in `build/reports/jacoco/test/html/index.html`

### Run Application
`./gradlew bootRun`

## Dockerize Application

### Create Docker Image   
`docker build -t patient-hub-service .`

### Run docker image
`docker run -p 8080:8080 patient-hub-service`

Pipeline image is included in img.png