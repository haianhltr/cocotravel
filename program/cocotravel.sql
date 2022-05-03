-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th5 03, 2021 lúc 01:27 PM
-- Phiên bản máy phục vụ: 10.4.18-MariaDB
-- Phiên bản PHP: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `cocotravel`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `flight`
--


create database cocotravel;
CREATE TABLE `flight` (
  `flight_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `departure` varchar(100) NOT NULL,
  `destination` varchar(100) NOT NULL,
  `duration` varchar(100) DEFAULT NULL,
  `transit` varchar(100) DEFAULT NULL,
  `price` decimal(50,0) DEFAULT NULL,
  `luggage` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `flight`
--

INSERT INTO `flight` (`flight_id`, `date`, `departure`, `destination`, `duration`, `transit`, `price`, `luggage`) VALUES
(1, '2021-04-26', 'Taipei', 'Saigon', '6 hours', '2 transits', '200', 1),
(2, '2021-04-19', 'Hong Kong', 'Saigon', '5 hours', 'no transit', '500', 1),
(3, '2021-04-16', 'Bangkok', 'Saigon', '2 hours', 'no transit', '200', 0),
(4, '2021-04-30', 'Los Angeles', 'Saigon', '12 hours', 'no transit', '800', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `FlightSale`
--

CREATE TABLE `FlightSale` (
  `SaleID` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `SaleDate` date DEFAULT NULL,
  `flight_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `FlightSale`
--

INSERT INTO `FlightSale` (`SaleID`, `customer_id`, `SaleDate`, `flight_id`) VALUES
(3, 1, '2021-04-15', 3),
(4, 1, '2021-04-16', 3),
(5, 1, '2021-04-19', 1),
(6, 1, '2021-04-19', 1),
(7, 1, '2021-04-24', 3),
(8, 1, '2021-04-25', 3),
(9, 2, '2021-04-26', 1),
(10, 8, '2021-04-26', 1),
(11, 9, '2021-04-26', 4),
(12, 9, '2021-04-26', 1),
(13, 2, '2021-05-03', 1),
(14, 10, '2021-05-03', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `foodlist`
--

CREATE TABLE `foodlist` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `vietnamese_name` varchar(100) DEFAULT NULL,
  `description` varchar(700) NOT NULL,
  `url` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `foodlist`
--

INSERT INTO `foodlist` (`id`, `name`, `vietnamese_name`, `description`, `url`) VALUES
(1, 'Pho', 'Phở', 'Pho is a Vietnamese soup consisting of broth, rice noodles (bánh phở), herbs, and meat (usually beef) (phở bò), sometimes chicken (phở gà).Pho is a popular food in Vietnam where it is served in households, street stalls and restaurants countrywide. Pho is considered Vietnam national dish.', 'https:]]icdn.one]upload]2021]01]21]20210121134831-8ed640e8.jpg'),
(2, 'Banh Mi', 'Bánh Mì', 'Banh Mi is the Vietnamese word for bread. In Vietnamese cuisine, it also refers to a type of short baguette with thin, crisp crust and soft, airy texture inside that is often split lengthwise and filled with various savory ingredients like a submarine sandwich and served as a meal.', 'https:]]res.klook.com]image]upload]fl_lossy.progressive,q_85]c_fill,w_680]v1617091709]blog]erykuinxdtlo280gxm2d.webp'),
(3, 'Sticky Rice', 'Xôi ', 'Sticky rice is a common food made from sticky glutinous rice steamed or cooked – a rustic dish which is very prevalent in many Asian countries.', 'https:]]res.klook.com]image]upload]fl_lossy.progressive,q_85]c_fill,w_680]v1617091731]blog]y8udm7xbqa6j76jrieq5.webp'),
(4, 'Rice Noodle Soup', 'Bánh canh', 'Rice Noodle Soup is a thick Vietnamese noodle that can be made from tapioca flour or a mixture of rice and tapioca flour', 'https:]]res.klook.com]image]upload]fl_lossy.progressive,q_85]c_fill,w_680]v1617091766]blog]gulkyim0opnfqmavzztn.webp'),
(5, 'Springroll', 'Gỏi Cuốn', 'These light and healthy fresh spring rolls are a wholesome choice when you\'ve been indulging in too much of the fried food in Vietnam. The translucent parcels are first packed with salad greens, a slither of meat or seafood and a layer of coriander, before being neatly rolled and dunked in Vietnam\'s favorite condiment -- fish sauce.', 'https:]]dynaimage.cdn.cnn.com]cnn]q_auto,w_634,c_fill,g_auto,h_357,ar_16:9]http%3A%2F%2Fcdn.cnn.com%2Fcnnnext%2Fdam%2Fassets%2F170306134418-goi-cuon.jpg'),
(6, 'Hue Noodle', 'Bún Bò Huế', 'Central Vietnam\'s take on noodles caters to carnivores with its meaty broth and piles of beef and pork. The thick slippery rice noodles also make for a heartier meal than noodles found in the north and south.', 'https:]]i.ytimg.com]vi]A_o2qfaTgKs]maxresdefault.jpg'),
(7, 'Banh Cuon', 'Bánh Cuốn', 'These rolled up rice flour pancakes are best when served piping hot, still soft and delicate. Although seemingly slender and empty they have a savory filling of minced pork and mushrooms. Zest is also added by dunking the slippery parcels in a fishy dipping sauce.', 'https://dynaimage.cdn.cnn.com/cnn/q_auto,w_634,c_fill,g_auto,h_357,ar_16:9/http%3A%2F%2Fcdn.cnn.com%2Fcnnnext%2Fdam%2Fassets%2F170504150157-banh-cuon.jpg'),
(8, 'Bot Chien', 'Bột Chiên', 'Saigon\'s favorite streetside snack, bot chien, is popular with both the afterschool and the after-midnight crowd. Chunks of rice flour dough are fried in a large wok until crispy and then an egg is broken into the mix. Once cooked it\'s served with slices of papaya, shallots and green onions, before more flavor is added with pickled chili sauce and rice vinegar.', 'https:]]dynaimage.cdn.cnn.com]cnn]q_auto,w_634,c_fill,g_auto,h_357,ar_16:9]http%3A%2F%2Fcdn.cnn.com%2Fcnnnext%2Fdam%2Fassets%2F160524092144-vietnam-street-food-bot-chien.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hotel`
--

CREATE TABLE `hotel` (
  `hotel_id` int(10) NOT NULL,
  `city` varchar(50) NOT NULL,
  `district` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `rating` decimal(2,1) NOT NULL,
  `address` varchar(100) NOT NULL,
  `fromcenter` decimal(10,0) NOT NULL,
  `breakfast` tinyint(1) NOT NULL,
  `price` decimal(50,0) NOT NULL,
  `airportpickup` tinyint(1) NOT NULL,
  `image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hotel`
--

INSERT INTO `hotel` (`hotel_id`, `city`, `district`, `name`, `rating`, `address`, `fromcenter`, `breakfast`, `price`, `airportpickup`, `image`) VALUES
(1, 'Saigon', '1', 'Aroma', '9.0', '2 Dong Khoi', '4', 1, '30', 1, NULL),
(2, 'Saigon', '1', 'La Familia', '8.5', '65-67 Hai Ba Trung, District 1, Ho Chi Minh City, Vietnam', '2', 1, '25', 1, '[value-11]'),
(3, 'Saigon', '1', 'Orchids Saigon Hotel', '9.1', '28-30-32 Le Lai Street, Ben Thanh Ward, District 1, Ho Chi Minh City, Vietnam', '2', 1, '25', 1, '[value-11]'),
(4, 'Saigon', 'Binh Thanh', 'The Myst', '8.6', '20 Pham Ngoc Thach Street, District 3, District 3, Ho Chi Minh City, Vietnam', '2', 1, '25', 1, '[value-11]'),
(5, 'Saigon', '3', 'Winsuites Saigon', '7.5', '192 Pasteur Street, Ward 6, District 3, District 3, Ho Chi Minh City, Vietnam', '2', 1, '25', 1, '[value-11]'),
(6, 'Saigon', '5', 'Pullman Hotel', '9.0', '28-30-32 Le Lai Street, Ben Thanh Ward, District 5, Ho Chi Minh City, Vietnam', '2', 1, '25', 1, '[value-11]');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `HotelSale`
--

CREATE TABLE `HotelSale` (
  `SaleID` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `SaleDate` date NOT NULL,
  `hotel_id` int(10) NOT NULL,
  `checkinDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `HotelSale`
--

INSERT INTO `HotelSale` (`SaleID`, `customer_id`, `SaleDate`, `hotel_id`, `checkinDate`) VALUES
(1, 1, '2021-04-16', 1, '0000-00-00'),
(2, 1, '2021-04-17', 1, '0000-00-00'),
(3, 1, '2021-04-19', 1, '0000-00-00'),
(4, 2, '2021-04-26', 1, '2021-04-19'),
(5, 2, '2021-04-26', 1, '2021-04-28'),
(6, 2, '2021-04-26', 1, '2021-04-25'),
(7, 2, '2021-04-26', 1, '2021-04-25'),
(8, 8, '2021-04-26', 2, '2021-04-26'),
(9, 9, '2021-04-26', 1, '2021-04-26'),
(10, 2, '2021-05-03', 5, '2021-05-03');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `others`
--

CREATE TABLE `others` (
  `id` int(3) NOT NULL,
  `type` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `url` varchar(300) NOT NULL,
  `address` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `others`
--

INSERT INTO `others` (`id`, `type`, `name`, `url`, `address`) VALUES
(1, 'Laundry', 'Bảo Châu laundry', 'https:]]lh3.googleusercontent.com]p]AF1QipObErryhT3uOMShg7FDegymsD4vnsQfUD1F8TLV=s1600-w400', '75 Lý Tự Trọng, phía sau, Quận 1, Thành phố Hồ Chí Minh, Việt Nam'),
(2, 'Convenience store', 'Circle K', 'https:]]vinatechjsc.vn]wp-content]uploads]2019]10]chu%E1%BB%97i-c%E1%BB%ADa-h%C3%A0ng-Circle-K-1024x768.jpg', '92 Nguyễn Hữu Cảnh, Phường 22, Bình Thạnh, Thành phố Hồ Chí Minh, Việt Nam\r\n'),
(3, 'Gaming', 'Cao Toc Gaming', 'https:]]scontent.fmci1-3.fna.fbcdn.net]v]t1.18169-9]12042773_1160715650612338_4090130684275208822_n.jpg?_nc_cat=108&ccb=1-3&_nc_sid=730e14&_nc_ohc=HHhqmyzYwaMAX9Sxbj2&_nc_ht=scontent.fmci1-3.fna&oh=f5993f2fb72cc3c17040b4b09a988b1f&oe=60ABF6F3', '67A, Trần Quang Khải, P. Tân Định, Q. 1, Tp. Hồ Chí Minh'),
(4, 'Bars', 'Boheme Bui Vien', 'https:]]kenh14cdn.com]2020]11]3]1-1604383359109433983450.jpg', '28/2A Bùi Viện, P. Phạm Ngũ Lão, Quận 1, TP. HCM'),
(5, 'Bars', 'Atmos Bar', 'https:]]vietnamnightlife.com]uploads]images]2020]06]1592204717-single_product2-atmosclubcoverphoto.jpg', '153 Tôn Thất Đạm, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh, Việt Nam'),
(6, 'Convenience store', 'Family Mart', 'https:]]img.websosanh.vn]v2]users]review]images]danh-sach-cac-cua-hang]rlsqykg64ie5p.jpg', ' 92 Nguyễn Hữu Cảnh, P.22, Q. Bình Thạnh,Thành phố Hồ Chí Minh,'),
(7, 'Gaming', 'Nextgen Gaming', 'https:]]thicong.kingdomstore.vn]wp-content]uploads]2019]04]phong-net-da-thi-cong-6.jpg', '125/2 Đ. Hoà Hưng, Phường 12, Quận 10, Thành phố Hồ Chí Minh, Việt Nam');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `place`
--

CREATE TABLE `place` (
  `place_id` int(5) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `ranking` int(3) NOT NULL,
  `category` varchar(100) NOT NULL,
  `address` varchar(300) NOT NULL,
  `district` varchar(50) NOT NULL,
  `image` varchar(500) NOT NULL,
  `bus_support` tinyint(1) NOT NULL,
  `shortdescription` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `place`
--

INSERT INTO `place` (`place_id`, `name`, `ranking`, `category`, `address`, `district`, `image`, `bus_support`, `shortdescription`) VALUES
(1, 'Cu Chi Tunnel', 10, 'Historic', 'Road 15, Phu Hiep, District Cu Chi, Ho Chi Minh City, Vietnam', 'Cu Chi District', 'https:]]www.planetware.com]wpimages]2020]04]vietnam-ho-chi-minh-city-top-attractions-cu-chi-tunnels.jpg', 1, 'About 60 kilometers from Ho Chi Minh City, the Cu Chi Tunnels are a must-see half-day trip and one of the top tours for visitors to the city. This vast 250-kilometer-plus network of tunnels served as the base for the Viet Cong\'s military operations during the Vietnam War. Soldiers used the excruciatingly cramped tunnels as hiding places, hospitals, communication bases, supply routes, and even living quarters.'),
(2, 'Bitexco', 9, 'Famous Building', '2 Hai Trieu Street District 1,District 1, Ho Chi Minh City, Vietnam', 'District 1', 'https:]]i1.wp.com]fairdinkumtraveller.com]wp-content]uploads]2017]01]2017-01-23-11.21.41.jpg?w=1080', 1, 'Bitexco Financial Tower (Vietnamese: Tháp Tài chính Bitexco) is a skyscraper in Ho Chi Minh City, Vietnam. At its completion in 2010, it became the tallest building in Vietnam and kept this status until January 2011, when it was surpassed by Keangnam Hanoi Landmark Tower. With 68 floors above ground and three basements, the building has a height of 262.5 metres (861 ft), making it the second tallest building in the city, fifth tallest in Vietnam, and the 263rd tallest in the world, as of the beginning of 2018.'),
(3, 'Ben Thanh Market', 5, 'Historic', 'Le Loi Street, District 1, Ho Chi Minh City, Vietnam', 'District 1', 'https:]]media.christinas.vn]uploads]2017]01]ben-thanh-market-11.jpg', 1, 'Bến Thành Market (Vietnamese: Chợ Bến Thành) is located in the center of Hồ Chí Minh City, Vietnam in District 1. The market is one of the earliest surviving structures in Saigon and an important symbol of the city. Ben Thanh Market is a famous destination for many local and foreign tourists from all around the world. The market operates all year round and opens at around 6am} every day until the official closing time at 6pm. After 6pm, the day market transitions into a night market which runs until 10pm.'),
(4, 'War Remnants Museum', 11, 'Historic', '28 Vo Van Tan, Ward 6, District 3, Ho Chi Minh City, Vietnam', 'District 3', 'https:]]www.planetware.com]photos-large]VIE]vietnam-ho-chi-minh-city-war-remnants-museum-2.jpg', 1, 'The War Remnants Museum is one of the most popular museums in Vietnam, with harrowing exhibits related to the horrors of war in this battle-worn nation. The museum primarily focuses on the Vietnam War, however, some exhibits relate to the first Indochina War with French colonialists.'),
(5, 'Notre Dame Cathedral Basilica of Saigon', 1, 'Historic', 'Notre Dame Cathedral Basilica of Saigon, Ben Nghé, tp. Ho Chí Minh, Ho Chi Minh', 'District 1', 'https:]]www.planetware.com]photos-large]VIE]vietnam-ho-chi-minh-city-saigon-opera-house-1.jpg', 1, 'Also known as The Municipal Theatre of Ho Chi Minh City, the elegant Saigon Opera House, at the start of the famous tree-lined Le Loi Avenue, is eye-candy for architecture buffs - especially fans of the French colonial style. It was built as Opera de Saigon in 1897 by Eugene Ferret, a French architect, to entertain French colonists, and its striking facade echoes the style of the Petit Palais, which was built in the same year in Paris.'),
(6, 'Reunification Palace', 2, 'Historic', '135 Nam Ky Khoi Nghia Street, District 1, Ho Chi Minh City\r\n\r\n', 'District 1', 'https:]]www.planetware.com]photos-large]VIE]vietnam-ho-chi-minh-city-reunification-palace.jpg', 1, 'A visit to the Reunification Palace, once known as Independence Palace, is more about the historic events that took place here than any pomp and grandeur. In fact, this 1960s-style building, with its large, airy rooms and dated furnishings, seems frozen in time since April 30, 1975, when a North Vietnamese army tank crashed through the iron gates here, bringing an end to the Vietnam War. For locals, the palace represents this historic event and the reunification of the country.'),
(7, 'Starlight Bridge', 15, 'Saigonese', '2 Phan Van Chuong Street, District 7, Ho Chi Minh City, Vietnam', 'District 7', 'https:]]i.ytimg.com]vi]T8-GMum3Kl4]maxresdefault.jpg', 0, 'The Starlight Bridge in Ho Chi Minh City, called Cau Anh Sao in Vietnamese is a romantic and poetic bridge located in the heart of the international commercial and financial district of Phu My Hung. It has been the one of the most modern and beautiful pedestrian bridges in Vietnam. Each night, the bridge squirt out the water mixed with the colorful lights, which is a breathtaking view.'),
(8, 'Thien Hau Temple', 18, 'Saigonese', '710 Nguyen Trai Street, District 5, Ho Chi Minh City', 'District 5', 'https:]]www.planetware.com]photos-large]VIE]vietnam-ho-chi-minh-city-thien-hau-temple.jpg', 1, 'The atmospheric 19th-century Thiên Hau Temple is one of the best places to visit in Ho Chi Minh City\'s Chinatown (ChoLon) and one of the oldest Chinese temples in the city. Dedicated to the Lady of the Sea, Thiên Hau, this evocative temple is visited by local worshippers, as well as tourists, and many of the materials used in its construction were brought from China'),
(9, 'Museum of Vietnamese History', 14, 'Saigonese', '2 Nguyen Binh Khiêm Street, Ben Nghé Ward Ho Chí Minh City', 'District 1', 'https:]]www.planetware.com]photos-large]VIE]vietnam-ho-chi-minh-city-museum-of-vietnamese-history.jpg', 1, 'Within the grounds of the botanic gardens, the Museum of Vietnamese History unveils the country\'s cultural evolution from the Bronze Age to the early 20th century. The exhibits are organized chronologically and include artifacts from Vietnam\'s former ethnic groups, including the Dong Son, Funan, Khmer, and Cham civilizations. Particularly interesting are the stone and bronze sculptures, Angkor Wat relics, and the well-preserved mummy.'),
(10, 'Bach Dang Harbor', 3, 'Historic', '22 Nguyen Hue Street,Ben Nghe Ward, District 1, Ho Chi Minh city, Vietnam', 'District 1', 'https:]]img.theculturetrip.com]1440x]smart]wp-content]uploads]2018]02]shutterstock_659454553.png', 1, 'Bach Dang Wharf (Bến Bạch Đằng) is located next to the ferry terminal and close to the Renaissance hotel in central Ho Chi Minh City. The pier is a popular launching point for boat trips along the Saigon River.'),
(11, 'Walking Street Nguyen Hue', 4, 'Saigonese', '22 Nguyen Hue Street,Ben Nghe Ward, District 1, Ho Chi Minh city, Vietnam', 'District 1', 'http:]]static.asiawebdirect.com]m]bangkok]portals]vietnam]homepage]ho-chi-minh-city]attractions]nguyen-hue-street]allParagraphs]BucketComponent]ListingContainer]03]BucketList]0]image2]nguyen-hue-street-04.jpg', 1, 'At the other end of the promenade you find the Saigon River from where you can enjoy a nice view with a breeze. Walking from one end of the street to the other takes you around ten minutes and you will find some nice bars and restaurants along the way. It is nice to walk on Nguyen Hue Street, or take a seat on one of the benches, to have a look at the daily life and routine of the locals.'),
(12, 'Phu My Bridge', 7, 'Saigonese', '194 Nguyen Van Quy Street, Phu Thuan Ward, Disctrict 7, Ho Chi Minh City, Vietnam', 'District 7', 'https:]]media.istockphoto.com]photos]phu-my-bridge-at-colorful-sunset-in-ho-chi-minh-city-vietnam-picture-id1223732185?k=6&m=1223732185&s=612x612&w=0&h=hOei4KRyKuuTotbkWfydw-UBO9Ohd5l0D4q7DQ4xhM8=', 1, 'At the other end of the promenade you find the Saigon River from where you can enjoy a nice view with a breeze. Walking from one end of the street to the other takes you around ten minutes and you will find some nice bars and restaurants along the way. It is nice to walk on Nguyen Hue Street, or take a seat on one of the benches, to have a look at the daily life and routine of the locals.');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `restaurant`
--

CREATE TABLE `restaurant` (
  `id` int(100) NOT NULL,
  `type` varchar(20) NOT NULL,
  `country` varchar(25) NOT NULL,
  `address` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `typeofdining` varchar(25) NOT NULL,
  `url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `restaurant`
--

INSERT INTO `restaurant` (`id`, `type`, `country`, `address`, `name`, `typeofdining`, `url`) VALUES
(1, 'Dining', 'Vietnam', '80 Dong Khoi Street, Ben Nghe Ward, District 1, Ho Chi Minh City, Vietnam', 'Pho Hung Restaurant', 'In-store', 'https:]]media-cdn.tripadvisor.com]media]photo-s]19]a1]e2]a3]caption.jpg'),
(2, 'Dining', 'Vietnam', '46A Dinh Cong Trang Street, Ward 1, District 1, Ho Chi Minh City, Vietnam', 'Banh Xeo Dinh Cong Trang', 'In-store', 'https:]]images.foody.vn]res]g15]143129]prof]s576x330]foody-mobile-0sfv7lbk-jpg-410-636192085587043778.jpg'),
(3, 'Dining', 'Vietnam', '127 Dinh Tien Hoang Street, Dakao Ward, District 1, Ho Chi Minh City, Vietnam', 'Banh Cuon Tay Ho', 'In-store', 'https:]]images.foody.vn]res]g1]1777]prof]s576x330]foody-upload-api-foody-mobile-tay-ho-jpg-180504085833.jpg'),
(4, 'Drinks', 'Vietnam', '26 Ly Tu Trong District 1, Ho Chi Minh City, Vietnam', 'Loft Cafe', 'In-store', 'https:]]cdn.justfly.vn]602x401]media]28]37]1cdf-7e42-4ae4-acc8-52b6093487cb.jpg'),
(5, 'Dining', 'China', '2 Hai Trieu 2nd Floor. Bitexco Tower, District 1, Ho Chi Minh City, Vietnam', 'Haidilao Hotpot', 'In-store', 'https:]]savorjapan.com]gg]content_image]haidilao_hotpot.jpg'),
(6, 'Drinks', 'Vietnam', '01 Cong xa Paris Ben Nghe Ward, District 1, Ho Chi Minh City, Vietnam', 'Street Coffee', 'Street', 'https:]]i.doanhnhansaigon.vn]2019]06]24]cafe4-1561377661.jpg'),
(7, 'Dining', 'Vietnam', '32 Xom Chieu Market, Dong Khoi, District 4, Ho Chi Minh City, Vietnam', 'Xom Chieu Market', 'Street', 'https:]]konareal.vn]wp-content]uploads]2020]01]cho-xom-chieu-quan-4-2.jpg'),
(8, 'Dining', 'Mexico', '40/24 Bui Vien Street W.Pham Ngu Lao, D.1, Ho Chi Minh City,Vietnam', 'Nonla Guys', 'In-store', 'https:]]media-cdn.tripadvisor.com]media]photo-s]13]a3]29]2d]tacos.jpg'),
(9, 'Drinks', 'Vietnam', '76 le Lai, Ben Thanh AB Tower, Ho Chi Minh City, Vietnam', 'Starbucks Coffee', 'In-store', 'http:]]2.bp.blogspot.com]-9w4T6MJNjio]VLtTbUaIpRI]AAAAAAAAFtg]i4NitOOT02k]s1600]IMG_3162.JPG'),
(10, 'Dining', 'China', '189 Bis Bui Vien Pham Ngu Lao, District 1, Ho Chi Minh City Vietnam', 'Dong Bac Dumplings', 'In-store', 'https:]]media-cdn.tripadvisor.com]media]photo-s]0e]6e]51]be]delicious-shrimps.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `usertable`
--

CREATE TABLE `usertable` (
  `customer_id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `user_email` varchar(100) NOT NULL,
  `user_password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `usertable`
--

INSERT INTO `usertable` (`customer_id`, `first_name`, `last_name`, `user_email`, `user_password`) VALUES
(1, 'Anh', 'Le', 'haianhltr@yahoo.com.vn', 'haianh123'),
(2, 'd', 'd', 'd', 'd'),
(3, 'anh', 'le', 'anh.le@washburn', 'haianh'),
(6, 'anh', 'le', 'anh.le@test', 'Haianh123'),
(7, 'anh', 'le', 'anh.le@test1', 'haianh123'),
(8, 'Anh', 'Le', 'anh.le@test10', 'Haianh123'),
(9, 'Anh', 'Le', 'anh.le@test4', 'haianh'),
(10, 'anh', 'le', 'haianhltr@hhh', 'ddd');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `flight`
--
ALTER TABLE `flight`
  ADD PRIMARY KEY (`flight_id`);

--
-- Chỉ mục cho bảng `FlightSale`
--
ALTER TABLE `FlightSale`
  ADD PRIMARY KEY (`SaleID`),
  ADD KEY `CustomerID_fk` (`customer_id`),
  ADD KEY `Flight_fk` (`flight_id`);

--
-- Chỉ mục cho bảng `foodlist`
--
ALTER TABLE `foodlist`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`hotel_id`);

--
-- Chỉ mục cho bảng `HotelSale`
--
ALTER TABLE `HotelSale`
  ADD PRIMARY KEY (`SaleID`),
  ADD KEY `CustomerHotelID_fk` (`customer_id`),
  ADD KEY `HotelSaleID_fk` (`hotel_id`);

--
-- Chỉ mục cho bảng `others`
--
ALTER TABLE `others`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `place`
--
ALTER TABLE `place`
  ADD PRIMARY KEY (`place_id`);

--
-- Chỉ mục cho bảng `restaurant`
--
ALTER TABLE `restaurant`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `usertable`
--
ALTER TABLE `usertable`
  ADD PRIMARY KEY (`customer_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `flight`
--
ALTER TABLE `flight`
  MODIFY `flight_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `FlightSale`
--
ALTER TABLE `FlightSale`
  MODIFY `SaleID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `hotel`
--
ALTER TABLE `hotel`
  MODIFY `hotel_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `HotelSale`
--
ALTER TABLE `HotelSale`
  MODIFY `SaleID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `others`
--
ALTER TABLE `others`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `place`
--
ALTER TABLE `place`
  MODIFY `place_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `restaurant`
--
ALTER TABLE `restaurant`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `usertable`
--
ALTER TABLE `usertable`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `FlightSale`
--
ALTER TABLE `FlightSale`
  ADD CONSTRAINT `CustomerID_fk` FOREIGN KEY (`customer_id`) REFERENCES `usertable` (`customer_id`),
  ADD CONSTRAINT `Flight_fk` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`flight_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `HotelSale`
--
ALTER TABLE `HotelSale`
  ADD CONSTRAINT `CustomerHotelID_fk` FOREIGN KEY (`customer_id`) REFERENCES `usertable` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `HotelSaleID_fk` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
