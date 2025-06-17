# Subscription Manager Это система для управления пользователями и их подписками на сервисы.

Используемые инструменты : -Spring boot -Spring Security - Postgres -Maven -Swagger -Spring boot JPA -Hibernate -Persistent -Docker

# Решение
## Первый шаг : 
Создание базы данных в PostgreSQL с использованием Docker
(Код содержится в файле docker-compose.yml)
## Воторой шаг :
Создание таблиц с использованием аннотаций Entity:

Users (id, firstName, lastName, email, password, phoneNumber, role) с реализацией UserDetails

Subscription (id, digitalServiceName, subscriberId)
(Исходный код доступен в соответствующих файлах)

## Третий шаг :
Настройка подключения Spring Boot к PostgreSQL через application.yml
(Конфигурация в файле application.yml)

## Четвертый шаг :
Реализация CRUD-функционала (Код находится в исходном файле)

## Пятый шаг :
Настройка системы безопасности с использованием Spring Security

## Шестой шаг :
Создание контроллеров:

Контроллер аутентификации

Контроллер подписок

## Седмой шаг :
Генерация API-документации с использованием SpringDoc и Swagger UI
(Конфигурация в docker-compose.yml)
## Восмой шаг :
Генерация JSON-файла спецификации API и JAR-файла:
mvn clean package 

## Девятий шаг :
Запуск системы:
docker compose up 

# Демонстрация
Регистрация пользователя
POST-запрос:
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

Авторизация
POST-запрос:
```
http://localhost:8080/api/v1/auth/login
```

```
{
    "password" : "123",
    "email" : "danhizam@gmail.com"
}
```

Добавление цифрового сервиса
POST-запрос:
```
http://127.0.0.1:8080/api/v1/auth/login/add_sub_service/{digitalServiceName}
```

Подписка на сервис
POST-запрос:
```
http://localhost:8080/api/v1/auth/login/subscribe/{digitalServiceName}"
```

Редактирование профиля
PUT-запрос:
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

Просмотр подписок
```
http://localhost:8080/api/v1/auth/login/display_my_subscribed_services
```

Просмотр информации о пользователе
```
http://localhost:8080/api/v1/auth/login/view_user_info_by_email/{email}
```

Удаление пользователя
DELETE-запрос:
```
http://localhost:8080/api/v1/auth/login/delete_by_email/{email}
```

Отписка от сервиса
PUT-запрос:

```
http://localhost:8080/api/v1/auth/login/unsubscribe/{service_name}
```

Отписка от всех сервисов
PUT-запрос:

```
http://localhost:8080/api/v1/auth/login/unsubscribe_from_all_services
```

