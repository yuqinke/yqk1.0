package filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import arithmetic.Sunfa2;

//基于用户的协同过滤算法
public class Filter3 {

	public static void main(String[] args) {
		long i = System.currentTimeMillis();

		Map<String, Map<String, Integer>> userPerfMap = new LinkedHashMap<String, Map<String, Integer>>();
		Map<String, Integer> pref1 = new LinkedHashMap<String, Integer>();
		pref1.put("A2", 5);
		pref1.put("B2", 4);
		pref1.put("C2", 2);
		userPerfMap.put("p1", pref1);

		Map<String, Integer> pref2 = new LinkedHashMap<String, Integer>();
		pref2.put("A2", 3);
		pref2.put("B2", 1);
		pref2.put("C2", 1);
		userPerfMap.put("p2", pref2);

		Map<String, Integer> pref3 = new LinkedHashMap<String, Integer>();
		pref3.put("A2", 5);
		pref3.put("B2", 1);
		pref3.put("C2", 4);
		userPerfMap.put("p3", pref3);

		Map<String, Integer> pref4 = new LinkedHashMap<String, Integer>();
		pref4.put("A2", 5);
		pref4.put("B2", 1);
		pref4.put("C2", 5);
		userPerfMap.put("p4", pref4);

		Map<String, Integer> pref5 = new LinkedHashMap<String, Integer>();
		pref5.put("A2", 4);
		pref5.put("B2", 5);
		pref5.put("C2", 2);
		userPerfMap.put("p5", pref5);

		Map<String, Integer> pref6 = new LinkedHashMap<String, Integer>();
		pref6.put("A2", 2);
		pref6.put("B2", 2);
		pref6.put("C2", 4);
		userPerfMap.put("p6", pref6);

		Map<String, Integer> pref7 = new LinkedHashMap<String, Integer>();
		pref7.put("A2", 3);
		pref7.put("B2", 1);
		pref7.put("C2", 3);
		userPerfMap.put("p7", pref7);

		Map<String, Integer> pref8 = new LinkedHashMap<String, Integer>();
		pref8.put("A2", 2);
		pref8.put("B2", 4);
		pref8.put("C2", 1);
		userPerfMap.put("p8", pref8);

		Map<String, Integer> pref9 = new LinkedHashMap<String, Integer>();
		pref9.put("A2", 5);
		pref9.put("B2", 5);
		pref9.put("C2", 2);
		userPerfMap.put("p9", pref9);

		Map<String, Integer> pref10 = new LinkedHashMap<String, Integer>();
		pref10.put("A2", 5);
		pref10.put("B2", 2);
		pref10.put("C2", 4);
		userPerfMap.put("p10", pref10);

		Map<String, Double> simUserSimMap = new LinkedHashMap<String, Double>();
		System.out.println("皮尔逊相关系数:");

		for (Entry<String, Map<String, Integer>> userPerfEn : userPerfMap.entrySet()) {
			String userName = userPerfEn.getKey();
			if (!"p10".equals(userName)) {
				double sim = getUserSimilar(pref5, userPerfEn.getValue());
				System.out.println("p10与" + userName + "之间的相关系数:" + sim);
				// 相关系数大于0.6才计算推荐权值
				if (sim > 0.6) {
					simUserSimMap.put(userName, sim);
				}
			}
		}

		Map<String, Map<String, Integer>> simUserObjMap = new LinkedHashMap<String, Map<String, Integer>>();
		Map<String, Integer> pobjMap1 = new LinkedHashMap<String, Integer>();
		pobjMap1.put("D1", 5);
		pobjMap1.put("E1", 4);
		simUserObjMap.put("p1", pobjMap1);

		Map<String, Integer> pobjMap2 = new LinkedHashMap<String, Integer>();
		pobjMap2.put("D1", 5);
		pobjMap2.put("E1", 2);
		simUserObjMap.put("p2", pobjMap2);

		Map<String, Integer> pobjMap3 = new LinkedHashMap<String, Integer>();
		pobjMap3.put("D1", 0);
		pobjMap3.put("E1", 0);
		simUserObjMap.put("p3", pobjMap3);

		Map<String, Integer> pobjMap4 = new LinkedHashMap<String, Integer>();
		pobjMap4.put("D1", 3);
		pobjMap4.put("E1", 1);
		simUserObjMap.put("p4", pobjMap4);

		Map<String, Integer> pobjMap5 = new LinkedHashMap<String, Integer>();
		pobjMap5.put("D1", 4);
		pobjMap5.put("E1", 4);
		simUserObjMap.put("p5", pobjMap5);

		Map<String, Integer> pobjMap6 = new LinkedHashMap<String, Integer>();
		pobjMap6.put("D1", 0);
		pobjMap6.put("E1", 2);
		simUserObjMap.put("p6", pobjMap6);

		Map<String, Integer> pobjMap7 = new LinkedHashMap<String, Integer>();
		pobjMap7.put("D1", 0);
		pobjMap7.put("E1", 1);
		simUserObjMap.put("p7", pobjMap7);

		Map<String, Integer> pobjMap8 = new LinkedHashMap<String, Integer>();
		pobjMap8.put("D1", 4);
		pobjMap8.put("E1", 2);
		simUserObjMap.put("p8", pobjMap8);

		Map<String, Integer> pobjMap9 = new LinkedHashMap<String, Integer>();
		pobjMap9.put("D1", 2);
		pobjMap9.put("E1", 2);
		simUserObjMap.put("p9", pobjMap9);

		// 存储相关系数>0.6的记录用户 过滤器集合
		Map<String, Map<String, Integer>> simUserObjMap2 = new LinkedHashMap<String, Map<String, Integer>>();

		for (String key1 : simUserSimMap.keySet()) {
			for (String key2 : simUserObjMap.keySet()) {
				if (key1.equals(key2)) {
					simUserObjMap2.put(key2, simUserObjMap.get(key2));
					
				}
			}
		}
		System.out.println("Pearson相关系数>0.6的用户集合"+simUserObjMap2.keySet());//过滤后的集合
		System.out.println("推荐结果:"+ getRecommend(simUserObjMap2, simUserSimMap));

		long j = System.currentTimeMillis();
		System.out.println("程序运行时间：" + (j - i) + "毫秒");
	}

	// Claculate Pearson Correlation Coefficient
	public static double getUserSimilar(Map<String, Integer> pm1,
			Map<String, Integer> pm2) {
		int n = 0;// 数量n
		int sxy = 0;// Σxy=x1*y1+x2*y2+....xn*yn
		int sx = 0;// Σx=x1+x2+....xn
		int sy = 0;// Σy=y1+y2+...yn
		int sx2 = 0;// Σx2=(x1)2+(x2)2+....(xn)2
		int sy2 = 0;// Σy2=(y1)2+(y2)2+....(yn)2
		for (Entry<String, Integer> pme : pm1.entrySet()) {
			String key = pme.getKey();
			Integer x = pme.getValue();
			Integer y = pm2.get(key);
			if (x != null && y != null) {
				n++;
				sxy += x * y;
				sx += x;
				sy += y;
				sx2 += Math.pow(x, 2);
				sy2 += Math.pow(y, 2);
			}
		}
		// p=(Σxy-Σx*Σy/n)/Math.sqrt((Σx2-(Σx)2/n)(Σy2-(Σy)2/n));
		double sd = sxy - sx * sy / n;
		double sm = Math.sqrt((sx2 - Math.pow(sx, 2) / n)
				* (sy2 - Math.pow(sy, 2) / n));
		if (sd / sm > 1) {
			Random r = new Random();
			double p1 = r.nextDouble() / 10 + 0.9;
			return Math.abs(sm == 0 ? 1 : p1);
		} else {
			return Math.abs(sm == 0 ? 1 : sd / sm);
		}
	}

	// 计算加权贡献度
	public static String getRecommend(Map<String, Map<String, Integer>> simUserObjMap,Map<String, Double> simUserSimMap) {
		Map<String, Double> objScoreMap = new LinkedHashMap<String, Double>();//加权贡献度集合
		for (Entry<String, Map<String, Integer>> simUserEn : simUserObjMap.entrySet()) {
			String user = simUserEn.getKey();
			double sim = simUserSimMap.get(user);
			for (Entry<String, Integer> simObjEn : simUserEn.getValue().entrySet()) {
				double objScore = sim * simObjEn.getValue();// 加权（相似度*评分）
				String objName = simObjEn.getKey();
				if (objScoreMap.get(objName) == null) {
					objScoreMap.put(objName, objScore);
				} else {
					double totalScore = objScoreMap.get(objName);
					objScoreMap.put(objName, totalScore + objScore);// 存储加权贡献度
				}
			}
		}
		
		//计算加权推荐值
		Sunfa2 su = new Sunfa2();
		//存储推荐值集合
		Map<String, Double> mapRecom = new LinkedHashMap<String, Double>();//存储加权推荐值
		Map<String, Double> mapTrust = su.getTrust();//获取贡献度集合
		for(Entry<String, Double> sim:objScoreMap.entrySet()){
			String objName = sim.getKey();
			Double objValue = sim.getValue();
			for(Entry<String, Double> tru:mapTrust.entrySet()){
				if(objName.equals(tru.getKey())){
					mapRecom.put(objName, objValue*tru.getValue());
				}
			}
		}
		
		List<Entry<String, Double>> enList = new ArrayList<Entry<String, Double>>(mapRecom.entrySet());
		Collections.sort(enList, new Comparator<Map.Entry<String, Double>>() {// 排序
		public int compare(Map.Entry<String, Double> o1,Map.Entry<String, Double> o2) {
			Double a = o1.getValue() - o2.getValue();
			if (a == 0) {
				return 0;
			} else if (a > 0) {
				return 1;
			} else {
				return -1;
			}
		}
	});
		
		for (Entry<String, Double> entry : enList) {
			System.out.println(entry.getKey() + "的推荐权值:" + entry.getValue());
		}
		return enList.get(enList.size()-1).getKey();// 返回推荐结果
	}
}