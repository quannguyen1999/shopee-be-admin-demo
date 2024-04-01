--Handler Supplier
ALTER TABLE supplier ADD COLUMN created date;
ALTER TABLE supplier ADD COLUMN updated date;
ALTER TABLE supplier ADD COLUMN user_created varchar(200);
ALTER TABLE supplier ADD COLUMN user_updated varchar(200);
ALTER TABLE supplier ALTER COLUMN created SET DEFAULT current_date;
ALTER TABLE supplier ALTER COLUMN updated SET DEFAULT current_date;
ALTER TABLE supplier ALTER COLUMN user_created SET DEFAULT 'admin';
ALTER TABLE supplier ALTER COLUMN user_updated SET DEFAULT 'admin';
ALTER TABLE supplier ALTER COLUMN created TYPE timestamp USING created::timestamp;
ALTER TABLE supplier ALTER COLUMN updated TYPE timestamp USING updated::timestamp;
ALTER TABLE supplier ALTER COLUMN created set DEFAULT current_timestamp::timestamp;
ALTER TABLE supplier ALTER COLUMN updated set DEFAULT current_timestamp::timestamp;

--Handler Order
CREATE TABLE OrderEcommer  (
    ID UUID PRIMARY KEY,
    order_date DATE,
    ship_city VARCHAR(500),
    shipped_date DATE,
    ship_region varchar(500),
    created date default current_date,
    updated date default current_date,
    user_created varchar(200) default 'admin',
    user_updated varchar(200) default 'admin',
    account_id UUID,
    FOREIGN KEY (account_id) REFERENCES Account(ID)
);
ALTER TABLE OrderEcommer ALTER COLUMN created TYPE timestamp USING created::timestamp;
ALTER TABLE OrderEcommer ALTER COLUMN updated TYPE timestamp USING updated::timestamp;
ALTER TABLE OrderEcommer ALTER COLUMN created set DEFAULT current_timestamp::timestamp;
ALTER TABLE OrderEcommer ALTER COLUMN updated set DEFAULT current_timestamp::timestamp;

--Handler Product
ALTER TABLE product ADD COLUMN supplier_id UUID;

--Handler OrderDetail
CREATE TABLE order_detail (
	discount float8 NULL,
	quantity int4 NOT NULL,
	total_amount float8 NULL,
	order_id uuid NOT NULL,
	product_id uuid NOT NULL,
	created date default current_date,
    updated date default current_date,
    user_created varchar(200) default 'admin',
    user_updated varchar(200) default 'admin',
    id uuid primary key
);
ALTER TABLE order_detail ALTER COLUMN created TYPE timestamp USING created::timestamp;
ALTER TABLE order_detail ALTER COLUMN updated TYPE timestamp USING updated::timestamp;
ALTER TABLE order_detail ALTER COLUMN created set DEFAULT current_timestamp::timestamp;
ALTER TABLE order_detail ALTER COLUMN updated set DEFAULT current_timestamp::timestamp;
