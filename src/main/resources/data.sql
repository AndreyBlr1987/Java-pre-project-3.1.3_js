
INSERT INTO users VALUES (1, '23', 'ivan@mail.ru', 'vanya' ,'ivanov', '$2a$12$jvAIxDK4uZLj.MQ//JhkpeoU/NjJjkRfA6qvPpLGGbs5tnHEi.b7K');
INSERT INTO users VALUES (2, '23', 'Stas@mail.ru', 'stasic', 'stasov', '$2a$12$jvAIxDK4uZLj.MQ//JhkpeoU/NjJjkRfA6qvPpLGGbs5tnHEi.b7K');


INSERT INTO roles VALUES (1, 'USER');
INSERT INTO roles VALUES (2, 'ADMIN');

INSERT INTO users_roles VALUES (1, 1);
INSERT INTO users_roles VALUES (2, 2);