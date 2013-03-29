/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013/3/28 17:37:04                           */
/*==============================================================*/


drop table if exists t_app_menu;

drop table if exists t_app_role;

drop table if exists t_app_role_menu;

drop index Index_1 on t_app_user;

drop table if exists t_app_user;

drop table if exists t_app_user_role;

/*==============================================================*/
/* Table: t_app_menu                                            */
/*==============================================================*/
create table t_app_menu
(
   id                   int not null auto_increment,
   menuName             varchar(32) not null comment '菜单名称',
   menuCode             varchar(32) comment '父菜单编号',
   menuUrl              varchar(256) comment '菜单URL',
   urlTarget            varchar(32) comment '页面打开位置',
   sort                 int comment '排序',
   remark               varchar(64) comment '备注',
   createTime           datetime not null,
   lastUpdate           datetime not null,
   primary key (id)
);

/*==============================================================*/
/* Table: t_app_role                                            */
/*==============================================================*/
create table t_app_role
(
   id                   int not null auto_increment comment '管理角色标识',
   roleName             varchar(32) not null comment '角色名称',
   createTime           datetime not null comment '角色创建时间',
   lastUpdate           datetime not null comment '角色最近修改时间',
   status               int not null comment '0:禁用,1:启用',
   primary key (id)
);

/*==============================================================*/
/* Table: t_app_role_menu                                       */
/*==============================================================*/
create table t_app_role_menu
(
   id                   int not null auto_increment,
   roleId               int not null comment '角色id',
   menuId               int not null comment '菜单ID',
   primary key (id)
);

/*==============================================================*/
/* Table: t_app_user                                            */
/*==============================================================*/
create table t_app_user
(
   id                   int not null auto_increment,
   userName             varchar(32) not null,
   passWord             varchar(128) not null,
   createTime           datetime not null,
   lastUpdate           datetime not null,
   status               int not null default 1 comment '0:禁用,1:启用',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create index Index_1 on t_app_user
(
   userName
);

/*==============================================================*/
/* Table: t_app_user_role                                       */
/*==============================================================*/
create table t_app_user_role
(
   id                   int not null auto_increment,
   userId               int not null comment '用户id',
   roleId               int not null comment '角色id',
   primary key (id)
);

