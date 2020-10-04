define(function(require) {

    "use strict";

    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone'),
        tpl = require('text!tpl/Dialog.html'),

        template = _.template(tpl);

    return Backbone.View.extend({

        el: '#dialog_content',

        initialize: function(router) {
            this.router = router;
        },

        render: function() {
            this.$el.append(template());
            // this.$el.html(template());
            return this;
        },

        events: {
            "click button.btn.btn-danger": "closeError"

        },

        openErrorDialog: function(title, msg, toPage) {
            console.log('error')
            $('#errorModal').fadeIn();
            return false;
        },

        closeError: function() {
            $('#errorModal').fadeOut();
            return false;
        }

    });

});