-- TABLE - TRACKING
CREATE TABLE tracking (
    id BINARY(16) NOT NULL PRIMARY KEY,
    tracking_number VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    status_details VARCHAR(255),
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

