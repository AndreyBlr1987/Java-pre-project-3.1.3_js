
INSERT INTO users VALUES (1, '222', 'ivan@mail.ru', 'ivan' ,'userov', '$2a$12$jvAIxDK4uZLj.MQ//JhkpeoU/NjJjkRfA6qvPpLGGbs5tnHEi.b7K');
INSERT INTO users VALUES (2, '111', 'stas@mail.ru', 'admin', 'admivon', '$2a$12$jvAIxDK4uZLj.MQ//JhkpeoU/NjJjkRfA6qvPpLGGbs5tnHEi.b7K');


INSERT INTO roles VALUES (1, 'USER');
INSERT INTO roles VALUES (2, 'ADMIN');

INSERT INTO users_roles VALUES (1, 1);
INSERT INTO users_roles VALUES (2, 2);