define(function(require) {

	"use strict";

	var $ = require('jquery'), 
		_ = require('underscore'), 
		Backbone = require('backbone'), 
		tpl = require('text!tpl/Dialog.html'),
		
		template = _.template(tpl);

	return Backbone.View.extend({

		el : '#content',

		initialize : function(router) {
			this.router = router;
			this.render();
		},

		render : function() {
			this.$el.html(template());
			return this;
		},

		events : {

		},

		openErrorDialog : function(title, msg, toPage) {
			console.log('error')
			$('#errorModal').fadeIn();
			return false;
		}

	});

});