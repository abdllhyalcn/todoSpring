# todoSpring

This repository includes main properties from Spring Web, Security, PostgreSQL, JPA/Hibernate.

Authentication and authorization layer secured under JWT/Bearer token.

Only "/auth/signin" endpoint is permitted.

Following end-points are;

#Auth operations
POST-> /auth/signin
POST-> /auth/signup

#Todo CRUD operations
POST-> /todo/addTodo
GET-> /todo/getTodos
GET-> /todo/getUserTodos
POST-> todo/updateTodo
DELETE-> todo/deleteTodo

#User operations
GET-> /user/getUsers
DELETE-> /user/deleteUser
POST-> user/updateUser

Server is running at https://todo-spring-apps.herokuapp.com/

Login info:
abdllhyalcn : 12345678 (Admin)
aliveli : 123456789 (Admin)
hacisakir: 12345678 (User)