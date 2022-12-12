CREATE TABLE "empleados" (
	"id"	integer NOT NULL,
	"nombre"	text,
	"apellido"	text,
	"nombreUsuario"	text,
	"contraseña"	text,
	"cargo"	text,
	"rol"	text,
	PRIMARY KEY("id")
);

CREATE TABLE "facturaPeli" (
	"id"	integer NOT NULL,
	"idPelicula"	integer NOT NULL,
	"cantBoletos"	numeric,
	"precio"	real,
	PRIMARY KEY("id")
);

CREATE TABLE "facturaSnack" (
	"id"	integer NOT NULL,
	"idSnack"	integer NOT NULL,
	"precio"	real,
	"imagen"	blob,
	"cantidad"	numeric,
	"total"	real,
	PRIMARY KEY("id")
);

CREATE TABLE "peliculas" (
	"id"	integer NOT NULL,
	"idCategoria"	integer NOT NULL,
	"idSala"	integer NOT NULL,
	"nombre"	text,
	"rEdad"	numeric,
	"fechaEmision"	text,
	"fechaFin"	text,
	"tipo"	text,
	"precio"	real,
	"image"	blob,
	PRIMARY KEY("id")
);

CREATE TABLE "snackAdicional" (
	"id"	integer NOT NULL,
	"nombre"	text,
	"precio"	real,
	"tipo"	text,
	"imagen"	blob,
	PRIMARY KEY("id")
);

CREATE TABLE "snackCombo" (
	"id"	integer NOT NULL,
	"nombre"	text,
	"precio"	real,
	"tipo"	text,
	"imagen"	BLOB,
	PRIMARY KEY("id")
);

INSERT INTO empleados (nombre,apellido,nombreUsuario,contraseña,cargo,rol) VALUES ('Carlos A.','La Hoz Frias','xCarlosDevx','12345678','administrador','admin')