-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3336
-- Tiempo de generación: 27-05-2024 a las 17:38:49
-- Versión del servidor: 8.0.30
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `eventos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividades`
--

CREATE TABLE `actividades` (
  `fec_final` datetime(6) NOT NULL,
  `fec_inicio` datetime(6) NOT NULL,
  `id` bigint NOT NULL,
  `id_ofertantes` bigint DEFAULT NULL,
  `tipo_actividad_id` bigint DEFAULT NULL,
  `descripcion` varchar(1000) NOT NULL,
  `materiales` varchar(1000) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `precio` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `actividades`
--

INSERT INTO `actividades` (`fec_final`, `fec_inicio`, `id`, `id_ofertantes`, `tipo_actividad_id`, `descripcion`, `materiales`, `nombre`, `precio`) VALUES
('2024-06-30 02:00:00.000000', '2024-06-01 02:00:00.000000', 1, 1, 1, 'Clases grupales de tenis para principiantes.', 'Raquetas y pelotas proporcionadas', 'Clases de Tenis', '50.00'),
('2024-07-15 02:00:00.000000', '2024-07-15 02:00:00.000000', 2, 1, 5, 'Excursión guiada por el Parque Nacional. Descubre la naturaleza y disfruta del aire libre.', 'Botas de senderismo y agua potable recomendadas', 'Excursión de Senderismo', '25.00'),
('2024-06-30 02:00:00.000000', '2024-05-20 02:00:00.000000', 3, 1, 8, 'Clases de yoga para todos los niveles. Aprende a relajarte y mejorar tu flexibilidad.', 'Tapete de yoga opcional', 'Clases de Yoga', '30.00'),
('2024-07-30 02:00:00.000000', '2024-06-15 02:00:00.000000', 5, 2, 7, 'Sesiones intensivas de entrenamiento funcional. Mejora tu fuerza, resistencia y flexibilidad.', 'Ropa deportiva cómoda y zapatillas adecuadas', 'Entrenamiento de CrossFit', '55.00'),
('2024-08-30 02:00:00.000000', '2024-07-05 02:00:00.000000', 6, 3, 8, 'Entrenamiento centrado en la fuerza, la flexibilidad y la coordinación. Mejora tu postura y bienestar general.', 'Mat de Pilates', 'Clases de Pilates', '35.00'),
('2024-07-20 02:00:00.000000', '2024-07-20 02:00:00.000000', 7, 4, 1, 'Explora la ciudad en bicicleta con guías locales. Descubre los lugares más emblemáticos y secretos de la ciudad.', 'Bicicleta y casco proporcionados', 'Tour en Bicicleta', '20.00'),
('2024-08-15 02:00:00.000000', '2024-06-25 02:00:00.000000', 8, 4, 1, 'Aprende a bailar salsa, bachata y merengue. Diviértete y aprende nuevos movimientos.', 'Calzado cómodo', 'Clases de Baile Latino', '30.00'),
('2024-07-20 02:00:00.000000', '2024-07-10 02:00:00.000000', 9, 5, 10, 'Aprende a surfear o mejora tus habilidades en nuestro campamento. Clases impartidas por instructores experimentados.', 'Tabla de surf', 'Campamento de Surf', '300.00'),
('2024-08-31 02:00:00.000000', '2024-06-15 02:00:00.000000', 10, 6, 6, 'Entrenamiento de boxeo para todos los niveles. Mejora tu condición física y defensa personal.', 'Guantes de boxeo proporcionados', 'Clases de Boxeo', '40.00'),
('2024-06-12 02:00:00.000000', '2024-05-31 02:00:00.000000', 12, 2, 5, 'A donde el Momo que la cancha esta re vacia', 'Libera a maestro splinter', 'Jugando en la cancha de platense', '10'),
('2024-07-10 02:00:00.000000', '2024-07-10 02:00:00.000000', 13, 3, 3, 'Competencia de natación de larga distancia en piscina olímpica.', 'Gorro de natación, gafas de natación, traje de baño', 'Maratón de natación', '50.00'),
('2024-06-05 02:00:00.000000', '2024-06-05 02:00:00.000000', 14, 3, 8, 'Sesión de yoga para todos los niveles en el parque.', 'Esterilla de yoga, botella de agua, toalla', 'Clase de yoga al aire libre', '20.00'),
('2024-08-20 02:00:00.000000', '2024-08-15 02:00:00.000000', 15, 3, 4, 'Competencia de fútbol entre equipos locales.', 'Balón de fútbol, uniformes, calzado deportivo', 'Torneo de fútbol', '100.00'),
('2024-05-30 02:00:00.000000', '2024-05-30 02:00:00.000000', 16, 4, 2, 'Sesión intensiva de levantamiento de pesas para avanzados.', 'Pesas, guantes de entrenamiento, toalla', 'Clase de levantamiento de pesas', '25.00'),
('2024-09-01 02:00:00.000000', '2024-09-01 02:00:00.000000', 17, 4, 10, 'Sesión de escalada en roca al aire libre con instructores certificados.', 'Arnés de escalada, casco, calzado de escalada', 'Escalada en roca', '75.00'),
('2024-10-05 02:00:00.000000', '2024-10-05 02:00:00.000000', 18, 4, 7, 'Carrera de 10 km para mejorar la resistencia cardiovascular.', 'Zapatillas de correr, ropa deportiva, botella de agua', 'Carrera de resistencia', '30.00'),
('2024-06-20 02:00:00.000000', '2024-06-20 02:00:00.000000', 19, 4, 8, 'Clase de pilates para mejorar la flexibilidad y el equilibrio.', 'Esterilla de pilates, banda elástica, pelota de pilates', 'Clase de pilates', '20.00'),
('2024-12-22 01:00:00.000000', '2024-12-20 01:00:00.000000', 20, 5, 9, 'Competencia de esquí en pista de nieve para todas las edades.', 'Esquís, bastones, casco, gafas de esquí', 'Competencia de esquí', '150.00'),
('2024-06-05 02:00:00.000000', '2024-06-05 02:00:00.000000', 21, 5, 2, 'Entrenamiento personalizado para mejorar la fuerza muscular.', 'Barras de pesas, mancuernas', 'Entrenamiento de fuerza', '25.00'),
('2024-06-10 02:00:00.000000', '2024-06-10 02:00:00.000000', 22, 5, 2, 'Circuito anaeróbico para aumentar la fuerza y resistencia muscular.', 'Cuerda de batalla, pesas, kettlebells', 'Circuito de alta intensidad', '30.00'),
('2024-06-20 02:00:00.000000', '2024-06-20 02:00:00.000000', 23, 5, 2, 'Clase especializada en el uso de kettlebells para mejorar la fuerza y el acondicionamiento.', 'Kettlebells, colchoneta', 'Entrenamiento con kettlebells', '18.00'),
('2024-06-01 02:00:00.000000', '2024-06-01 02:00:00.000000', 24, 6, 3, 'Sesión de natación para todos los niveles, enfocada en el acondicionamiento físico y la diversión.', 'Gorro de natación, gafas, bañador', 'Natación recreativa', '15.00'),
('2024-06-05 02:00:00.000000', '2024-06-05 02:00:00.000000', 25, 6, 3, 'Entrenamiento de waterpolo para principiantes y avanzados.', 'Gorro de waterpolo, bañador', 'Clases de waterpolo', '25.00'),
('2024-06-10 02:00:00.000000', '2024-06-10 02:00:00.000000', 26, 6, 3, 'Clase de buceo libre para aprender las técnicas básicas de apnea.', 'Traje de neopreno, aletas, máscara', 'Buceo libre', '50.00'),
('2024-06-05 02:00:00.000000', '2024-06-05 02:00:00.000000', 27, 1, 4, 'Torneo de baloncesto por equipos con premios para los ganadores.', 'Balón de baloncesto, uniformes', 'Torneo de baloncesto', '20.00'),
('2024-06-10 02:00:00.000000', '2024-06-10 02:00:00.000000', 28, 1, 4, 'Partido recreativo de voleibol en la playa.', 'Balón de voleibol, red', 'Partido de voleibol', '15.00'),
('2024-06-15 02:00:00.000000', '2024-06-15 02:00:00.000000', 29, 1, 4, 'Torneo de rugby con varios equipos locales.', 'Balón de rugby, uniformes, protectores', 'Torneo de rugby', '25.00'),
('2024-06-05 02:00:00.000000', '2024-06-05 02:00:00.000000', 30, 4, 6, 'Sesión de judo para principiantes y avanzados, enfocada en técnicas de lanzamiento y agarre.', 'Judogi', 'Entrenamiento de judo', '25.00'),
('2024-06-10 02:00:00.000000', '2024-06-10 02:00:00.000000', 31, 4, 6, 'Entrenamiento de taekwondo para mejorar las técnicas de patadas y defensa.', 'Dobok, protectores', 'Clase de taekwondo', '18.00'),
('2024-06-15 02:00:00.000000', '2024-06-15 02:00:00.000000', 32, 4, 6, 'Clase de kickboxing enfocada en la técnica y el acondicionamiento físico.', 'Guantes de boxeo, protectores de piernas', 'Entrenamiento de kickboxing', '22.00'),
('2024-12-10 01:00:00.000000', '2024-12-10 01:00:00.000000', 33, 4, 9, 'Sesión de patinaje sobre hielo en pista cubierta, ideal para todas las edades.', 'Patines de hielo, casco', 'Patinaje sobre hielo', '20.00'),
('2024-12-15 01:00:00.000000', '2024-12-15 01:00:00.000000', 34, 4, 9, 'Entrenamiento de biatlón combinando esquí de fondo y tiro con rifle.', 'Esquís de fondo, rifle de aire comprimido, casco', 'Clases de biatlón', '60.00'),
('2024-12-05 01:00:00.000000', '2024-12-05 01:00:00.000000', 35, 4, 9, 'Lección de snowboard para principiantes y avanzados, centrada en técnicas de deslizamiento y trucos.', 'Tabla de snowboard, casco', 'Lección de snowboard', '55.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consumidores`
--

CREATE TABLE `consumidores` (
  `id` bigint NOT NULL,
  `usuario_id` bigint DEFAULT NULL,
  `nif` varchar(9) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `telefono` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `consumidores`
--

INSERT INTO `consumidores` (`id`, `usuario_id`, `nif`, `nombre`, `apellidos`, `correo`, `direccion`, `telefono`) VALUES
(1, 1, '41637285H', 'Carlos', 'Gómez Sánchez', 'carlos.gomez@example.com', 'Plaza España, 8, Valencia', '+34 677 890 123'),
(3, 3, '77930712L', 'María', 'Pérez López', 'fanmejor@gmail.com', 'Calle Mayor, 10, Sevilla', '+34 600 123 456'),
(4, 4, '76543210X', 'Laura', 'González Martínez', 'laura.gonzalez@example.com', 'Calle Mayor, 10, Barcelona', '+34 655 432 109'),
(5, 5, '54321098M', 'Facundo Fernando', 'Rodríguez Fernández', 'juan.rodriguez@example.com', 'Avenida Diagonal, 50, Barcelona', '+34 688 999 777'),
(6, NULL, 'SS987P', 'Stephen', 'Strange', 'stephen@sanctums.com', 'Sanctum Sanctorum, New York City', '+1 (212) 555-0169'),
(7, NULL, 'SR123T', 'Steve', 'Rogers', 'steve@avengers.com', 'Avengers Tower, New York City', '+1 (212) 555-0197'),
(8, NULL, 'BB456Y', 'Bruce', 'Banner', 'bruce@hulklab.com', 'Hulk Lab, New York City', '+1 (212) 555-0153'),
(9, NULL, 'NR789U', 'Natasha', 'Romanoff', 'natasha@shield.com', 'SHIELD Headquarters, Washington D.C.', '+1 (212) 555-0128'),
(10, NULL, 'TO321W', 'Thor', 'Odinson', 'thor@asgard.com', 'Asgardian Embassy, New York City', '+1 (212) 555-0176');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ofertantes`
--

CREATE TABLE `ofertantes` (
  `id` bigint NOT NULL,
  `usuario_id` bigint DEFAULT NULL,
  `nif` varchar(9) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `telefono` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `ofertantes`
--

INSERT INTO `ofertantes` (`id`, `usuario_id`, `nif`, `nombre`, `apellidos`, `correo`, `direccion`, `telefono`) VALUES
(1, NULL, 'HT741D', 'Hiroshi', 'Tanaka', 'hiroshi.tanaka@example.jp', '1-1 Ginza, Tokyo, Japan', '+81 555 555 555'),
(2, 5, '54321098M', 'Facundo Fernando', 'Rodríguez Fernández', 'juan.rodriguez@example.com', 'Avenida Diagonal, 50, Barcelona', '+34 688 999 777'),
(3, NULL, 'MA789Z', 'Mohammed', 'Ali', 'mohammed.ali@example.com', '789 Elm Street, Metropolis, USA', '+1 (555) 555-5555'),
(4, NULL, 'EJ987A', 'Emma', 'Johnson', 'emma.johnson@example.com', '987 Maple Avenue, Smalltown, USA', '+1 (555) 321-9876'),
(5, NULL, 'JM654B', 'Juan', 'Martínez', 'juan.martinez@example.es', 'Calle Mayor, Madrid, España', '+34 555 123 456'),
(6, NULL, 'SB852C', 'Sophie', 'Brown', 'sophie.brown@example.co.uk', '22 Park Lane, London, UK', '+44 555 987 654');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reseñas`
--

CREATE TABLE `reseñas` (
  `puntos` int NOT NULL,
  `fecha` date NOT NULL,
  `id` bigint NOT NULL,
  `id_actividades` bigint DEFAULT NULL,
  `id_consumidores` bigint DEFAULT NULL,
  `comentario` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `reseñas`
--

INSERT INTO `reseñas` (`puntos`, `fecha`, `id`, `id_actividades`, `id_consumidores`, `comentario`) VALUES
(4, '2024-04-01', 2, 1, 1, '¡Excelente actividad!'),
(3, '2024-04-01', 3, 2, 1, 'Nos lo pasamos bien, pero esperábamos un poco más de emoción.'),
(4, '2024-04-01', 4, 2, 1, 'Una actividad muy interesante, pero podría mejorar en algunos aspectos.'),
(5, '2024-04-01', 5, 3, 1, '¡Fue asombroso! Definitivamente, una de las mejores experiencias de mi vida.'),
(4, '2024-04-01', 7, 5, 1, 'Una actividad emocionante, pero hubo algunos contratiempos.'),
(4, '2024-04-01', 8, 6, 1, 'Una actividad emocionante, pero hubo algunos contratiempos.'),
(4, '2024-04-01', 9, 7, 1, 'Una actividad emocionante, pero hubo algunos contratiempos.'),
(4, '2024-05-25', 11, 1, 5, 'Estuvo bastante piola'),
(5, '2024-05-10', 13, 5, 3, 'Una experiencia inolvidable, todo salió perfecto.'),
(3, '2024-03-15', 14, 12, 3, 'La actividad estuvo bien, pero la organización podría mejorar.'),
(2, '2024-02-20', 15, 12, 4, 'No fue lo que esperaba, hubo varios problemas.'),
(1, '2024-01-30', 16, 10, 4, 'Muy decepcionante, nada salió bien.'),
(5, '2024-05-22', 17, 10, 6, 'Excelente, superó mis expectativas.'),
(2, '2024-02-10', 18, 9, 6, 'Demasiados inconvenientes, no lo recomendaría.'),
(4, '2024-04-25', 19, 9, 7, 'En general bien, aunque hubo algunos problemas menores.'),
(5, '2024-05-17', 20, 8, 7, 'Fantástica experiencia, todo perfecto.'),
(4, '2024-04-05', 21, 8, 8, 'La actividad fue divertida, pero hubo algunas demoras.'),
(3, '2024-03-25', 22, 12, 8, 'Entretenido, pero podría haber sido mejor organizado.'),
(1, '2024-01-15', 23, 12, 9, 'Muy mal, muchos inconvenientes y mal servicio.'),
(2, '2024-02-28', 24, 10, 9, 'No cumplió con mis expectativas, varios problemas.'),
(5, '2024-05-01', 25, 8, 10, 'Increíble, repetiría sin dudarlo.'),
(3, '2024-03-10', 26, 6, 10, 'La actividad fue aceptable, pero esperaba más.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitudes_actividades`
--

CREATE TABLE `solicitudes_actividades` (
  `fec_solictitud` datetime(6) NOT NULL,
  `id` bigint NOT NULL,
  `id_actividad` bigint DEFAULT NULL,
  `id_consumidores` bigint DEFAULT NULL,
  `id_ofertantes` bigint DEFAULT NULL,
  `estado` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `solicitudes_actividades`
--

INSERT INTO `solicitudes_actividades` (`fec_solictitud`, `id`, `id_actividad`, `id_consumidores`, `id_ofertantes`, `estado`) VALUES
('2024-05-20 19:22:44.394000', 1, 1, 5, 1, 'Pendiente'),
('2024-05-20 19:51:55.304000', 2, 2, 5, 1, 'Pendiente'),
('2024-05-26 12:21:29.329000', 4, 9, 5, 5, 'Pendiente'),
('2024-05-26 12:21:45.951000', 5, 8, 5, 4, 'Pendiente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_actividad`
--

CREATE TABLE `tipo_actividad` (
  `id` bigint NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `descripcion` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tipo_actividad`
--

INSERT INTO `tipo_actividad` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Aeróbicas', 'Actividades que implican ejercicio cardiovascular y aumento del ritmo cardíaco, como correr, nadar o hacer ciclismo.'),
(2, 'Anaeróbicas', 'Ejercicios de alta intensidad que no dependen del oxígeno, como el levantamiento de pesas o ejercicios de fuerza.'),
(3, 'Acuáticas', 'Actividades realizadas en el agua, como natación, waterpolo o buceo.'),
(4, 'De equipo', 'Deportes que se juegan en equipos, como fútbol, baloncesto, voleibol, rugby, entre otros.'),
(5, 'Individuales', 'Actividades que se realizan de manera individual, como tenis, golf, escalada, entre otros.'),
(6, 'De combate', 'Actividades que implican combate físico, como boxeo, judo, taekwondo, entre otros.'),
(7, 'De resistencia', 'Ejercicios que se centran en mejorar la resistencia muscular o cardiovascular, como el entrenamiento de circuito o el triatlón.'),
(8, 'De flexibilidad y equilibrio', 'Actividades que se enfocan en mejorar la flexibilidad y el equilibrio, como yoga, pilates o tai chi.'),
(9, 'Deportes de invierno', 'Actividades realizadas en la nieve o hielo, como esquí, snowboard o patinaje sobre hielo.'),
(10, 'Deportes extremos', 'Actividades que implican un alto nivel de riesgo, como surf, paracaidismo o escalada en roca.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` bigint NOT NULL,
  `nif` varchar(9) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) NOT NULL,
  `usuario` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nif`, `apellidos`, `direccion`, `email`, `nombre`, `password`, `role`, `telefono`, `usuario`) VALUES
(1, '41637285H', 'Gómez Sánchez', 'Plaza España, 8, Valencia', 'carlos.gomez@example.com', 'Carlos', 'G0m3zPass!', 'CONSUMER', '+34 677 890 123', 'carlosgomez_76'),
(3, '77930712L', 'Pérez López', 'Calle Mayor, 10, Sevilla', 'fanmejor@gmail.com', 'María', 'Segur@123', 'CONSUMER', '+34 600 123 456', 'elmejorfan'),
(4, '76543210X', 'González Martínez', 'Calle Mayor, 10, Barcelona', 'laura.gonzalez@example.com', 'Laura', 'GonzalezPass!', 'CONSUMER', '+34 655 432 109', 'lauragonzalez_85'),
(5, '54321098M', 'Rodríguez Fernández', 'Avenida Diagonal, 50, Barcelona', 'juan.rodriguez@example.com', 'Facundo Fernando', 'RodriguezPass!', 'PROVIDER', '+34 688 999 777', 'juanrodriguez_88');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actividades`
--
ALTER TABLE `actividades`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdlh0mjmtsg8oxhxfsm91ukyn7` (`id_ofertantes`),
  ADD KEY `FKh37dt81fg8wm73rhekexvdnt9` (`tipo_actividad_id`);

--
-- Indices de la tabla `consumidores`
--
ALTER TABLE `consumidores`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_fv6602xiqygj7nm3mh9jhb9i` (`nif`),
  ADD UNIQUE KEY `UK_3voyqt9olsmw4dj1p2g2vtfsv` (`correo`),
  ADD UNIQUE KEY `UK_eyo16tqx3y0ljkcthdit7mils` (`usuario_id`);

--
-- Indices de la tabla `ofertantes`
--
ALTER TABLE `ofertantes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_2qemdwiv2m2bl0u8srxj0i69` (`nif`),
  ADD UNIQUE KEY `UK_2k7oarpmq18ql7tt4l2veljuw` (`correo`),
  ADD UNIQUE KEY `UK_3qpuapw8ngilt5e2r1u65iibc` (`usuario_id`);

--
-- Indices de la tabla `reseñas`
--
ALTER TABLE `reseñas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKk41fdhcsr2cgwfvao1ah2ax9w` (`id_actividades`),
  ADD KEY `FK618v5ln9ls79muu7fjxivb8yu` (`id_consumidores`);

--
-- Indices de la tabla `solicitudes_actividades`
--
ALTER TABLE `solicitudes_actividades`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkfq1iu5ppn3t4yd0iris637cy` (`id_actividad`),
  ADD KEY `FKgc4g5xe446tmfpc20fhotv9bm` (`id_consumidores`),
  ADD KEY `FKato2arigs7byfvr0e67lycoua` (`id_ofertantes`);

--
-- Indices de la tabla `tipo_actividad`
--
ALTER TABLE `tipo_actividad`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_igfhcb3km5lfdcc9v0v3mdbbg` (`nif`),
  ADD UNIQUE KEY `UK_kfsp0s1tflm1cwlj8idhqsad0` (`email`),
  ADD UNIQUE KEY `UK_3m5n1w5trapxlbo2s42ugwdmd` (`usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actividades`
--
ALTER TABLE `actividades`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT de la tabla `consumidores`
--
ALTER TABLE `consumidores`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `ofertantes`
--
ALTER TABLE `ofertantes`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `reseñas`
--
ALTER TABLE `reseñas`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `solicitudes_actividades`
--
ALTER TABLE `solicitudes_actividades`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tipo_actividad`
--
ALTER TABLE `tipo_actividad`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actividades`
--
ALTER TABLE `actividades`
  ADD CONSTRAINT `FKdlh0mjmtsg8oxhxfsm91ukyn7` FOREIGN KEY (`id_ofertantes`) REFERENCES `ofertantes` (`id`),
  ADD CONSTRAINT `FKh37dt81fg8wm73rhekexvdnt9` FOREIGN KEY (`tipo_actividad_id`) REFERENCES `tipo_actividad` (`id`);

--
-- Filtros para la tabla `consumidores`
--
ALTER TABLE `consumidores`
  ADD CONSTRAINT `FKi8321uyy5ydvu4x5wo1n1lpxa` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `ofertantes`
--
ALTER TABLE `ofertantes`
  ADD CONSTRAINT `FK66i8m73qkvmfrsmirwk0vkyjq` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `reseñas`
--
ALTER TABLE `reseñas`
  ADD CONSTRAINT `FK618v5ln9ls79muu7fjxivb8yu` FOREIGN KEY (`id_consumidores`) REFERENCES `consumidores` (`id`),
  ADD CONSTRAINT `FKk41fdhcsr2cgwfvao1ah2ax9w` FOREIGN KEY (`id_actividades`) REFERENCES `actividades` (`id`);

--
-- Filtros para la tabla `solicitudes_actividades`
--
ALTER TABLE `solicitudes_actividades`
  ADD CONSTRAINT `FKato2arigs7byfvr0e67lycoua` FOREIGN KEY (`id_ofertantes`) REFERENCES `ofertantes` (`id`),
  ADD CONSTRAINT `FKgc4g5xe446tmfpc20fhotv9bm` FOREIGN KEY (`id_consumidores`) REFERENCES `consumidores` (`id`),
  ADD CONSTRAINT `FKkfq1iu5ppn3t4yd0iris637cy` FOREIGN KEY (`id_actividad`) REFERENCES `actividades` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
