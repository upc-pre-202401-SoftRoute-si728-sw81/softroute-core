SET @st1_id = UUID_TO_BIN('555e4567-e89b-12d3-a456-426614174000');
SET @first_day_last_month = DATE_SUB(DATE_FORMAT(NOW(), '%Y-%m-01 07:00:00'), INTERVAL 1 MONTH);
SET @now = now();
SET @st1_tracking_code = 'TRK1234';

-- Insertar datos en la tabla tracking
INSERT INTO tracking (id, tracking_number, current_step, last_step, encoded_polyline, status, status_date, latitude, longitude, street, district, province, country, timestamp) VALUES
    (@st1_id, @st1_tracking_code, 0, 148, 'h`{hAn~vtMbD~ENZPz@AJDTJLP@JA\\PbClDjA`BfC|Dn@lAnFtIvChEn@nAVp@^fBFr@Bd@DTr@`WJxDe@t@[Xq@\\o@J_BLyC\\UFqCXsGb@Y@kg@rDqEd@}Jp@}CPuIl@cLx@uHf@QEmBJsEZmAPcBl@gB|@cBr@sA\\w@H{@?mAUk@SeAk@c@c@]}@Ou@@WFQNMPCZ@RFDFTv@TpAJxDFlID~CLpARdAfAlE\\nBRtBLdB\\nC\\`Ed@dIVrDb@jDLvATvDKpBSrACfAD`BP~BJZr@vAVl@Nl@TvAvAtRM^K`AW`AQf@IHAXH~As@A}@DgDZUFcEZ_DZaDF_AHwBd@mAj@iAr@wAtAsAnBm@xAw@hDmAvGW|@_@~@e@r@g@j@{@r@q@`@}@\\s@Jo@D_A?_AMSAUFSNKPCNURe@Ts@Jq@HmDPwKv@qCV_@PMJWb@GZtA|Hl@xCe`@lCcKr@AOsBN','IN_TRANSIT', '2023-05-25 10:00:00', 37.7749, -122.4194, 'Avenida Primavera 2389', 'Santiago de Surco', 'Lima', 'Peru', @now);

INSERT INTO tracking_history (id, status, status_details, status_date, tracking_id) VALUES
    (UUID_TO_BIN('111e4567-e89b-12d3-a456-426614174000'), 'RECEIVED', 'Package has recieved',  @first_day_last_month, @st1_id),
    (UUID_TO_BIN('111e4567-e89b-12d3-a456-426614174001'), 'PREPARING', 'Package is on the way', @first_day_last_month + INTERVAL 1 HOUR, @st1_id),
    (UUID_TO_BIN('111e4567-e89b-12d3-a456-426614174002'), 'DISPATCHED', 'Package is on the way', @first_day_last_month + INTERVAL 2 HOUR, @st1_id),
    (UUID_TO_BIN('111e4567-e89b-12d3-a456-426614174003'), 'IN_TRANSIT', 'Package is on the way', @first_day_last_month + INTERVAL 4 HOUR, @st1_id),
    (UUID_TO_BIN('111e4567-e89b-12d3-a456-426614174004'), 'ARRIVED_AT_DESTINATION', 'Package is on the way', @first_day_last_month + INTERVAL 6 HOUR, @st1_id),
    (UUID_TO_BIN('111e4567-e89b-12d3-a456-426614174005'), 'OUT_FOR_DELIVERY', 'Package is on the way', @first_day_last_month + INTERVAL 7 HOUR, @st1_id),
    (UUID_TO_BIN('222e4567-e89b-12d3-a456-426614174006'), 'DELIVERED', 'Package delivered to the recipient', @first_day_last_month + INTERVAL 8 HOUR, @st1_id);


