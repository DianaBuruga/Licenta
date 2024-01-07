USE career_startup;

CREATE TABLE IF NOT EXISTS skill
(
    id   BINARY(16)   NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS user
(
    id          BINARY(16)                                                       NOT NULL PRIMARY KEY,
    email       VARCHAR(255)                                                     NOT NULL,
    role        ENUM ('STUDENT','TEACHER','COMPANY_REPRESENTATIVE', 'MODERATOR') NOT NULL,
    name        VARCHAR(255)                                                     NOT NULL,
    phone       VARCHAR(10),
    website     VARCHAR(255),
    description TEXT,
    status      ENUM ('RECRUITING','OPEN_TO_WORK','EMPLOYEE')
);

CREATE TABLE IF NOT EXISTS language
(
    id           BINARY(16)                                          NOT NULL PRIMARY KEY,
    name         VARCHAR(60)                                         NOT NULL,
    listening    ENUM ('A1', 'A2', 'B1', 'B2', 'C1', 'C2', 'TONGUE') NOT NULL,
    reading      ENUM ('A1', 'A2', 'B1', 'B2', 'C1', 'C2', 'TONGUE') NOT NULL,
    speaking     ENUM ('A1', 'A2', 'B1', 'B2', 'C1', 'C2', 'TONGUE') NOT NULL,
    conversation ENUM ('A1', 'A2', 'B1', 'B2', 'C1', 'C2', 'TONGUE') NOT NULL,
    writing      ENUM ('A1', 'A2', 'B1', 'B2', 'C1', 'C2', 'TONGUE') NOT NULL,
    user_id      BINARY(16)                                          NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS company
(
    id      BINARY(16)   NOT NULL PRIMARY KEY,
    name    VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    website VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS job_history
(
    id                 BINARY(16)   NOT NULL PRIMARY KEY,
    user_id            BINARY(16)   NOT NULL,
    company_id         BINARY(16)   NOT NULL,
    position           VARCHAR(100) NOT NULL,
    start_date         DATETIME     NOT NULL,
    end_date           DATETIME,
    description        TEXT,
    need_qualification BOOLEAN      NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE IF NOT EXISTS review
(
    id          BINARY(16)               NOT NULL PRIMARY KEY,
    company_id  BINARY(16)               NOT NULL,
    position    VARCHAR(255)             NOT NULL,
    description TEXT                     NOT NULL,
    type        ENUM ('INTERVIEW','JOB') NOT NULL,
    rating      INT                      NOT NULL,
    FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE IF NOT EXISTS file
(
    table_id   BINARY(16)                                               NOT NULL,
    table_name VARCHAR(255)                                             NOT NULL,
    name       VARCHAR(255)                                             NOT NULL,
    content    LONGBLOB                                                 NOT NULL,
    type       ENUM ('CV','CERTIFICATE', 'PROFILE_PHOTO', 'POST_PHOTO') NOT NULL,
    PRIMARY KEY (table_name, table_id)
);

CREATE TABLE IF NOT EXISTS posted_job
(
    id          BINARY(16)                 NOT NULL PRIMARY KEY,
    description TEXT                       NOT NULL,
    open_until  DATETIME,
    posted_date DATETIME                   NOT NULL,
    status      ENUM ('ACTIVE','INACTIVE') NOT NULL,
    location    VARCHAR(100)               NOT NULL,
    type        ENUM ('REMOTE','ONSITE')   NOT NULL,
    company_id  BINARY(16)                 NOT NULL,
    FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE IF NOT EXISTS event
(
    id          BINARY(16)   NOT NULL PRIMARY KEY,
    user_id     BINARY(16)   NOT NULL,
    description TEXT         NOT NULL,
    date        DATETIME     NOT NULL,
    location    VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS experience
(
    id          BINARY(16)                                                      NOT NULL PRIMARY KEY,
    user_id     BINARY(16)                                                      NOT NULL,
    title       VARCHAR(100)                                                    NOT NULL,
    description TEXT                                                            NOT NULL,
    date        DATETIME                                                        NOT NULL,
    url         VARCHAR(255),
    type        ENUM ('COMPETITION','PROJECT', 'ACCREDITATION', 'VOLUNTEERING') NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS bibliography
(
    id       BINARY(16)   NOT NULL PRIMARY KEY,
    user_id  BINARY(16)   NOT NULL,
    skill_id BINARY(16)   NOT NULL,
    text     TEXT         NOT NULL,
    title    VARCHAR(255) NOT NULL,
    date     DATETIME     NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (skill_id) REFERENCES skill (id)
);

CREATE TABLE IF NOT EXISTS user_skills
(
    user_id     BINARY(16) NOT NULL,
    skill_id    BINARY(16) NOT NULL,
    proficiency INTEGER    NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (skill_id) REFERENCES skill (id)
);

CREATE TABLE IF NOT EXISTS job_candidates
(
    candidate_id     BINARY(16) NOT NULL,
    job_id           BINARY(16) NOT NULL,
    application_date DATETIME   NOT NULL,
    FOREIGN KEY (candidate_id) REFERENCES user (id),
    FOREIGN KEY (job_id) REFERENCES posted_job (id)
);

CREATE TABLE IF NOT EXISTS referral
(
    id          BINARY(16)   NOT NULL PRIMARY KEY,
    teacher_id  BINARY(16)   NOT NULL,
    user_id     BINARY(16)   NOT NULL,
    title       VARCHAR(100) NOT NULL,
    description TEXT         NOT NULL,
    FOREIGN KEY (teacher_id) REFERENCES user (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS message
(
    id          BINARY(16) NOT NULL PRIMARY KEY,
    sender_id   BINARY(16) NOT NULL,
    receiver_id BINARY(16) NOT NULL,
    message     TEXT       NOT NULL,
    send_date   DATETIME   NOT NULL,
    seen        BOOLEAN    NOT NULL,
    FOREIGN KEY (sender_id) REFERENCES user (id),
    FOREIGN KEY (receiver_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS notification
(
    id           BINARY(16)   NOT NULL PRIMARY KEY,
    user_id      BINARY(16)   NOT NULL,
    notification VARCHAR(255) NOT NULL,
    date         DATETIME     NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS event_subscribers
(
    user_id  BINARY(16) NOT NULL,
    event_id BINARY(16) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (event_id) REFERENCES event (id)
);

CREATE TABLE IF NOT EXISTS faculty
(
    id             BINARY(16)   NOT NULL PRIMARY KEY,
    name           VARCHAR(100) NULL,
    address        VARCHAR(100) NULL,
    years_of_study INTEGER
);

CREATE TABLE IF NOT EXISTS specialization
(
    id            BINARY(16) NOT NULL PRIMARY KEY,
    user_id       BINARY(16) NOT NULL,
    faculty_id    BINARY(16) NOT NULL,
    started_date  DATETIME   NOT NULL,
    finished_date DATETIME   NOT NULL,
    degree        VARCHAR(4) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (faculty_id) REFERENCES faculty (id)
);


CREATE TABLE IF NOT EXISTS course
(
    id                BINARY(16)  NOT NULL PRIMARY KEY,
    specialization_id BINARY(16)  NOT NULL,
    name              VARCHAR(45) NOT NULL,
    year              INTEGER     NOT NULL,
    semester          INTEGER     NOT NULL,
    FOREIGN KEY (specialization_id) REFERENCES specialization (id)
);

CREATE TABLE IF NOT EXISTS course_skills
(
    course_id BINARY(16) NOT NULL,
    skill_id  BINARY(16) NOT NULL,
    PRIMARY KEY (course_id, skill_id),
    FOREIGN KEY (course_id) REFERENCES course (id),
    FOREIGN KEY (skill_id) REFERENCES skill (id)
);