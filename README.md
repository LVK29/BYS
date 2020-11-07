# BYS
Book Your Show – movie ticket booking backend solution developed on Spring boot project with MySQL as the DB.
The solution is addressed for two types of users, the admins and the customers. Before the customers can register and book shows, the prerequisite is that theater, theater audi, and its seats, movie, movieShow models are to be created by the admins.
Customers have access to user and booking related APIs and the Admins have access to all the APIs.
The Admins have to create, theater, audi, movie, movieShow,showSeats in this order.
The Customers can register, and verified customers can book their showSeats.
All users are authenticated by oauth2
Available MovieShows APIs can be accessed by anyone.
