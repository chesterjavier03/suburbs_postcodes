CREATE TABLE SUBURBS (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1000000, INCREMENT BY 1) PRIMARY KEY,
  suburb VARCHAR(50) NOT NULL,
  post_code VARCHAR(50) NOT NULL
);