define([
	'aloha/plugin',
	'i18n!schulung/nls/i18n',
	'i18n!aloha/nls/i18n',
	'jquery',
	'css!schulung/css/schulung.css'
], function(Plugin, i18n, i18nCore, jQuery) {
	'use strict';

	var Aloha = window.Aloha;
	
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
		}

		/**
		 * Initialize the buttons
		 */
		createButtons: function () {
			var that = this;
			
			this._toggleSchulungButton = Ui.adopt("toggleSchulung", ToggleButton, {
				tooltip : i18n.t('button.schulung.tooltip'),
				icon: 'aloha-icon aloha-icon-schulung',
				scope: 'Aloha.continuoustext',
				click : function () { console.log('click'); }
			});
		}
	});
});