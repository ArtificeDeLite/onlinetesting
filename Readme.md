# Online Testing
## API for online testing

    Notes
    1. Собрать проект: mvn clean package 
    2. Запуск из командной строки java -jar -Dfile.encoding=UTF-8 onlinetesting-1.0-SNAPSHOT.jar
    3. Для удобства реализации выбрана HSQLBD с хранением в виртуальной памяти, поэтому,
       при каждом запуске API происходит инициализация БД.

Для работы с данными требуется авторизация (basic auth)\
Регистрация нового пользователя возможна без авторизации\
Для получения данных необходимо авторизоваться с типом учетной записи **USER**.\
Для внесения изменений в данные, необходимо авторизоваться с типом учетной записи **ADMIN**.\
Для получения данных о пользователях, нужен тип учетной записи **ADMIN**.

Примеры ***curl*** команд приведены в приложении.

### Типы данных

API предполагает работу со следующими типами данных:

- Пользователь API (**User**)
- Вопрос (**Question**)
- Результат ответа на вопрос (**Result**)
- Общая статистика (**Statistics**)
- Статистика пользователя (**UserStatistics**)

### Пользователь (User)

Каждый пользователь содержит следующую информацию:
- Логин пользователя `login`
- Пароль `password`
- Тип учетной записи (**USER** или **ADMIN**) `role`

Логин пользователя является уникальным.

Работать с пользователями можно только с типом учетной записи **ADMIN**.

##### Работа с пользователями включает в себя:
 - получение списка всех пользователей `/users`

### Вопрос (Question)

Каждая вопрос включает в себя следующую информацию:
- Вопрос `question`
- Верный ответ `answer`

Добавлять новые вопросы можно только с типом учетной записи **ADMIN**.
Выполнять чтение данных может любой зарегистрированный  пользователь.

##### Работа с вопросами включает в себя:
 - получение списка всех вопросов `/testing`
 - получение вопроса N (1...5) для текущего пользователя `/testing/N`
 - добавление нового вопроса `/testing`
 
### Результат ответа на вопрос (Result)
 
 Каждое результат включает в себя следующую информацию:
 - Id ответившего пользователя `userId`
 - Id вопроса `questionId`
 - Ответ пользователя `userAnswer`
 - Результат ответа (верно/неверно) `result`
 
 Добавлять ответ на вопрос и получать список результатов пользователя может любой зарегистрированный  пользователь.
 
 ##### Работа с информацией о результатах включает в себя:
  - добавление ответа пользователя `/testing/N`
  - получение всех результатов пользователя `/testing/results`
  
 ### Общая статистика (Statistics)
   
   Общая статистика включает в себя следующую информацию:
   - Количество зарегистрированных пользователей `userCount`
   - Количество пользователей, прошедших тестирование (4 верных ответ и больше) `userPassed`
   - Количество пользователей, ответивших на все вопросы тестирования `userAllQuestionCount`
   - Количество пользователей, ответивших на все вопросы тестирования правильно `getUserAllQuestionRightCount`
   
   Получать информацию о статистике может только зарегистрированный пользователь.
   
   #### Работа со статистикой:
   - получение статистики `/statistics`
  
 ### Статистика текущего пользователя (UserStatistics)
   
   Статистика текущего пользователя включает в себя следующую информацию:
   - Процент верных ответов `passingPercent`
   - Процент пользователей, справившихся с тестированием лучше текущего пользователя `abovePercent`
   - Процент пользователей, справившихся с тестированием хуже текущего пользователя `belowPercent`
  
   Получать статистику текущего пользователя может только зарегистрированный пользователь.
   
   #### Работа со статистикой:
   - получение статистики пользователя `/statistics/profile`
