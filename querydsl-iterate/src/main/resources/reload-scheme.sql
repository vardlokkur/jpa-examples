--
-- Target Database: HyperSQL DB
--
DROP TABLE EMPLOYEE IF EXISTS;

--
-- Employer
--
CREATE TABLE EMPLOYEE (
  ID           VARBINARY(16)  NOT NULL,
  NAME         VARCHAR(255)   NOT NULL,
  EMPLOYED_ON  DATE           NOT NULL,
  CONSTRAINT EMPLOYEE_PK PRIMARY KEY (ID)
);