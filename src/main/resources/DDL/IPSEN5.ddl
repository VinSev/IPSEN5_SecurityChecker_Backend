create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres;

create sequence scan_scan_id_seq;

alter sequence scan_scan_id_seq owner to postgres;

create sequence result_id_seq
	as integer;

alter sequence result_id_seq owner to postgres;

create sequence scan_scan_id_seq1;

alter sequence scan_scan_id_seq1 owner to postgres;

create sequence result_id_sequ;

alter sequence result_id_sequ owner to postgres;

create table user_gbm
(
	email text not null
		constraint user_gbm_pk
			primary key,
	password text,
	role text,
	name text,
	telephone text
);

alter table user_gbm owner to postgres;

create unique index user_gbm_qw_uindex
	on user_gbm (email);

create table role
(
	role_name text not null
		constraint role_pk
			primary key,
	role_description text
);

alter table role owner to postgres;

create table user_role
(
	user_id varchar(255) not null
		constraint fklm2hyknh7a5iq6t2o4ldt8emm
			references user_gbm,
	role_id varchar(255) not null
		constraint fka68196081fvovjhkek5m97n3y
			references role,
	constraint user_role_pkey
		primary key (user_id, role_id)
);

alter table user_role owner to postgres;

create table api_scan
(
	id bigserial
		constraint sub_scan_pkey
			primary key,
	name varchar(255),
	url varchar(255)
);

alter table api_scan owner to postgres;

create table scan_user
(
	id serial
		constraint scan_user_pk
			primary key,
	email text,
	name text,
	telphone text
);

alter table scan_user owner to postgres;

create table scan
(
	scan_id bigint default nextval('scan_scan_id_seq1'::regclass) not null
		constraint scan_pkey
			primary key,
	update_date varchar(255),
	url varchar(255),
	"user" integer
		constraint scan_scan_user_id_fk
			references scan_user
				on update cascade on delete cascade
);

alter table scan owner to postgres;

alter sequence scan_scan_id_seq1 owned by scan.scan_id;

create table result
(
	id bigint default nextval('result_id_sequ'::regclass) not null
		constraint result_pk
			primary key,
	headers text not null,
	xss_and_injection text,
	certificates text,
	wordpress_vulnerability text,
	version_url text,
	login_url text,
	data_security text,
	seo text,
	scanid varchar(255) not null,
	xssand_injection integer not null,
	word_press_vulnerability integer not null,
	scan_scan_id integer not null
		constraint fkc86r9tup03u90nglqtr2kdyjw
			references scan
);

alter table result owner to postgres;

alter sequence result_id_sequ owned by result.id;

create unique index result_id_uindex
	on result (id);

create table scan_category
(
	grade integer not null,
	sub_scan_id bigint not null
		constraint scan_category_pkey
			primary key
		constraint fkrdm4ucbrwxu2bmsnhflncdf78
			references api_scan,
	scan_scan_id bigint not null
		constraint fkfkwljmo2vigwqktdr8tw0nw83
			references scan,
	loading boolean,
	result text
);

alter table scan_category owner to postgres;

