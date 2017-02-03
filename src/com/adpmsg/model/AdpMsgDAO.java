package com.adpmsg.model;

import java.util.*;
import java.sql.*; 
import javax.naming.Context; 
import javax.naming.InitialContext; 
import javax.naming.NamingException; 
import javax.sql.DataSource; 
/** 
 *表格名稱 : <br>
 *	領養活動留言<br>
 *	英文:adpMsg<br>
 */ 
public class AdpMsgDAO implements AdpMsgDAO_interface{
	private static DataSource ds = null; 
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/AnimalMapDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//====以下是一般指令====
	public static final String INSERT_STMT = "INSERT INTO adpMsg(adpMsg_Id,adp_Id,mem_Id,msg,adpMsgDate,adpMsgadp_upDate ) VALUES  ( adpMsg_seq1.nextval , ? , ? , ? , ? , ?  )  " ; 
	//====以下是更新指令====
	public static final String UPDATE = "UPDATE adpMsg SET msg=?,adpMsgDate=? ,adpMsgadp_upDate=?  WHERE adpMsg_Id=? " ; 
	//====以下是刪除指令====
	public static final String DELETE = "DELETE FROM adpMsg WHERE adpMsg_Id=? " ; 
	//====以下是單筆資料查詢指令====
	public static final String GET_ONE_STMT = "SELECT adpMsg_Id,adp_Id,mem_Id,msg,to_char(adpMsgDate,'yyyy-mm-dd') adpMsgDate,to_char(adpMsgadp_upDate,'yyyy-mm-dd') adpMsgadp_upDate FROM adpmsg WHERE adpMsg_Id=? " ; 
	//====以下是單筆資料查詢指令====
	public static final String GET_ALL_STMT = "SELECT adpMsg_Id,adp_Id,mem_Id,msg,to_char(adpMsgDate,'yyyy-mm-dd') adpMsgDate,to_char(adpMsgadp_upDate,'yyyy-mm-dd') adpMsgadp_upDate FROM adpMsg order by adpMsg_Id " ; 
	//====以下是新增指令====
	private static final String UPDATE_MSG =" UPDATE adpMsg set MSG=?  WHERE adpMsg_Id=? " ; 
	private static final String UPDATE_ADPMSGDATE =" UPDATE adpMsg set ADPMSGDATE=?  WHERE adpMsg_Id=? " ; 
	private static final String UPDATE_ADPMSGADP_UPDATE =" UPDATE adpMsg set ADPMSGADP_UPDATE=?  WHERE adpMsg_Id=? " ; 
	//====以下是改寫insert方法====
	@Override
	public void insert(AdpMsgVO aAdpMsgVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(AdpMsgDAO.INSERT_STMT);
			pstmt.setString (1, aAdpMsgVO.getAdp_Id());
			pstmt.setString (2, aAdpMsgVO.getMem_Id());
			pstmt.setString (3, aAdpMsgVO.getMsg());
			pstmt.setDate (4, aAdpMsgVO.getAdpMsgDate());
			pstmt.setDate (5, aAdpMsgVO.getAdpMsgadp_upDate());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	} 
	//====以下是改寫update方法====
	@Override
	public void update(AdpMsgVO aAdpMsgVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(AdpMsgDAO.UPDATE);
			pstmt.setString (1, aAdpMsgVO.getMsg());
			pstmt.setDate (2, aAdpMsgVO.getAdpMsgDate());
			pstmt.setDate (3, aAdpMsgVO.getAdpMsgadp_upDate());
			pstmt.setString (4, aAdpMsgVO.getAdpMsg_Id());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	} 
	//====以下是改寫delete方法====
	@Override
	public void delete(String  aAdpMsg){
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(AdpMsgDAO.DELETE);
			pstmt.setString (1,aAdpMsg);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	} 
	//====以下是改寫findByPrimaryKey方法====
	@Override
	public AdpMsgVO findByPrimaryKey(String  aPK_NO){
		AdpMsgVO adpmsgVO = null; 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(AdpMsgDAO.GET_ONE_STMT);
			pstmt.setString (1,aPK_NO);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				adpmsgVO = new AdpMsgVO();
				adpmsgVO.setAdpMsg_Id(rs.getString("adpMsg_Id"));
				adpmsgVO.setAdp_Id(rs.getString("adp_Id"));
				adpmsgVO.setMem_Id(rs.getString("mem_Id"));
				adpmsgVO.setMsg(rs.getString("msg"));
				adpmsgVO.setAdpMsgDate(rs.getDate("adpMsgDate"));
				adpmsgVO.setAdpMsgadp_upDate(rs.getDate("adpMsgadp_upDate"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return adpmsgVO; 
	} 

    @Override
    public List<AdpMsgVO> getAll() {
        List<AdpMsgVO> list = new ArrayList<AdpMsgVO>();
        AdpMsgVO adpmsgVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // adpmsgVO 也稱為 Domain objects
                adpmsgVO = new AdpMsgVO();
                adpmsgVO.setAdpMsg_Id(rs.getString("adpMsg_Id"));
                adpmsgVO.setAdp_Id(rs.getString("adp_Id"));
                adpmsgVO.setMem_Id(rs.getString("mem_Id"));
                adpmsgVO.setMsg(rs.getString("msg"));
                adpmsgVO.setAdpMsgDate(rs.getDate("adpMsgDate"));
                adpmsgVO.setAdpMsgadp_upDate(rs.getDate("adpMsgadp_upDate"));

                list.add(adpmsgVO); // Store the row in the vector
            }

            // Handle any driver errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return list;
    }

}