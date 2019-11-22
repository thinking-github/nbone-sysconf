/**
   优惠券：
  优惠卷类型管理 增加优惠卷类型、设置类型状态
  优惠卷管理： 增加优惠卷、设置优惠卷状态、系统发放优惠卷
  优惠卷任务：优惠卷过期失效

  用户领取优惠卷、用户使用优惠卷
 */
CREATE TABLE coupon_type
(
    id          int                    NOT NULL AUTO_INCREMENT COMMENT '编号',
    name        varchar(100)           NOT NULL COMMENT '名称',
    value       varchar(100) DEFAULT '-1' COMMENT '对应的值,一般情况下为空，特殊需要含有值填充',
    description varchar(100) COMMENT '描述',
    status      int          DEFAULT 1 NOT NULL COMMENT '状态标记 0失效 1有效',
    sort        decimal(10, 0)         NOT NULL COMMENT '排序（升序）',
    create_by   varchar(64)            NOT NULL COMMENT '创建者',
    create_time datetime               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by   varchar(64)            NOT NULL COMMENT '更新者',
    update_time datetime               NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    remarks     varchar(255) COMMENT '备注信息',
    deleted     int          DEFAULT 0 NOT NULL COMMENT '删除标记',
    PRIMARY KEY (id)
) COMMENT = '优惠卷类型';

CREATE TABLE coupon_batch
(
    id          bigint          NOT NULL AUTO_INCREMENT COMMENT '编号',
    create_num  int           NOT NULL COMMENT '优惠卷生成数量',
    description varchar(100) COMMENT '描述',
    status      int DEFAULT 1 NOT NULL COMMENT '状态标记 0失效(该生成批次失效) 1有效',
    create_by   varchar(64)   NOT NULL COMMENT '创建者',
    create_time datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by   varchar(64)   NOT NULL COMMENT '更新者',
    update_time datetime      NOT NULL COMMENT '更新时间',
    remarks     varchar(255) COMMENT '备注信息',
    deleted     int DEFAULT 0 NOT NULL COMMENT '删除标记',
    PRIMARY KEY (id)
) COMMENT = '优惠卷生成';

CREATE TABLE coupon_info
(
    id          bigint          NOT NULL AUTO_INCREMENT COMMENT '编号',
    coupon_no   bigint          NOT NULL COMMENT '优惠卷号,可以根据优惠卷号生产二维码',
    batch_id    bigint          NOT NULL COMMENT '优惠卷生成批次 coupon_batch.id',
    type        int           NOT NULL COMMENT '优惠卷类型 coupon_type.id',
    status      int DEFAULT 0 NOT NULL COMMENT '状态标记 0未使用 1已使用 2已过期',
    start_time  datetime      not null COMMENT '有效开始时间',
    end_time    datetime      not null COMMENT '有效结束时间',
    grant_type     int COMMENT '0用户领取 1系统发放',
    user_id     long COMMENT '所属用户',
    create_by   varchar(64)   NOT NULL COMMENT '创建者',
    create_time datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by   varchar(64)   NOT NULL COMMENT '更新者',
    update_time datetime      NOT NULL COMMENT '更新时间',
    remarks     varchar(255) COMMENT '备注信息',
    deleted     int DEFAULT 0 NOT NULL COMMENT '删除标记',
    PRIMARY KEY (id)
) COMMENT = '优惠卷信息';