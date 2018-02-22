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
					if (config.onError) {
						config.onError(xhr.status, xhr.responseText);
					}
				}
			};

			xhr.open('POST', url);
	        xhr.send(fd);
		}
		
		image.onerror = function() {
			if (config.notImage) {
				config.notImage(xhr.responseText);
			}
		};
	}
	
	window.ws_image_upload = _upload;
})(window);

$(document).ready(function() {
	$("#btn").on('click',function(){
		$("#upload").trigger('click');
	});
	
	
	$("#upload").on("change", function() {
		var file = document.getElementById("upload").files[0];
		var url = "http://127.0.0.1:8080/upload/upload.json";
		ws_image_upload.upload(file, url, {width:1240,height:1754,quality:0.7, filename:'file',onSuccess:function(e){alert(e);},onError:function(e){alert(e);}});
	});
});