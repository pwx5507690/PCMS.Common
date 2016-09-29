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
            name: _elmTarget.name.val(),
            value: _elmTarget.value.val(),
            type: _language
        };
        if (!data.name) {
            _elmTarget.name.css("border-color","red");
            return;
        }
        if (!data.value) {
            _elmTarget.value.css("border-color","red");
            return;
        }
        var method = _containsKeyBylanguageData(data.name) ? "update" : "add";
        _showLoading();
        _elmTarget.value.css("border-color","#BDC4C9");
        _elmTarget.name.css("border-color","#BDC4C9");
        console.log(_server + "?method=" + method + "&type=" + _language);
        jQuery.post(_server + "?method=" + method + "&type=" + _language, data).done(function (result) {
            console.log(result);
            _closeLoading();
        }).error(function () {
            _closeLoading();
        });
    };

    var _containsKeyBylanguageData = function (name) {
        for (var i = 0; i < _cache["languageData"]; i++) {
            var item = _cache["languageData"][i];
            if (item["name"] === name) {
                return true;
            }
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
        _language = "zh";
    };

    var _createLanguageList = function (data) {
        if (data === undefined || data.length === 0) {
            return;
        }
        var temp = [];
        for (var i = 0; i < data.length; i++) {
            var item = data[i];
            temp.push('<tr><td class="text-center"><div class="checkbox margin-t-0">');
            temp.push('<input data-id="',item["name"],'" type="checkbox"><label for="',item["name"],'"></label></div></td>');
            temp.push('<td>',item["name"],'</td><td>',item["value"],'</td></tr>');
        }
        _elmTarget.langugeContent.html(temp.join(''));
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
                .on("click", "#sub", _submit)
                .on("click", "#languageType", function () {
                    _language = $(this).val();
                });
    };

    var _initLanguageList = function () {
        jQuery.get(_server + "?method=initLanguge&type=" + _language).done(function (result) {
            if (result) {
                _cache = JSON.parse(result);
                _createLanguageType(_cache["language"]);
                _createLanguageList(_cache["languageData"]);
            }
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

