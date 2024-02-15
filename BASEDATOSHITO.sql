CREATE DATABASE HITO_WATER;

USE HITO_WATER;

-- Tabla: USUARIO
CREATE TABLE USUARIO (
    PF_NIK VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255),
    activo BOOLEAN,
    permiso VARCHAR(50),
    categoria VARCHAR(255),
    nombre VARCHAR(255),
    apellidos VARCHAR(255),
    email VARCHAR(255),
    tlf VARCHAR(20)
);

-- Tabla: PROYECTO
CREATE TABLE PROYECTO (
    ID_PROYECTO INT AUTO_INCREMENT PRIMARY KEY,
    NOMBRE VARCHAR(255),
    DESCRIPCION TEXT,
    ZONA VARCHAR(255),
    FECHA DATE
);

-- Tabla: TAREA
CREATE TABLE TAREA (
    ID_TAREA INT AUTO_INCREMENT PRIMARY KEY,
    DESCRIPCION TEXT,
    INICIOPREVISTO DATETIME,
    FINPREVISTO DATETIME,
    INICIOREAL DATETIME,
    FINREAL DATETIME,
    ESTADO VARCHAR(50),
    NIF VARCHAR(20),
    ID_PROYECTO INT,
    FOREIGN KEY (ID_PROYECTO) REFERENCES PROYECTO(ID_PROYECTO)
);

-- Tabla: ROLES
CREATE TABLE ROLES (
    ID_ROL INT AUTO_INCREMENT PRIMARY KEY,
    ROL VARCHAR(50)
);

-- Tabla: ESTADOS
CREATE TABLE ESTADOS (
    ID_ESTADO INT AUTO_INCREMENT PRIMARY KEY,
    ESTADO VARCHAR(50)
);

-- Insertar roles
INSERT INTO ROLES (ROL) VALUES ('SUPERVISOR');
INSERT INTO ROLES (ROL) VALUES ('TRABAJADOR');

-- Insertar estados
INSERT INTO ESTADOS (ESTADO) VALUES ('PENDIENTE');
INSERT INTO ESTADOS (ESTADO) VALUES ('EN CURSO');
INSERT INTO ESTADOS (ESTADO) VALUES ('FINALIZADA');


-- INSERTAR USUARIOS EN LA TABLA USUARIO
INSERT INTO USUARIO (PF_NIK, password, activo, permiso, categoria, nombre, apellidos, email, tlf) 
VALUES ('48282215V', '123', true, 'trabajador', 'Ingeniería Ambiental', 'Juan', 'González', 'juan.gonzalez@example.com', '123456789');

INSERT INTO USUARIO (PF_NIK, password, activo, permiso, categoria, nombre, apellidos, email, tlf) 
VALUES ('48782214Q', '1234', true, 'supervisor', 'Gestión de Proyectos', 'María', 'López', 'maria.lopez@example.com', '987654321');

INSERT INTO USUARIO (PF_NIK, password, activo, permiso, categoria, nombre, apellidos, email, tlf) 
VALUES ('48562547Y', '12345', true, 'trabajador', 'Biología Acuática', 'Pedro', 'Martínez', 'pedro.martinez@example.com', '111222333');

INSERT INTO USUARIO (PF_NIK, password, activo, permiso, categoria, nombre, apellidos, email, tlf) 
VALUES ('27845123F', '123456', true, 'trabajador', 'Química del Agua', 'Ana', 'García', 'ana.garcia@example.com', '444555666');

INSERT INTO USUARIO (PF_NIK, password, activo, permiso, categoria, nombre, apellidos, email, tlf) 
VALUES ('55555555G', '1234567', true, 'trabajador', 'Investigación Ambiental', 'David', 'Rodríguez', 'david.rodriguez@example.com', '777888999');

-- INSERTAR PROYECTOS EN LA TABLA PROYECTOS 
INSERT INTO PROYECTO (NOMBRE, DESCRIPCION, ZONA, FECHA)
VALUES ('Estudio de calidad del agua en el río Bravo', 'Estudio detallado de la calidad del agua en la cuenca del río Bravo', 'Río Bravo, Texas', '2024-02-09');

INSERT INTO PROYECTO (NOMBRE, DESCRIPCION, ZONA, FECHA)
VALUES ('Monitoreo de calidad del agua en Ciudad X', 'Programa de monitoreo continuo de la calidad del agua en Ciudad X', 'Ciudad X', '2024-02-10');

INSERT INTO PROYECTO (NOMBRE, DESCRIPCION, ZONA, FECHA)
VALUES ('Estudio de contaminación del agua en el lago Y', 'Investigación sobre la contaminación del agua en el lago Y', 'Lago Y', '2024-02-11');

INSERT INTO PROYECTO (NOMBRE, DESCRIPCION, ZONA, FECHA)
VALUES ('Análisis de calidad del agua en Zona Z', 'Análisis exhaustivo de la calidad del agua en la Zona Z', 'Zona Z', '2024-02-12');

INSERT INTO PROYECTO (NOMBRE, DESCRIPCION, ZONA, FECHA)
VALUES ('Proyecto de tratamiento de aguas residuales', 'Desarrollo de un sistema de tratamiento de aguas residuales', 'Varios', '2024-02-13');




-- INSERT INTO PARA LA TABLA TAREA
INSERT INTO TAREA (DESCRIPCION, INICIOPREVISTO, FINPREVISTO, ESTADO, NIF, ID_PROYECTO)
VALUES ('Recolección de muestras en el río Bravo', '2024-02-09 08:00:00', '2024-02-09 12:00:00', 'PENDIENTE', 'juanperez', 1);

INSERT INTO TAREA (DESCRIPCION, INICIOPREVISTO, FINPREVISTO, ESTADO, NIF, ID_PROYECTO)
VALUES ('Análisis de muestras recolectadas en Ciudad X', '2024-02-10 13:00:00', '2024-02-10 18:00:00', 'PENDIENTE', 'mariagomez', 2);

INSERT INTO TAREA (DESCRIPCION, INICIOPREVISTO, FINPREVISTO, ESTADO, NIF, ID_PROYECTO)
VALUES ('Muestreo de aguas residuales en el lago Y', '2024-02-11 08:00:00', '2024-02-11 12:00:00', 'PENDIENTE', 'pedromartinez', 3);

INSERT INTO TAREA (DESCRIPCION, INICIOPREVISTO, FINPREVISTO, ESTADO, NIF, ID_PROYECTO)
VALUES ('Análisis de calidad del agua en Zona Z', '2024-02-12 13:00:00', '2024-02-12 18:00:00', 'PENDIENTE', 'carlosruiz', 4);

INSERT INTO TAREA (DESCRIPCION, INICIOPREVISTO, FINPREVISTO, ESTADO, NIF, ID_PROYECTO)
VALUES ('Desarrollo de sistema de tratamiento de aguas residuales', '2024-02-13 09:00:00', '2024-02-13 17:00:00', 'PENDIENTE', 'lauragonzalez', 5);



