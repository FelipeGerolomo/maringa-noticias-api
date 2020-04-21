const express = require("express");
const mongoose = require("mongoose");
const helmet = require("helmet");
const bodyParser = require("body-parser");
const morgan = require("morgan");
const bluebird = require("bluebird");
const schedule = require("node-schedule");

const config = require("./config");
const routes = require("./routes");
const crawler = require("./crawler/crawler");
const weatherService = require("./schema/weather/serviceWeather");

const app = express();

mongoose.Promise = bluebird;
mongoose.connect(config.mongo.url, {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});
mongoose.set("useCreateIndex", true);

app.use(helmet());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(morgan("tiny"));
app.use(express.static("public"));

app.use("/", routes);
app.use("/static", express.static("public"));

app.listen(config.server.port, () => {
  console.log(`Magic happens on port ${config.server.port}`);
});

const updateWeather = schedule.scheduleJob("*/20 * * * *", () => {
  weatherService.WeatherService();
});

const updateNews = schedule.scheduleJob("*/3 * * * *", () => {
  crawler.scrape().then(() => console.log("Finalizado!"));
});

module.exports = app;
