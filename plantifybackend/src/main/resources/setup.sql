DROP TABLE IF EXISTS DetailsArchive;
DROP TABLE IF EXISTS Details;
DROP TABLE IF EXISTS Plant;
DROP TABLE IF EXISTS Arduino;
DROP TABLE IF EXISTS Client;

CREATE TABLE Client
(
    email    VARCHAR(30) NOT NULL
        PRIMARY KEY,
    password VARCHAR(30) NOT NULL
);

CREATE TABLE Arduino
(
    physicalIdentifier INT         NOT NULL UNIQUE  PRIMARY KEY ,
    series             VARCHAR(30) NOT NULL,
    ledSetting         BOOLEAN     NOT NULL,
    redCode            SMALLINT    NOT NULL,
    greenCode          SMALLINT    NOT NULL,
    blueCode           SMALLINT    NOT NULL,
    isConfigured       bool DEFAULT false
);



CREATE TABLE Plant
(
    plantID                   INT         NOT NULL
        PRIMARY KEY,
    userEmail                 VARCHAR(30) NOT NULL
        CONSTRAINT fk_userEmail REFERENCES Client (email)
            ON DELETE CASCADE,
    plantName                 VARCHAR(30) NOT NULL,
    plantType                 VARCHAR(30) NOT NULL,
    dateAdded                 TIMESTAMP   NOT NULL
        DEFAULT CURRENT_TIMESTAMP,
    arduinoPhysicalIdentifier INT UNIQUE
        CONSTRAINT fk_arduinoPhysicalIdentifier REFERENCES arduino (physicalIdentifier)
        ON DELETE SET NULL
);

CREATE TABLE Details
(
    ID          INT         NOT NULL
        GENERATED ALWAYS AS IDENTITY
        PRIMARY KEY,
    plantID     INT         NOT NULL
        CONSTRAINT fk_plantID REFERENCES Plant (plantID)
            ON DELETE CASCADE,
    temperature NUMERIC(10) NOT NULL,
    humidity    NUMERIC(10) NOT NULL,
    moisture    NUMERIC(10) NOT NULL,
    light       NUMERIC(10) NOT NULL,
    refreshTime TIMESTAMP
        DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE DetailsArchive
(
    ID                 INT         NOT NULL
        GENERATED ALWAYS AS IDENTITY
        PRIMARY KEY,
    plantID            INT         NOT NULL
        CONSTRAINT fk_plantID REFERENCES Plant (plantID)
            ON DELETE CASCADE,
    temperatureAvg     INT NOT NULL,
    humidityAvg        INT NOT NULL,
    moistureAvg        INT NOT NULL,
    lightAvg           INT NOT NULL,
    refreshTime        TIMESTAMP
        DEFAULT CURRENT_TIMESTAMP,
    minimumTemperature numeric(10) NOT NULL,
    maximumTemperature numeric(10) NOT NULL,
    minimumHumidity    numeric(10) NOT NULL,
    maximumHumidity    numeric(10) NOT NULL,
    minimumMoisture    numeric(10) NOT NULL,
    maximumMoisture    numeric(10) NOT NULL,
    minimumLight       numeric(10) NOT NULL,
    maximumLight       numeric(10) NOT NULL,
    totalRowsArchived  numeric(10) NOT NULL
);

