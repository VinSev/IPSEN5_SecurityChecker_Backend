-- auto-generated definition
-- we don't know how to generate root <with-no-name> (class Root) :(
create table result
(
    id text not null
        constraint result_pk
            primary key,
    headers text not null,
    xss_and_injection text,
    certificates text,
    wordpress_vulnerability text,
    version_url text,
    login_url text,
    data_security text,
    seo text
);

alter table result owner to postgres;

create unique index result_id_uindex
    on result (id);

create table scan
(
    scan_id numeric not null
        constraint scan_pk
            primary key,
    phone_number text,
    "declarationOwnership" boolean,
    url text,
    scan_result numeric,
    update_date text
);

alter table scan owner to postgres;

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

