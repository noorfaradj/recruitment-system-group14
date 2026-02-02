CREATE TABLE job_application (
  id BIGSERIAL PRIMARY KEY,
  first_name VARCHAR(100) NOT NULL,
  last_name  VARCHAR(100) NOT NULL,
  status     VARCHAR(20)  NOT NULL
);
