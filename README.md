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

And we have all the books wrote by Tora Kaze below

![image](https://user-images.githubusercontent.com/60205850/230166107-68116778-8181-4192-b5d3-2c51578793c1.png)

And let's say Gary is a big fan of Tora Kaze, so he buys both of his books here, Golden Days and The Light of Carleton

![image](https://user-images.githubusercontent.com/60205850/230166260-bb5920e9-ea3d-47d7-8196-120b40109215.png)

![image](https://user-images.githubusercontent.com/60205850/230166401-377e99d5-ea71-46e3-a614-e36623b008ca.png)

You can even buy more than one books by clicking the increment icon here, say if you want to buy The Light of Carleton for a friend.

Next, once we put both books into the shopping cart, we can view our shopping cart by clicking the shopping cart icon on the nav bar here.

![image](https://user-images.githubusercontent.com/60205850/230166817-f35a868c-28d8-433f-83dd-96fc4e1da77e.png)

And next we can click Proceed to Checkout

![image](https://user-images.githubusercontent.com/60205850/230167177-1bfcc74c-9251-4d84-a331-204cd3fa53d7.png)

And we can see the total cost of our inventory is about 50 dollars. And next, we click on Confirm Checkout, we are taken to the Purchased Books page where we can see a page of confirmation.

![image](https://user-images.githubusercontent.com/60205850/230167717-27d81cb8-780c-4335-8f1b-846119552128.png)

## Jaccard Distance Feature

Next, we will log out, and log back in with a sample account, customer1, where we will buy one of the books by the famous author Tora Kaze, and check out if Jaccard Distance will recommend us the other book also buy Tora Kaze, since customer1's taste will be similar to Gary's taste in books.

![image](https://user-images.githubusercontent.com/60205850/230168347-77c36723-30ab-41d0-a92e-13e29cdb8c42.png)

Now, we will buy 1 of Golden Days, to see if The Light of Carleton will get recommended.

![image](https://user-images.githubusercontent.com/60205850/230168279-59b9038a-6dda-497b-ae7f-9fb40455d036.png)

Next we proceed to checkout and buy the book in our My Cart shopping cart.

![image](https://user-images.githubusercontent.com/60205850/230169188-46ceb835-22d1-49c5-91e0-a0c2078d003e.png)

And volia! The Light of Carleton, a book which customer1 has not bought before, is recommended, since customer1 bought Golden Days, and our other customer, Gary, has also bought Golden Days, and also bought The Light of Carleton, therefore, our system intelligently uses Jaccard Distance to determine our two user's tastes are similar, and recommended The Light of Carleton to this user.

![image](https://user-images.githubusercontent.com/60205850/230169508-e9e12dc3-c1bf-4ede-b238-6a7b7af0f449.png)

# Group Members

- Sabin Plaiasu

- YouHeng Zhou

- Sahil Agrawal

- Muhammad Furqan

- Nikolas Paterson
