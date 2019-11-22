CREATE TABLE sys_group
(
    id           int                    NOT NULL AUTO_INCREMENT COMMENT '编号',
    name         varchar(100)           NOT NULL COMMENT '名称',
    type         varchar(100)           NOT NULL COMMENT '类型',
    app_id       varchar(64)            NOT NULL COMMENT 'app id',
    package_name varchar(64)            NOT NULL COMMENT '产品包名',
    description  varchar(100) COMMENT '描述',
    parent_id    int          DEFAULT 0 COMMENT '父级编号',
    level        int          DEFAULT 1 COMMENT '级别 1一级 2二级 以此类推',
    status       int          DEFAULT 0 NOT NULL COMMENT '状态标记 0禁用 1启用',
    sort         decimal(10, 0)         NOT NULL COMMENT '排序（升序）',
    create_by    varchar(64)            NOT NULL COMMENT '创建者',
    create_time  datetime               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by    varchar(64)            NOT NULL COMMENT '更新者',
    update_time  datetime               NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    remarks      varchar(255) COMMENT '备注信息',
    deleted      int          DEFAULT 0 NOT NULL COMMENT '删除标记',
    PRIMARY KEY (id)
) COMMENT = '业务分组表-支持树形结构';

CREATE TABLE sys_dict
(
    id           int             NOT NULL AUTO_INCREMENT COMMENT '编号',
    value        varchar(100)    NOT NULL COMMENT '数据值',
    label        varchar(100)    NOT NULL COMMENT '标签名',
    type         varchar(100)    NOT NULL COMMENT '类型',
    app_id       varchar(64)     NOT NULL COMMENT 'app id',
    package_name varchar(64)     NOT NULL COMMENT '产品包名',
    description  varchar(100)    NOT NULL COMMENT '描述',
    parent_id    int DEFAULT 0 COMMENT '父级编号',
    level        int DEFAULT 1 COMMENT '级别 1一级 2二级 以此类推',
    status       int DEFAULT 0   NOT NULL COMMENT '状态标记 0禁用 1启用',
    sort         decimal(10, 0)  NOT NULL COMMENT '排序（升序）',
    create_by    varchar(64)     NOT NULL COMMENT '创建者',
    create_time  datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by    varchar(64)     NOT NULL COMMENT '更新者',
    update_time  datetime        NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    remarks      varchar(255) COMMENT '备注信息',
    deleted      int DEFAULT '0' NOT NULL COMMENT '删除标记 0:未删除,1已删除',
    PRIMARY KEY (id)
) COMMENT = '字典表-支持树形结构';
