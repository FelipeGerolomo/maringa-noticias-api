const Router = require('express').Router;
const router = new Router();

const news = require('./schema/news/router');
const provider = require('./schema/provider/router');
const weather = require('./schema/weather/router');

router.route('/').get((req, res) => {
  res.json({ message: 'Welcome to maringa-noticias-api API!' });
})

router.use('/news', news);
router.use('/provider', provider);
router.use('/weather', weather);

module.exports = router
