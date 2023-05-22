Проект c использованием Spring Webflux Security
================================


## Приложение создано с использованием следующих технологий:
 - Spring Boot 3
 - Spring Security (JWT)
 - Spring WebFlux
 - Spring Data R2DBC
 - MapStruct
 - PostgreSQL
 - Flyway


## Локальный запуск приложения
- Установить PostgreSQL

## Создать БД
```sql
CREATE DATABASE "webflux_security";
```

## Установить корректные значения в application.yaml
```sql
spring:r2dbc:username
```

```sql
spring:r2dbc:password
```

# cURL запросов:

## 1. Регистрация пользователя
```bash
curl --location 'http://localhost:8083/api/v1/auth/register' \
--header 'Content-Type: application/json' \
--data '{
    "username": "maximOr",
    "password": "IPFOhge4g8",
    "first_name": "Maksim",
    "last_name": "Orekhov"
}'
```

Пример ответа:
```json
{
  "id": 1,
  "username": "maximOr",
  "role": "USER",
  "first_name": "Maksim",
  "last_name": "Orekhov",
  "enabled": true,
  "created_at": "2023-05-22T21:23:37.8925537",
  "updated_at": "2023-05-22T21:23:37.8925537"
}
```

## 2. Аутентификация пользователя
```bash
curl --location 'http://localhost:8083/api/v1/auth/login' \
--header 'Content-Type: application/json' \
--data '{
    "username": "maximOr",
    "password": "IPFOhge4g8"
  }'
```

Пример ответа
```json
{
  "user_id": 1,
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwicm9sZSI6IlVTRVIiLCJpc3MiOiJvcmVraG9mZiIsImV4cCI6MTY4NDc3NjM1NiwiaWF0IjoxNjg0NzcyNzU2LCJqdGkiOiI3YzBmZTMxMS0zMTBlLTRjMjUtYTcyMC02ZWJjOGVjN2RiN2YiLCJ1c2VybmFtZSI6Im1heGltT3IifQ.bp-qG5KWLPz1joFa_8giV6Uitbbx87AaSxznDr-HyXU",
  "issued_at": "2023-05-22T16:25:56.768+00:00",
  "expires_at": "2023-05-22T17:25:56.768+00:00"
}
```

## 3. Получение данных пользователя с использованием токена, полученного в предыдущем запросе

```bash
curl --location 'http://localhost:8083/api/v1/auth/info' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwicm9sZSI6IlVTRVIiLCJpc3MiOiJvcmVraG9mZiIsImV4cCI6MTY4NDc3NjM1NiwiaWF0IjoxNjg0NzcyNzU2LCJqdGkiOiI3YzBmZTMxMS0zMTBlLTRjMjUtYTcyMC02ZWJjOGVjN2RiN2YiLCJ1c2VybmFtZSI6Im1heGltT3IifQ.bp-qG5KWLPz1joFa_8giV6Uitbbx87AaSxznDr-HyXU'
```

Пример ответа
```json
{
  "id": 1,
  "username": "maximOr",
  "role": "USER",
  "first_name": "Maksim",
  "last_name": "Orekhov",
  "enabled": true,
  "created_at": "2023-05-22T21:23:37.892554",
  "updated_at": "2023-05-22T21:23:37.892554"
}
```

