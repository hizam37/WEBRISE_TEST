# Subscription Manager Это система для управления пользователями и их подписками на сервисы.

Используемые инструменты : -Spring boot -Spring Security - Postgres -Maven -Swagger -Spring boot JPA -Hibernate -Persistent -Docker

# Решение
## Первый шаг : 
Созданим базы данных в Postgres через Docker

(Код находится в docker compose)

## Воторой шаг :
Создадим таблицы с помощью аннотация Entity Users(id,firstName,lastName,email,password,phoneNumber,
role) с имплементация UserDetails (Код находится в исходном файле) Subscription (id,digitalServiceName,subscriberId) (Код находится в исходном файле)

## Третий шаг :
Конфигируем spring boot с Postgres через application.yml (Код находится в исходном файле)

## Четвертый шаг :
Созданим Система CREAD (Код находится в исходном файле)

## Пятый шаг :
Созданим зашиненный система с помощью spring security 

## Шестой шаг :
Создадим контроллера аутентификации и контроллера подписка для работы с api

## Седмой шаг :
Создадим api спецификация с помощью springdoc и swagger-ui

(Код находится в docker compose)

## Восмой шаг :
генирируем jsonfile для api спецификации и jar file 
с помощью mvn clean package 

## Девятий шаг :
запускаем docker compose при использования комманда docker compose up 

# Демонстрация
Регистрауия пользователя с использованием запрос POST через 
```
http://localhost:8090/api/v1/auth/register
```

```
{
    "firstName" : "Dan",
    "lastName" : "Hizam",
    "password" : "123",
    "phoneNumber" : "79500296488",
    "role" : "ROLE_MANAGER",
    "email" : "danhizam@gmail.com"
}
```

```
{
    "firstName" : "Jimmy",
    "lastName" : "Hizam",
    "password" : "123",
    "phoneNumber" : "79500296488",
    "role" : "ROLE_SUBSCRIBER",
    "email" : "Jimhizam@gmail.com"
}
```

Вход в системе с использованием запрос POST через 
```
http://localhost:8080/api/v1/auth/login
```

```
{
    "password" : "123",
    "email" : "danhizam@gmail.com"
}
```

Добавления цифровой сервис в качества параметр с использованием запрос POST 
```
http://127.0.0.1:8080/api/v1/auth/login/add_sub_service/{digitalServiceName}
```

Подписка на цифровой сервис в качества параметр с использованием запрос POST 
```
http://localhost:8080/api/v1/auth/login/subscribe/{digitalServiceName}"
```

Редактирования информация пользователя с использованием запрос PUT
```
http://localhost:8080/api/v1/auth/login/edit_my_info
```
```
{
    "firstName" : "danil",
    "lastName" : "Hizam",
    "email" : "daniilhizam@gmail.com"
}
```

отображение списка подписок пользователяв с использованием запрос GET
```
http://localhost:8080/api/v1/auth/login/display_my_subscribed_services
```

отображение информация пользователя по email с использованием запрос GET
```
http://localhost:8080/api/v1/auth/login/view_user_info_by_email/{email}
```

Удаление пользователя по почта с использованием запрос DELETE

```
http://localhost:8080/api/v1/auth/login/delete_by_email/{email}
```

Удаление подписка с использованием запрос PUT

```
http://localhost:8080/api/v1/auth/login/unsubscribe/{service_name}
```

Удаление подписки с использованием запрос PUT

```
http://localhost:8080/api/v1/auth/login/unsubscribe_from_all_services
```

