# SYSC4806_Project
Amazin online bookstore

Bookstore Owner can upload and edit amazin.model.Book information (ISBN, picture, description, author, publisher,...) and inventory. 
User can search for, and browse through, the books in the bookstore, sort/filter them based on the above information. 
User can then decide to purchase one or many books by putting them in the Shopping Cart and proceeding to Checkout. 
The purchase itself will obviously be simulated, but purchases cannot exceed the inventory. 
User can also view amazin.model.Book Recommendations based on past purchases. 
This is done by looking for users whose purchases are most similar (using Jaccard distance: Google it!), and then recommending books purchased by those similar users but that the current User hasn't yet purchased.

Steps to Run:
1. Run BookStore.java
2. Enter "http://localhost:8080/landing" in a browser
3. Website displays available books
4. In the form area, enter an isbn and version number of an existing book.
5. Example ISBN: 948-0-123456-47-2 , Version: 1