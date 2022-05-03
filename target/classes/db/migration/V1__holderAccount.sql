create table if not exists holder_account
(
    holder_account_id uuid default gen_random_uuid() primary key,
    number            varchar,
    agency_number     varchar,
    balance numeric(8,2)
);

create table if not exists holder
(
    holder_id         uuid default gen_random_uuid() primary key,
    holder_name       varchar,
    cpf               varchar,
    email             varchar,
    holder_account_id uuid references holder_account
);