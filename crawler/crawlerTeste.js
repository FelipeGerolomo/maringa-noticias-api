const puppeteer = require("puppeteer");
const newsSchema = require("../schema/news/schema");
const Facade = require("../lib/facade");

exports.scrape = async () => {
  const browser = await puppeteer.launch({
    headless: false,
    executablePath: process.env.CHROME_BIN || null,
    args: ["--no-sandbox", "--disable-gpu"],
  });
  getGMC(browser).then((res) => console.log(res));
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
          dsUrl: htmlBody.querySelector(".article-title a").getAttribute("href"),
          dsImageUrl: htmlBody
          .querySelector("img")
          .getAttribute("data-src")
          .toString(),
          dsDescription: htmlBody.querySelector(".article-title").textContent,
          isHighlightedNews: false,
          nrViews: 0,
          provider,
        });
      }, provider);
    return noticias;
  }, provider);
  return result;
};
