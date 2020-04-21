const axios = require("axios");
const Facade = require("../../lib/facade");
const weatherSchema = require("./schema");

exports.WeatherService = async () => {
  axios
    .get("https://api.hgbrasil.com/weather?woeid=12580572&key=676b4a08")
    .then((response) => {
      const weather = response.data.results;
      const weatherPayload = {
        dsCityName: weather.city,
        nrTemp: weather.temp,
        dsDescription: weather.description,
        dsImageDescription: weather.img_id,
      };
      createWeather(weatherPayload);
    })
    .catch((error) => {
      console.log(error);
    });
};

const createWeather = (weather) => {
  const facade = new Facade("Weather", weatherSchema);
  facade.create(weather);
};
