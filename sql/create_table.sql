CREATE TABLE account(
	account_id SERIAL PRIMARY KEY,
	email_address TEXT UNIQUE DEFAULT 'deleted account' NOT NULL,
	passphrase TEXT DEFAULT 'deleted account'  NOT NULL,
	encrypter TEXT DEFAULT 'TYPE0' NOT NULL,
	available BOOLEAN DEFAULT false NOT NULL
)
CREATE TABLE personal(
	personal_id SERIAL PRIMARY KEY,
	account_id INTEGER REFERENCES account(account_id) NOT NULL,
	name TEXT DEFAULT 'deleted account' NOT NULL,
	roman_name TEXT DEFAULT 'deleted account' NOT NULL,
	data BYTEA DEFAULT 'deleted account' NOT NULL,
	file_name TEXT DEFAULT 'deleted account' NOT NULL,
	inserted_at TIMESTAMP DEFAULT current_timestamp NOT NULL,
	updated_at TIMESTAMP DEFAULT current_timestamp NOT NULL
)
CREATE TABLE message(
	message_id SERIAL PRIMARY KEY,
	account_id INTEGER REFERENCES account(account_id) NOT NULL,
	type TEXT NOT NULL,
	body TEXT NOT NULL,
	insert_at TIMESTAMP NOT NULL,
	check_available BOOLEAN  NOT NULL
)
CREATE TABLE read_message(
	read_message_id SERIAL PRIMARY KEY,
	message_id INTEGER REFERENCES message(message_id) NOT NULL,
	account_id INTEGER REFERENCES account(account_id) NOT NULL,
	inserted_at TIMESTAMP NOT NULL
	CONSTRAINT read_message_message_id_account_id_key UNIQUE (message_id, account_id)
)

CREATE TABLE check_message(
	check_message_id SERIAL PRIMARY KEY,
	message_id INTEGER REFERENCES message(message_id) NOT NULL,
	account_id INTEGER REFERENCES account(account_id) NOT NULL,
	inserted_at TIMESTAMP NOT NULL
	CONSTRAINT read_message_message_id_account_id_key UNIQUE (message_id, account_id)
)

CREATE TABLE message_file(
	message_file_id SERIAL PRIMARY KEY,
	message_id INTEGER REFERENCES message(message_id) NOT NULL,
	data BYTEA NOT NULL,
	file_name TEXT NOT NULL
)