# Приложение для метеорологического датчика

REST API для принятия HTTP запросов с данными в формате JSON от метеорологического датчика

<h2>Функционал:</h2>

- Регистрация нового датчика в системе
- Добавление нового измерения
- Просмотр всех измерений
- Подсчет количества дождливых дней

<h2>Используемые технологии</h2>
<ul>
  <li>Java</li>
  <li>Spring Boot</li>
  <li>Spring Web</li>
  <li>REST API</li>
  <li>Spring Data</li>
  <li>PostgreSQL</li>
  <li>Hibernate</li>
  <li>Maven</li>
  <li>Docker</li>
  <li>Docker compose</li>
  <li>Git</li>
</ul>

<h2>Деплой</h2>

<h3>Условие для запуска</h3>
<ul>
  <li> Установленый Docker, Docker-Сompose
</ul>

<h3>Запуск</h3>

<ul>
  <li> Скопировать репозиторий
  <li> Зайти в папку репозитория: cd AppForSensor
  <li> Создать jar архив Windows: ./mvnw package, Linux: ./mvn package </li>
  <li> Запустуть фаил docker-compose.yml: docker compose up</li>
  <li> Приложение доступно по адресу: localhost:8080/ </li>
</ul>


<h2>END POINTS</h2>

-  Регистрация нового датчика в системе: POST /sensors/registration
   </br>
   ![alt-text](/img/add_sensor.png)
-  Добавление измерения: POST /measurements/add
    </br>
   ![alt-text](/img/get_mes.png)
-  Получение всех измерений: GET /measurements
   </br>
   ![alt-text](/img/get_all.png)
-  Получение количества дождливых дней: GET /measurements/rainyDaysCount
   </br>
   ![alt-text](/img/rainy_day.png)




