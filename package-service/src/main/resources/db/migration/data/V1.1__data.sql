SET @st1_id = UUID_TO_BIN('555e4567-e89b-12d3-a456-426614174000');
SET @COSTUMER_1_ID = UUID_TO_BIN('444e3456-e89b-12d3-a456-426614174000');
SET @COSTUMER_2_ID = UUID_TO_BIN('444e3456-e89b-12d3-a456-426614174001');

SET @DIEGO_SAC_ID = UUID_TO_BIN('a1e4a8a8-1e9b-4f8c-8c8e-123456789000');

INSERT INTO package (id, owner_id, tracking_id, company_id, description, weight, height, width, length, status) VALUES
    (UUID_TO_BIN('112e4567-e89b-12d3-a456-426614174000'), @COSTUMER_1_ID, @st1_id, @DIEGO_SAC_ID, 'Frozen seafood that needs to stay below -18°C', 5.0, 10.0, 15.0, 20.0, 'IN_TRANSIT'),
    (UUID_TO_BIN('112e4567-e89b-12d3-a456-426614174001'), @COSTUMER_1_ID, NULL, @DIEGO_SAC_ID, 'Fresh vegetables that need to stay between 0°C and 4°C', 3.2, 8.0, 12.0, 18.0, 'DELIVERED'),
    (UUID_TO_BIN('112e4567-e89b-12d3-a456-426614174002'), @COSTUMER_1_ID, NULL, @DIEGO_SAC_ID, 'Dairy products that need to stay between 2°C and 4°C', 4.5, 9.0, 14.0, 22.0, 'DELIVERED'),
    (UUID_TO_BIN('112e4567-e89b-12d3-a456-426614174003'), @COSTUMER_1_ID, NULL, @DIEGO_SAC_ID, 'Frozen meat that needs to stay below -18°C', 6.0, 11.0, 16.0, 24.0, 'DELIVERED'),
    (UUID_TO_BIN('112e4567-e89b-12d3-a456-426614174004'), @COSTUMER_1_ID, NULL, @DIEGO_SAC_ID, 'Pharmaceuticals that need to stay between 2°C and 8°C', 2.8, 7.0, 13.0, 19.0, 'DELIVERED'),
    (UUID_TO_BIN('112e4567-e89b-12d3-a456-426614174005'), @COSTUMER_1_ID, NULL, @DIEGO_SAC_ID, 'Frozen seafood that needs to stay below -18°C', 5.5, 10.5, 15.5, 20.5, 'DELIVERED'),
    (UUID_TO_BIN('112e4567-e89b-12d3-a456-426614174006'), @COSTUMER_2_ID, NULL, @DIEGO_SAC_ID, 'Fresh vegetables that need to stay between 0°C and 4°C', 3.6, 8.4, 12.4, 18.4, 'DELIVERED'),
    (UUID_TO_BIN('112e4567-e89b-12d3-a456-426614174007'), @COSTUMER_2_ID, NULL, @DIEGO_SAC_ID, 'Dairy products that need to stay between 2°C and 4°C', 4.8, 9.3, 14.3, 22.3, 'DELIVERED'),
    (UUID_TO_BIN('112e4567-e89b-12d3-a456-426614174008'), @COSTUMER_2_ID, NULL, @DIEGO_SAC_ID, 'Frozen meat that needs to stay below -18°C', 6.2, 11.2, 16.2, 24.2, 'DELIVERED'),
    (UUID_TO_BIN('112e4567-e89b-12d3-a456-426614174009'), @COSTUMER_2_ID, NULL, @DIEGO_SAC_ID, 'Pharmaceuticals that need to stay between 2°C and 8°C', 2.9, 7.1, 13.1, 19.1, 'DELIVERED'),
    (UUID_TO_BIN('112e4567-e89b-12d3-a456-426614174010'), @COSTUMER_2_ID, NULL, @DIEGO_SAC_ID, 'Frozen seafood that needs to stay below -18°C', 5.0, 10.0, 15.0, 20.0, 'DELIVERED'),
    (UUID_TO_BIN('112e4567-e89b-12d3-a456-426614174011'), @COSTUMER_2_ID, NULL, @DIEGO_SAC_ID, 'Fresh vegetables that need to stay between 0°C and 4°C', 3.1, 7.9, 11.9, 17.9, 'DELIVERED'),
    (UUID_TO_BIN('112e4567-e89b-12d3-a456-426614174012'), @COSTUMER_2_ID, NULL, @DIEGO_SAC_ID, 'Dairy products that need to stay between 2°C and 4°C', 4.3, 8.8, 13.8, 21.8, 'DELIVERED'),
    (UUID_TO_BIN('112e4567-e89b-12d3-a456-426614174013'), @COSTUMER_2_ID, NULL, @DIEGO_SAC_ID, 'Frozen meat that needs to stay below -18°C', 5.7, 10.7, 15.7, 23.7, 'DELIVERED'),
    (UUID_TO_BIN('112e4567-e89b-12d3-a456-426614174014'), @COSTUMER_2_ID, NULL, @DIEGO_SAC_ID, 'Pharmaceuticals that need to stay between 2°C and 8°C', 2.4, 6.6, 12.6, 18.6, 'DELIVERED');

