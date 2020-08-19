var aesPrivateKey = '1234567890ABCDEF';
/**
 * AES 加密
 * @param encryptString 要加密的字符串
 * @returns {string} 加密后的字符串
 */
function aesEncrypt(encryptString) {
	var key = CryptoJS.enc.Utf8.parse(aesPrivateKey);
	var srcs = CryptoJS.enc.Utf8.parse(encryptString);
	var encrypted = CryptoJS.AES.encrypt(srcs, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});
	return encrypted.toString();
}

/**
 * AES 解密
 * @param decryptString 要解密的字符串
 * @returns {string} 解密后的字符串
 */
function aesDecrypt(decryptString) {
	if(decryptString == null){
		return null;
	}
	var key = CryptoJS.enc.Utf8.parse(aesPrivateKey);
	var decrypt = CryptoJS.AES.decrypt(decryptString, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});
	//return CryptoJS.enc.Utf8.stringify(decrypt).toString();
	return decrypt.toString(CryptoJS.enc.Utf8);
}

//星号替换
function replacechar(source, start, end){
	if(source == null){
		return null;
	}
	if(start < 0 || end >= source.length){
		return source;
	}
	if(start > end){
		return source;
	}
	
	var result = '';
	for(var i = 0; i < source.length; i++){
		if(i < start || i >= end){
			result += source[i];
		}
		else if(i >= start && i < end){
			result += "*";
		}
	}
	return result;
}

//显示姓名
function showName(name){
	return replacechar(name, 1, 2);
}

function showMobile(mobile){
	return replacechar(mobile, 3, 7);
}

function showTel(tel){
	return replacechar(tel, 4, 8);
}

function showEmail(email){
	return replacechar(email, 1, 4);
}

function showIDCard(idcard){
	return replacechar(idcard, 3, 16);
}

function showData(data, type){
	if(type == 1){
		return showName(data);
	}
	else if(type == 2){
		return showMobile(data);
	}
	else if(type == 3){
		return showTel(data);
	}
	else if(type == 4){
		return showEmail(data);
	}
	else if(type == 5){
		return showIDCard(data);
	}
	else {
		return data;
	}
}