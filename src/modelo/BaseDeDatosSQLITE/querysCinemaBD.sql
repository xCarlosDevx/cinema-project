CREATE TABLE IF NOT EXISTS empleados (
id integer primary key NOT NULL ,
nombre text,
apellido text,
nombreUsuario text,
contraseña text,
cargo text,
rol text
);
CREATE TABLE IF NOT EXISTS facturacion(
id integer PRIMARY KEY NOT NULL,
idPelicula integer NOT NULL,
cantBoletos numeric,
idSnack integer,
idAdicional integer,
cantidadSnack numeric,
cantidadAdicionalSnack numeric
);
CREATE TABLE IF NOT EXISTS peliculas(
id integer NOT NULL primary key ,
idCategoria integer NOT NULL,
idSala integer NOT NULL,
nombre text,
rEdad numeric,
fechaEmision text,
fechaFin text,
tipo text,
precio real,
image blob
);
CREATE TABLE IF NOT EXISTS categorias (
id integer NOT NULL PRIMARY KEY ,
nombre text
);
CREATE TABLE IF NOT EXISTS salas (
id integer NOT NULL PRIMARY KEY ,
numero numeric,
tipo text,
maxCantidad numeric
);
CREATE TABLE IF NOT EXISTS snackCombo(
id integer NOT NULL PRIMARY KEY ,
nombre text,
precio real,
tipo text,
imagen blob
);
CREATE TABLE snackAdicional(
id integer NOT NULL PRIMARY KEY ,
nombre text,
precio real,
tipo text,
imagen blob
);
CREATE TABLE IF NOT EXISTS horarios(
id integer NOT NULL PRIMARY KEY,
idPelicula integer NOT NULL,
horaEntrada text,
horaSalida text
);

INSERT INTO empleados (nombre,apellido,nombreUsuario,contraseña,cargo,rol) VALUES ('Carlos A.','La Hoz Frias','xCarlosDevx','12345678','administrador','admin')