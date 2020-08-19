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
	var key = CryptoJS.enc.Utf8.parse(aesPrivateKey);
	var decrypt = CryptoJS.AES.decrypt(decryptString, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});
	//return CryptoJS.enc.Utf8.stringify(decrypt).toString();
	return decrypt.toString(CryptoJS.enc.Utf8);
}