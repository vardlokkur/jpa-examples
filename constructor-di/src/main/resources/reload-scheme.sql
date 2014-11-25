--
-- Target Database: HyperSQL DB
--
DROP TABLE EMPLOYER IF EXISTS;

--
-- Employer
--
CREATE TABLE EMPLOYER (
  ID              VARBINARY(16)             NOT NULL,
  NAME            VARCHAR(255)              NOT NULL,
  CONSTRAINT EMPLOYER_PK PRIMARY KEY (ID)
);