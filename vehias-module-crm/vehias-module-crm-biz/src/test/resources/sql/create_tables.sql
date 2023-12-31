CREATE TABLE IF NOT EXISTS "crm_contract" (
  "id" bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
  "name" varchar NOT NULL,
  "customer_id" bigint,
  "business_id" bigint,
  "process_instance_id" bigint,
  "order_date" varchar,
  "owner_user_id" bigint,
  "no" varchar,
  "start_time" varchar,
  "end_time" varchar,
  "price" int,
  "discount_percent" int,
  "product_price" int,
  "ro_user_ids" varchar,
  "rw_user_ids" varchar,
  "contact_id" bigint,
  "sign_user_id" bigint,
  "contact_last_time" varchar,
  "remark" varchar,
  "creator" varchar DEFAULT '',
  "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updater" varchar DEFAULT '',
  "update_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  "deleted" bit NOT NULL DEFAULT FALSE,
  PRIMARY KEY ("id")
) COMMENT '合同表';

CREATE TABLE IF NOT EXISTS "crm_clue" (
  "id" bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
  "transform_status" bit NOT NULL,
  "follow_up_status" bit NOT NULL,
  "name" varchar NOT NULL,
  "customer_id" bigint NOT NULL,
  "contact_next_time" varchar,
  "telephone" varchar,
  "mobile" varchar,
  "address" varchar,
  "owner_user_id" bigint,
  "contact_last_time" varchar,
  "remark" varchar,
  "creator" varchar DEFAULT '',
  "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updater" varchar DEFAULT '',
  "update_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  "deleted" bit NOT NULL DEFAULT FALSE,
  "tenant_id" bigint NOT NULL,
  PRIMARY KEY ("id")
) COMMENT '线索表';

CREATE TABLE IF NOT EXISTS "crm_receivable" (
    "id" bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    "no" varchar,
    "plan_id" bigint,
    "customer_id" bigint,
    "contract_id" bigint,
    "check_status" int,
    "process_instance_id" bigint,
    "return_time" varchar,
    "return_type" varchar,
    "price" varchar,
    "owner_user_id" bigint,
    "batch_id" bigint,
    "sort" int,
    "data_scope" int,
    "data_scope_dept_ids" varchar,
    "status" int NOT NULL,
    "remark" varchar,
    "creator" varchar DEFAULT '',
    "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updater" varchar DEFAULT '',
    "update_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    "deleted" bit NOT NULL DEFAULT FALSE,
    PRIMARY KEY ("id")
) COMMENT '回款';

CREATE TABLE IF NOT EXISTS "crm_receivable_plan" (
     "id" bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
     "index_no" bigint,
     "receivable_id" bigint,
     "status" int NOT NULL,
     "check_status" varchar,
     "process_instance_id" bigint,
     "price" varchar,
     "return_time" varchar,
     "remind_days" bigint,
     "remind_time" varchar,
     "customer_id" bigint,
     "contract_id" bigint,
     "owner_user_id" bigint,
     "sort" int,
     "remark" varchar,
     "creator" varchar DEFAULT '',
     "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
     "updater" varchar DEFAULT '',
     "update_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     "deleted" bit NOT NULL DEFAULT FALSE,
     PRIMARY KEY ("id")
) COMMENT '回款计划';

CREATE TABLE IF NOT EXISTS "crm_customer" (
  "id" bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
  "name"              varchar(255),
  "follow_up_status"  int NOT NULL,
  "lock_status"       int NOT NULL,
  "deal_status"       int NOT NULL,
  "industry_id"       int,
  "level"             int,
  "source"            int,
  "mobile"            varchar(255),
  "telephone"         varchar(255),
  "website"           varchar(255),
  "qq"                varchar(255),
  "wechat"            varchar(255),
  "email"             varchar(255),
  "description"       varchar(255),
  "remark"            varchar(255),
  "owner_user_id" bigint,
  "area_id"           int,
  "detail_address"    varchar(255),
  "contact_last_time" datetime,
  "contact_next_time" datetime,
  "creator" varchar DEFAULT '',
  "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "updater" varchar DEFAULT '',
  "update_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  "deleted" bit NOT NULL DEFAULT FALSE,
  "tenant_id" bigint NOT NULL,
  PRIMARY KEY ("id")
) COMMENT '客户表';

CREATE TABLE IF NOT EXISTS "crm_customer_limit_config" (
   "id" bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
   "type" int NOT NULL,
   "user_ids" varchar,
   "dept_ids" varchar,
   "max_count" int NOT NULL,
   "deal_count_enabled" varchar,
   "creator" varchar DEFAULT '',
   "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   "updater" varchar DEFAULT '',
   "update_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   "deleted" bit NOT NULL DEFAULT FALSE,
   "tenant_id" bigint NOT NULL,
   PRIMARY KEY ("id")
) COMMENT '客户限制配置表';

CREATE TABLE IF NOT EXISTS "crm_permission"
(
    "id"          bigint   NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    "biz_id"      bigint   NOT NULL,
    "biz_type"    int      NOT NULL,
    "user_id"     bigint   NOT NULL,
    "level"       int      NOT NULL,
    "creator"     varchar           DEFAULT '',
    "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updater"     varchar           DEFAULT '',
    "update_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    "deleted"     bit      NOT NULL DEFAULT FALSE,
   "tenant_id" bigint NOT NULL,
   PRIMARY KEY ("id")
) COMMENT '客户限制配置表';