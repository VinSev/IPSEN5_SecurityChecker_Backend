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
    seo text,
    scanid varchar(255) not null,
    xssand_injection integer not null,
    word_press_vulnerability integer not null
);

alter table result owner to postgres;

create unique index result_id_uindex
    on result (id);

create table scan
(
    scan_id text not null
        constraint scan_pk
            primary key,
    phone_number text,
    "declarationOwnership" boolean,
    url text,
    result numeric,
    update_date text,
    declaration_ownership boolean not null,
    scan_result_scanid varchar(255)
        constraint fkct4akvxauljjgf4ghge0xevnw
            references result
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

create table user_gbm_scans
(
    user_email varchar(255) not null
        constraint fkg3stx9qdga7wog3mhgk9bpy5x
            references user_gbm,
    scans_scanid varchar(255) not null
        constraint uk_s1d7kshi7hinntpro0k4uy341
            unique
        constraint fk8b4d1ug4t5q9enedl0tvkt3wv
            references result,
    constraint user_gbm_scans_pkey
        primary key (user_email, scans_scanid)
);

alter table user_gbm_scans owner to postgres;

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

