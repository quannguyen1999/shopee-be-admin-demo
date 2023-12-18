ALTER TABLE account
    ADD IF NOT EXISTS birthday date;

ALTER TABLE account
    add IF NOT EXISTS gender boolean default false;

ALTER TABLE account
    ADD IF NOT EXISTS email varchar (200);

ALTER TABLE account
    ADD IF NOT EXISTS avatar varchar;

ALTER TABLE account
    ADD IF NOT EXISTS codeRole varchar;

-- ALTER TABLE account
--     ADD FOREIGN KEY (codeRole) REFERENCES RoleAccount (code);
-- Add Foreign Key
DO
$$
BEGIN
  IF
NOT EXISTS (SELECT * FROM pg_constraint WHERE conname = 'account_coderole_fkey') THEN
ALTER TABLE account
    add FOREIGN KEY (codeRole) REFERENCES RoleAccount (code);
END IF;
END $$;

CREATE TABLE IF NOT EXISTS RoleAccount
(
    code
    varchar
    PRIMARY
    KEY,
    name
    VARCHAR
(
    20
)
    );


