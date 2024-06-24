SET @ADMIN_DIEGO_SAC_ID = UUID_TO_BIN('b004a8a8-1e9b-4f8c-8c8e-123456789000');
SET @ADMIN_LENNIN_SAC_ID = UUID_TO_BIN('b004a8a8-1e9b-4f8c-8c8e-123456789001');

SET @DIEGO_SAC_ID = UUID_TO_BIN('a1e4a8a8-1e9b-4f8c-8c8e-123456789000');
SET @LENNIN_SAC_ID = UUID_TO_BIN('a1e4a8a8-1e9b-4f8c-8c8e-123456789001');

-- COMPANIES
INSERT INTO company (id, ruc, name, created_by) VALUES
    (@DIEGO_SAC_ID, '43254367890', 'Diego S.A.C', @ADMIN_DIEGO_SAC_ID),
    (@LENNIN_SAC_ID, '75846386951', 'Transportistas Lennin S.A.C', @ADMIN_LENNIN_SAC_ID);

-- EMPLOYEES
INSERT INTO employee (id, user_id, company_id) VALUES
    (UUID_TO_BIN('b1e4a8a8-1e9b-4f8c-8c8e-123456789000'),UUID_TO_BIN('a1e4a8a8-1e9b-4f8c-8c8e-123456789002'), @DIEGO_SAC_ID),
    (UUID_TO_BIN('b1e4a8a8-1e9b-4f8c-8c8e-123456789001'),UUID_TO_BIN('a1e4a8a8-1e9b-4f8c-8c8e-123456789003'), @DIEGO_SAC_ID);

-- CLIENTS
INSERT INTO client (id, names, surnames, dni, email, phone_number, company_id) VALUES
    (UUID_TO_BIN('444e3456-e89b-12d3-a456-426614174000'), 'Jenny Aremy', 'Balceda Calixto', '65745467', 'jennybalcedacalixto01@gmail.com', '945845612', @DIEGO_SAC_ID),
    (UUID_TO_BIN('444e3456-e89b-12d3-a456-426614174001'), 'Diego Antonio', 'De la Cruz Arellano', '76859871', 'diego04s03@gmail.com', '985621741', @DIEGO_SAC_ID);