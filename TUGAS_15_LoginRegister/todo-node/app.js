const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const uuid = require('uuid');

app.use(bodyParser.urlencoded({ extended: false}));
app.use(bodyParser.json());

const userRoute = require('./src/routes/users-route');
app.use('/', userRoute);

const taskRoute = require('./src/routes/todo-route');
app.use('/', taskRoute);





app.listen(8080, ()=> {
    console.log("Server Berjalan di port 8080");
})
