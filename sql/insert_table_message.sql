CREATE TABLE message(
	message_id SERIAL PRIMARY KEY,
	account_id INTEGER REFERENCES account(account_id) NOT NULL,
	message_type INTEGER DEFAULT 0 NOT NULL,
	message_body TEXT DEFAULT 'メッセージはありません' NOT NULL,
	insert_at TIMESTAMP NOT NULL,
	check_available BOOLEAN DEFAULT false NOT NULL
)