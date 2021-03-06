package com.template.busi.safe;

import java.io.UnsupportedEncodingException;

public class ParseUtil {

	/**将二进制转换成16进制 
     * @param buf 
     * @return 
     */  
    public static String parseByte2HexStr(byte buf[]) {  
            StringBuffer sb = new StringBuffer();  
            for (int i = 0; i < buf.length; i++) {  
                    String hex = Integer.toHexString(buf[i] & 0xFF);  
                    if (hex.length() == 1) {  
                            hex = '0' + hex;  
                    }  
                    sb.append(hex.toUpperCase());  
            }  
            return sb.toString();  
    } 
    
    /**将16进制转换为二进制 
     * @param hexStr 
     * @return 
     */  
    public static byte[] parseHexStr2Byte(String hexStr) {  
            if (hexStr.length() < 1)  
                    return null;  
            byte[] result = new byte[hexStr.length()/2];  
            for (int i = 0;i< hexStr.length()/2; i++) {  
                    int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
                    int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
                    result[i] = (byte) (high * 16 + low);  
            }  
            return result;  
    }
    
    public static void main(String[] args) {
    	String s = "2827E4B6502D8710F4C63FA68A0E7A152D8972E3EF5541A46DDE8B3A62A549C1B093F0A10875FEBDE5B17F4F918A6F10CAA2EA622595552DEE59C9627930D8AAE3535B937469326E18AA84865BAD3DC6C1A7DA64988D2A81F1A429AD691BEC3C662D26E88914ED474507E583978013FB39E843499D82596B2618CD689FA015E1201CDC81DB346AAE41EC83FB19ABED536E48A1FE92964DABD22B02545450128F5FB427A463A372F788658E5867448A03EE1DCAA4FC961F876DD0AFD069A75A73EBFF54828C099FA915A530221B8DFCFA79F23DF449882ECC6212DF7BC60BE0614D31D7AB141699CFCCC4A03576FED38D858B28511956AEA35353EDCB665F5271D9D935D6E2468296DF5963EDDF6BAC5FABC0661D7469B83BA323A18B86B275DFF84E92A932F09456D32F6AF176EC9D9F0C350500201E46CEC205EB4D51FAFBC092B275896F59931EBAE053C2E8B1CE77BF4DCA8E9F6CBF81456E2AD2F3DB0E212F697F5264A17D7E9A9613EC93B411CDB13B390ACC56650E9492DF610561045B273B10A6A80E83D1D88FFE7E5CB6D2C2B77133B8D304412723AC0F1F26A92FB370EAED837121783CAD3D7EF825151B8D5C12816097DBB2C19346D397B0939E45E1728D11FB067C3CBA2A4748BAF8C435220CFBEA669916F87FEB510BC5187CBD839FDD6B3403C6004D18F7D65BFF580ED4F445153D890BBDFCB9F8CE455CDE0F9101C2CD8AA615FA83B88C413057454832A96F35AF8F05F750ADB59D5A5D7C5FA3A4B5E218921D843B47DFA6163495E51EC686D6D1F16493768169A1826D919D98EB0231B64C2609114348FCA76B4BCBB6411A9FC5E28939D3EDB77BF67509171407B061137932142C62CA99BB78C3B1056A5607154FDCEBE9D2A4746525F18A5D5E9E84E0AF241838F3761F35853D56F1F0D381471896F120936CDBB5F379F3014F50EF00613DDAC5CBEEF7C0710F865D7EC50F9FFBD1548373BA65DD1678DEBB6D3AB42F7610745C5E6A223BDF60D9988B5263E816F7D1477561E803950C2576A21816EF7AF95E24E1FFABA92BABA8634257707D628B8FE532F344E156FA59CA0A021C1F4762189C368574A3E74E782EA9DD50EAC9F9EDB8070E26AB88240BCDBBFF64FF036E4B8DF7AE89C796FBDD9B37AC163BF30B3B9AD80495FBF67ED3D6B212488DA437F4";
    	byte[] data = ParseUtil.parseHexStr2Byte(s);
    	try {
			System.out.println(new String(data, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
