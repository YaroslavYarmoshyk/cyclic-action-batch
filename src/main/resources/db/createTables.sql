CREATE TABLE IF NOT EXISTS positions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    store VARCHAR(255),
    manager VARCHAR(255),
    carryover INT,
    action_code INT,
    store_format INT,
    third_group VARCHAR(255),
    action_type VARCHAR(255),
    action_start_date DATE,
    action_end_date DATE,
    before_action_start_date DATE,
    before_action_end_date DATE,
    action_average_sales NUMERIC(10, 2),
    before_action_average_sales NUMERIC(10, 2),
    actual_average_sales NUMERIC(10, 2),
    algorithm INT
);