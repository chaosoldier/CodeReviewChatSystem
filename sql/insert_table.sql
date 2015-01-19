INSERT INTO account (email_address,passphrase,encrypter,available)
VALUES ('abc@abc.com','p','TYPE2',true),('abd@abc.com','q','TYPE2',true),('abe@abc.com','r','TYPE2',true),('abf@abc.com','s','TYPE2',true),('abg@abc.com','t','TYPE2',false);

INSERT INTO personal (account_id,name,roman_name,data,file_name,inserted_at,updated_at)
VALUES (1	,'田中太郎','tanakatarou','a','abc.png',current_timestamp,current_timestamp),
(2,'佐藤太郎','satoutarou','a','abc.png',current_timestamp,current_timestamp),
(3,'小林太郎','kobayasitarou','a','abc.png',current_timestamp,current_timestamp),
(4,'渡辺太郎','watanabetarou','a','abc.png',current_timestamp,current_timestamp),
(5,'Robert De Niro','roba-todeni-ro','a','abc.png',current_timestamp,current_timestamp);