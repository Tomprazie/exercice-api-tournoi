DROP TABLE IF EXISTS player;
CREATE TABLE player (
                        id SERIAL PRIMARY KEY,
                        pseudo VARCHAR(255),
                        points INT
);
INSERT INTO player (pseudo, points) VALUES ('Alice', 100);
INSERT INTO player (pseudo, points) VALUES ('Bob', 200);
INSERT INTO player (pseudo, points) VALUES ('Charlie', 300);
INSERT INTO player (pseudo, points) VALUES ('David', 400);