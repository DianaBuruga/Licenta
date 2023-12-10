USE career_startup;

CREATE TABLE IF NOT EXISTS skill
(
    id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS user
(
    email           VARCHAR(255) NOT NULL PRIMARY KEY,
    role            VARCHAR(45)  NOT NULL,
    name            VARCHAR(255) NOT NULL,
    phone           VARCHAR(10),
    picture_name    VARCHAR(255),
    picture_content LONGBLOB,
    url             VARCHAR(255),
    description     TEXT,
    status          VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS language
(
    id           INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(60)  NOT NULL,
    listening    VARCHAR(3)   NOT NULL,
    reading      VARCHAR(2)   NOT NULL,
    speaking     VARCHAR(2)   NOT NULL,
    conversation VARCHAR(2)   NOT NULL,
    writing      VARCHAR(2)   NOT NULL,
    user_email   VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_email) REFERENCES user (email)
);

CREATE TABLE IF NOT EXISTS company
(
    id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    logo_url VARCHAR(255),
    address  VARCHAR(255),
    url      VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS job_history
(
    id                    INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_email            VARCHAR(255) NOT NULL,
    company_id            INT          NOT NULL,
    position              VARCHAR(60)  NOT NULL,
    start_date            DATETIME     NOT NULL,
    end_date              DATETIME,
    description           TEXT,
    rating_job_at_company INT,
    review_job_at_company TEXT,
    need_qualification    TINYINT      NOT NULL,
    FOREIGN KEY (user_email) REFERENCES user (email),
    FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE IF NOT EXISTS interview
(
    id         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    position   VARCHAR(255) NOT NULL,
    interview  TEXT         NOT NULL,
    rating     INT          NOT NULL,
    company_id INT          NOT NULL,
    FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE IF NOT EXISTS interview_file
(
    id           INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    content      LONGBLOB     NOT NULL,
    interview_id INT          NOT NULL,
    FOREIGN KEY (interview_id) REFERENCES interview (id)
);

CREATE TABLE IF NOT EXISTS posted_job
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description TEXT         NOT NULL,
    open_until  DATETIME,
    status      VARCHAR(60)  NOT NULL,
    location    VARCHAR(255) NOT NULL,
    type        VARCHAR(60)  NOT NULL,
    company_id  INT          NOT NULL,
    FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE IF NOT EXISTS event
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_email  VARCHAR(255) NOT NULL,
    description TEXT         NOT NULL,
    date        DATETIME     NOT NULL,
    url         VARCHAR(255),
    location    VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_email) REFERENCES user (email)
);

CREATE TABLE IF NOT EXISTS experience
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_email  VARCHAR(255) NOT NULL,
    title       VARCHAR(255) NOT NULL,
    description TEXT         NOT NULL,
    date        DATETIME     NOT NULL,
    url         VARCHAR(255),
    type        VARCHAR(45)  NOT NULL,
    FOREIGN KEY (user_email) REFERENCES user (email)
);

CREATE TABLE IF NOT EXISTS event_file
(
    id                 INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    event_id           INT          NOT NULL,
    event_file_name    VARCHAR(255) NOT NULL,
    event_file_content LONGBLOB     NOT NULL,
    FOREIGN KEY (event_id) REFERENCES event (id)
);

CREATE TABLE IF NOT EXISTS language_file
(
    id           INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    language_id  INT          NOT NULL,
    file_name    VARCHAR(255) NOT NULL,
    file_content LONGBLOB     NOT NULL,
    FOREIGN KEY (language_id) REFERENCES language (id)
);

CREATE TABLE IF NOT EXISTS bibliography
(
    id         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(255) NOT NULL,
    skill_id   INT          NOT NULL,
    url        VARCHAR(255),
    text       TEXT         NOT NULL,
    title      VARCHAR(255) NOT NULL,
    date       DATETIME     NOT NULL,
    FOREIGN KEY (user_email) REFERENCES user (email),
    FOREIGN KEY (skill_id) REFERENCES skill (id)
);

CREATE TABLE IF NOT EXISTS user_skills
(
    user_email  VARCHAR(255) NOT NULL,
    skill_id    INT          NOT NULL,
    proficiency INT          NOT NULL,
    FOREIGN KEY (user_email) REFERENCES user (email),
    FOREIGN KEY (skill_id) REFERENCES skill (id)
);

CREATE TABLE IF NOT EXISTS job_candidates
(
    candidate_email  VARCHAR(255) NOT NULL,
    job_id           INT          NOT NULL,
    application_date DATETIME     NOT NULL,
    FOREIGN KEY (candidate_email) REFERENCES user (email),
    FOREIGN KEY (job_id) REFERENCES posted_job (id)
);

CREATE TABLE IF NOT EXISTS referral
(
    id            INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    teacher_email VARCHAR(255) NOT NULL,
    user_email    VARCHAR(255) NOT NULL,
    title         VARCHAR(255) NOT NULL,
    description   TEXT         NOT NULL,
    FOREIGN KEY (teacher_email) REFERENCES user (email),
    FOREIGN KEY (user_email) REFERENCES user (email)
);

CREATE TABLE IF NOT EXISTS message
(
    id             INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sender_email   VARCHAR(255) NOT NULL,
    receiver_email VARCHAR(255) NOT NULL,
    message        TEXT         NOT NULL,
    datetime       DATETIME     NOT NULL,
    seen           TINYINT      NOT NULL,
    FOREIGN KEY (sender_email) REFERENCES user (email),
    FOREIGN KEY (receiver_email) REFERENCES user (email)
);

CREATE TABLE IF NOT EXISTS experience_file
(
    id                    INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    file_name             VARCHAR(255) NOT NULL,
    file_content          LONGBLOB     NOT NULL,
    experience_id INT          NOT NULL,
    FOREIGN KEY (experience_id) REFERENCES experience (id)
);

CREATE TABLE IF NOT EXISTS cv
(
    id           INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    file_name    VARCHAR(255) NOT NULL,
    file_content LONGBLOB     NOT NULL,
    user_email   VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_email) REFERENCES user (email)
);

CREATE TABLE IF NOT EXISTS education
(
    id             INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_email     VARCHAR(255) NOT NULL,
    specialization VARCHAR(255) NOT NULL,
    faculty        VARCHAR(255) NOT NULL,
    started_date   DATETIME     NOT NULL,
    finished_date  DATETIME     NOT NULL,
    degree         TINYINT      NOT NULL,
    FOREIGN KEY (user_email) REFERENCES user (email)
);

CREATE TABLE IF NOT EXISTS notification
(
    id           INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_email   VARCHAR(255) NOT NULL,
    notification VARCHAR(500) NOT NULL,
    date         DATETIME     NOT NULL,
    FOREIGN KEY (user_email) REFERENCES user (email)
);