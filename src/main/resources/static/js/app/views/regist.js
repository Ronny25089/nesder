define(function(require) {

	"use strict";

	var $ = require('jquery'), 
		_ = require('underscore'), 
		Backbone = require('backbone'), 
		tpl = require('text!tpl/Regist.html'),
		models = require('app/models/account'),
		dialogs = require('app/views/dialog'),
		
		template = _.template(tpl);

	return Backbone.View.extend({
		
		el: '#content',
		
		initialize: function(router) {
			this.router = router;
			this.dialog = new dialogs();
		},

		render : function() {
			this.$el.html(template());
			$('input[required]').before('<span style="color:red">*</span>');
			return this;
		},

		events : {
			"change .file-upload" : "readURL",
			"click .upload-button" : "uploadAvatar",
			"submit .form-horizontal" : "regist"
		},

		readURL : function(event) {
			if (event.currentTarget.files && event.currentTarget.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('.profile-pic').attr('src', e.target.result);
				}

				reader.readAsDataURL(event.currentTarget.files[0]);
			}
		},

		uploadAvatar : function(event) {
			$(".file-upload").click();
		},

		regist : function(event) {
			var data = new models.Account({
				account_id: $('#inputAccount_Id').val(),
				password: $('#inputPassword').val(),
				nick_name: $('#inputNick_Name').val(),
				email: $('#inputEmail').val(),
				gender: $('input[name="Gender"]:checked').val(),
				birthday: $('#inputBirthDay').val(),
				introduction: $('#inputIntroduction').val(),
				avatarurl: ""
			});
			var self = this;
			data.save(null, {
				url: '/auth/regist',
                success: function(model, resp, options){
					
                    console.log('model',model);
                    console.log('resp',resp);
                    // index
                    self.router.navigate("login", {trigger: true});
                },
				error: function(model, resp, options){
                	self.dialog.openErrorDialog();
					// index
				}
            });
			
			return false;
		}

	});

});