-- DICTIONARY DATA -- 

INSERT INTO message_type (name) VALUES ('INFO');
INSERT INTO message_type (name) VALUES ('WARN');
INSERT INTO message_type (name) VALUES ('ERROR');

-- DICTIONARY DATA END -- 

INSERT INTO message (type, text) VALUES ('INFO', 'Logged test message');
INSERT INTO message (type, text) VALUES ('ERROR', 'Overheat ERROR at core: 1');
INSERT INTO message (type, text) VALUES ('INFO', 'Additional cooling applied at core: 1');
INSERT INTO message (type, text) VALUES ('INFO', 'Application "stress-test" started for core: 1, 2, 3, 4');
INSERT INTO message (type, text) VALUES ('WARN', 'Possible overheat at core: 2');
INSERT INTO message (type, text) VALUES ('WARN', 'Possible overheat at core: 3');
INSERT INTO message (type, text) VALUES ('WARN', 'Possible overheat at core: 4');
INSERT INTO message (type, text) VALUES ('ERROR', 'Overheat ERROR at core: 4');
INSERT INTO message (type, text) VALUES ('INFO', 'Additional cooling applied at core: 4');
INSERT INTO message (type, text) VALUES ('WARN', 'Application "stress-test" stopped manually for core: 1, 2, 3, 4');
INSERT INTO message (type, text) VALUES ('INFO', 'Report saved for last "stress-test" run');
INSERT INTO message (type, text) VALUES ('INFO', 'Core: 1 temperature back to normal');
INSERT INTO message (type, text) VALUES ('INFO', 'Core: 4 temperature back to normal');