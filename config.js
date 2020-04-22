const config = {
  environment: process.env.NODE_ENV || 'dev',
  server: {
    port: process.env.PORT || 8080
  },
  mongo: {
    url: process.env.MONGO_DB_URI || 'mongodb://gerolomo:gerolomo5298@maringa-noticias-shard-00-00-y6ngn.gcp.mongodb.net:27017,maringa-noticias-shard-00-01-y6ngn.gcp.mongodb.net:27017,maringa-noticias-shard-00-02-y6ngn.gcp.mongodb.net:27017/maringa-noticias?ssl=true&replicaSet=maringa-noticias-shard-0&authSource=admin&retryWrites=true&w=majority'
  }
}

module.exports = config
