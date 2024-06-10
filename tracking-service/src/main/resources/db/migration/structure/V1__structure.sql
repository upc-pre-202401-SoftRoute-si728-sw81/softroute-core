-- TABLE - TRACKING
CREATE TABLE tracking (
    id BINARY(16) NOT NULL PRIMARY KEY,
    tracking_number VARCHAR(255) NOT NULL,
    encoded_polyline TEXT NOT NULL,
    status VARCHAR(50) NOT NULL,
    street VARCHAR(120) NOT NULL,
    district VARCHAR(120) NOT NULL,
    province VARCHAR(120) NOT NULL,
    country VARCHAR(120) NOT NULL,
    current_step INT NOT NULL,
    last_step INT NOT NULL,
    status_date TIMESTAMP,
    latitude DOUBLE,
    longitude DOUBLE,
    timestamp TIMESTAMP
);

-- TABLE - TRACKING HISTORY
CREATE TABLE tracking_history (
    id BINARY(16) NOT NULL,
    status VARCHAR(50) NOT NULL,
    status_details TEXT,
    status_date TIMESTAMP NOT NULL,
    tracking_id BINARY(16) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (tracking_id) REFERENCES tracking(id)
);

