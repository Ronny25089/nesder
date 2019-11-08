require.config({

    baseUrl: 'js/lib',

    paths: {
        app: '../app',
        tpl: '../tpl'
    },

    map: {
        '*': {
            'app/models/employee': 'app/models/memory/employee'
        }
    },

    shim: {
        'backbone': {
            deps: ['underscore', 'jquery'],
            exports: 'Backbone'
        },
        'underscore': {
            exports: '_'
        }
    }
});

require(['jquery', 'backbone', 'app/router'], function ($, Backbone, Router) {
	var App = {
	           Views: {},
	           Routers: {},
	           Models: {},
	           data: {},

	           initialize: function() {
	             this.data.mainRouter = new Router();
	           }
	         };
    var router = new Router();
    Backbone.history.start();
});