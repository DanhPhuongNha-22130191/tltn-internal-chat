-- V2__add_deleted_to_accounts.sql

ALTER TABLE accounts ADD COLUMN deleted BOOLEAN NOT NULL DEFAULT FALSE;
