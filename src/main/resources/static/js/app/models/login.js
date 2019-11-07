define(function (require) {

    "use strict";

    var $           = require('jquery'),
        Backbone    = require('backbone'),

        Account = Backbone.Model.extend({

            urlRoot: "/auth/account"

        })


    return {
    	Account: Account
    };

});