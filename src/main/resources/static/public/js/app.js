/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, { enumerable: true, get: getter });
/******/ 		}
/******/ 	};
/******/
/******/ 	// define __esModule on exports
/******/ 	__webpack_require__.r = function(exports) {
/******/ 		if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 			Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 		}
/******/ 		Object.defineProperty(exports, '__esModule', { value: true });
/******/ 	};
/******/
/******/ 	// create a fake namespace object
/******/ 	// mode & 1: value is a module id, require it
/******/ 	// mode & 2: merge all properties of value into the ns
/******/ 	// mode & 4: return value when already ns object
/******/ 	// mode & 8|1: behave like require
/******/ 	__webpack_require__.t = function(value, mode) {
/******/ 		if(mode & 1) value = __webpack_require__(value);
/******/ 		if(mode & 8) return value;
/******/ 		if((mode & 4) && typeof value === 'object' && value && value.__esModule) return value;
/******/ 		var ns = Object.create(null);
/******/ 		__webpack_require__.r(ns);
/******/ 		Object.defineProperty(ns, 'default', { enumerable: true, value: value });
/******/ 		if(mode & 2 && typeof value != 'string') for(var key in value) __webpack_require__.d(ns, key, function(key) { return value[key]; }.bind(null, key));
/******/ 		return ns;
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "/";
/******/
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 0);
/******/ })
/************************************************************************/
/******/ ({

/***/ "./resources/js/app.js":
/*!*****************************!*\
  !*** ./resources/js/app.js ***!
  \*****************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Theme: Metrica - Responsive Bootstrap 4 Admin Dashboard
 * Author: Mannatthemes
 * Module/App: Main Js
 */
(function ($) {
  'use strict';

  function initSlimscroll() {
    $('.slimscroll').slimscroll({
      height: 'auto',
      position: 'right',
      size: "7px",
      color: '#e0e5f1',
      opacity: 1,
      wheelStep: 5,
      touchScrollStep: 50
    });
  }

  function initMetisMenu() {
    //metis menu
    $("#main_menu_side_nav").metisMenu();
  }

  function initLeftMenuCollapse() {
    // Left menu collapse
    $('.button-menu-mobile').on('click', function (event) {
      event.preventDefault();
      $("body").toggleClass("enlarge-menu");
      initSlimscroll();
    });
  }

  function initEnlarge() {
    if ($(window).width() < 1025) {
      $('body').addClass('enlarge-menu');
    } else {
      if ($('body').data('keep-enlarged') != true) $('body').removeClass('enlarge-menu');
    }
  }

  function initSerach() {
    $('.search-btn').on('click', function () {
      var targetId = $(this).data('target');
      var $searchBar;

      if (targetId) {
        $searchBar = $(targetId);
        $searchBar.toggleClass('open');
      }
    });
  }

  function initMainIconMenu() {
    $('.main-icon-menu .nav-link').on('click', function (e) {
      e.preventDefault();
      $(this).addClass('active');
      $(this).siblings().removeClass('active');
      $('.main-menu-inner').addClass('active');
      var targ = $(this).attr('href');
      $(targ).addClass('active');
      $(targ).siblings().removeClass('active');
    });
  }

  function initTooltipPlugin() {
    $.fn.tooltip && $('[data-toggle="tooltip"]').tooltip();
    $('[data-toggle="tooltip-custom"]').tooltip({
      template: '<div class="tooltip tooltip-custom" role="tooltip"><div class="arrow"></div><div class="tooltip-inner"></div></div>'
    });
  }

  function initActiveMenu() {
    // === following js will activate the menu in left side bar based on url ====
    $(".left-sidenav a").each(function () {
      var pageUrl = window.location.href.split(/[?#]/)[0];

      if (this.href == pageUrl) {
        $(this).addClass("active");
        $(this).parent().parent().addClass("in");
        $(this).parent().parent().addClass("mm-show");
        $(this).parent().parent().prev().addClass("active");
        $(this).parent().parent().parent().addClass("active");
        $(this).parent().parent().parent().addClass("mm-active");
        $(this).parent().parent().parent().parent().addClass("in");
        $(this).parent().parent().parent().parent().parent().addClass("active");
        $(this).parent().parent().parent().parent().parent().parent().addClass("active");
        var menu = $(this).closest('.main-icon-menu-pane').attr('id');
        $("a[href='#" + menu + "']").addClass('active');
      }
    });
  }

  function init() {
    initSlimscroll();
    initMetisMenu();
    initLeftMenuCollapse();
    initEnlarge();
    initSerach();
    initMainIconMenu();
    initTooltipPlugin();
    initActiveMenu();
    Waves.init();
  }

  init();
})(jQuery);

/***/ }),

/***/ "./resources/sass/app.scss":
/*!*********************************!*\
  !*** ./resources/sass/app.scss ***!
  \*********************************/
/*! no static exports found */
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 0:
/*!*************************************************************!*\
  !*** multi ./resources/js/app.js ./resources/sass/app.scss ***!
  \*************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

__webpack_require__(/*! D:\Komputer\my_programs\my_xamp\htdocs\deploy_napa_career\napa_career\resources\js\app.js */"./resources/js/app.js");
module.exports = __webpack_require__(/*! D:\Komputer\my_programs\my_xamp\htdocs\deploy_napa_career\napa_career\resources\sass\app.scss */"./resources/sass/app.scss");


/***/ })

/******/ });