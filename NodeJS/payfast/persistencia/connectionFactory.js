var mysql  = require('mysql');
require('dotenv').config();

function createDBConnection(){

		return mysql.createConnection({
			
			host: process.env.MYSQL_SERVIDOR,
			port: process.env.MYSQL_PORT,
			user: process.env.MYSQL_USER_PAYFAST,
			password: process.env.MYSQL_PASSWORD_PAYFAST,
			database: 'payfast'
		
		});
}

module.exports = function() {
	return createDBConnection;
}
