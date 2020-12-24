-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-12-2020 a las 04:48:12
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
-- Base de datos: `appdis`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `nombres` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `numconvencional` varchar(10) NOT NULL,
  `numcelular` varchar(10) NOT NULL,
  `contrasena` varchar(100) NOT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  `eliminado` tinyint(4) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `usuario`, `cedula`, `nombres`, `apellido`, `direccion`, `correo`, `numconvencional`, `numcelular`, `contrasena`, `activo`, `eliminado`) VALUES
(27, 'fgutama', '0105452171', 'Cristina', 'gutama', 'BA', 'fanny@gmail.com', '4042805', '0987846389', 'xcP5AWE0JK', 0, 0),
(29, 'fgutama', '0103277661', 'fanny', 'gutama', 'BA', 'fanny@gmail.com', '4042805', '0987846389', 'vtgfu4kZ8C', 0, 0),
(36, 'mgutama', '0107045726', 'Monica', 'Gutama', 'ba', 'fanny@gmail.com', '4521', '414212', '4422', 0, 0),
(37, 'fgutama', '0104534482', 'pablo', 'Gutama', 'ba', 'fannycrisssg@gmail.com', '4042805', '0999', 'LIIEb43BbN', 1, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `id` int(10) UNSIGNED NOT NULL,
  `tipo_cuenta` varchar(50) NOT NULL,
  `numerocuenta` varchar(100) NOT NULL,
  `fechaapertura` date NOT NULL,
  `saldo` double NOT NULL,
  `empleado` int(11) NOT NULL,
  `elimado` tinyint(4) NOT NULL,
  `id_cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cuenta`
--

INSERT INTO `cuenta` (`id`, `tipo_cuenta`, `numerocuenta`, `fechaapertura`, `saldo`, `empleado`, `elimado`, `id_cliente`) VALUES
(6, 'Ahorro', 'CUHA02S15', '2020-12-02', 2, 0, 0, 27),
(7, 'Ahorro', 'CUHA02S19', '2020-12-02', 100, 0, 0, 29),
(11, 'Ahorro', 'numero293S4', '2020-12-03', 0, 0, 0, 36),
(12, 'Ahorro', 'numero363S34', '2020-12-03', 100, 0, 0, 37);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id` int(11) NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `nombres` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `contracenia` varchar(45) NOT NULL,
  `rol` varchar(45) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `eliminado` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id`, `cedula`, `nombres`, `apellido`, `telefono`, `direccion`, `correo`, `contracenia`, `rol`, `activo`, `eliminado`) VALUES
(3, '0107051534', 'Fanny', 'Gutama', '4042805', 'BA', 'fannycrisssg@gmail.com', '4422', 'Administrador', 0, 1),
(10, '0103277661', 'Marriia', 'Gutama', '4042805', 'BA', 'fannycrisssg@gmail.com', 'vHfJ1VC6zA', 'Cajero', 1, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `intereses`
--

CREATE TABLE `intereses` (
  `id` int(11) NOT NULL,
  `tiempo` int(11) NOT NULL,
  `tasa` double NOT NULL,
  `empleado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `acceso` tinyint(1) NOT NULL,
  `Cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `poliza`
--

CREATE TABLE `poliza` (
  `id` int(11) NOT NULL,
  `monto` double NOT NULL,
  `plazo` int(11) NOT NULL,
  `interes_ganado` double DEFAULT NULL,
  `cuenta` int(10) UNSIGNED NOT NULL,
  `frecuencia` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas`
--

CREATE TABLE `preguntas` (
  `id` int(11) NOT NULL,
  `pregunta` varchar(120) NOT NULL,
  `respuesta` varchar(122) NOT NULL,
  `cliente_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

CREATE TABLE `solicitud` (
  `id` int(11) NOT NULL,
  `cliente` int(11) NOT NULL,
  `frecuencia` varchar(50) NOT NULL,
  `plazo` int(11) NOT NULL,
  `tasa_interes` double NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `copiacedula` varchar(500) NOT NULL,
  `planilla_serv_basicos` varchar(500) NOT NULL,
  `eliminado` tinyint(4) DEFAULT NULL,
  `documento` bit(15) NOT NULL,
  `monto` double NOT NULL
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
-- Volcado de datos para la tabla `transaciones`
--

INSERT INTO `transaciones` (`id`, `tipo`, `cantidad`, `fecha`, `Cuenta_id`) VALUES
(1, 'Deposito', '2.0', '2020-12-22', 6),
(2, 'Deposito', '1.0', '2020-12-22', 6),
(3, 'Retiro', '1.0', '2020-12-22', 6),
(4, 'Deposito', '1000.0', '2020-12-23', 7),
(5, 'Retiro', '1200.0', '2020-12-23', 7),
(6, 'Deposito', '500.0', '2020-12-23', 7),
(7, 'Deposito', '100.0', '2020-12-23', 12),
(8, 'Retiro', '200.0', '2020-12-23', 7);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cedula_UNIQUE` (`cedula`) USING BTREE;

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_empleado` (`empleado`),
  ADD KEY `fk_id_cliente` (`id_cliente`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cedula_UNIQUE` (`cedula`);

--
-- Indices de la tabla `intereses`
--
ALTER TABLE `intereses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_empleados` (`empleado`);

--
-- Indices de la tabla `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ALogin_Cliente1_idx` (`Cliente`);

--
-- Indices de la tabla `poliza`
--
ALTER TABLE `poliza`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Credito_Cuenta1_idx` (`cuenta`);

--
-- Indices de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_cliente_id` (`cliente_id`);

--
-- Indices de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Solicitud_Cliente1_idx` (`cliente`);

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
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `intereses`
--
ALTER TABLE `intereses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `poliza`
--
ALTER TABLE `poliza`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `transaciones`
--
ALTER TABLE `transaciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `fk_id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`);

--
-- Filtros para la tabla `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `FK3wep84emhfh9tsewsjvjwudlw` FOREIGN KEY (`Cliente`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `fk_ALogin_Cliente1` FOREIGN KEY (`Cliente`) REFERENCES `cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `poliza`
--
ALTER TABLE `poliza`
  ADD CONSTRAINT `FK2fieudwa29c5y0h4xhqh3nkgq` FOREIGN KEY (`cuenta`) REFERENCES `cuenta` (`id`),
  ADD CONSTRAINT `fk_Credito_Cuenta1` FOREIGN KEY (`cuenta`) REFERENCES `cuenta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `preguntas`
--
ALTER TABLE `preguntas`
  ADD CONSTRAINT `fk_cliente_id` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Filtros para la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD CONSTRAINT `FKyeoky7om0mnomspc2vyfodb9` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `fk_Solicitud_Cliente1` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `transaciones`
--
ALTER TABLE `transaciones`
  ADD CONSTRAINT `FKlxujb2d5npkvru0j6d7v06py2` FOREIGN KEY (`Cuenta_id`) REFERENCES `cuenta` (`id`),
  ADD CONSTRAINT `fk_Transaciones_Cuenta1` FOREIGN KEY (`Cuenta_id`) REFERENCES `cuenta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
