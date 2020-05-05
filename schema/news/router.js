const controller = require('./controller')
const Router = require('express').Router
const router = new Router()

router.route('/')
  .get((...args) => controller.find(...args))
  .post((...args) => controller.create(...args))

router.route('/pageable')
  .get((...args) => controller.findNewsPaginated(...args))

router.route('/like')
  .get((...args) => controller.findNewsDsTitleLike(...args))

router.route('/:id')
  .put((...args) => controller.updateOne(...args))
  .get((...args) => controller.findById(...args))
  .delete((...args) => controller.remove(...args))

router.route('/updateNrViewNews')
  .post((...args) => controller.updateNrViewNews(...args))

module.exports = router
