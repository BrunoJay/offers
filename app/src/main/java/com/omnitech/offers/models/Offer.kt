package com.omnitech.offers.models

class Offer {

    var id: String
    var url: String
    var name: String
    var description: String
    var terms: String
    var currentValue: String

    constructor(id: String, url: String, name: String, description: String, terms: String, currentValue: String) {
        this.id = id
        this.url = url
        this.name = name
        this.description = description
        this.terms = terms
        this.currentValue = currentValue
    }
}
