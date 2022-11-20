#!/usr/bin/env just --justfile

# renovate: datasource=docker depName=github/super-linter
super_linter_version := "slim-v4.9.7@sha256:900277f36d47d5ddc460d901ea9dfcb1d348f7390066f800a0895cd88866b31f" # editorconfig-checker-disable-line

# test change
