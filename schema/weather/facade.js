const Facade = require('../../lib/facade')
const weatherSchema = require('./schema')

class WeatherFacade extends Facade {}

module.exports = new WeatherFacade('Weather', weatherSchema)
