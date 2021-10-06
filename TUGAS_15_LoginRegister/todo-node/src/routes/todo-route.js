const router = require('express').Router();
const { todo } = require('../controllers');

router.post('/todo/add', todo.addTodo);

router.get('/todo', todo.getTodo);

router.get('/todo/:id', todo.getTodoById);

router.post('/todo/edit', todo.editTodo);

router.post('/todo/delete', todo.deleteTodo);

module.exports = router;