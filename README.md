# Сборка и запуск

#### Примечание к репозиторию

Для вашего удобства в директории `target` располагается располагается уже собранный исполняемый файл `test202001-1.0.jar` и прилагемый к нему набор данных (`/target/datase/data.sqlite`).

### Сборка

Для сборки проекта в вашем окружении должен присутствовать **Maven**. 

Чтобы собрать проект, необходимо выполнить следующие действия:
1. Запустить терминал
2. Перйти в директорию, в которой располагается файл `pom.xml`
3. Вызвать сборку командой `mvn package`

После выполнения указанных действий в директории target появится исполняемый файл `test202001-1.0.jar`. Также деректория с `test202001-1.0.jar` должна содержать обрабатывемые данные единственным образом: `/dataset/data.sqlite`.

### Запуск приложения

Чтобы запустить приложение, необходимо обратиться к исполняемому файлу `test202001-1.0.jar`.

1. Запустить терминал
2. Перйти в директорию, в которой располагается исполняемый файл `test202001-1.0.jar`
3. Запустить приложение командой `java -jar test202001-1.0.jar`
4. Приложение будет активно после сообщения "ru.mycompany.test202001.Main  : Started Main"

### Указания по использованию

К приложению можно обратиться по следующему адресу 

```
http://localhost:8080/row=?&col=?
```

где `?` означет a/b/c/d/y. Параметр `col` является необязательным.

Чтобы завершить работу приложения, можно обратиться к следующему адресу

```
http://localhost:8080/shutdown
```


# Задача - сводная таблица (Pivot table) для датасета "Налоги РФ"

### Цели

* Работа с кодом Java SE, Spring Boot
* Работа с БД, понимание SQL
* Понимание принципов плоского и многомерного представления данных.
* Проектирование и реализация API.
* Работа с HTTP API, JSON.
* Инструменты для CI/CD
* Документирование

Файл БД `dataset/data.sqlite` содержит единственную таблицу `source_data` (датасет) с 10К записей по налогам РФ за 2010-2015 годы. Нужно реализовать API для отображения датасета в виде двухмерной [сводной таблицы](https://en.wikipedia.org/wiki/Pivot_table) на стеке JavaSE/Spring Boot

### Описание колонок таблицы source_data

* a - группа налога 
* b - подгруппа налога
* c - округ РФ
* d - регион РФ 
* y - год
* v - значение

## Серверная часть

Принимает запросы клиента по HTTP API, выполняет запросы в БД и отдает результат в виде JSON.

Вызов API GET принимает два параметра 

* row - название столбца БД для группировки таблицы по горизонтали
* col - название столбца БД для группировки таблицы по вертикали

Каждый параметр может принимать значения a,b,c,d,y 

```
http://localhost:8080/row=b&col=d
```

В результате запроса в БД *sqlite* возвращается массив JSON, где каждый элемент - результат агрегации по указанным столбцам таблицы

```
[
    {"row" : "Транспортный налог", "col" : "Краснодарский край", "val" : 1},
    {"row" : "Транспортный налог", "col" : "Ростовская область", "val" : 2},
    {"row" : "Земельный налог", "col" : "Краснодарский край", "val" : 3},
    {"row" : "Земельный налог", "col" : "Ростовская область", "val" : 4}
]
```

Реализация с использованием **Java SE 11** и **Spring Boot**, остальные библиотеки и зависимости на усмотрение разработчика.
Сборка и запуск проекта **Maven** или **Gradle** на усмотрение разработчика.
 
## /etc

Результатом задания является ссылка на публичный github репозиторий.

Репозиторий должен содержать проект с исходниками, готовыми к сборке и запуску в dev окружении.

Должен быть общий **README.md** с инструкциями по сборке и запуску. Ручная установка зависимостей недопустима.

По желанию проект может собираться в один **Docker** контейнер, в таком случае должен быть подготовлен файл сборки контейнера, рекомендуется использовать базовый образ `alpine-openjdk-11`.
