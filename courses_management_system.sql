-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 30, 2020 at 03:37 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `courses management system`
--

-- --------------------------------------------------------

--
-- Table structure for table `addcourses`
--

CREATE TABLE `addcourses` (
  `NAME` varchar(15) NOT NULL,
  `AGE` int(11) NOT NULL,
  `courseandprice` varchar(20) NOT NULL,
  `ID` varchar(20) NOT NULL,
  `gender` varchar(15) NOT NULL,
  `phonenumber` varchar(15) NOT NULL,
  `date` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `addcourses`
--

INSERT INTO `addcourses` (`NAME`, `AGE`, `courseandprice`, `ID`, `gender`, `phonenumber`, `date`) VALUES
('ahmed', 18, 'css300$', '20', 'Female', '01145264876', '2020-05-30'),
('bassam', 33, 'css  300$', '45', 'Male', '01123456789', '2020-05-30');

-- --------------------------------------------------------

--
-- Table structure for table `createaccount`
--

CREATE TABLE `createaccount` (
  `USERNAME` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `repeatpassword` varchar(15) NOT NULL,
  `phonenumber` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `createaccount`
--

INSERT INTO `createaccount` (`USERNAME`, `password`, `repeatpassword`, `phonenumber`) VALUES
('ahmed', '1234', '1234', '01145264876');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `addcourses`
--
ALTER TABLE `addcourses`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `createaccount`
--
ALTER TABLE `createaccount`
  ADD PRIMARY KEY (`USERNAME`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
