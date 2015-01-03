CREATE TABLE read_message(
	read_message_id SERIAL PRIMARY KEY,
	message_id INTEGER REFERENCES message(message_id) NOT NULL,
	account_id INTEGER REFERENCES account(account_id) NOT NULL,
	inserted_at TIMESTAMP NOT NULL
)
