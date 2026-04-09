# JDBC setup

This project now includes the MySQL JDBC driver in `AJPSecA/lib/mysql-connector-j-9.6.0.jar`.

## Files

- `src/jdbc/MyDB.java`: JDBC demo that creates a database, creates a table, inserts sample rows, and prints them.
- `jdbc.properties`: local connection settings for your MySQL server.
- `sql/jdbc_demo_setup.sql`: optional SQL script with the same schema/sample data.
- `run-jdbc-demo.cmd`: compiles and runs the JDBC example on Windows.

## Default configuration

The demo expects MySQL on:

- host: `localhost`
- port: `3306`
- database: `ajp_jdbc_demo`
- username: `root`
- password: blank

If your MySQL password is not blank, update `jdbc.properties` before running the program.

## Run

From `AJPSecA`, run:

```bat
run-jdbc-demo.cmd
```
