const MongoClient = require("mongodb").MongoClient;
const { ObjectId } = require("mongodb");
const assert = require('assert');
/*
 * Requires the MongoDB Node.js Driver
 * https://mongodb.github.io/node-mongodb-native
 */

const filter = {
  provider: new ObjectId("5e8295ad5e48285700695e52"),
};
const skip = 1;
const limit = 10;

MongoClient.connect(
  "mongodb://localhost/maringa-noticias",
  { useNewUrlParser: true, useUnifiedTopology: true },
  function (connectErr, client) {
    assert.equal(null, connectErr);
    const coll = client.db("maringa-noticias").collection("news");
    const result = coll.find(filter, { skip: skip, limit: limit }).toArray();
    setTimeout(() => {
        console.log(result);
    }, 2000);
    
    //client.close();
  }
);
