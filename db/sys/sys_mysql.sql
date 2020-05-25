CREATE TABLE sys_configuration
(
    id           int          NOT NULL AUTO_INCREMENT COMMENT '编号',
    name         varchar(256) NOT NULL COMMENT '配置参数名称',
    value        varchar(100) NOT NULL COMMENT '配置参数值',
    app_id       varchar(64)  NOT NULL COMMENT 'app id',
    package_name varchar(64)  NOT NULL COMMENT '产品包名',
    description  varchar(100) COMMENT '描述',
    create_time  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time  datetime     NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted      int                   DEFAULT 0 NOT NULL COMMENT '删除标记',
    PRIMARY KEY (id)
) COMMENT = '系统配置表';

CREATE TABLE sys_group
(
    id           int          NOT NULL AUTO_INCREMENT COMMENT '编号',
    name         varchar(100) NOT NULL COMMENT '名称',
    type         varchar(64)  NOT NULL COMMENT '类型',
    app_id       varchar(64)  NOT NULL COMMENT 'app id',
    package_name varchar(64)  NOT NULL COMMENT '产品包名',
    description  varchar(100) COMMENT '描述',
    parent_id    int                   DEFAULT 0 COMMENT '父级编号',
    level        int                   DEFAULT 1 COMMENT '级别 1一级 2二级 以此类推',
    status       int                   DEFAULT 0 NOT NULL COMMENT '状态标记 0禁用 1启用',
    sort         int                   DEFAULT 0 NOT NULL COMMENT '排序（升序）',
    create_by    varchar(64)  NOT NULL COMMENT '创建者',
    create_time  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by    varchar(64)  NOT NULL COMMENT '更新者',
    update_time  datetime     NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    remarks      varchar(255) COMMENT '备注信息',
    deleted      int          NOT NULL DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (id)
) COMMENT = '业务分组表-支持树形结构';

CREATE TABLE sys_dict
(
    id           int          NOT NULL AUTO_INCREMENT COMMENT '编号',
    value        varchar(100) NOT NULL COMMENT '数据值',
    label        varchar(100) NOT NULL COMMENT '标签名',
    type         varchar(64)  NOT NULL COMMENT '类型',
    app_id       varchar(64)  NOT NULL COMMENT 'app id',
    package_name varchar(64)  NOT NULL COMMENT '产品包名',
    description  varchar(100) NOT NULL COMMENT '描述',
    parent_id    int                   DEFAULT 0 COMMENT '父级编号',
    level        int                   DEFAULT 1 COMMENT '级别 1一级 2二级 以此类推',
    status       int          NOT NULL DEFAULT 0 COMMENT '状态标记 0禁用 1启用',
    sort         int          NOT NULL DEFAULT 0 COMMENT '排序（升序）',
    create_by    varchar(64)  NOT NULL COMMENT '创建者',
    create_time  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by    varchar(64)  NOT NULL COMMENT '更新者',
    update_time  datetime     NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    remarks      varchar(255) COMMENT '备注信息',
    deleted      int          NOT NULL DEFAULT 0 COMMENT '删除标记 0:未删除,1已删除',
    PRIMARY KEY (id)
) COMMENT = '字典表-支持树形结构';

CREATE TABLE IF NOT EXISTS sys_resource_language
(
    id            int          NOT NULL AUTO_INCREMENT COMMENT '编号',
    name          varchar(128) NOT NULL COMMENT '名称(对应语言)',
    content       varchar(256) NULL COMMENT '内容',
    language_code varchar(8)   NOT NULL COMMENT '语言编码',
    description   varchar(128) NULL COMMENT '描述',
    type          varchar(64)  NULL COMMENT '类型,可选字段当存储多种资源时使用',
    resource_id   int          NOT NULL COMMENT '资源ID',
    sort          int          NOT NULL DEFAULT 0 COMMENT '排序（升序）',
    create_by     varchar(64)  NOT NULL COMMENT '创建者',
    create_time   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by     varchar(64)  NOT NULL COMMENT '更新者',
    update_time   datetime     NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    remarks       varchar(255) NULL COMMENT '备注信息',
    deleted       int          NOT NULL DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (id)
) comment '资源多语言设置表';
