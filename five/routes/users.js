var express = require('express');
var router = express.Router();

var userDao = require('../dao/userDao');

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.send('respond with a resource');
});

// 增加用户
router.get('/addUser', function (req, res, next) {
    userDao.add(req, res, next);
});
// 查询所有用户
router.get('/queryAllUser', function (req, res, next) {
    userDao.queryAll(req, res, next);
});
// 修改用户
router.get('/editUser',function (reg, res, next) {
    userDao.update(reg, res, next)
})
//删除用户
router.get('/delUser',function (reg, res, next) {
    userDao.delete(reg, res, next)
})
//登录
router.get('/userLogin',function (reg, res, next) {
    userDao.userLogin(reg, res, next)
})

module.exports = router;
