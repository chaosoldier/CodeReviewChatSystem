INSERT INTO account (email_address,passphrase,encrypter,available)
VALUES ('abc@abc.com','70bf66de4b22069472296b1a9aded72ca998d431ff6130ffdcb99d877b51e5ab','TYPE2',true),('abd@abc.com','12826811bcfc336275e9fb66b2d9466fa3635d08ef5275a3e26e4789ed75d0d7','TYPE2',true),('abe@abc.com','8a161009ba26647a5e427db251498e8803b3d6a453ce7ea82920fe0a6567bbd0','TYPE2',true),('abf@abc.com','5d14678bc5e5f212959459dbe143cf8f404fa8e9feaf04adf6aa175ca0e7baec','TYPE2',true),('abg@abc.com','76f79ad4b39685112fda117eef7cd3453c6e1e531a7040aae40005311d43c5ad','TYPE2',false);

INSERT INTO personal (account_id,name,roman_name,data,file_name,inserted_at,updated_at)
VALUES (1	,'田中太郎','tanakatarou','a','abc.png',current_timestamp,current_timestamp),
(2,'佐藤太郎','satoutarou','a','abc.png',current_timestamp,current_timestamp),
(3,'小林太郎','kobayasitarou','a','abc.png',current_timestamp,current_timestamp),
(4,'渡辺太郎','watanabetarou','a','abc.png',current_timestamp,current_timestamp),
(5,'Robert De Niro','roba-todeni-ro','a','abc.png',current_timestamp,current_timestamp);