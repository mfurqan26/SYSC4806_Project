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

http://localhost:8080

To see the website from the home page, which is has our login/sign up prompts for vendor and customer workflows

# Entity Relation Diagram (Persistent Entities)

<img src="./docs/ER_Diagram_Milestone3.png">

# UML Diagram
<img src="./docs/Uml_Class_Diagram_M3.png">

# Milestone 3, Final Milestone Feature Set

For milestone 3, the final milestone, we have finished the milestone goals we layed out in milestone 2, which is to finish the customer shopping cart, buy books, checkout, and jaccard distance recommendation system workflows.

We have successfully implemented all of the features along with tests for all the pages and backend repository calls along with CSS styling rework for all of our webpages 

![image](https://user-images.githubusercontent.com/60205850/230150209-9d3dd3e6-81bd-4751-9ca9-cd478a8c405a.png)

Above is our main page for the bookstore, which has a nav bar with three buttons, Customer Login, Vendor Login, and Sign Up that leads to each of our user's usecase workflows.

## Login/Create Account Features

Next, we have kept the Sign Up page features the same from previous milestones, except for the CSS styling changes here, which are overhauled to be consistent throughout our entire website.

![image](https://user-images.githubusercontent.com/60205850/230150927-8289fa92-2e7d-413a-88f1-2d96cd94c8c1.png)

Below, we can still create a new account, and since we did vendor's workflow for milestone 2, and this time for our milestone 3's workflow we are creating customers' workflow, we will create a new customer here, Gary.

![image](https://user-images.githubusercontent.com/60205850/230163640-434512fc-b2ee-4020-b6cf-85a5eea31e71.png)

And we will sign in as Gary below:

## Customer User Features

Next, once we logged in as Gary, we get to see all the books that are in the bookstore below.

![image](https://user-images.githubusercontent.com/60205850/230165021-0bf44a1f-b1e3-4738-abaf-62cdc54132d9.png)

Here, we have filter features

![image](https://user-images.githubusercontent.com/60205850/230165172-150cd7d3-0e01-42f0-b162-70fe6e7a1713.png)

We can filter by publisher, such as O'Reilly

![image](https://user-images.githubusercontent.com/60205850/230165375-37f0b79c-2b81-46ec-94f5-1557dae115e3.png)

And here are the books published by O'Reilly

![image](https://user-images.githubusercontent.com/60205850/230165476-2908c7f1-f2c6-4514-b8fd-30ff7efaa5fe.png)

And we can filter by author, such as Tora Kaze

![image](https://user-images.githubusercontent.com/60205850/230165573-a279a182-3a52-4ef6-9a2d-f232235253c0.png)


## Jaccard Distance Feature

## Vendor User Features












=======
>>>>>>> html-edits

# Milestone 2 use case flow

The Vendor use case we have completed is create a Vendor account, Login, View all Book, Create New Book and Edit Book for books in the database.

On the home page on `localhost:8080`, which double as the sign up page we can create a Vendor Account by entering a username, password and selecting the checkbox that this is a Vendor Account.

![image](https://user-images.githubusercontent.com/60205850/227032312-0c1f661c-e0f9-4a0b-a9d0-1c8836106800.png)

If the Sign Up is succesfully (Non empty username + password And Unique username), then we will redirected to the VendorLogin page.
On the Vendor Login Page we can log in with our newly created Vendor Account.

![image](https://user-images.githubusercontent.com/60205850/226985248-57f80d8b-066f-4dd3-9bda-607b662b6f66.png)

If our login is succeful, we will be redirected to the Vendor main page which will display all the books currently in inventory.

![image](https://user-images.githubusercontent.com/60205850/226985320-e6adc769-ee52-4d8f-a557-2c938f66c4cd.png)

We Can Use the "Create Books" link above to go to the Create Books page where we can create a new Book to add in our inventory.

![image](https://user-images.githubusercontent.com/91328394/226955189-d387958b-11bd-47f6-81a3-b0d821c8bed0.png)

If succesful, we will be redirected to Vendor Home page and see our newly added book Displayed.

![image](https://user-images.githubusercontent.com/91328394/226955450-3bf69e89-aa3f-4dc2-9ae4-f41cc96e3569.png)

We Can Use the "Edit Books: link above to go to the Edit Books page where we can edit a currently existing Book in our Inventory based on isbn and version.

![image](https://user-images.githubusercontent.com/91328394/226955994-9d409020-a6a1-4983-9676-3c39c82bf721.png)

If succesful, the Book will be edited in our inventory

![image](https://user-images.githubusercontent.com/91328394/226956180-33b5e201-7dc0-418f-92a5-00fbcdd180bd.png)

# Milestone 3 Plan

For milestone 3, we want to flesh out the shopper and shopping cart features of the project, as well as have better CSS for all of the webpages.

Also the jaccard distance should be finished.

## phase 1 (sprint 1)

complete shopper view, the customer view which will list all the books

the shopper should have two buttons to increment and decreement the books from the inventory and add it/remove it from the shopping cart

## phase 2 (sprint 1)

complete the shopping cart feature which will display all the books in the shopping cart

the shopping cart should checkout and display the total price

## phase 3 (sprint 2)

complete the jaccard distance feature for our customer and other users to see how similar everyone's code are.

# Group Members

- Sabin Plaiasu

- YouHeng Zhou

- Sahil Agrawal

- Muhammad Furqan

- Nikolas Paterson
