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
    account_id FOREIGN KEY (account_id) REFERENCES Account(ID)
);
ALTER TABLE OrderEcommer ALTER COLUMN created TYPE timestamp USING created::timestamp;
ALTER TABLE OrderEcommer ALTER COLUMN updated TYPE timestamp USING updated::timestamp;
ALTER TABLE OrderEcommer ALTER COLUMN created set DEFAULT current_timestamp::timestamp;
ALTER TABLE OrderEcommer ALTER COLUMN updated set DEFAULT current_timestamp::timestamp;

--Handler Product
ALTER TABLE product ADD COLUMN supplier_id UUID;
ALTER TABLE product ADD CONSTRAINT supplier_id FOREIGN KEY (supplier_id) REFERENCES Supplier(ID);

--Handler OrderDetail
CREATE TABLE OrderDetail  (
    order_id UUID PRIMARY KEY,
    product_id UUID PRIMARY KEY,
    discount double,
    quantity double,
    total_amount double,
    created date default current_date,
    updated date default current_date,
    user_created varchar(200) default 'admin',
    user_updated varchar(200) default 'admin'
);
ALTER TABLE OrderDetail ALTER COLUMN created TYPE timestamp USING created::timestamp;
ALTER TABLE OrderDetail ALTER COLUMN updated TYPE timestamp USING updated::timestamp;
ALTER TABLE OrderDetail ALTER COLUMN created set DEFAULT current_timestamp::timestamp;
ALTER TABLE OrderDetail ALTER COLUMN updated set DEFAULT current_timestamp::timestamp;
