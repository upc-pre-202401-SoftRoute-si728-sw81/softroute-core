SET @st1_id = UUID_TO_BIN('555e4567-e89b-12d3-a456-426614174000');
SET @first_day_last_month = DATE_SUB(DATE_FORMAT(NOW(), '%Y-%m-01 07:00:00'), INTERVAL 1 MONTH);
SET @now = now();
SET @st1_tracking_code = 'TRK1234';

-- Insertar datos en la tabla tracking
INSERT INTO tracking (id, tracking_number, status, status_details, status_date, latitude, longitude, timestamp) VALUES
    (@st1_id, @st1_tracking_code, 'IN_TRANSIT', 'Package is in transit', '2023-05-25 10:00:00', 37.7749, -122.4194, @now);

INSERT INTO tracking_history (id, status, status_details, status_date, tracking_id) VALUES
    (UUID_TO_BIN('111e4567-e89b-12d3-a456-426614174000'), 'RECEIVED', 'Package has recieved',  @first_day_last_month, @st1_id),
    (UUID_TO_BIN('111e4567-e89b-12d3-a456-426614174001'), 'PREPARING', 'Package is on the way', @first_day_last_month + INTERVAL 1 HOUR, @st1_id),
    (UUID_TO_BIN('111e4567-e89b-12d3-a456-426614174002'), 'DISPATCHED', 'Package is on the way', @first_day_last_month + INTERVAL 2 HOUR, @st1_id),
    (UUID_TO_BIN('111e4567-e89b-12d3-a456-426614174003'), 'IN_TRANSIT', 'Package is on the way', @first_day_last_month + INTERVAL 4 HOUR, @st1_id),
    (UUID_TO_BIN('111e4567-e89b-12d3-a456-426614174004'), 'ARRIVED_AT_DESTINATION', 'Package is on the way', @first_day_last_month + INTERVAL 6 HOUR, @st1_id),
    (UUID_TO_BIN('111e4567-e89b-12d3-a456-426614174005'), 'OUT_FOR_DELIVERY', 'Package is on the way', @first_day_last_month + INTERVAL 7 HOUR, @st1_id),
    (UUID_TO_BIN('222e4567-e89b-12d3-a456-426614174006'), 'DELIVERED', 'Package delivered to the recipient', @first_day_last_month + INTERVAL 8 HOUR, @st1_id);


