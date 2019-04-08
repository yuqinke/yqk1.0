package arithmetic;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

public class Trust {
	@Test
	//计算置信度
	public Map<String, Double> getTrust() {
		Map<String, Integer> mapSupport = new LinkedHashMap<String, Integer>();
		Map<String, Double> mapTrust = new LinkedHashMap<String, Double>();
		int n = 9;
		mapSupport.put("D1", 6);
		mapSupport.put("E1", 8);
		
		for(String key:mapSupport.keySet()){
			Integer in = mapSupport.get(key);
			double trust = ((double)in/n);
			mapTrust.put(key, trust);
		}
		
		return mapTrust;
		//保留2位小数
	/*	DecimalFormat df = new DecimalFormat("#.00");
		for(String key:mapTrust.keySet()){
			System.out.println(key+"的置信度为"+df.format(mapTrust.get(key))+"%");
		}*/
	}
}
