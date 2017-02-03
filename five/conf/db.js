/**
 * Created by Administrator on 2017/1/26.
 */
// conf/db.js
// MySQL数据库联接配置
module.exports = {
    mysql: {
        host: '127.0.0.1',
        user: 'root',
        password: 'root',
        database: 'node', // 前面建的user表位于这个数据库中
        port: 3306
    }
};