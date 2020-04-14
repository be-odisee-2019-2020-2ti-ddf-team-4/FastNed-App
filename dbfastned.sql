-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Gegenereerd op: 14 apr 2020 om 16:57
-- Serverversie: 10.4.11-MariaDB
-- PHP-versie: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbfastned`
--

-- --------------------------------------------------------

--
-- Stand-in structuur voor view `afspraken_all`
-- (Zie onder voor de actuele view)
--
CREATE TABLE `afspraken_all` (
);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `tbl_afspraken`
--

CREATE TABLE `tbl_afspraken` (
  `AFSPRK_ID` char(11) NOT NULL,
  `Laadpaal_FK` char(11) NOT NULL,
  `Installateur_FK` char(11) NOT NULL,
  `Contract_FK` char(11) NOT NULL,
  `Status` varchar(50) NOT NULL,
  `Installatie_FK` char(11) NOT NULL,
  `Reparatie_FK` char(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `tbl_contracten`
--

CREATE TABLE `tbl_contracten` (
  `CNTRCT_ID` char(11) NOT NULL,
  `ContractDatum` datetime NOT NULL,
  `UitvoeringsDatum` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `tbl_installateurs`
--

CREATE TABLE `tbl_installateurs` (
  `INSTLR_ID` char(11) NOT NULL,
  `Persoon_FK` char(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `tbl_installaties`
--

CREATE TABLE `tbl_installaties` (
  `INSTLTIE_ID` char(11) NOT NULL,
  `InstallatieCompleet` datetime DEFAULT NULL,
  `Probleem_FK` char(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `tbl_laadpalen`
--

CREATE TABLE `tbl_laadpalen` (
  `LDPL_ID` char(11) NOT NULL,
  `Locatiehouder_FK` char(11) NOT NULL,
  `InstallatieDocumentatie` text DEFAULT NULL,
  `ReparatieDocumentatie` text DEFAULT NULL,
  `LaadpaalType` char(50) NOT NULL,
  `Status` char(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `tbl_locatiehouders`
--

CREATE TABLE `tbl_locatiehouders` (
  `LCTHDR_ID` char(11) NOT NULL,
  `Bedrijfsnaam` varchar(25) DEFAULT NULL,
  `Adres` varchar(50) DEFAULT NULL,
  `BTWNummer` varchar(15) DEFAULT NULL,
  `Persoon_FK` char(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `tbl_oplossingen`
--

CREATE TABLE `tbl_oplossingen` (
  `OPLSNG_ID` char(11) NOT NULL,
  `oplossing` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `tbl_oplossingen`
--

INSERT INTO `tbl_oplossingen` (`OPLSNG_ID`, `oplossing`) VALUES
('OPLSNG_0', 'Test 1'),
('OPLSNG_1', 'Test 2'),
('OPLSNG_2', 'Oplossing 1'),
('OPLSNG_3', 'Oplossing 1'),
('OPLSNG_4', 'Oplossing 1'),
('OPLSNG_5', 'Oplossing 2'),
('OPLSNG_6', 'Oplossing 1'),
('OPLSNG_7', 'Oplossing 2'),
('OPLSNG_8', 'Oplossing 1'),
('OPLSNG_9', 'Oplossing 2');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `tbl_personen`
--

CREATE TABLE `tbl_personen` (
  `PRSN__ID` char(11) NOT NULL,
  `naam` int(50) DEFAULT NULL,
  `voornaam` int(50) DEFAULT NULL,
  `geslacht` char(50) DEFAULT 'Man',
  `emailadres` int(50) NOT NULL,
  `gsm` int(20) DEFAULT NULL,
  `gebruikersnaam` varchar(50) NOT NULL,
  `wachtwoord` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `tbl_problemen`
--

CREATE TABLE `tbl_problemen` (
  `PBLM_ID` char(11) NOT NULL,
  `Beschrijving` varchar(150) NOT NULL,
  `Status` varchar(25) NOT NULL,
  `Oplossing_FK` char(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Gegevens worden geëxporteerd voor tabel `tbl_problemen`
--

INSERT INTO `tbl_problemen` (`PBLM_ID`, `Beschrijving`, `Status`, `Oplossing_FK`) VALUES
('PRBLM_0', 'Probleem 1', 'aangemaakt', 'OPLSNG_3'),
('PRBLM_1', 'Probleem 1', 'aangemaakt', 'OPLSNG_4'),
('PRBLM_2', 'Probleem 2', 'aangemaakt', 'OPLSNG_5'),
('PRBLM_3', 'Probleem 1', 'aangemaakt', 'OPLSNG_6'),
('PRBLM_4', 'Probleem 2', 'aangemaakt', 'OPLSNG_7'),
('PRBLM_5', 'Probleem 1', 'aangemaakt', 'OPLSNG_8'),
('PRBLM_6', 'Probleem 2', 'aangemaakt', 'OPLSNG_9');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `tbl_reparaties`
--

CREATE TABLE `tbl_reparaties` (
  `RPRTIE_ID` char(11) NOT NULL,
  `ReparatieCompleet` datetime DEFAULT NULL,
  `Probleem_FK` char(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structuur voor de view `afspraken_all`
--
DROP TABLE IF EXISTS `afspraken_all`;

CREATE ALGORITHM=UNDEFINED DEFINER=`Afspraken_All`@`%` SQL SECURITY DEFINER VIEW `afspraken_all`  AS  select `afspraken`.`ID` AS `ID`,`afspraken`.`Laadpaal_FK` AS `Laadpaal_FK`,`afspraken`.`Installateur_FK` AS `Installateur_FK`,`afspraken`.`Contract_FK` AS `Contract_FK`,`afspraken`.`Status` AS `Status`,`afspraken`.`Installatie_FK` AS `Installatie_FK`,`afspraken`.`Reparatie_FK` AS `Reparatie_FK` from `afspraken` ;

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `tbl_afspraken`
--
ALTER TABLE `tbl_afspraken`
  ADD PRIMARY KEY (`AFSPRK_ID`),
  ADD KEY `Laadpaal_FK` (`Laadpaal_FK`),
  ADD KEY `Installateur_FK` (`Installateur_FK`),
  ADD KEY `Contract_FK` (`Contract_FK`),
  ADD KEY `Installatie_FK` (`Installatie_FK`),
  ADD KEY `Reparatie_FK` (`Reparatie_FK`);

--
-- Indexen voor tabel `tbl_contracten`
--
ALTER TABLE `tbl_contracten`
  ADD PRIMARY KEY (`CNTRCT_ID`);

--
-- Indexen voor tabel `tbl_installateurs`
--
ALTER TABLE `tbl_installateurs`
  ADD PRIMARY KEY (`INSTLR_ID`),
  ADD KEY `Persoon_FK` (`Persoon_FK`);

--
-- Indexen voor tabel `tbl_installaties`
--
ALTER TABLE `tbl_installaties`
  ADD PRIMARY KEY (`INSTLTIE_ID`),
  ADD KEY `Probleem_FK` (`Probleem_FK`);

--
-- Indexen voor tabel `tbl_laadpalen`
--
ALTER TABLE `tbl_laadpalen`
  ADD PRIMARY KEY (`LDPL_ID`),
  ADD KEY `Locatiehouder_FK` (`Locatiehouder_FK`);

--
-- Indexen voor tabel `tbl_locatiehouders`
--
ALTER TABLE `tbl_locatiehouders`
  ADD PRIMARY KEY (`LCTHDR_ID`),
  ADD KEY `Persoon_FK` (`Persoon_FK`);

--
-- Indexen voor tabel `tbl_oplossingen`
--
ALTER TABLE `tbl_oplossingen`
  ADD PRIMARY KEY (`OPLSNG_ID`);

--
-- Indexen voor tabel `tbl_personen`
--
ALTER TABLE `tbl_personen`
  ADD PRIMARY KEY (`PRSN__ID`);

--
-- Indexen voor tabel `tbl_problemen`
--
ALTER TABLE `tbl_problemen`
  ADD PRIMARY KEY (`PBLM_ID`),
  ADD KEY `Rel_Oplossing_to_Probleem` (`Oplossing_FK`);

--
-- Indexen voor tabel `tbl_reparaties`
--
ALTER TABLE `tbl_reparaties`
  ADD PRIMARY KEY (`RPRTIE_ID`),
  ADD KEY `Probleem_FK` (`Probleem_FK`);

--
-- Beperkingen voor geëxporteerde tabellen
--

--
-- Beperkingen voor tabel `tbl_afspraken`
--
ALTER TABLE `tbl_afspraken`
  ADD CONSTRAINT `Rel_Afspraak_To_Contract` FOREIGN KEY (`Contract_FK`) REFERENCES `tbl_contracten` (`CNTRCT_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Rel_Afspraak_To_Installateur` FOREIGN KEY (`Installateur_FK`) REFERENCES `tbl_installateurs` (`INSTLR_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Rel_Afspraak_To_Installatie` FOREIGN KEY (`Installatie_FK`) REFERENCES `tbl_installaties` (`INSTLTIE_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Rel_Afspraak_To_Laadpaal` FOREIGN KEY (`Laadpaal_FK`) REFERENCES `tbl_laadpalen` (`LDPL_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Rel_Afspraak_To_Reparatie` FOREIGN KEY (`Reparatie_FK`) REFERENCES `tbl_reparaties` (`RPRTIE_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Beperkingen voor tabel `tbl_installateurs`
--
ALTER TABLE `tbl_installateurs`
  ADD CONSTRAINT `Rel_Installateurs_To_Persoon` FOREIGN KEY (`Persoon_FK`) REFERENCES `tbl_personen` (`PRSN__ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Beperkingen voor tabel `tbl_installaties`
--
ALTER TABLE `tbl_installaties`
  ADD CONSTRAINT `Rel_Installatie_To_Probleem` FOREIGN KEY (`Probleem_FK`) REFERENCES `tbl_problemen` (`PBLM_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Beperkingen voor tabel `tbl_laadpalen`
--
ALTER TABLE `tbl_laadpalen`
  ADD CONSTRAINT `Rel_Laadpaal_To_Locatiehouder` FOREIGN KEY (`Locatiehouder_FK`) REFERENCES `tbl_locatiehouders` (`LCTHDR_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Beperkingen voor tabel `tbl_problemen`
--
ALTER TABLE `tbl_problemen`
  ADD CONSTRAINT `Rel_Oplossing_to_Probleem` FOREIGN KEY (`Oplossing_FK`) REFERENCES `tbl_oplossingen` (`OPLSNG_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Beperkingen voor tabel `tbl_reparaties`
--
ALTER TABLE `tbl_reparaties`
  ADD CONSTRAINT `Rel_Reparatie_to_Probleem` FOREIGN KEY (`Probleem_FK`) REFERENCES `tbl_problemen` (`PBLM_ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
