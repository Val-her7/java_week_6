CREATE TABLE IF NOT EXISTS companies (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  vat_number VARCHAR(50) UNIQUE NOT NULL,
  company_type ENUM('PROVIDER', 'CLIENT') NOT NULL
);

CREATE TABLE IF NOT EXISTS contacts (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(100),
  last_name VARCHAR(100),
  email VARCHAR(255),
  company_id INT,
  FOREIGN KEY (company_id) REFERENCES companies(id)
);

CREATE TABLE IF NOT EXISTS invoices (
  id INT AUTO_INCREMENT PRIMARY KEY,
  number VARCHAR(50) NOT NULL,
  date DATE NOT NULL,
  company_id INT,
  contact_id INT,
  FOREIGN KEY (company_id) REFERENCES companies(id),
  FOREIGN KEY (contact_id) REFERENCES contacts(id)
);

CREATE TABLE IF NOT EXISTS roles (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) UNIQUE NOT NULL,
  description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  role_id INT NOT NULL,
  FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Insertion into companies
INSERT INTO companies (name, vat_number, company_type) VALUES
('Jean-Michel Toys', 'FR12345678901', 'PROVIDER'),
('PokeExchange Inc.', 'FR98765432109', 'CLIENT'),
('MegaWidgets', 'FR11122233344', 'PROVIDER'),
('SuperClients Ltd', 'FR55566677788', 'CLIENT');

-- Insertion into contacts
INSERT INTO contacts (first_name, last_name, email, company_id) VALUES
('Jean', 'Michel', 'jean.michel@jmtoys.com', 1),
('Sacha', 'Ketchum', 'sacha.k@pokeexchange.com', 2),
('Lara', 'Croft', 'lara.croft@megawidgets.com', 3),
('Peter', 'Parker', 'peter.parker@superclients.com', 4);

-- Insertion into invoices
INSERT INTO invoices (number, date, company_id, contact_id) VALUES
('INV-001', '2025-01-15', 1, 1),
('INV-002', '2025-02-10', 2, 2),
('INV-003', '2025-03-05', 3, 3),
('INV-004', '2025-04-20', 4, 4);

-- Insertion into roles
INSERT INTO roles (name, description) VALUES
('ADMIN', 'read and write access to everything'),
('USER', 'read-only');

-- Insertion into users
INSERT INTO users (username, password, role_id) VALUES
('jcranu', 'admin123', 1),
('johndoe', 'user123', 2);
