const puppeteer = require("puppeteer");
const newsSchema = require("../schema/news/schema");
const Facade = require("../lib/facade");

exports.scrape = async () => {
  const browser = await puppeteer.launch({
    executablePath: process.env.CHROME_BIN || null,
    args: ["--no-sandbox", "--headless", "--disable-gpu"],
  });
  Promise.all([
    getProvider("Maringá na Hora").then((provider) =>
      getMaringaNaHora(browser, provider).then((res) => createNews(res))
    ),
    getProvider("Maringá Post").then((provider) =>
      getMaringaPost(browser, provider).then((res) => createNews(res))
    ),
    getProvider("André Almenara").then((provider) =>
      getAndreAlmenara(browser, provider).then((res) => createNews(res))
    ),
    getProvider("GMC Online").then((provider) =>
      getGMC(browser, provider).then((res) => createNews(res))
    ),
    getProvider("Plantão Maringá").then((provider) =>
      getPlantaoMaringa(browser, provider).then((res) => createNews(res))
    ),
  ]).then(() => browser.close());
};

const createNews = (news) => {
  const facade = new Facade("News", newsSchema);
  facade.createAll(news);
};

const getProvider = async (dsName) => {
  const facade = new Facade("provider", newsSchema);
  const provider = await facade.findOne({ dsName });
  return provider;
};

const getMaringaNaHora = async (browser, provider) => {
  const page = (await browser.pages())[0];
  page.setDefaultNavigationTimeout(0);
  await page.goto("https://www.maringanahora.com/noticias", {
    waitUntil: "load",
    timeout: 0,
  });

  const result = await page.evaluate((provider) => {
    const noticias = [];
    const website = "https://www.maringanahora.com/";

    document.querySelectorAll("div.col-sm-6").forEach((htmlBody) => {
      noticias.push({
        dsTitle: htmlBody.querySelector(
          "div.post_caption > a.wow.fadeInUp.heding"
        ).textContent,
        dsUrl: website.concat(
          htmlBody
            .querySelector("div.post_caption > a.wow.fadeInUp.heding")
            .getAttribute("href")
            .toString()
        ),
        dsImageUrl: website.concat(
          htmlBody
            .querySelector("div.font_load_post > a > img")
            .getAttribute("src")
            .toString()
        ),
        dsDescription: htmlBody.querySelector(
          "div.font_load_post > .post_caption > a:last-child"
        ).textContent,
        isHighlightedNews: false,
        nrViews: 0,
        provider,
      });
    });
    return noticias;
  }, provider);
  return result;
};

const getMaringaPost = async (browser, provider) => {
  const page = await browser.newPage();
  page.setDefaultNavigationTimeout(0);
  await page.goto("https://maringapost.com.br/", {
    waitUntil: "load",
    timeout: 0,
  });
  const result = await page.evaluate((provider) => {
    const noticias = [];
    document
      .querySelectorAll(
        "div.td_module_flex_1.td_module_wrap.td-animation-stack"
      )
      .forEach((htmlBody) => {
        noticias.push({
          dsTitle: htmlBody.querySelector(
            "div.td-module-meta-info > h3.entry-title > a"
          ).textContent,
          dsUrl: htmlBody
            .querySelector("div.td-module-meta-info > h3.entry-title > a")
            .getAttribute("href")
            .toString(),
          dsImageUrl: htmlBody
            .querySelector("div.td-image-container a span ")
            .getAttribute("data-bg")
            .toString()
            .replace(/(url\(|\)|")/g, ""),
          dsDescription: htmlBody.querySelector(
            "div.td-module-meta-info > div.td-excerpt"
          ).textContent,
          isHighlightedNews: false,
          nrViews: 0,
          provider,
        });
      });
    return noticias;
  }, provider);
  return result;
};

const getAndreAlmenara = async (browser, provider) => {
  const page = await browser.newPage();
  page.setDefaultNavigationTimeout(0);
  await page.goto("https://www.andrealmenara.com.br/", {
    waitUntil: "load",
    timeout: 0,
  });
  const result = await page.evaluate((provider) => {
    const noticias = [];
    document
      .querySelectorAll("div.pp-content-post.pp-content-grid-post")
      .forEach((htmlBody) => {
        noticias.push({
          dsTitle: htmlBody
            .querySelector("meta[itemprop='mainEntityOfPage']")
            .getAttribute("content"),
          dsUrl: htmlBody
            .querySelector("meta[itemprop='mainEntityOfPage']")
            .getAttribute("itemid")
            .toString(),
          dsImageUrl: htmlBody
            .querySelector("div[itemprop='image'] > meta[itemprop='url']")
            .getAttribute("content")
            .toString(),
          dsDescription: null,
          isHighlightedNews: false,
          nrViews: 0,
          provider,
        });
      });
    return noticias;
  }, provider);
  return result;
};

const getPlantaoMaringa = async (browser, provider) => {
  const page = await browser.newPage();
  page.setDefaultNavigationTimeout(0);
  await page.goto("https://www.plantaomaringa.com/", {
    waitUntil: "load",
    timeout: 0,
  });
  const result = await page.evaluate((provider) => {
    const noticias = [];
    document
      .querySelectorAll("div.col-md-12 > div.box_branca")
      .forEach((htmlBody) => {
        noticias.push({
          dsTitle: htmlBody.querySelector("h2.hidden-xs").textContent,
          dsUrl: htmlBody
            .querySelector("div.desc_noticia a")
            .getAttribute("href")
            .toString(),
          dsImageUrl: htmlBody
            .querySelector("img")
            .getAttribute("src")
            .toString(),
          dsDescription: htmlBody.querySelector("div.desc_noticia p")
            .textContent,
          isHighlightedNews: false,
          nrViews: 0,
          provider,
        });
      });
    return noticias;
  }, provider);
  return result;
};

const getGMC = async (browser, provider) => {
  const page = await browser.newPage();
  page.setDefaultNavigationTimeout(0);
  await page.goto("https://gmconline.com.br/editoria/noticias/cidade/", {
    waitUntil: "load",
    timeout: 0,
  });
  const result = await page.evaluate((provider) => {
    const noticias = [];
    document
      .querySelectorAll(".noticias-lista article.noticia")
      .forEach((htmlBody) => {
        noticias.push({
          dsTitle: htmlBody.querySelector(".article-title").textContent,
          dsUrl: htmlBody
            .querySelector(".article-title a")
            .getAttribute("href"),
          dsImageUrl: htmlBody
            .querySelector("img")
            .getAttribute("data-src")
            .toString(),
          dsDescription: htmlBody.querySelector(".article-title").textContent,
          isHighlightedNews: false,
          nrViews: 0,
          provider,
        });
      });
    return noticias;
  }, provider);
  return result;
};
