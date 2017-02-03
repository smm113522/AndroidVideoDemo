/**
 * Created by Administrator on 2017/1/26.
 */
// dao/userSqlMapping.js
// CRUD SQL语句 v
var user = {
    insert: 'INSERT INTO user(id, name , age , password) VALUES(0,?,?,?)',
    update: 'update user set name=?, age=? ,password=? where id=?',
    delete: 'delete from user where id=?',
    queryById: 'select * from user where id=?',
    queryAll: 'select * from user',
    Login: 'select * from user where name=? and password=?'

};
module.exports = user;
