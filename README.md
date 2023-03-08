# SYSC4806_Project
Amazin online bookstore

# Project Requirement

Bookstore Owner can upload and edit amazin.model.Book information (ISBN, picture, description, author, publisher,...) and inventory. 
User can search for, and browse through, the books in the bookstore, sort/filter them based on the above information. 
User can then decide to purchase one or many books by putting them in the Shopping Cart and proceeding to Checkout. 
The purchase itself will obviously be simulated, but purchases cannot exceed the inventory. 
User can also view amazin.model.Book Recommendations based on past purchases. 
This is done by looking for users whose purchases are most similar (using Jaccard distance: Google it!), and then recommending books purchased by those similar users but that the current User hasn't yet purchased.

# How To Run

Run the main method in BookStore.java or

Run maven life cycle package in IntelliJ and then

Run `proj2-1.0-SNAPSHOT.jar` in the /target output folder to run the program using the generated jar file

And visit:

http://localhost:8080/landing

To see the mock landing page we have right now

# Milestone 1 use case flow

The 1 use case we have completed is to query for books in the database.

On the landing page, we can enter the book isbn such as `978-0-122453-12-1`

And then we can enter `1` as the version

![image](https://user-images.githubusercontent.com/60205850/223818456-ab4f3417-ff75-4593-baa8-471ead14638c.png)

And we will be taken to a page where we have the book object from the BookRepository in JSON data form

![image](https://user-images.githubusercontent.com/60205850/223817964-b267d204-4533-49f8-ae9d-353e917ea1fc.png)

# Milestone 1 Plan

For milestone 1, we want to complete the ground work of the project, that is the schemas for the book object and the Spring backend.

## Phase 1

complete Book, BookController, BookRepository

complete github action files for cicd

complete sample test suite with some simple tests

## phase 2

complete FrontController and Main method to run the program by initializing 2 Books

## phase 3

complete landing page thymeleaf html

# Group Members

YouHeng Zhou
Sahil Agrawal
Muhammad Furqan
Sabin Plaiasu
Nikolas Paterson
