CREATE TABLE blog_post
(
	id bigint auto_increment comment '文章id',
	title varchar(255) NOT NULL comment '文章标题',
	content text NULL comment '文章内容',
	star_num int default 0 NULL comment '点赞数',
	create_id bigint not null comment '发布者',
    update_id bigint not null comment '发布者',
    create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	constraint blog_post_pk
		primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '博客文章表';

CREATE FULLTEXT INDEX ft_index ON blog_post (title,content) WITH PARSER ngram;

CREATE TABLE blog_user
(
	id bigint auto_increment comment '用户id',
	username varchar(255) NOT NULL comment '登录名',
	`password` varchar(255) NOT NULL comment '密码',
	nickname varchar(10) default 0 NULL comment '昵称',
	constraint blog_user_pk
		primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '博客用户表';

CREATE INDEX idx_username_password ON blog_user (username,password);

CREATE TABLE blog_comment
(
	id bigint auto_increment comment '评论id',
	post_id bigint NOT NULL comment '文章ID',
	content text NULL comment '评价内容',
	create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	constraint blog_comment_pk
		primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '博客评论表';