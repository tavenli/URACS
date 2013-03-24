var pilicat = {};
pilicat.alternately = function(table_id) {
	$('.' + table_id + ' tr:odd').addClass('odd');
	$('.' + table_id + ' tr:even').addClass('even');
	$('.' + table_id + ' tr').hover(function() {
		$(this).addClass('activity');
	}, function() {
		$(this).removeClass('activity');
	});
};
pilicat.title2div = function(css) {
	var title;
	$('.' + css)
			.hover(
					function() {
						title = this.title;
						this.title = '';
						$(this).css('cursor', 'pointer');
						$('body').append(
								'<div id="titlediv_jq" class="title_div rounded">'
										+ title + '</div>');
						var win_width = document.documentElement.clientWidth ? document.documentElement.clientWidth
								: (window.innerWidth ? window.innerWidth
										: (document.body.clientWidth ? document.body.clientWidth
												: 1024));
						var width = $('#titlediv_jq').width();
						var x = $(this).offset().left;
						var left = x < (win_width / 2) ? x + $(this).width()
								: x - width;
						var top = $(this).offset().top + $(this).height();
						$('#titlediv_jq').css({
							'top' : top + "px",
							"left" : left + "px",
							"opacity" : '0'
						});
						$('#titlediv_jq').animate({
							opacity : '0.9'
						}, 600);
					}, function() {
						this.title = title;
						$('#titlediv_jq').remove();
					});
};
pilicat.keysubmit = function(form_id, button_id, detect_func) {
	$('#' + button_id).click(function() {
		if (eval(detect_func) === true)
			$('#' + form_id).submit();
	});
	$(document).bind('keydown', function(e) {
		e = window.event || e;
		if (e.ctrlKey && e.keyCode == 13) {
			if (eval(detect_func) === true)
				$('#' + form_id).submit();
		}
	});
};
pilicat.select_all = function(form) {
	for ( var i = 0; i < form.elements.length; i++) {
		var e = form.elements[i];
		if (e.Name != "allChkbox" && e.disabled != true && e.type == 'checkbox') {
			e.checked = form.allChkbox.checked;
		}
	}
};
pilicat.acsubmit = function(form_id, button_id, msg, action, is_change) {
	if (!is_change) {
		$('#' + button_id).click(function() {
			if (confirm(msg)) {
				$('#action').val(action);
				$('#' + form_id).submit();
			}
		});
	} else {
		$('#' + button_id).change(function() {
			if (confirm(msg)) {
				$('#action').val(action);
				$('#' + form_id).submit();
			}
		});
	}
};
pilicat.setcookie = function(name, value, seconds, path, domain) {
	var expires = new Date();
	expires.setTime(expires.getTime() + seconds * 1000);
	document.cookie = escape(name)
			+ '='
			+ escape(value)
			+ (typeof (seconds) != 'undefined' ? '; expires='
					+ expires.toGMTString() : '')
			+ (typeof (path) != 'undefined' ? '; path=' + path : '; path=/')
			+ (typeof (domain) != 'undefined' ? '; domain=' + domain : '');
}
pilicat.getcookie = function(name) {
	var cookie_start = document.cookie.indexOf(name);
	var cookie_end = document.cookie.indexOf(";", cookie_start);
	return cookie_start == -1 ? '' : unescape(document.cookie.substring(
			cookie_start + name.length + 1,
			(cookie_end > cookie_start ? cookie_end : document.cookie.length)));
}
pilicat.switchbar = function() {
	if (pilicat.getcookie('ucenter_left_menu') != 'hide') {
		$('#frame_side').animate({
			'left' : '-230px'
		}, 600);
		$('body').animate({
			'background-position' : '-230px'
		}, 600);
		$('#body_box').animate({
			'margin-left' : '0px'
		}, 600);
		$('#switch').html('打开菜单');
		pilicat.setcookie('ucenter_left_menu', 'hide', 86400 * 365);
	} else {
		$('#frame_side').animate({
			'left' : '0'
		}, 600);
		$('body').animate({
			'background-position' : '0px'
		}, 600);
		$('#body_box').animate({
			'margin-left' : '230px'
		}, 600);
		$('#switch').html('关闭菜单');
		pilicat.setcookie('ucenter_left_menu', 'show', 86400 * 365);
	}
}
pilicat.topmenu = function(button, element) {
	var oTime;
	$(button).click(function() {
		$(element).slideDown('fast');
	});
	$(element).hover(function() {
		window.clearTimeout(oTime);
	}, function() {
		oTime = window.setTimeout(function() {
			$(element).fadeOut(300);
		}, 300);
	});
	$(button).mouseout(function() {
		oTime = window.setTimeout(function() {
			$(element).fadeOut(300);
		}, 300);
	});
};
pilicat.top_error = function(msg) {
	scroll(0, 0);
	$('.top_error span').html(msg);
	$('.top_error').css({
		'opacity' : '1'
	});
	$('.top_error').slideDown(400);
	return false;
};
pilicat.option = function() {
	$('.advanced_button').click(
			function() {
				$('.advanced_button').css('background-image',
						'url(images/switch_bg.png)');
				$('.basic_button').css('background', 'none');
				$('#basic').fadeOut(600);
				$('#advanced').fadeIn(600);
			});
	$('.basic_button').click(
			function() {
				$('.basic_button').css('background-image',
						'url(images/switch_bg.png)');
				$('.advanced_button').css('background', 'none');
				$('#advanced').fadeOut(600);
				$('#basic').fadeIn(600);
			});
}
pilicat.menu = function() {
	$('#menu ol ul').hide();
	if (pilicat.getcookie('ucenter_left_menu') != 'hide') {
		if ($('#menu ol #current').length > 0) {
			$('#menu ol #current').parent().parent().slideToggle('normal');
			$('#menu ol #current').parents('ol').find('a').first().addClass(
					'item_current');
			$('#menu ol #current').parent().addClass('current');
		} else {
			$('#menu ol ul').eq(0).slideToggle('normal');
		}
		$('#frame_side').css('left', '0px');
		$('#body_box').css('margin-left', '230px');
		$('#switch').html('关闭菜单');
	} else {
		$('#frame_side').css('left', '-230px');
		$('#body_box').css('margin-left', '0px');
		$('#switch').html('打开菜单');
		$('body').css('background-position', '-230px');
	}
	$('#menu .item').click(function() {
		$(this).parent().siblings().find('ul').slideUp('normal');
		$(this).next().slideToggle('normal');
		return false;
	});
	$('#menu .item').hover(function() {
		$(this).stop().animate({
			'padding-right' : '30px'
		}, 300);
	}, function() {
		$(this).stop().animate({
			'padding-right' : '10px'
		}, 300);
	});
	pilicat.topmenu('#lang_button', '.header #lang_menu');
	pilicat.topmenu('#menu_option', '.header .options');
}
pilicat.ip = function(ip, id, lang) {
	if (lang == 0 || lang == pilicat.lang) {
		$.ajax({
			url : '../api',
			type : 'GET',
			data : 'a=ip&ip=' + ip,
			dataType : 'text',
			async : true,
			success : function(data) {
				isNaN(data) && $('#' + id).html(data);
			}
		});
	}
}
var lang = pilicat.getcookie('global_language');
pilicat.lang = (isNaN(lang) || lang == '') ? 1 : lang;
$(function() {
	$('.attention ol,.information ol,.success ol,.error ol').click(function() {
		$(this).parent().fadeTo(400, 0, function() {
			$(this).slideUp(400);
		});
		return false;
	});
});


jQuery.stringLimit = function(content,limit){ 
    var contentLen = content.length;
	if(contentLen > limit){ 
		return content.substring(0,limit) + "...";
	}else{
		return content;
	}
};


jQuery.fn.check = function() {
	return this.each(function() {
		this.checked = true;
	});
};

jQuery.fn.uncheck = function() {
	return this.each(function() {
		this.checked = false;
	});
};

jQuery.fn.toggleCheck = function() {
	return this.each(function() {
		this.checked = !this.checked;
	});
};

/*
 * parseDate('2010-1-1') return new Date(2010,0,1) 4 parseDate(' 2010-1-1 ')
 * return new Date(2010,0,1) 5 parseDate('2010-1-1 15:14:16') return new
 * Date(2010,0,1,15,14,16) 6 parseDate(' 2010-1-1 15:14:16 ') return new
 * Date(2010,0,1,15,14,16); 7 parseDate('2010-1-1 15:14:16.254') return new
 * Date(2010,0,1,15,14,16,254) 8 parseDate(' 2010-1-1 15:14:16.254 ') return new
 * Date(2010,0,1,15,14,16,254) 9 parseDate('不正确的格式') retrun null 10
 */
function parseDate(str) {
	if (typeof str == 'string') {
		var results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) *$/);
		if (results && results.length > 3) {
			return new Date(parseInt(results[1], 10),
					(parseInt(results[2], 10) - 1), parseInt(results[3], 10));
		}
		results = str
				.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2}) *$/);
		if (results && results.length > 6)
			return new Date(parseInt(results[1], 10),
					parseInt(results[2], 10) - 1, parseInt(results[3], 10),
					parseInt(results[4], 10), parseInt(results[5], 10),
					parseInt(results[6], 10));
		results = str
				.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2})\.(\d{1,9}) *$/);
		if (results && results.length > 7)
			return new Date(parseInt(results[1], 10),
					parseInt(results[2], 10) - 1, parseInt(results[3], 10),
					parseInt(results[4], 10), parseInt(results[5], 10),
					parseInt(results[6], 10), parseInt(results[7], 10));
	}
	return null;
}

/*
 * 将Date/String类型,解析为String类型. 传入String类型,则先解析为Date类型 不正确的Date,返回 ''
 * 如果时间部分为0,则忽略,只返回日期部分.
 */
function formatDate2(v) {
	if (typeof v == 'string')
		v = parseDate(v);
	if (v instanceof Date) {
		var y = v.getFullYear();
		var m = v.getMonth() + 1;
		var d = v.getDate();
		var h = v.getHours();
		var i = v.getMinutes();
		var s = v.getSeconds();
		var ms = v.getMilliseconds();
		if (ms > 0)
			return y + '-' + m + '-' + d + ' ' + h + ':' + i + ':' + s + '.'
					+ ms;
		if (h > 0 || i > 0 || s > 0)
			return y + '-' + m + '-' + d + ' ' + h + ':' + i + ':' + s;
		return y + '-' + m + '-' + d;
	}
	return '';
}

function formatDate(v, format) {
	if (format == null || typeof (format) == "undefined") {
		format = "yyyy-MM-dd hh:mm:ss";
	}
	if (typeof v == 'string')
		v = parseDate(v);
	if (v instanceof Date) {
		var o = {
			"M+" : v.getMonth() + 1,
			"d+" : v.getDate(),
			"h+" : v.getHours(),
			"m+" : v.getMinutes(),
			"s+" : v.getSeconds(),
			"q+" : Math.floor((v.getMonth() + 3) / 3),
			"S" : v.getMilliseconds()
		}

		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (v.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}

		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	}
	return '';
}