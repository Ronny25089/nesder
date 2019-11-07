define(function (require) {

    "use strict";

    var $           = require('jquery'),
        Backbone    = require('backbone'),

        Account = Backbone.Model.extend({

            urlRoot: "/nesder/account"

        }),

        AccountCollection = Backbone.Collection.extend({

            model: Account,

            url: "/nesder/account/all"

        });

    return {
    	Account: Account,
    	AccountCollection: AccountCollection
    };

});