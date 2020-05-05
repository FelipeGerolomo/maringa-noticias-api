const Controller = require("../../lib/controller");
const newsFacade = require("./facade");

class NewsController extends Controller {
  findNewsPaginated(req, res, next) {
    const pageOptions = {
      page: parseInt(req.query.page, 10) || 0,
      limit: parseInt(req.query.limit, 10) || 10,
    };

    let providerFilter = {};

    if (req.query.provider) {
      providerFilter = { provider: req.query.provider };
    }

    return newsFacade
      .getModel()
      .find(providerFilter)
      .skip(pageOptions.page * pageOptions.limit)
      .limit(pageOptions.limit)
      .sort({ createdAt: -1 })
      .exec()
      .then((collection) => res.status(200).json(collection))
      .catch((err) => next(err));
  }

  findNewsDsTitleLike(req, res, next) {
    const pageOptions = {
      dsTitle: req.query.dsTitle,
    };

    console.log({ dsTitle: `/${pageOptions.dsTitle}/` });

    return newsFacade
      .getModel()
      .find({ dsTitle: { $regex: `${pageOptions.dsTitle}`, $options: "i" } })
      .limit(5)
      .sort({ createdAt: -1 })
      .exec()
      .then((collection) => res.status(200).json(collection))
      .catch((err) => next(err));
  }

  updateNrViewNews(req, res, next) {
    console.log(true);
    const news = req.body;
    news.nrViews++;
    console.log(news);

    this.facade.updateOne({ _id: req.params.id }, news)
      .then((results) => {
        res.sendStatus(204)
      })
      .catch(err => next(err))
  }
}

module.exports = new NewsController(newsFacade);
