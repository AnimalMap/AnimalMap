package heibernate_com.joinlist.model;
/*
 Hibernate is providing a factory.getCurrentSession() method for retrieving the current session. A
 new session is opened for the first time of calling this method, and closed when the transaction is
 finished, no matter commit or rollback. But what does it mean by the “current session”? We need to
 tell Hibernate that it should be the session bound with the current thread.
 <hibernate-configuration>
 <session-factory>
 ...
 <property name="current_session_context_class">thread</property>
 ...
 </session-factory>
 </hibernate-configuration>
 */
import org.hibernate.*;
import hibernate.util.HibernateUtil;
import java.util.*;
import heibernate_com.pet_group.model.Pet_groupVO;
public class JoinListDAO implements JoinList_interface {
	private static final String GET_ALL_STMT = "from JoinListVO order by joinList_GrpId";
	@Override
	public void insert(JoinListVO joinlistVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(joinlistVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	@Override
	public void update(JoinListVO joinlistVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(joinlistVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	@Override
	public void delete(String joinList_GrpId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
//        【此時多方(宜)可採用HQL刪除】
//			Query query = session.createQuery("delete JoinListVO where joinList_GrpId=?");
//			query.setParameter(0, joinList_GrpId);
//			//System.out.println("刪除的筆數=" + query.executeUpdate());
//        【或此時多方(也)可採用去除關聯關係後，再刪除的方式】
			JoinListVO joinlistVO = new JoinListVO();
			Pet_groupVO pet_groupVO = new Pet_groupVO();
			pet_groupVO.setGrp_Id(joinList_GrpId);
			joinlistVO.setPet_groupVO(pet_groupVO);
			session.delete(joinlistVO);
//        【此時多方不可(不宜)採用cascade聯級刪除】
//        【多方joinlist2.hbm.xml如果設為 cascade="all"或 cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除】
//			JoinListVO joinlistVO = (JoinListVO) session.get(JoinListVO.class, joinList_GrpId);
//			session.delete(joinlistVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	@Override
	public JoinListVO findByPrimaryKey(String joinList_GrpId) {
		JoinListVO joinlistVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			joinlistVO = (JoinListVO) session.get(JoinListVO.class, joinList_GrpId);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return joinlistVO;
	}
	@Override
	public List<JoinListVO> getAll() {
		List<JoinListVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
}
