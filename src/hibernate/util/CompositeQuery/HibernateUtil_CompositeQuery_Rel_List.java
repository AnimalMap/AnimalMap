/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */
package hibernate.util.CompositeQuery;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import hibernate.util.HibernateUtil;
import java.util.*;
import heibernate_com.rel_list.model.Rel_ListVO;
public class HibernateUtil_CompositeQuery_Rel_List {
	public static Criteria get_aCriteria_For_AnyDB(Criteria query, String columnName,String value) {
		if ("isBlackList".equals(columnName))    //用於varchar
			query.add(Restrictions.like(columnName, "%"+value+"%"));
		if ("isInvited".equals(columnName))    //用於varchar
			query.add(Restrictions.like(columnName, "%"+value+"%"));
		return query;
	}
	public static List<Rel_ListVO> getAllC(Map<String, String[]> map) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<Rel_ListVO> list = null;
		try {
			Criteria query = session.createCriteria(Rel_ListVO.class);
			Set<String> keys = map.keySet();
			int count = 0;
			for (String key : keys) {
				String value = map.get(key)[0];
				if (value!=null && value.trim().length()!=0 && !"action".equals(key)) {
					count++;					
					query = get_aCriteria_For_AnyDB(query, key, value);
					System.out.println("有送出查詢資料的欄位數count = " + count);
				}
			}
			query.addOrder( Order.asc("rel_MemId") );
			list = query.list();
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			throw ex;
		}
		return list;
	}
	public static void main(String argv[]) {
		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("rel_MemId", new String[] { "7001" });	
//		map.put("added_MemId", new String[] { "7001" });	
//		map.put("isBlackList", new String[] { "7001" });	
//		map.put("isInvited", new String[] { "7001" });	

//		map.put("action", new String[] { "getXXX" }); //注意Map裡面會含有action的key
		List<Rel_ListVO> list = getAllC(map);
		for (Rel_ListVO aEmp : list) {
			//System.out.print(aEmp.getRel_MemId() + ",");
			//System.out.print(aEmp.getAdded_MemId() + ",");
			//System.out.print(aEmp.getIsBlackList() + ",");
			//System.out.print(aEmp.getIsInvited() + ",");
			System.out.println();
		}
	}
}
