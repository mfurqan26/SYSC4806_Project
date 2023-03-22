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

# Entity Relation Diagram 

<img src="./docs/er_diagram.svg">

# Milestone 2 use case flow

The Vendor use case we have completed is create a Vendor account, Login, View all Book, Create New Book and Edit Book for books in the database.

On the Sign Up Page we can create a Vendor Account by entering a username, password and selecting the checkbox that this is a Vendor Account.

![image](https://user-images.githubusercontent.com/91328394/226953721-cdf0a478-3bf3-4c1d-a507-68f1836452dc.png)

If the Sign Up is succesfully (Non empty username + password And Unique username), then we will redirected to the VendorLogin page.
On the Vendor Login Page we can log in with our newly created Vendor Account.

![image](https://user-images.githubusercontent.com/91328394/226953213-4ed7f4d3-ff41-438b-9f7a-27debfccdfa2.png)

If our login is succeful, we will be redirected to the Vendor main page which will display all the books currently in inventory.

![image](https://user-images.githubusercontent.com/91328394/226954886-5eff5fbb-2b10-4d32-8e54-f87fc66dd667.png)

We Can Use the "Create Books" link above to go to the Create Books page where we can create a new Book to add in our inventory.

![image](https://user-images.githubusercontent.com/91328394/226955189-d387958b-11bd-47f6-81a3-b0d821c8bed0.png)

If succesful, we will be redirected to Vendor Home page and see our newly added book Displayed.

![image](https://user-images.githubusercontent.com/91328394/226955450-3bf69e89-aa3f-4dc2-9ae4-f41cc96e3569.png)

We Can Use the "Edit Books: link above to go to the Edit Books page where we can edit a currently existing Book in our Inventory based on isbn and version.

![image](https://user-images.githubusercontent.com/91328394/226955994-9d409020-a6a1-4983-9676-3c39c82bf721.png)

If succesful, the Book will be edited in our inventory

![image](https://user-images.githubusercontent.com/91328394/226956180-33b5e201-7dc0-418f-92a5-00fbcdd180bd.png)



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

- Sabin Plaiasu

- YouHeng Zhou

- Sahil Agrawal

- Muhammad Furqan

- Nikolas Paterson
