# Diesel microservice

Микросервисный проект интернет магазина.


**Содержит модули:**
* storage - RestFull Api склада товаров.
* kafka_mail_notifier - модуль отправляющий письма на электронную почту клиентов при поступлении новых товаров.
* web-client - модуль ответственный за web часть приложения.
* eureka-server - сервер регистрации микросервисов
* api-gateway - сервис маршрутизации запросов к микросервисам.

**Порядок запуска сервисов**
* развернуть контейнер с kafka, используя docker-compose.yaml из docker-kafka-main
* развернуть ELK и , используя docker-compose.yaml из docker-elk-main
* запустить eureka-server
* запустить storage, kafka_mail_notifier, web-client
* запустить api-gateway


## Контакты

Если Вас заинтересовала моя работа и Вы хотите со мной связаться:
* evgen986@mail.ru
* https://t.me/evgen04986

Благодарю за внимание!