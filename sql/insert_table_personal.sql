INSERT INTO account (account_id,name,name_pronunciation,file_binary)
VALUES ('p','p',true),('q','q',true),('r','r',true),('s','s',true),('t','t',false)

CREATE TABLE personal(
	personal_id SERIAL PRIMARY KEY,
	account_id INTEGER REFERENCES account(account_id) NOT NULL,
	name TEXT DEFAULT '退会者' NOT NULL,
	name_pronunciation TEXT DEFAULT 'taikaisya' NOT NULL,
	file_binary BYTEA NOT NULL,
	file_name TEXT DEFAULT 'notsetting.png' NOT NULL,
	inserted_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL
)