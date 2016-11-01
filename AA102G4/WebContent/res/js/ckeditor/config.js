/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.toolbar = 'xxxx';
	config.toolbar_Basic =
		 [
		    [ 'Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Link', 'Unlink' ],
		    [ 'FontSize', 'TextColor', 'BGColor' ],
		    '/',
		    ['ShowBlock','Source']
		 ]; 
		 
	config.toolbar_xxxx =
		 [		  
		  { name: 'styles', items : [ 'Font','FontSize'] },
		  { name: 'colors', items : [ 'TextColor','BGColor' ] },
		  '/',
		  { name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript' ] },
		  { name: 'links', items : [ 'Link','Unlink' ] },
		  { name: 'insert', items : [ 'Table','HorizontalRule','Smiley','SpecialChar','PageBreak' ] },		  
		  '/',
		  { name: 'editing', items : [ 'Find','Replace','-','SelectAll','-','SpellChecker' ] },		  		  
		  { name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-' ] },
		  { name: 'clipboard', items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
		  { name: 'document', items : [ 'Source','-','Preview','Print' ] }
		 ]; 
		
};

