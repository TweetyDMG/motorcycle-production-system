# MispisAPP — Подсистема управления производством мотоциклов

Десктопное JavaFX-приложение для автоматизации полного цикла производства мотоциклов:
управление заказами клиентов, учёт материалов и компонентов, контроль сборки, тестирования
и доставки готовой продукции. Ролевая модель разграничивает доступ для клиентов,
менеджеров, производственных рабочих, сборщиков, тестировщиков и логистов.

---

## 🛠 Технологический стек

| Категория | Технология |
|-----------|-----------|
| **Язык** | Java 23 |
| **UI** | JavaFX 17.0.6 (Controls + FXML) |
| **Доп. UI** | FormsFX 11.6.0 |
| **База данных** | PostgreSQL 42.7.2 (JDBC) |
| **Сборка** | Maven 3.8.5 / Maven Wrapper |
| **Тестирование** | JUnit Jupiter 5.10.2 |
| **Сборщик** | javafx-maven-plugin 0.0.8 |

---

## 🚀 Ключевой функционал

### Ролевая модель (4 роли, 6 дашбордов)

| Роль | Подроль | Дашборд | Возможности |
|------|---------|---------|-------------|
| **Клиент** | — | `ClientDashboard` | Создание заказов, просмотр истории |
| **Менеджер** | — | `ManagerDashboard` | Управление заказами и поставщиками |
| **Работник** | Производственный | `ProductionWorkerDashboard` | Изготовление компонентов |
| | Сборщик | `AssemblerDashboard` | Сборка мотоциклов |
| | Тестировщик | `TesterDashboard` | Тестирование, фиксация дефектов |
| **Логист** | — | `LogisticianDashboard` | Управление доставками |

### Бизнес-процессы

```
Клиент ──> Оформление заказа ──> Менеджер ──> Сборка ──> Тестирование ──> Доставка ──> Клиент
                              (назначает       │         (Tester)         (Логист)
                               статус)         │
                                         ┌─────┴──────┐
                                         │ Сборщик     │
                                         │ ┌───────────┤
                                         │ │ Компоненты │
                                         │ │   ┌───────┤
                                         │ │   │ Мат-лы │
                                         │ │   │ +Пост. │
                                         └─┤───┴───────┘
                                           └───────────┘
```

- **CRUD** для всех 10 сущностей: клиенты, заказы, мотоциклы, компоненты, материалы,
  работники, менеджеры, поставщики, доставки, тесты

---

## 📁 Архитектура

### Слои приложения

```
┌─────────────────────────────────────────────────────┐
│  UI (JavaFX FXML)        ui/*Controller.java        │
│  ├── LoginForm / MainMenu                           │
│  ├── 6 Role Dashboards                              │
│  └── 9 CRUD Forms (через ServiceFactory)            │
├─────────────────────────────────────────────────────┤
│  Handler              handler/*Handler.java          │
│  (Alert-хендлеры, тонкий слой между UI и Service)   │
├─────────────────────────────────────────────────────┤
│  Service (бизнес-логика)   service/*Service.java    │
├─────────────────────────────────────────────────────┤
│  DAO (JDBC)               dao/*DAOImpl.java          │
│  └── DatabaseConnection.java (env-конфигурация)      │
├─────────────────────────────────────────────────────┤
│  Model (POJO)             model/*.java               │
└─────────────────────────────────────────────────────┘
                 │
         ┌───────┴───────┐
         │  PostgreSQL    │
         └───────────────┘
```

### DI (ServiceFactory)

Все UI-контроллеры получают сервисы через `ServiceFactory` — центральную фабрику,
которая управляет созданием и зависимостями сервисов и DAO. Ни один UI-контроллер
не создаёт DAO или сервисы напрямую.

### Схема базы данных

```
users ──┬── clients        (1:1, userId = customerId)
        ├── managers        (1:1, userId = managerId)
        ├── workers         (1:1, userId = workerId)
        └── логист — без отдельной таблицы

clients  ────────────────┐
   1:N                   │
 orders ── N:1 ─── motorcycles ── 1:1 ─── tests
                           │
                   motorcycle_components
                           │
components ── N:1 ────────┘
    │
materials ── 1:N ─── suppliers
```

### Таблицы БД

| Таблица | Поля | FK |
|---------|------|----|
| `users` | `user_id`, `login`, `password`, `role`, `subrole` | — |
| `clients` | `customer_id`, `name`, `contact_info` | → users |
| `orders` | `order_id`, `customer_id`, `motorcycle_id`, `order_date`, `status` | → clients, motorcycles |
| `motorcycles` | `motorcycle_id`, `model`, `status` | — |
| `motorcycle_components` | `motorcycle_id`, `component_id` | → motorcycles, components |
| `components` | `component_id`, `name`, `material_id`, `status` | → materials |
| `materials` | `material_id`, `name`, `quantity` | — |
| `workers` | `worker_id`, `name`, `specialization` | → users |
| `managers` | `manager_id`, `name`, `contact_info` | → users |
| `suppliers` | `supplier_id`, `material_id`, `name`, `contact_info` | → materials |
| `deliveries` | `delivery_id`, `motorcycle_id`, `customer_id`, `destination`, `status` | → motorcycles, clients |
| `tests` | `test_id`, `motorcycle_id`, `result`, `issues` | → motorcycles |

---

## 💻 Локальное развертывание

### Требования

- JDK 23
- PostgreSQL (порт 5433 по умолчанию, настраивается)
- Maven 3.8+ (или встроенный `./mvnw`)

### 1. Клонирование

```bash
git clone https://github.com/TweetyDMG/MispisAPP.git
cd MispisAPP
```

### 2. Настройка базы данных

```bash
# Создание БД
createdb -p 5433 motorcycle_production

# Применение схемы
psql -p 5433 -d motorcycle_production -f src/main/resources/schema.sql
```

### 3. Настройка окружения (опционально)

Если параметры подключения отличаются от стандартных, отредактируйте `.env`:

```bash
# .env (создаётся автоматически при клонировании)
DB_HOST=localhost
DB_PORT=5433
DB_NAME=motorcycle_production
DB_USER=postgres
DB_PASSWORD=1234
```

Либо задайте переменные окружения — они имеют приоритет над `.env`:

```bash
export DB_HOST=localhost DB_PORT=5433 DB_NAME=motorcycle_production
```

### 4. Сборка и запуск

```bash
# Сборка
./mvnw clean compile

# Запуск
./mvnw clean javafx:run
```

Или после сборки:

```bash
./mvnw clean package
java --module-path target/classes \
     -m motorcycle.production.subsystem.mispisapp/\
        motorcycle.production.subsystem.MotorcycleProductionSubsystemStructure
```

---

## 🧪 Тестирование

```bash
./mvnw test
```

> **Текущий статус**: JUnit 5 присутствует в зависимостях,
> тесты не добавлены. Ожидается покрытие сервисного слоя.

---

## 📦 Структура проекта

```
MispisAPP/
├── pom.xml                         # Maven (Java 23, JavaFX 17)
├── mvnw / mvnw.cmd                 # Maven Wrapper
├── .env / .env.example             # Конфигурация БД
├── .gitignore
│
├── src/main/java/
│   └── motorcycle/production/subsystem/
│       ├── MotorcycleProductionSubsystemStructure.java   # ★ Точка входа
│       ├── model/                   # POJO сущности (10 шт.)
│       ├── dao/                     # DAO: интерфейсы + JDBC-реализации
│       │   └── DatabaseConnection.java
│       ├── service/                 # Бизнес-логика (10 сервисов)
│       ├── handler/                 # Alert-хендлеры (10 шт.)
│       ├── util/
│       │   └── ServiceFactory.java  # DI-фабрика
│       └── ui/                      # JavaFX FXML-контроллеры (17 шт.)
│
└── src/main/resources/
    └── motorcycle/production/subsystem/
        ├── styles.css               # Глобальные стили
        ├── schema.sql               # DDL (дублируется в resources)
        ├── LoginForm.fxml
        ├── MainMenu.fxml
        ├── *Dashboard.fxml          # 6 дашбордов
        └── *Form.fxml               # 9 CRUD-форм
```

---

## 📄 Лицензия

Проект распространяется под лицензией MIT.

---

## 👥 Авторы

- **Разработчик**: Рогачев Артем Юрьевич ([@TweetyDMG](https://github.com/TweetyDMG))

---
