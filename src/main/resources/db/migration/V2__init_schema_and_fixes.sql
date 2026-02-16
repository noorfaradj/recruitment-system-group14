-- 1. Skapa tabell för ansökningar som KOPPLAR TILL person_id
CREATE TABLE job_application (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    person_id INT NOT NULL REFERENCES person(person_id),
    status VARCHAR(20) NOT NULL DEFAULT 'UNHANDLED'
);

-- 2. Aktivera pgcrypto i Supabase för att hasha lösenord (Krav 7)
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- 3. Hantera "Lacking Data" för högre betyg (Krav 28)
-- Sätt email som username där det saknas
UPDATE person 
SET username = email 
WHERE role_id = 2 AND username IS NULL;

-- Sätt ett tillfälligt klarspråkslösenord för sökande där det saknas
UPDATE person 
SET password = 'password' 
WHERE role_id = 2 AND password IS NULL;

-- 4. HASHA ALLA LÖSENORD (Krav 7)
-- Krypterar allt till BCrypt
UPDATE person
SET password = crypt(password, gen_salt('bf', 10));

-- 5. Skapa en jobbansökan för varje "Applicant" (role_id=2)
INSERT INTO job_application (person_id, status)
SELECT person_id, 'UNHANDLED' 
FROM person 
WHERE role_id = 2;