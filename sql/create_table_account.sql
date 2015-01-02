CREATE TABLE account(
	account_id SERIAL PRIMARY KEY,
	email_address TEXT UNIQUE NOT NULL,
	passphrase TEXT NOT NULL,
	active_flag BOOLEAN DEFAULT true NOT NULL
)