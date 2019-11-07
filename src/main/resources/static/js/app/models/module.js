define(function (require) {

    "use strict";

    var $           = require('jquery'),
        Backbone    = require('backbone'),

        Module = Backbone.Model.extend({

            urlRoot: "/nesder/module"

        }),

        ModuleCollection = Backbone.Collection.extend({

            model: Module,

            url: "/nesder/module/all"

        });

    return {
    	Module: Module,
    	ModuleCollection: ModuleCollection
    };

});