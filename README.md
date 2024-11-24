Hey there! This is my implementation of a Java app that talks to PostgreSQL using JDBC. 

🎯 What This Project Does

Nothing too fancy, but it gets the job done! 

It can:

Pull all users from the database into nice Java objects

Update user info when they need to change their details

Find a specific user when you know their ID

🚀 Getting Started

First things first, grab the code:
git clone https://github.com/smallchungus/LyssnProject.git

cd LyssnProject

📋 Prerequisites

You'll need these installed on your machine:

Java (>17 or recent version will do)
Maven (for handling dependencies)
PostgreSQL (I'm using version 14, but others should work too)

🔧 Setting Things Up

Get those Maven dependencies sorted:
mvn clean install

Set up your database:

Fire up PostgreSQL
Create our users table by running this SQL:

CREATE TABLE users (
userid BIGINT PRIMARY KEY,
fname TEXT NOT NULL,
name TEXT NOT NULL,
signupdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

Quick thing - update the database connection in DatabaseHelper.java with your details:

private static final String URL = "jdbc:postgresql://localhost:5433/mydb";
private static final String USER = "your-username";    // change these!
private static final String PASSWORD = "your-password";


🏃‍♂️ Running the App

Package everything up:

mvn clean package

Let it rip!

mvn exec:java


🎉 What You Should See

If everything's working, you'll get something like this:

Testing database connection...
Connected successfully!

Querying all users...
User{userid=1, fname='John', name='Doe', signupdate=2024-11-24 10:00:00.0}
User{userid=2, fname='Jane', name='Smith', signupdate=2024-11-24 10:05:00.0}
User{userid=3, fname='Alice', name='Johnson', signupdate=2024-11-24 10:10:00.0}

Getting user with ID 1...
User{userid=1, fname='John', name='Doe', signupdate=2024-11-24 10:00:00.0}

Updating user with ID 1...
Update successful: true

Getting updated user with ID 1...
User{userid=1, fname='Johnny', name='Doe', signupdate=2024-11-24 10:00:00.0}