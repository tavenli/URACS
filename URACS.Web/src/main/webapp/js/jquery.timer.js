/*!
 * jQuery Timer Plugin
 * https://github.com/ajgon/jquery-timer
 *
 * Copyright 2012, Igor Rzegocki
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.opensource.org/licenses/GPL-2.0
 *
 */
/*jslint browser: true, nomen: true, white: true */
/*properties
    '#LIST#', _activate, _finish, _options, _passedTime, _pausedInterval,
    _startTime, _status, _timerFunction, _timerID, extend, finishCallback,
    getTime, indexOf, interval, kill, match, pause, pauseCallback, prototype,
    push, resume, resumeCallback, splice, start, startCallback, status, stop,
    stopCallback, timeout, timer, timerCallback, useSetTimeout
*/

var jQuery = typeof(jQuery) === 'undefined' ? null : jQuery;

(function($) {

    "use strict";

    if($ === null) {
        throw 'jQuery is not defined';
    }

    var Timer = function(options) {

        var defaults = {
            timerCallback:  function() {},
            startCallback:  function() {},
            pauseCallback:  function() {},
            resumeCallback: function() {},
            stopCallback:   function() {},
            finishCallback: function() {},
            interval:       1000,
            timeout:        false,
            useSetTimeout:  false
        };

        options.interval = parseInt(options.interval, 10);
        options.interval = isNaN(options.interval) ? defaults.interval : options.interval * 1000;
        options.timeout = parseInt(options.timeout, 10);
        options.timeout = isNaN(options.timeout) ? defaults.timeout : options.timeout * 1000;
        this._options = $.extend(defaults, options);

        this._timerFunction = options.useSetTimeout ? 'Timeout' : 'Interval';
        this._timerID = null;
        this._startTime = 0;
        this._pausedInterval = -1;
        this._passedTime = 0;
        this._status = 'stopped';
    };

    Timer.prototype = {
        _activate: function() {
            var self = this;
            this._startTime = new Date().getTime();
            this._timerID = window['set' + this._timerFunction](function() {
                self._passedTime = (new Date().getTime() - self._startTime);
                if(self._options.timeout && self._passedTime >= self._options.timeout) {
                    self._finish();
                } else {
                    self._options.timerCallback();
                }
            }, this._options.interval);
        },

        _finish: function() {
            window['clear' + this._timerFunction](this._timerID);
            this._startTime = 0;
            this._pausedInterval = -1;
            this._passedTime = 0;
            this._timerID = null;
            this._options.finishCallback();
            this._status = 'finished';
        },

        start: function() {
            if(this._startTime !== 0) {
                throw 'Timer is running';
            }
            this._activate();
            this._options.startCallback();
            this._status = 'running';
        },

        pause: function() {
            if(this._startTime === 0 || this._timerID === null) {
                throw 'Timer not started';
            }
            window['clear' + this._timerFunction](this._timerID);
            this._passedTime = (new Date().getTime() - this._startTime);
            this._pausedInterval = this._passedTime % this._options.interval;
            this._timerID = null;
            this._options.pauseCallback();
            this._status = 'paused';
        },

        resume: function(fullFrame) {
            fullFrame = typeof(fullFrame) === 'undefined' ? false : fullFrame;
            if(this._pausedInterval === -1) {
                throw 'Timer not paused';
            }
            if(fullFrame) {
                var self = this;
                setTimeout(function() {
                    self._options.timerCallback();
                    if(!self._options.useSetTimeout) {
                        self._activate();
                    }
                }, this._options.interval - this._pausedInterval);
            } else {
                this._activate();
            }
            this._options.resumeCallback();
            this._status = 'running';
        },

        stop: function() {
            if(this._startTime === 0 || this._timerID === null) {
                throw 'Timer not started';
            }
            this._finish();
            this._options.stopCallback();
            this._status = 'stopped';
        },

        kill: function() {
            if(this._timerID !== null) {
                window['clear' + this._timerFunction](this._timerID);
                this._startTime = 0;
                this._pausedInterval = -1;
                this._timerID = null;
                this._status = 'stopped';
            }
        },

        status: function() {
            return this._status;
        }
    };

    $.timer = function(name, callback, interval, options) {
        if(typeof(name) === 'undefined') {
            return $.timer['#LIST#'];
        }
        if(typeof(name) !== 'string' || !name.match(/^[a-zA-Z][a-zA-Z_0-9]*$/)) {
            throw 'Invalid timer name (it must start with a letter and cannot contain other characters than letters, numbers and underscore';
        }
        if(typeof(callback) === 'undefined') {
            return $.timer[name];
        }
        if(callback === null) {
            if($.timer[name]) {
                $.timer[name].kill();
                delete($.timer[name]);
                var list_index = $.timer['#LIST#'].indexOf(name);
                if(list_index > -1) {
                    $.timer['#LIST#'].splice(list_index, 1);
                }
            }
            return true;
        }
        if(typeof(callback) !== 'function') {
            throw 'Please provide a callback function for setTimer/setInterval function';
        }
        interval = parseInt(interval, 10);
        if(isNaN(interval) || interval <= 0) {
            throw 'Please provide an interval for timer (in seconds)';
        }

        options = options || {};

        options.timerCallback = callback;
        options.interval = interval;
        $.timer[name] = new Timer(options);
        $.timer['#LIST#'].push(name);

        return $.timer[name];
    };

    $.timer['#LIST#'] = [];

}(jQuery));
