-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-01-2022 a las 18:02:18
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `biblioteca_aev04`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `identificador` int(8) NOT NULL,
  `titulo` varchar(50) DEFAULT NULL,
  `autor` varchar(50) DEFAULT NULL,
  `anyo_nac` varchar(4) DEFAULT NULL,
  `anyo_pub` varchar(4) DEFAULT NULL,
  `editorial` varchar(50) DEFAULT NULL,
  `num_pag` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`identificador`, `titulo`, `autor`, `anyo_nac`, `anyo_pub`, `editorial`, `num_pag`) VALUES
(1, 'El se�or de los anillos', 'J.R.R. Tolkien', '1890', '1950', 'Minotauro', '1392'),
(2, 'El juego de Ender', 'Orson Scott Card', '1951', '1977', 'Ediciones B', '509'),
(3, 'Lazarillo de Tormes', 'An�nimo', 'N.C.', '1554', 'Cl�sicos Populares', '150'),
(4, 'Las uvas de la ira', 'John Steinbeck', '1902', '1939', 'Alianza', '619'),
(5, 'Watchmen', 'Alan Moore', '1953', '1980', 'ECC', '416'),
(6, 'La hoguera de las vanidades', 'Tom Wolfe', '1930', '1980', 'Anagrama', '636'),
(7, 'La familia de Pascual Duarte', 'Camilo Jos� Cela', '1916', '1942', 'Destino', '165'),
(8, 'El se�or de las moscas', 'William Golding', '1911', '1972', 'Alianza', '236'),
(9, 'La ciudad de los prodigios', 'Eduardo Mendoza', '1943', '1986', 'Seix Barral', '541'),
(10, 'Ensayo sobre la ceguera', 'Jos� Saramago', '1922', '1995', 'Santillana', '439'),
(11, 'Los surcos del azar', 'Paco Roca', '1969', '2013', 'Astiberri', '349'),
(12, 'Ghosts of Spain', 'Giles Tremlett', '1962', '2006', 'Faber & Faber', '468'),
(13, 'Sidi', 'Arturo P�rez Reverte', '1951', '2019', 'Penguin', '369'),
(14, 'Dune', 'Frank Herbert', '1920', '1965', 'Acervo', '741');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`identificador`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `libros`
--
ALTER TABLE `libros`
  MODIFY `identificador` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
