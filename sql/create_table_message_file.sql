CREATE TABLE message_file(
	message_file_id SERIAL PRIMARY KEY,
	message_id INTEGER REFERENCES message(message_id) NOT NULL,
	file_binary BYTEA NOT NULL,
	file_name TEXT DEFAULT 'notsetting.png' NOT NULL,
)