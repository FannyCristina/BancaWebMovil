-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-12-2020 a las 07:18:11
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistemabancario`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alogin`
--

CREATE TABLE `alogin` (
  `id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `ip` varchar(45) NOT NULL,
  `acceso` tinyint(4) NOT NULL,
  `Cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `nombres` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `fechNac` date NOT NULL,
  `correo` varchar(45) NOT NULL,
  `contracenia` varchar(45) NOT NULL,
  `eliminado` tinyint(4) DEFAULT 0,
  `activo` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `id` int(10) UNSIGNED NOT NULL,
  `numero` varchar(45) NOT NULL,
  `saldo` double NOT NULL,
  `fecha` date NOT NULL,
  `eliminado` tinyint(4) DEFAULT NULL,
  `cliente` int(11) NOT NULL,
  `tipo_cuenta` varchar(20) NOT NULL,
  `poliza_id` int(11) NOT NULL,
  `transacion_id` int(11) NOT NULL,
  `login_id` int(11) NOT NULL,
  `trabajador_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `poliza`
--

CREATE TABLE `poliza` (
  `id` int(11) NOT NULL,
  `monto` double NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `interes` double NOT NULL,
  `frecuencia` varchar(20) NOT NULL,
  `trabajador_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

CREATE TABLE `solicitud` (
  `id` int(11) NOT NULL,
  `monto` double NOT NULL,
  `frecuencia` varchar(120) NOT NULL,
  `plazo` int(11) NOT NULL,
  `tasainteres` double NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `copia_cedula` longblob NOT NULL,
  `planilla` longblob NOT NULL,
  `trabajador_id` int(11) NOT NULL,
  `id_cuenta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tasa_interes`
--

CREATE TABLE `tasa_interes` (
  `id` int(11) NOT NULL,
  `plazo` int(11) NOT NULL,
  `tasa` double NOT NULL,
  `trabajador_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajador`
--

CREATE TABLE `trabajador` (
  `id` int(11) NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `nombres` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `contracenia` varchar(45) NOT NULL,
  `rol` varchar(45) NOT NULL,
  `sueldo` double NOT NULL,
  `eliminado` tinyint(4) DEFAULT 0,
  `activo` tinyint(4) DEFAULT NULL,
  `id_tasa_interes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transaciones`
--

CREATE TABLE `transaciones` (
  `id` int(11) NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `cantidad` varchar(45) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `Cuenta_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alogin`
--
ALTER TABLE `alogin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ALogin_Cliente1_idx` (`Cliente`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cedula_UNIQUE` (`cedula`);

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numero_UNIQUE` (`numero`),
  ADD KEY `fk_Cuenta_Cliente1_idx` (`cliente`),
  ADD KEY `fk_poliza_id` (`poliza_id`),
  ADD KEY `fk_transaccion_id` (`transacion_id`),
  ADD KEY `fk_login_id` (`login_id`),
  ADD KEY `fk_trabajador_idsss` (`trabajador_id`);

--
-- Indices de la tabla `poliza`
--
ALTER TABLE `poliza`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_trabajador_ids` (`trabajador_id`);

--
-- Indices de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_trabajador_idss` (`trabajador_id`);

--
-- Indices de la tabla `tasa_interes`
--
ALTER TABLE `tasa_interes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_trabajador_id` (`trabajador_id`);

--
-- Indices de la tabla `trabajador`
--
ALTER TABLE `trabajador`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cedula_UNIQUE` (`cedula`),
  ADD KEY `fk_id_tasa_interes` (`id_tasa_interes`);

--
-- Indices de la tabla `transaciones`
--
ALTER TABLE `transaciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Transaciones_Cuenta1_idx` (`Cuenta_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alogin`
--
ALTER TABLE `alogin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `poliza`
--
ALTER TABLE `poliza`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tasa_interes`
--
ALTER TABLE `tasa_interes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `trabajador`
--
ALTER TABLE `trabajador`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `transaciones`
--
ALTER TABLE `transaciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alogin`
--
ALTER TABLE `alogin`
  ADD CONSTRAINT `fk_ALogin_Cliente1` FOREIGN KEY (`Cliente`) REFERENCES `cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `fk_Cuenta_Cliente1` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_login_id` FOREIGN KEY (`login_id`) REFERENCES `alogin` (`id`),
  ADD CONSTRAINT `fk_poliza_id` FOREIGN KEY (`poliza_id`) REFERENCES `poliza` (`id`),
  ADD CONSTRAINT `fk_trabajador_idsss` FOREIGN KEY (`trabajador_id`) REFERENCES `trabajador` (`id`),
  ADD CONSTRAINT `fk_transaccion_id` FOREIGN KEY (`transacion_id`) REFERENCES `transaciones` (`id`);

--
-- Filtros para la tabla `poliza`
--
ALTER TABLE `poliza`
  ADD CONSTRAINT `fk_trabajador_ids` FOREIGN KEY (`trabajador_id`) REFERENCES `trabajador` (`id`);

--
-- Filtros para la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD CONSTRAINT `fk_trabajador_idss` FOREIGN KEY (`trabajador_id`) REFERENCES `trabajador` (`id`);

--
-- Filtros para la tabla `tasa_interes`
--
ALTER TABLE `tasa_interes`
  ADD CONSTRAINT `fk_trabajador_id` FOREIGN KEY (`trabajador_id`) REFERENCES `trabajador` (`id`);

--
-- Filtros para la tabla `trabajador`
--
ALTER TABLE `trabajador`
  ADD CONSTRAINT `fk_id_tasa_interes` FOREIGN KEY (`id_tasa_interes`) REFERENCES `tasa_interes` (`id`);

--
-- Filtros para la tabla `transaciones`
--
ALTER TABLE `transaciones`
  ADD CONSTRAINT `fk_Transaciones_Cuenta1` FOREIGN KEY (`Cuenta_id`) REFERENCES `cuenta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
