DROP SCHEMA IF EXISTS dat107oblig3 CASCADE;

CREATE SCHEMA dat107oblig3;

CREATE TABLE dat107oblig3.avdeling (
    avdeling_id SERIAL PRIMARY KEY,
    navn VARCHAR(100) NOT NULL,
    sjef_id INTEGER 
);

CREATE TABLE dat107oblig3.ansatt (
    ansatt_id SERIAL PRIMARY KEY,
    brukernavn VARCHAR(50) UNIQUE NOT NULL,
    fornavn VARCHAR(50),
    etternavn VARCHAR(50),
    dato_for_ansettelse DATE,
    stilling VARCHAR(50),
    manedslonn NUMERIC(10,2),
    avdeling_id INTEGER  
);

CREATE TABLE dat107oblig3.prosjekt (
    prosjekt_id SERIAL PRIMARY KEY,
    navn VARCHAR(100) NOT NULL,
    beskrivelse TEXT
);

CREATE TABLE dat107oblig3.prosjektdeltagelse (
    pd_id SERIAL PRIMARY KEY,
    prosjekt_id INTEGER NOT NULL,
    ansatt_id INTEGER NOT NULL,
    rolle VARCHAR(50) NOT NULL,
    timer INTEGER NOT NULL DEFAULT 0
);

ALTER TABLE dat107oblig3.ansatt
    ADD CONSTRAINT fk_ansatt_avdeling 
    FOREIGN KEY (avdeling_id)
    REFERENCES dat107oblig3.avdeling(avdeling_id)
    ON DELETE RESTRICT;

ALTER TABLE dat107oblig3.avdeling
    ADD CONSTRAINT fk_avdeling_sjef 
    FOREIGN KEY (sjef_id)
    REFERENCES dat107oblig3.ansatt(ansatt_id)
    ON DELETE RESTRICT;

ALTER TABLE dat107oblig3.prosjektdeltagelse
    ADD CONSTRAINT fk_pd_prosjekt 
    FOREIGN KEY (prosjekt_id)
    REFERENCES dat107oblig3.prosjekt(prosjekt_id)
    ON DELETE CASCADE;

ALTER TABLE dat107oblig3.prosjektdeltagelse
    ADD CONSTRAINT fk_pd_ansatt 
    FOREIGN KEY (ansatt_id)
    REFERENCES dat107oblig3.ansatt(ansatt_id)
    ON DELETE CASCADE;

INSERT INTO dat107oblig3.avdeling (navn)
VALUES 
    ('Utvikling'),
    ('Prosjekt'),
    ('Support');

INSERT INTO dat107oblig3.ansatt (brukernavn, fornavn, etternavn, dato_for_ansettelse, stilling, manedslonn, avdeling_id)
VALUES
 ('lph', 'Lars', 'Pedersen', '2020-05-10', 'Utvikler', 55000.00, 1),
 ('ane', 'Anne', 'Nilsen', '2018-03-15', 'Prosjektleder', 70000.00, 2),
 ('koi', 'Kåre', 'Olsen', '2021-07-20', 'Support', 45000.00, 3);

UPDATE dat107oblig3.avdeling SET sjef_id = 1 WHERE avdeling_id = 1;
UPDATE dat107oblig3.avdeling SET sjef_id = 2 WHERE avdeling_id = 2;
UPDATE dat107oblig3.avdeling SET sjef_id = 3 WHERE avdeling_id = 3;

INSERT INTO dat107oblig3.prosjekt (navn, beskrivelse)
VALUES
    ('Nettside', 'Lage ny responsiv nettside'),
    ('Mobile App', 'Utvikle applikasjon for mobil'),
    ('Data Migration', 'Migrere data til ny plattform');

INSERT INTO dat107oblig3.prosjektdeltagelse (prosjekt_id, ansatt_id, rolle, timer)
VALUES
    (1, 1, 'Utvikler', 20),
    (1, 2, 'Tester', 10),
    (2, 2, 'Prosjektleder', 30),
    (3, 3, 'Support', 15);