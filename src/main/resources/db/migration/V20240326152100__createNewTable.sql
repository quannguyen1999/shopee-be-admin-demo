ALTER TABLE product
    ADD CONSTRAINT supplier_id FOREIGN KEY (category_id) REFERENCES Category(ID);

CREATE TABLE Supplier  (
    ID UUID PRIMARY KEY,
    phone VARCHAR(200),
    address VARCHAR(500),
    companyName varchar(200)
);




