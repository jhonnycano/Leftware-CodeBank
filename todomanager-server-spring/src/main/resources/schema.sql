CREATE TABLE project (
    id varchar(255) not null
  , name varchar(255)
  , primary key (id)
);

CREATE TABLE task (
    id varchar(255) not null
  , project_id varchar(255)
  , text varchar(255)
  , status varchar(255)
  , completed_at TIMESTAMP
  , primary key (id)
);

CREATE TABLE task_log (
    id varchar(255) not null
  , project_id varchar(255)
  , task_id varchar(255)
  , old_status varchar(255)
  , new_status varchar(255)
  , created_at TIMESTAMP
  , primary key (id)
);