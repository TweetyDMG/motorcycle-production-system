-- MispisAPP — Database Schema
-- PostgreSQL

-- Drop tables in reverse dependency order
DROP TABLE IF EXISTS motorcycle_components CASCADE;
DROP TABLE IF EXISTS tests CASCADE;
DROP TABLE IF EXISTS deliveries CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS components CASCADE;
DROP TABLE IF EXISTS suppliers CASCADE;
DROP TABLE IF EXISTS materials CASCADE;
DROP TABLE IF EXISTS motorcycles CASCADE;
DROP TABLE IF EXISTS workers CASCADE;
DROP TABLE IF EXISTS managers CASCADE;
DROP TABLE IF EXISTS clients CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- ============================================
-- Users & Authentication
-- ============================================
CREATE TABLE users (
    user_id     VARCHAR(36)  PRIMARY KEY,
    login       VARCHAR(100) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        VARCHAR(50)  NOT NULL,
    subrole     VARCHAR(50)
);

-- ============================================
-- Clients
-- ============================================
CREATE TABLE clients (
    customer_id  VARCHAR(36)  PRIMARY KEY REFERENCES users(user_id) ON DELETE CASCADE,
    name         VARCHAR(255),
    contact_info VARCHAR(255)
);

-- ============================================
-- Managers
-- ============================================
CREATE TABLE managers (
    manager_id   VARCHAR(36)  PRIMARY KEY REFERENCES users(user_id) ON DELETE CASCADE,
    name         VARCHAR(255),
    contact_info VARCHAR(255)
);

-- ============================================
-- Workers
-- ============================================
CREATE TABLE workers (
    worker_id      VARCHAR(36)  PRIMARY KEY REFERENCES users(user_id) ON DELETE CASCADE,
    name           VARCHAR(255),
    specialization VARCHAR(255),
    subrole        VARCHAR(50)
);

-- ============================================
-- Materials
-- ============================================
CREATE TABLE materials (
    material_id VARCHAR(36)  PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    quantity    INTEGER      NOT NULL DEFAULT 0
);

-- ============================================
-- Suppliers
-- ============================================
CREATE TABLE suppliers (
    supplier_id  VARCHAR(36)  PRIMARY KEY,
    material_id  VARCHAR(36)  REFERENCES materials(material_id) ON DELETE SET NULL,
    name         VARCHAR(255) NOT NULL,
    contact_info VARCHAR(255)
);

-- ============================================
-- Components
-- ============================================
CREATE TABLE components (
    component_id VARCHAR(36)  PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    material_id  VARCHAR(36)  REFERENCES materials(material_id) ON DELETE SET NULL,
    status       VARCHAR(50)  NOT NULL DEFAULT 'Not Manufactured'
);

-- ============================================
-- Motorcycles
-- ============================================
CREATE TABLE motorcycles (
    motorcycle_id VARCHAR(36)  PRIMARY KEY,
    model         VARCHAR(255) NOT NULL,
    status        VARCHAR(50)  NOT NULL DEFAULT 'Not Started'
);

-- Many-to-many: motorcycle ↔ component
CREATE TABLE motorcycle_components (
    motorcycle_id VARCHAR(36) NOT NULL REFERENCES motorcycles(motorcycle_id) ON DELETE CASCADE,
    component_id  VARCHAR(36) NOT NULL REFERENCES components(component_id)   ON DELETE CASCADE,
    PRIMARY KEY (motorcycle_id, component_id)
);

-- ============================================
-- Orders
-- ============================================
CREATE TABLE orders (
    order_id      VARCHAR(36)  PRIMARY KEY,
    customer_id   VARCHAR(36)  NOT NULL REFERENCES clients(customer_id)     ON DELETE CASCADE,
    motorcycle_id VARCHAR(36)  NOT NULL REFERENCES motorcycles(motorcycle_id) ON DELETE CASCADE,
    order_date    VARCHAR(10)  NOT NULL,
    status        VARCHAR(50)  NOT NULL DEFAULT 'Pending'
);

-- ============================================
-- Deliveries
-- ============================================
CREATE TABLE deliveries (
    delivery_id   VARCHAR(36)  PRIMARY KEY,
    motorcycle_id VARCHAR(36)  NOT NULL REFERENCES motorcycles(motorcycle_id) ON DELETE CASCADE,
    customer_id   VARCHAR(36)  NOT NULL REFERENCES clients(customer_id)      ON DELETE CASCADE,
    destination   VARCHAR(255) NOT NULL,
    status        VARCHAR(50)  NOT NULL DEFAULT 'Pending'
);

-- ============================================
-- Tests
-- ============================================
CREATE TABLE tests (
    test_id       VARCHAR(36)  PRIMARY KEY,
    motorcycle_id VARCHAR(36)  NOT NULL REFERENCES motorcycles(motorcycle_id) ON DELETE CASCADE,
    result        VARCHAR(50)  NOT NULL DEFAULT 'Pending',
    issues        TEXT         NOT NULL DEFAULT ''
);
