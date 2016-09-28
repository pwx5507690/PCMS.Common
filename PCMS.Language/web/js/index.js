/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (window, jQuery) {
    "use strict";

    var _server = "Server";

    var _language;

    var _cache;

    var _elmStr = {
        "name": "name",
        "value": "value",
        "submit": "sub",
        "langugeContent": "langugeContent",
        "languageType": "languageType",
        "loading": "loading"
    };

    var _elmTarget = null;

    var _setElmTarget = function () {
        _elmTarget = new Object();
        for (var key in _elmStr) {
            _elmTarget[key] = jQuery("#" + _elmStr[key]);
        }
    };

    var _submit = function () {
        var data = {
            name: _elmStr.name.value,
            value: _elmStr.value.value,
            type: _language
        };
        jQuery.post(_server, data).done(function () {

        });
    };

    var _containsKeyBylanguageData = function () {
        for (var i = 0; i < _cache["languageData"]; i++) {
            var item =  _cache["languageData"][i];
        }
        return false;
    };

    var _closeLoading = function (callback) {
        if (!_elmTarget.loading.is(":hidden")) {
            _elmTarget.loading.hide(300, callback);
        }
    };

    var _showLoading = function () {
        if (_elmTarget.loading.is(":hidden")) {
            _elmTarget.loading.show();
        }
    };

    var _setLanguageType = function () {
        _language = "zh_cn";
    };

    var _createLanguageList = function () {
        _elmTarget.langugeContent.html();
    };

    var _createLanguageType = function (data) {
        if (data === undefined || data.length === 0) {
            return;
        }

        var temp = [];
        for (var i = 0; i < data.length; i++) {
            var item = data[i];
            temp.push('<option value="', item["name"], '">', item["describing"], '</option>');
        }
        _elmTarget.languageType.html(temp.join(''));
    };

    var _event = function () {
        jQuery(document)
                .on("body", "#sub", _submit)
                .on("body", "#languageType", function () {
                    _language = $(this).val();
                });
    };

    var _initLanguageList = function () {
        jQuery.get(_server + "?method=init&type=zh").done(function (result) {
            _cache = JSON.parse(result);
            _createLanguageType(_cache["language"]);
            _createLanguageList(_cache["languageData"]);
            _closeLoading();
        }).error(function () {
            _closeLoading();
        });
    };

    var _init = function () {
        _setElmTarget();
        _showLoading();
        _setLanguageType();
        _initLanguageList();
        _event();
    };

    jQuery(function () {
        _init();
    });
})(window, $);

