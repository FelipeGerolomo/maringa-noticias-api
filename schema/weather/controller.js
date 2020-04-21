const Controller = require("../../lib/controller");
const weatherFacade = require("./facade");

class WeatherController extends Controller {
  findLastWeather(req, res, next) {
    return weatherFacade
      .getModel()
      .findOne()
      .sort({ _id: -1 })
      .then((collection) => res.status(200).json(collection))
      .catch((err) => next(err));
  }
}

module.exports = new WeatherController(weatherFacade);
