CREATE TABLE pm_roles
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_pm_roles PRIMARY KEY (id)
);

CREATE TABLE pm_users
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    username   VARCHAR(255),
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    full_name  VARCHAR(255),
    password   VARCHAR(255),
    is_enabled BOOLEAN                                 NOT NULL,
    CONSTRAINT pk_pm_users PRIMARY KEY (id)
);

CREATE TABLE pm_users_roles
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL
);

ALTER TABLE pm_roles
    ADD CONSTRAINT uc_pm_roles_name UNIQUE (name);

ALTER TABLE pm_users
    ADD CONSTRAINT uc_pm_users_username UNIQUE (username);

ALTER TABLE pm_users_roles
    ADD CONSTRAINT fk_pmuserol_on_user FOREIGN KEY (user_id) REFERENCES pm_users (id);

ALTER TABLE pm_users_roles
    ADD CONSTRAINT fk_pmuserol_on_user_role FOREIGN KEY (role_id) REFERENCES pm_roles (id);