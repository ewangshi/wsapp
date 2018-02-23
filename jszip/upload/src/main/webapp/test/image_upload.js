/*
 * Copyright © 2018 ewangshi.xyz version 0.1
 * The MIT License
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

(function(window) {
	var _upload = {upload: upload};
	
	
	function createCanvas(img, width, height) {
		var canvas, ctx;
		width = width || img.width;
		height = height || img.height;
		canvas = document.createElement('canvas');
		var _sp = img.width / img.height;
		var _tp = width / height;
		var w = width;var h = height;
		if((_sp > 1 && _tp < 1) || (_sp < 1 && _tp > 1)) {
			w = height;
			h = width;
		}
		
		canvas.width = w;
		canvas.height = h;
		ctx = canvas.getContext("2d");
		ctx.drawImage(img, 0, 0, w, h);

		return canvas;
	}

	function getBlob(canvas, quality){
	    var data = canvas.toDataURL("image/jpeg", quality);
	    data = data.split(',')[1];
	    data = window.atob(data);
	    var ia = new Uint8Array(data.length);
	    for(var i = 0; i < data.length; i++) {
	        ia[i] = data.charCodeAt(i);
	    }
	    return new Blob([ia], {            
	        type: "image/jpeg"
	    });
	}

	
	/**
	 * file: 图片文件
	 * url: 上传路径
	 * filename: 表单名称
	 * config: {width:宽, height: 高, quality: 质量, onSuccess: 上传成功后回调事件, onSendError: 上传失败(网络)后回调事件, notImage: 选择文件不是图片后回调事件} 
	 */
	function upload(file, url, config) {
		var image = new Image();
		var reader = new FileReader();
		reader.onload = function(){
			var url = reader.result;
	        image.src = url;
		};
		
		reader.readAsDataURL(file);

		image.onload = function() {
			var canvas = createCanvas(image, config.width, config.height);
			var blob = getBlob(canvas, config.quality || 1);

			var fd = new FormData(document.createElement("form"));
			fd.append(config.filename, blob, "upload.jpg");
			
			var xhr;

			if (window.XMLHttpRequest) {
				xhr = new XMLHttpRequest();
			} else {
				xhr = new ActiveXObject("Microsoft.XMLHTTP");
			}

			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					if (config.onSuccess) {
						config.onSuccess(xhr.responseText);
					}
				} else if (xhr.readyState == 4 && xhr.status != 200) {
					if (config.onSendError) {
						config.onSendError(xhr.status, xhr.responseText);
					}
				}
			};

			xhr.open('POST', url);
	        xhr.send(fd);
		}
		
		image.onerror = function() {
			if (config.notImage) {
				config.notImage();
			}
		};
	}
	
	window.ws_image_upload = _upload;
})(window);