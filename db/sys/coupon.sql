/**
   优惠券：
  优惠卷类型管理 增加优惠卷类型、设置类型状态
  优惠卷管理： 增加优惠卷、设置优惠卷状态、系统发放优惠卷
  优惠卷任务：优惠卷过期失效

  用户领取优惠卷、用户使用优惠卷 Template
 */
-- coupon.total.limit = 100 优惠券总量限制
CREATE TABLE coupon_rule
(
  id          int            NOT NULL AUTO_INCREMENT COMMENT '编号',
  name        varchar(100)   NOT NULL COMMENT '规则名称',
  type        int            NOT NULL COMMENT '类型 0:无门槛,1:满减,2:满折,3:随机,4:服务卡卷',
  value       varchar(100)            DEFAULT '-1' COMMENT '{}',
  description varchar(100) COMMENT '描述',
  sort        decimal(10, 0) NOT NULL COMMENT '排序（升序）',
  create_by   varchar(64)    NOT NULL COMMENT '创建者',
  create_time datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_by   varchar(64)    NOT NULL COMMENT '更新者',
  update_time datetime       NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  remarks     varchar(255) COMMENT '备注信息',
  deleted     int                     DEFAULT 0 NOT NULL COMMENT '删除标记',
  PRIMARY KEY (id)
) COMMENT = '优惠卷规则,系统初始化';

-- 按照产品分表
CREATE TABLE coupon_template
(
  id              int            NOT NULL AUTO_INCREMENT COMMENT '编号',
  name            varchar(64)    NOT NULL COMMENT '优惠券名称title',
  type            int            NOT NULL COMMENT '类型,0:无门槛,1:满减,2:满折,3:随机,4:服务卡卷',
  classifyId      int            NOT NULL DEFAULT -1 COMMENT '限制品类,默认为-1不限制',
  user_type       int            NOT NULL DEFAULT -1 COMMENT '限制用户类型领取,默认为-1不限制',
  limit           int            NOT NULL DEFAULT 1 COMMENT '限制用户领取数量,默认为1张',
  use_limit       int            NOT NULL DEFAULT 1 COMMENT '单次使用个数,默认为1',
  value           varchar(512)   NOT NULL COMMENT '规则数据{}',
  valid_type      int            NOT NULL COMMENT '有效期类型,0:固定日期,1:领取后有效多长时间',
  valid_date_num  int            NOT NULL COMMENT 'valid_type=1时,有效时长',
  valid_date_unit char(1)        NOT NULL COMMENT 'valid_type=1时,有效时长单位,有效值Y/M/W/D,默认为D',
  description     varchar(100) COMMENT '规则描述',
  give_type       int                     DEFAULT 0 COMMENT '0用户领取,1系统发放',
  give_total      int            NOT NULL COMMENT '优惠卷发送总量数量',
  package_name    varchar(64)    NOT NULL COMMENT '产品包名',
  status          int                     DEFAULT 1 NOT NULL COMMENT '状态标记,0失效1有效',
  sort            decimal(10, 0) NOT NULL COMMENT '排序（升序）',
  create_by       varchar(64)    NOT NULL COMMENT '创建者',
  create_time     datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_by       varchar(64)    NOT NULL COMMENT '更新者',
  update_time     datetime       NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  remarks         varchar(255) COMMENT '备注信息',
  deleted         int                     DEFAULT 0 NOT NULL COMMENT '删除标记',
  PRIMARY KEY (id)
) COMMENT = '优惠卷模板,产品根据规则设置模板';

-- 按照产品分表
CREATE TABLE coupon_batch
(
  id              bigint       NOT NULL AUTO_INCREMENT COMMENT '编号',
  template_id     int          NOT NULL COMMENT '优惠卷模板coupon_template.id',
  name            varchar(64)  NOT NULL COMMENT '优惠券名称 title',
  type            int          NOT NULL COMMENT '类型,0:无门槛,1:满减,2:满折,3:随机,4:服务卡卷',
  classifyId      int          NOT NULL DEFAULT -1 COMMENT '限制品类,优先使用运营设置的类目，模板次之,默认为-1不限制',
  user_type       int          NOT NULL DEFAULT -1 COMMENT '限制用户类型,优先使用运营设置的类型，模板次之 默认为-1不限制',
  limit           int          NOT NULL DEFAULT 1 COMMENT '限制用户领取数量 默认为1张',
  use_limit       int          NOT NULL DEFAULT 1 COMMENT '单次使用个数 默认为1',
  value           varchar(512) NOT NULL COMMENT '规则数据{}',
  valid_type      int          NOT NULL COMMENT '有效期类型，0:固定日期，1:领取后有效多长时间',
  valid_date_num  int          NOT NULL COMMENT 'valid_type=1时,有效时长',
  valid_date_unit char(1)      NOT NULL COMMENT 'valid_type=1时,有效时长单位 有效值Y/M/W/D,默认为D',
  description     varchar(100) COMMENT '规则描述',
  give_type       int                   DEFAULT 0 COMMENT '0用户领取 1系统发放',
  give_total      int          NOT NULL COMMENT '优惠卷发送总量数量',
  give_count      int          NOT NULL COMMENT '当前领取数量',
  used_count      int          NOT NULL COMMENT '当前使用数量',
  give_start_time datetime     NULL COMMENT '领取开始时间',
  give_end_time   datetime     NULL COMMENT '领取结束时间',
  start_time      datetime     NOT NULL COMMENT '有效开始时间',
  end_time        datetime     NOT NULL COMMENT '有效结束时间',
  package_name    varchar(64)  NOT NULL COMMENT '产品包名',
  status          int                   DEFAULT 1 NOT NULL COMMENT '状态标记 0失效(该生成批次失效) 1有效',
  create_by       varchar(64)  NOT NULL COMMENT '创建者',
  create_time     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_by       varchar(64)  NOT NULL COMMENT '更新者',
  update_time     datetime     NOT NULL COMMENT '更新时间',
  remarks         varchar(255) COMMENT '备注信息',
  deleted         int                   DEFAULT 0 NOT NULL COMMENT '删除标记',
  PRIMARY KEY (id)
) COMMENT = '优惠卷生成批次';
-- 按照产品分表
CREATE TABLE coupon_info
(
  id          bigint        NOT NULL AUTO_INCREMENT COMMENT '编号',
  coupon_no   bigint        NOT NULL COMMENT '优惠卷号',
  batch_id    bigint        NOT NULL COMMENT '优惠卷生成批次 coupon_batch.id',
  type        int           NOT NULL COMMENT '优惠卷类型 coupon_rule.type,类型,0:无门槛,1:满减,2:满折,3:随机,4:服务卡卷',
  status      int DEFAULT 0 NOT NULL COMMENT '状态标记 0未使用 1已使用 2已过期',
  start_time  datetime      not null COMMENT '有效开始时间',
  end_time    datetime      not null COMMENT '有效结束时间',
  grant_type  int COMMENT '0用户领取 1系统发放',
  user_id     long COMMENT '所属用户',
  create_by   varchar(64)   NOT NULL COMMENT '创建者',
  create_time datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_by   varchar(64)   NOT NULL COMMENT '更新者',
  update_time datetime      NOT NULL COMMENT '更新时间',
  remarks     varchar(255) COMMENT '备注信息',
  deleted     int DEFAULT 0 NOT NULL COMMENT '删除标记',
  PRIMARY KEY (id)
) COMMENT = '优惠卷信息';