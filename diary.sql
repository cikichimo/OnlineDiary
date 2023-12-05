-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2023 at 03:14 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `diary`
--

-- --------------------------------------------------------

--
-- Table structure for table `stories`
--

CREATE TABLE `stories` (
  `sid` char(36) NOT NULL,
  `uid` char(36) NOT NULL,
  `sdate` date NOT NULL,
  `descrip` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `stories`
--

INSERT INTO `stories` (`sid`, `uid`, `sdate`, `descrip`) VALUES
('034f1fce-4d13-46e9-8d28-4b6ab873af69', '099ffe4a-ea0e-437f-9cd0-9e451f8cb44d', '2023-12-06', 'ini liptia'),
('261c154a-7117-4929-a51d-0c46b4ef2c5e', '099ffe4a-ea0e-437f-9cd0-9e451f8cb44d', '2023-12-03', 'aku sedih'),
('3c17060e-aa2b-45b7-9c7b-76c88030601a', '6cc2ee48-1cab-4e0f-be8c-f662addd4493', '2023-12-19', 'ini aku ganti dadada jadi lalala trilili'),
('91ae5add-ed9d-4f44-8a97-9d74abb8a2d3', '6cc2ee48-1cab-4e0f-be8c-f662addd4493', '2023-11-30', 'bababa'),
('b94fed2f-7c55-4a99-88b2-5e7d378fb594', '6cc2ee48-1cab-4e0f-be8c-f662addd4493', '2023-12-05', 'bbjbjbj'),
('db42faa3-a43a-4354-b6e4-709efd77a111', '6cc2ee48-1cab-4e0f-be8c-f662addd4493', '2023-12-04', 'aku coba bikin');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `uid` char(36) NOT NULL,
  `uname` varchar(40) DEFAULT NULL,
  `pass` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`uid`, `uname`, `pass`) VALUES
('099ffe4a-ea0e-437f-9cd0-9e451f8cb44d', 'liptia', '123'),
('6cc2ee48-1cab-4e0f-be8c-f662addd4493', 'tia', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `stories`
--
ALTER TABLE `stories`
  ADD PRIMARY KEY (`sid`),
  ADD KEY `uid` (`uid`),
  ADD KEY `uid_2` (`uid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`uid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
