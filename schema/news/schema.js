const mongoose = require('mongoose')
const Schema = mongoose.Schema

const newsSchema = new Schema({
  dsTitle: { type: String },
  dsDescription: { type: String },
  dsUrl: { type: String, unique: true },
  dtDatePublished: { type: Date },
  dsImageUrl: { type: String },
  isHighlightedNews : { type: Boolean },
  nrViews : { type: Number },
  provider: 
    { type: Schema.Types.ObjectId, ref: 'Provider' }
}, {timestamps: true})

module.exports = newsSchema
