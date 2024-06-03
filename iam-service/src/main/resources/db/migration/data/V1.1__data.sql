SET @ADMIN_DIEGO_SAC_ID = UUID_TO_BIN('b004a8a8-1e9b-4f8c-8c8e-123456789000');
SET @ADMIN_LENNIN_SAC_ID = UUID_TO_BIN('b004a8a8-1e9b-4f8c-8c8e-123456789001');

SET @JENNY_EMPLOYEE_ID =  UUID_TO_BIN('a1e4a8a8-1e9b-4f8c-8c8e-123456789002');
SET @LUDWIN_EMPLOYEE_ID = UUID_TO_BIN('a1e4a8a8-1e9b-4f8c-8c8e-123456789003');

-- ROLES
INSERT INTO role (name) VALUES
    ('ROLE_USER'),
    ('ROLE_ADMIN');

INSERT INTO user (id, first_name, last_name, dni, birth_date, phone_number, company_id, email, password) VALUES
    (@ADMIN_DIEGO_SAC_ID, 'Diego Antonio', 'De la Cruz Arellano','75001808', '2003-04-04 00:00:00', '988717647', @DIEGO_SAC_ID, 'diegodelacruz@gmail.com', '$2a$10$9M3WL/h49/zRY/BJtlc03OAQDSsqZXSZqnufFagj0aObvm4RMU/jC'), -- diego1234
    (@ADMIN_LENNIN_SAC_ID, 'Lennin Daniel', 'Huaman Huaranga','74451503', '2001-02-08 00:00:00', '987451724',  @DIEGO_SAC_ID,'lenninhuaman@gmail.com', '$2a$10$FR2qemQ81zNp/R/JBqTdXuRYV3f9HTRtpn/7Edtvx66CJOViuUGSW'), -- lennin1234
    (@JENNY_EMPLOYEE_ID, 'Jenny Aremy', 'Huaman Huaranga','74325478', '2001-12-07 00:00:00', '953214852', @DIEGO_SAC_ID, 'jennybalceda@gmail.com', '$2a$10$G6mvHwYXgVU14ef79xAolu.DowIoY4omYCuY6HCedmcOZqxwAwIqC'), -- jenny1234
    (@LUDWIN_EMPLOYEE_ID, 'Luwin Roy', 'Reyes Suarez','75286934', '2003-08-01 00:00:00', '963258741', @DIEGO_SAC_ID, 'ludwinreyes@gmail.com', '$2a$10$sxC.ZkCA9xnRT407voFtzeNmARPygBw1R0mL6NpoH4K1ngy3LNqtK'); -- luwin1234

INSERT INTO user_roles (user_id, role_id) VALUES
    (@DIEGO_SAC_ID, 2),
    (@LENNIN_SAC_ID, 2),
    (@JENNY_EMPLOYEE_ID, 1),
    (@LUDWIN_EMPLOYEE_ID, 1);
