# FinGrad Backend

Модульное монолитное приложение на Java 22 + Spring Boot для финансовых услуг.

## Структура проекта

Проект организован в модульную структуру с разделением по функциональности:

### Модули

1. **auth_user** - Аутентификация и управление пользователями
2. **finance** - Финансовые операции и транзакции  
3. **calc_engine** - Движок расчетов
4. **advice_engine** - Движок рекомендаций
5. **report_engine** - Движок отчетов

### Структура каждого модуля

Каждый модуль содержит следующие пакеты:
- `entities` - JPA сущности
- `repositories` - JPA репозитории
- `services` - Бизнес-логика
- `controllers` - REST контроллеры
- `dto` - Объекты передачи данных
- `utils` - Утилиты
- `configuration` - Конфигурация модуля

## Технологии

- **Java 22**
- **Spring Boot 3.4.0**
- **Spring Data JPA**
- **H2 Database** (для разработки)
- **Liquibase** (миграции БД)
- **Lombok**
- **Maven**

## Запуск проекта

### Требования
- Java 22+
- Maven (или используйте Maven Wrapper)

### Локальный запуск

```bash
# Компиляция
./mvnw clean compile

# Запуск приложения
./mvnw spring-boot:run
```

### Профили

- **local** - для локальной разработки (application-local.yml)
- **def** - дефолтные настройки (application-def.yml)

### Конфигурация

- Порт: 8080
- Context path: /api
- H2 Console: http://localhost:8080/api/h2-console (только для local профиля)

## База данных

Проект использует H2 in-memory базу данных для разработки. Миграции Liquibase находятся в папке `src/main/resources/liquibase/`.

### Структура миграций

- `master-changelog.xml` - главный файл миграций
- `{module}/changelog.xml` - миграции для каждого модуля

## API Endpoints

После запуска приложения доступны следующие endpoints:

- `GET /api/actuator/health` - проверка состояния приложения
- `GET /api/actuator/info` - информация о приложении
- `GET /api/actuator/metrics` - метрики приложения

## Разработка

1. Добавляйте сущности в соответствующие пакеты `entities`
2. Создавайте репозитории в пакетах `repositories`
3. Реализуйте бизнес-логику в пакетах `services`
4. Создавайте REST endpoints в пакетах `controllers`
5. Добавляйте миграции БД в соответствующие `changelog.xml` файлы

## Структура файлов

```
src/
├── main/
│   ├── java/com/fingrad/
│   │   ├── auth_user/
│   │   ├── finance/
│   │   ├── calc_engine/
│   │   ├── advice_engine/
│   │   ├── report_engine/
│   │   └── FinGradBackendApplication.java
│   └── resources/
│       ├── application-local.yml
│       ├── application-def.yml
│       └── liquibase/
└── test/
```
