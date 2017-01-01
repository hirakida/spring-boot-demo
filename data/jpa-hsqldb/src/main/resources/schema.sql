/*
 classpath直下に以下のfileがあると自動で実行される
 - schema-(platform).sql
 - schema.sql
 - data-(platform).sql
 - data.sql
*/
CREATE TABLE IF NOT EXISTS account (
-- hsqlはUNSIGNEDが指定できない
  id INT PRIMARY KEY IDENTITY,
  name VARCHAR(30) NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL
);
