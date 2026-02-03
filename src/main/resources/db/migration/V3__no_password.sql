-- 1. Fixa saknade lösenord (Krav för högre betyg)
-- Det gamla systemet hade null-lösenord, vi sätter dem till 'password'
UPDATE person 
SET password = 'password' 
WHERE password IS NULL;

-- 2. Flytta data
-- Hämta alla 'Applicants' (role_id=2) från gamla tabellen 'person'
-- och stoppa in dem i vår nya tabell 'job_application'
INSERT INTO job_application (first_name, last_name, status)
SELECT 
    name, 
    surname, 
    'UNHANDLED' 
FROM person 
WHERE role_id = 2;