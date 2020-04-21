const Facade = require('../../lib/facade')
const providerSchema = require('./schema')

class ProviderFacade extends Facade {}

module.exports = new ProviderFacade('Provider', providerSchema)
