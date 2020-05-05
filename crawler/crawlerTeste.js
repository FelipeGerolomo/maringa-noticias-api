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
  await page.goto("http://www.gmconline.com.br/noticias/cidade/pag", {
    waitUntil: "load",
    timeout: 0,
  });
  const result = await page.evaluate((provider) => {
    const noticias = [];
    document
      .querySelectorAll("div.mais-noticias > div.row > a")
      .forEach((htmlBody) => {
        noticias.push({
          dsTitle: htmlBody.querySelector(
            ".texto.fc-cinza.col-xs-12.no-padding"
          ).textContent.replace(/^\s+/g, '').trimRight(),
          dsUrl: htmlBody.getAttribute("href").toString(),
          dsImageUrl: htmlBody
            .querySelector("img")
            .getAttribute("src")
            .toString(),
          dsDescription: htmlBody.querySelector(
            ".texto.fc-cinza.col-xs-12.no-padding"
          ).textContent.replace(/^\s+/g, '').trimRight(),
          isHighlightedNews: false,
          provider,
        });
      }, provider);
    return noticias;
  }, provider);
  return result;
};
