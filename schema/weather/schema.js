const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const weatherSchema = new Schema(
  {
    dsCityName: { type: String },
    nrTemp: { type: Number },
    dsDescription: { type: String },
    dsImageDescription: { type: String },
  },
  { timestamps: true, collection: "weather" }
);

module.exports = weatherSchema;
