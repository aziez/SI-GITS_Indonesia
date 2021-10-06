const router = require('express').Router();
const { user } = require('../controllers');

router.post('/register', user.addUser);

router.post('/login', user.login);

router.get('/user', user.getUser);

router.get('/user/:id', user.getUserById);

router.post('/user/edit', user.editUser);

router.post('/user/delete', user.deleteUser);

module.exports = router;