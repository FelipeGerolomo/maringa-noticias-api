const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const providersSchema = new Schema(
  {
    dsName: { type: String },
    dsUrl: { type: String },
    dsUrlProviderLabelImage: { type: String },
    dsUrlProviderIconImage: { type: String },
  },
  { timestamps: true }
);

module.exports = providersSchema;
