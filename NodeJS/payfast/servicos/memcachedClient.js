var memcached = require('memcached');

function createMencachedClient(){
    var client = new memcached('192.168.100.174:18080',
          {
            retries:10,
            retry:10000,
            remove:true
          });
    return client;
};

module.exports = function() {
    return createMencachedClient;
}