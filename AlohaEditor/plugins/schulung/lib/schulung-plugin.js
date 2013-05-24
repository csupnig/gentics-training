define([
	'aloha/core',
	'aloha/plugin',
	'ui/ui',
	'ui/button',
	'i18n!schulung/nls/i18n',
	'i18n!aloha/nls/i18n',
	'jquery',
	'css!schulung/css/schulung.css'
], function(Aloha, Plugin, Ui,	Button, i18n, i18nCore, jQuery) {
	'use strict';

	/**
	 * We create and return the plugin.
	 */
    return Plugin.create('schulung', {
		
		/**
		 * Configure the available languages
		 */
		languages: ['en', 'de'],

		/**
		 * Initialize the plugin
		 */
		init: function () {
			console.log("Schulung init");
			this.createButtons();
			this.bindListeners();
		},

		/**
		 * Initialize the buttons
		 */
		createButtons: function () {
			var that = this;
			
			this._toggleSchulungButton = Ui.adopt("toggleSchulung", Button, {
				tooltip : i18n.t('button.schulung.tooltip'),
				icon: 'aloha-icon aloha-icon-schulung',
				scope: 'Aloha.continuoustext',
				click : function () { that.buttonClick(); }
			});
			console.log(this._toggleSchulungButton);
		},

		/**
		 * Click handler
		 */
		buttonClick: function() {
			console.log('click');
			var
				markup = jQuery('<div class="schulung">'),
				rangeObject = Aloha.Selection.rangeObject;

			if ( rangeObject.isCollapsed() ) {
						GENTICS.Utils.Dom.extendToWord( rangeObject );
			}
			// add the markup
			GENTICS.Utils.Dom.addMarkup( rangeObject, markup );		
			// select the modified range
			rangeObject.select();

			console.log("Settings", this.settings);
			console.log("Editable settings", this.getEditableConfig( Aloha.activeEditable.obj ));
		},

		/**
		 * Initialize listeners
		 */
		bindListeners: function () {
			var that = this;
			
			Aloha.bind("aloha-editable-activated", function (jEvent, aEvent) {
				console.log("Editable activated", jEvent, aEvent, Aloha.activeEditable.obj);
			});

			Aloha.bind("aloha-smart-content-changed", function () {
				console.log("Smart content changed");
			});

			Aloha.bind('aloha-selection-changed', function (event, range) {
				console.log("Selection changed", event, range);
			});
		}
	});
});