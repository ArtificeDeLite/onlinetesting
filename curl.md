### curl samples

### Anonymous authentication
##### register
`curl --location --request POST 'http://localhost:8080/registration' --header 'Content-Type: application/json' --data-raw '{
 	"login" : "newLogin",
 	"password" : "12345"
 }'`

### Admin authentication

##### get All Users
`curl --location --request GET 'http://localhost:8080/users' --header 'Authorization: Basic QWRtaW46YWRtaW4='`

##### add new Question
`curl --location --request POST 'http://localhost:8080/testing' --header 'Authorization: Basic QWRtaW46YWRtaW4=' --header 'Content-Type: application/json' --data-raw '{
 	"question" : "Новый вопрос",
 	"answer" : "Ответ"
 }'`

### User authentication

#### Questions
##### get all questions
`curl --location --request GET 'http://localhost:8080/testing' --header 'Authorization: Basic VXNlcjpwYXNzd29yZA=='`

##### get question #1
`curl --location --request GET 'http://localhost:8080/testing/1' --header 'Authorization: Basic VXNlcjpwYXNzd29yZA=='`

##### answer the question #1
`curl --location --request POST 'http://localhost:8080/testing/1' --header 'Authorization: Basic VXNlcjpwYXNzd29yZA==' --header 'Content-Type: application/json' --data-raw 'ответ'`

#### Results
##### get all results by user
`curl --location --request GET 'http://localhost:8080/testing/result' --header 'Authorization: Basic VXNlcjpwYXNzd29yZA=='`

#### Statistics
##### get Statistics
`curl --location --request GET 'http://localhost:8080/statistics' --header 'Authorization: Basic VXNlcjpwYXNzd29yZA=='`

#### User Statistics
##### get User Statistics
`curl --location --request GET 'http://localhost:8080/statistics/profile' --header 'Authorization: Basic VXNlcjpwYXNzd29yZA=='`
