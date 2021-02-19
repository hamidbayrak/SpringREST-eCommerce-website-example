-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 19 Şub 2021, 13:47:28
-- Sunucu sürümü: 10.4.16-MariaDB
-- PHP Sürümü: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `mvc_project`
--

DELIMITER $$
--
-- Yordamlar
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `cart_proc` (IN `userid` INT)  NO SQL
SELECT
	cart.cartid,
	product.pid,
	product.img1,
	product.title,
	product.price,
	cart.quantity,
	cart.uid 
FROM
	cart
	JOIN product ON cart.pid = product.pid 
WHERE
	cart.uid = userid$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `wishlist_proc` (IN `userid` INT(255))  NO SQL
SELECT
	wishlist.wid,
	wishlist.pid,
	product.img1,
	product.title,
	product.price,
	wishlist.uid 
FROM
	product
	JOIN wishlist ON wishlist.pid = product.pid 
WHERE
	uid = userid$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `cart`
--

CREATE TABLE `cart` (
  `cartid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `uid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `cart`
--

INSERT INTO `cart` (`cartid`, `pid`, `quantity`, `uid`) VALUES
(51, 4, 1, 2),
(52, 7, 3, 2);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `cart_props`
--

CREATE TABLE `cart_props` (
  `cartid` int(11) NOT NULL,
  `img1` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `price` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `total` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `uid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `category`
--

CREATE TABLE `category` (
  `cid` int(11) NOT NULL,
  `category_name` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `category`
--

INSERT INTO `category` (`cid`, `category_name`) VALUES
(1, 'Men'),
(2, 'Women'),
(3, 'Kids'),
(4, 'Sport');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(56);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `product`
--

CREATE TABLE `product` (
  `pid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `date` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `detail` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `img1` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `img2` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `img3` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `price` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `price_old` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `product`
--

INSERT INTO `product` (`pid`, `cid`, `date`, `detail`, `img1`, `img2`, `img3`, `price`, `price_old`, `title`) VALUES
(1, 1, '2021-01-21', 'Harika Pantolon', 'ekp1.jpg', 'ekp2.jpg', 'ekp3.jpg', '180', '250', 'Erkek Kot Pantolon'),
(2, 1, '2021-01-21', 'Harika Gömlek', 'eg1.jpg', 'eg2.jpg', 'eg3.jpg', '125', '150', 'Erkek Gömlek'),
(3, 2, '2021-01-22', 'Kaliteli Bluz', 'kb1.jpg', 'kb2.jpg', 'kb3.jpg', '70', '95', 'Kadın Bluz'),
(4, 3, '2021-01-22', '3-5 Yaş Tulum', 'uct1.jpg', 'uct2.jpg', 'uct3.jpg', '50', '100', 'Unisex Çocuk Tulum'),
(5, 2, '2021-01-23', 'İnce Topuk 10 cm Ayakkabı', 'ka1.jpg', 'ka2.jpg', 'ka3.jpg', '250', '370', 'Kadın Ayakkabı'),
(6, 1, '2021-01-24', 'Yarım Balıkçı Boğaz Kazak', 'ek1.jpg', 'ek2.jpg', 'ek3.jpg', '180', '300', 'Erkek Kazak'),
(7, 3, '2021-01-25', '5-6 Yaş Bebek Spor Ayakkabı', '56ya1.jpg', '56ya2.jpg', '56ya3.jpg', '65', '85', '5-6 Yaş Ayakkabı'),
(8, 2, '2021-01-25', 'Fermuarlı Kalem etek', 'kef1.jpg', 'kef2.jpg', 'kef3.jpg', '60', '90', 'Kalem Etek');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `users`
--

CREATE TABLE `users` (
  `uid` int(11) NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `is_admin` bit(1) NOT NULL,
  `mail` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `remember` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `users`
--

INSERT INTO `users` (`uid`, `address`, `is_admin`, `mail`, `name`, `password`, `remember`) VALUES
(1, 'null', b'1', 'admin@mail.com', 'Admin SURNAME', '12345', NULL),
(2, 'İstanbul', b'0', 'user@mail.com', 'User SURNAME', '12345', NULL);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `wishlist`
--

CREATE TABLE `wishlist` (
  `wid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `pid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Tablo döküm verisi `wishlist`
--

INSERT INTO `wishlist` (`wid`, `uid`, `pid`) VALUES
(53, 2, 5),
(54, 2, 8);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `wishlist_props`
--

CREATE TABLE `wishlist_props` (
  `wid` int(11) NOT NULL,
  `img1` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `price` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_turkish_ci DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `pid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cartid`);

--
-- Tablo için indeksler `cart_props`
--
ALTER TABLE `cart_props`
  ADD PRIMARY KEY (`cartid`);

--
-- Tablo için indeksler `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`cid`);

--
-- Tablo için indeksler `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`pid`);

--
-- Tablo için indeksler `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`uid`);

--
-- Tablo için indeksler `wishlist`
--
ALTER TABLE `wishlist`
  ADD PRIMARY KEY (`wid`);

--
-- Tablo için indeksler `wishlist_props`
--
ALTER TABLE `wishlist_props`
  ADD PRIMARY KEY (`wid`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `cart`
--
ALTER TABLE `cart`
  MODIFY `cartid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- Tablo için AUTO_INCREMENT değeri `wishlist`
--
ALTER TABLE `wishlist`
  MODIFY `wid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
