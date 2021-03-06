package heibernate_com.emg_help.controller;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import heibernate_com.mem.model.MemVO;
import heibernate_com.mem.model.MemService;
import heibernate_com.emg_help.model.*;

@WebServlet(urlPatterns = { "/Heibernate_back-end/emg_help/emg_help_FrontEnd_map_Servlet.do" })
public class Emg_Help_FrontEnd_map_Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			getOne_For_Display(req, res);
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmg_Help.jsp 或  /dept/listEmg_Helps_ByDeptno.jsp 的請求
			getOne_For_Update(req, res);
		}
		if ("update".equals(action)) { // 來自update_emg_help_input.jsp的請求
			update(req, res);
		}
        if ("insert".equals(action)) { // 來自addEmg_Help.jsp的請求  
        	insert(req, res);
		}
		if ("delete".equals(action)) { // 來自listAllEmg_Help.jsp 或  /dept/listEmg_Helps_ByDeptno.jsp的請求
			delete(req, res);
		}
		if ("list_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			list_ByCompositeQuery(req, res,false);//預設複合查詢有Like
		}	
	}
	//===========================================【前端 - list_ByCompositeQuery】================================================ 		
	public void front_end_list_ByCompositeQuery(HttpServletRequest req, HttpServletResponse res,boolean ableLike)throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		try {
			/***************************1.將輸入資料轉為Map**********************************/ 
			//採用Map<String,String[]> getParameterMap()的方法 
			//注意:an immutable java.util.Map 
			//Map<String, String[]> map = req.getParameterMap();
			HttpSession session = req.getSession();
			Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
			if (req.getParameter("whichPage") == null){
				HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
				HashMap<String, String[]> map2 = new HashMap<String, String[]>();
				map2 = (HashMap<String, String[]>)map1.clone();
				session.setAttribute("map",map2);
				map = (HashMap<String, String[]>)req.getParameterMap();
			} 
			/***************************2.開始複合查詢***************************************/
			Emg_HelpService emg_helpSvc = new Emg_HelpService();
			List<Emg_HelpVO> list  = emg_helpSvc.getAll(map,ableLike);
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("listEmg_Helps_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
		}
	}	
	public void list_ByCompositeQuery(HttpServletRequest req, HttpServletResponse res,boolean ableLike)throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		try {
			/***************************1.將輸入資料轉為Map**********************************/ 
			//採用Map<String,String[]> getParameterMap()的方法 
			//注意:an immutable java.util.Map 
			//Map<String, String[]> map = req.getParameterMap();
			HttpSession session = req.getSession();
			Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
			if (req.getParameter("whichPage") == null){
				HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
				HashMap<String, String[]> map2 = new HashMap<String, String[]>();
				map2 = (HashMap<String, String[]>)map1.clone();
				session.setAttribute("map",map2);
				map = (HashMap<String, String[]>)req.getParameterMap();
			} 
			/***************************2.開始複合查詢***************************************/
			Emg_HelpService emg_helpSvc = new Emg_HelpService();
			List<Emg_HelpVO> list  = emg_helpSvc.getAll(map,ableLike);
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("listEmg_Helps_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
			RequestDispatcher successView = req.getRequestDispatcher("/Heibernate_back-end/emg_help/listEmg_Helps_ByCompositeQuery.jsp"); // 成功轉交listEmg_Helps_ByCompositeQuery.jsp
			successView.forward(req, res);
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/Heibernate_back-end/emg_help/select_page.jsp");
			failureView.forward(req, res);
		}
	}
	public void getOne_For_Display(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = req.getParameter("emg_H_Id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入求救編號編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Heibernate_back-end/emg_help/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			String emg_H_Id = null;
			try {
				emg_H_Id = new String(str);
			} catch (Exception e) {
				errorMsgs.add("求救編號編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Heibernate_back-end/emg_help/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			/***************************2.開始查詢資料*****************************************/
			Emg_HelpService emg_helpSvc = new Emg_HelpService();
			Emg_HelpVO emg_helpVO = emg_helpSvc.getOneEmg_Help(emg_H_Id);
			if (emg_helpVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Heibernate_back-end/emg_help/select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("emg_helpVO", emg_helpVO); // 資料庫取出的emg_helpVO物件,存入req
			String url = "/Heibernate_back-end/emg_help/listOneEmg_Help.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmg_Help.jsp
			successView.forward(req, res);
			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/Heibernate_back-end/emg_help/select_page.jsp");
			failureView.forward(req, res);
		}		
	}
	public void getOne_For_Update(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁path: 可能為【/emg_help/listAllEmg_Help.jsp】 或  【/dept/listEmg_Helps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】		
		try {
			/***************************1.接收請求參數****************************************/
			String emg_H_Id = new String(req.getParameter("emg_H_Id"));
			/***************************2.開始查詢資料****************************************/
			Emg_HelpService emg_helpSvc = new Emg_HelpService();
			Emg_HelpVO emg_helpVO = emg_helpSvc.getOneEmg_Help(emg_H_Id);
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("emg_helpVO", emg_helpVO); // 資料庫取出的emg_helpVO物件,存入req
			String url = "/Heibernate_back-end/emg_help/update_emg_help_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emg_help_input.jsp
			successView.forward(req, res);
			/***************************其他可能的錯誤處理************************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher(requestURL);
			failureView.forward(req, res);
		}		
	}
	public void update(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁path
		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			//==== getParameter設定 ====
				String emg_H_Id = req.getParameter("emg_H_Id").trim();
				String mem_Id = req.getParameter("mem_Id").trim();
				java.sql.Timestamp emg_H_start_date = null;
				try {
					emg_H_start_date = java.sql.Timestamp.valueOf(req.getParameter("emg_H_start_date").trim());
				} catch (IllegalArgumentException e) {
					emg_H_start_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				java.sql.Timestamp emg_H_end_date = null;
				try {
					emg_H_end_date = java.sql.Timestamp.valueOf(req.getParameter("emg_H_end_date").trim());
				} catch (IllegalArgumentException e) {
					emg_H_end_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				String emg_H_title = req.getParameter("emg_H_title").trim();
				String emg_H_content = req.getParameter("emg_H_content").trim();
				byte[] emg_H_Pic = null;
				try {
					Part part = req.getPart("emg_H_Pic");
					InputStream in = part.getInputStream();
					emg_H_Pic = new byte[part.getInputStream().available()];
					in.read(emg_H_Pic);
					in.close();
				} catch (Exception e) {
					emg_H_Pic = null;
					//errorMsgs.add("照片請上傳照片.");
				}
				String emg_H_Pic_format = req.getParameter("emg_H_Pic_format").trim();
				String emg_H_city = req.getParameter("emg_H_city").trim();
				String emg_H_town = req.getParameter("emg_H_town").trim();
				String emg_H_road = req.getParameter("emg_H_road").trim();
				Double emg_H_Lon = null;
				try {
					emg_H_Lon = new Double(req.getParameter("emg_H_Lon").trim());
				} catch (NumberFormatException e) {
					emg_H_Lon = 0.0;
					errorMsgs.add("緊急求救經度座標請填數字.");
				}
				Double emg_H_Lat = null;
				try {
					emg_H_Lat = new Double(req.getParameter("emg_H_Lat").trim());
				} catch (NumberFormatException e) {
					emg_H_Lat = 0.0;
					errorMsgs.add("緊急求救緯度座標請填數字.");
				}
				String emg_H_status = req.getParameter("emg_H_status").trim();
			//==== VO設定部分 ====			
				Emg_HelpVO emg_helpVO = new Emg_HelpVO();
				emg_helpVO.setEmg_H_Id(emg_H_Id);
				//以下3行程式碼因為要配合Hibernate的emg_helpVO,以能夠使用Hibernate的強大功能,所以這裏顯得比較麻煩!!
				MemVO memVO = new MemVO();
				memVO.setMem_Id(mem_Id);
				emg_helpVO.setMemVO(memVO);
				emg_helpVO.setEmg_H_start_date(emg_H_start_date);
				emg_helpVO.setEmg_H_end_date(emg_H_end_date);
				emg_helpVO.setEmg_H_title(emg_H_title);
				emg_helpVO.setEmg_H_content(emg_H_content);
				emg_helpVO.setEmg_H_Pic(emg_H_Pic);
				emg_helpVO.setEmg_H_Pic_format(emg_H_Pic_format);
				emg_helpVO.setEmg_H_city(emg_H_city);
				emg_helpVO.setEmg_H_town(emg_H_town);
				emg_helpVO.setEmg_H_road(emg_H_road);
				emg_helpVO.setEmg_H_Lon(emg_H_Lon);
				emg_helpVO.setEmg_H_Lat(emg_H_Lat);
				emg_helpVO.setEmg_H_status(emg_H_status);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("emg_helpVO", emg_helpVO); // 含有輸入格式錯誤的emg_helpVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Heibernate_back-end/emg_help/update_emg_help_input.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			/***************************2.開始修改資料*****************************************/
			Emg_HelpService emg_helpSvc = new Emg_HelpService();
			emg_helpVO = emg_helpSvc.updateEmg_Help(
					emg_H_Id
					,mem_Id
					,emg_H_start_date
					,emg_H_end_date
					,emg_H_title
					,emg_H_content
					,emg_H_Pic
					,emg_H_Pic_format
					,emg_H_city
					,emg_H_town
					,emg_H_road
					,emg_H_Lon
					,emg_H_Lat
					,emg_H_status
			);
			/***************************3.修改完成,準備轉交(Send the Success view)*************/				
			//if(requestURL.equals("/Heibernate_back-end/emg_help/listEmg_Helps_ByMem_Id.jsp") 
				//|| requestURL.equals("/Heibernate_back-end/emg_help/listAllEmg_Help.jsp")){
				//req.setAttribute("listEmg_Helps_ByMem_Id",emg_helpSvc.getEmg_HelpsByMem_Id(mem_Id)); // 資料庫取出的list物件,存入request
			//}
			//if(requestURL.equals("/Heibernate_back-end/emg_help/listEmg_Helps_ByCompositeQuery.jsp")){
				//HttpSession session = req.getSession();
				//Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				//List<Emg_HelpVO> list  = emg_helpSvc.getAll(map);
				//req.setAttribute("listEmg_Helps_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
			//}
			String url = requestURL;
			RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
			successView.forward(req, res);
			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/Heibernate_back-end/emg_help/update_emg_help_input.jsp");
			failureView.forward(req, res);
		}
	}
	public void insert(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		try {
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
               String mem_Id = req.getParameter("mem_Id").trim();	
               java.sql.Timestamp emg_H_start_date = null;
               try {
                   emg_H_start_date = java.sql.Timestamp.valueOf(req.getParameter("emg_H_start_date").trim());
               } catch (IllegalArgumentException e) {
                   emg_H_start_date=new java.sql.Timestamp(System.currentTimeMillis());
                   errorMsgs.add("請輸入日期!");
               }
               java.sql.Timestamp emg_H_end_date = null;
               try {
                   emg_H_end_date = java.sql.Timestamp.valueOf(req.getParameter("emg_H_end_date").trim());
               } catch (IllegalArgumentException e) {
                   emg_H_end_date=new java.sql.Timestamp(System.currentTimeMillis());
                   errorMsgs.add("請輸入日期!");
               }
               String emg_H_title = req.getParameter("emg_H_title").trim();	
               String emg_H_content = req.getParameter("emg_H_content").trim();	
               byte[] emg_H_Pic = null;
               try {
                   Part part = req.getPart("emg_H_Pic");
                   InputStream in = part.getInputStream();
                   emg_H_Pic = new byte[part.getInputStream().available()];
                   in.read(emg_H_Pic);
                   in.close();
               } catch (Exception e) {
                   //errorMsgs.add("照片請上傳照片.");
                   //e.printStackTrace();
                   emg_H_Pic = null;
               }	
               String emg_H_Pic_format = req.getParameter("emg_H_Pic_format").trim();	
               String emg_H_city = req.getParameter("emg_H_city").trim();	
               String emg_H_town = req.getParameter("emg_H_town").trim();	
               String emg_H_road = req.getParameter("emg_H_road").trim();	
               Double emg_H_Lon = null;
               try {
                   emg_H_Lon = new Double(req.getParameter("emg_H_Lon").trim());
               } catch (NumberFormatException e) {
                   emg_H_Lon = 0.0;
                   errorMsgs.add("緊急求救經度座標請填數字.");
                   e.printStackTrace();
               }
               Double emg_H_Lat = null;
               try {
                   emg_H_Lat = new Double(req.getParameter("emg_H_Lat").trim());
               } catch (NumberFormatException e) {
                   emg_H_Lat = 0.0;
                   errorMsgs.add("緊急求救緯度座標請填數字.");
                   e.printStackTrace();
               }
               String emg_H_status = req.getParameter("emg_H_status").trim();	
               Emg_HelpVO emg_helpVO = new Emg_HelpVO();
				//以下3行程式碼因為要配合Hibernate的emg_helpVO,以能夠使用Hibernate的強大功能,所以這裏顯得比較麻煩!!
				MemVO memVO = new MemVO();
				memVO.setMem_Id(mem_Id);
				emg_helpVO.setMemVO(memVO);
				emg_helpVO.setEmg_H_start_date(emg_H_start_date);
				emg_helpVO.setEmg_H_end_date(emg_H_end_date);
				emg_helpVO.setEmg_H_title(emg_H_title);
				emg_helpVO.setEmg_H_content(emg_H_content);
				emg_helpVO.setEmg_H_Pic(emg_H_Pic);
				emg_helpVO.setEmg_H_Pic_format(emg_H_Pic_format);
				emg_helpVO.setEmg_H_city(emg_H_city);
				emg_helpVO.setEmg_H_town(emg_H_town);
				emg_helpVO.setEmg_H_road(emg_H_road);
				emg_helpVO.setEmg_H_Lon(emg_H_Lon);
				emg_helpVO.setEmg_H_Lat(emg_H_Lat);
				emg_helpVO.setEmg_H_status(emg_H_status);
               // Send the use back to the form, if there were errors
               if (!errorMsgs.isEmpty()) {
                   req.setAttribute("emg_helpVO", emg_helpVO); // 含有輸入格式錯誤的emg_helpVO物件,也存入req
                   RequestDispatcher failureView = req.getRequestDispatcher("/Heibernate_back-end/emg_help/addEmg_Help.jsp");
                   failureView.forward(req, res);
                   return;
               }
               /***************************2.開始新增資料***************************************/
               Emg_HelpService emg_helpSvc = new Emg_HelpService();
               emg_helpVO = emg_helpSvc.addEmg_Help(
               	mem_Id
               	,emg_H_start_date
               	,emg_H_end_date
               	,emg_H_title
               	,emg_H_content
               	,emg_H_Pic
               	,emg_H_Pic_format
               	,emg_H_city
               	,emg_H_town
               	,emg_H_road
               	,emg_H_Lon
               	,emg_H_Lat
               	,emg_H_status
               ); 
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/Heibernate_back-end/emg_help/listAllEmg_Help.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmg_Help.jsp
			successView.forward(req, res);				
		/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/Heibernate_back-end/emg_help/addEmg_Help.jsp");
			failureView.forward(req, res);
		}
	}
	public void delete(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁path: 可能為【/emg_help/listAllEmg_Help.jsp】 或  【/dept/listEmg_Helps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
		try {
			/***************************1.接收請求參數***************************************/
			String emg_H_Id = new String(req.getParameter("emg_H_Id"));
			/***************************2.開始刪除資料***************************************/
			Emg_HelpService emg_helpSvc = new Emg_HelpService();
			Emg_HelpVO emg_helpVO = emg_helpSvc.getOneEmg_Help(emg_H_Id);
			emg_helpSvc.deleteEmg_Help(emg_H_Id);
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/
			MemService memSvc = new MemService();
			if(requestURL.equals("/mem/listEmg_Helps_ByMem_Id.jsp") || requestURL.equals("/mem/listAllMem.jsp")){
			  //req.setAttribute("listEmg_Helps_ByMem_Id",memSvc.getEmg_HelpsByMem_Id(emg_helpVO.getMem_Id())); // 資料庫取出的list物件,存入request
			  //req.setAttribute("listEmg_Helps_ByMem_Id",memSvc.getEmg_HelpsByMem_Id(emg_helpVO.getMemVO().getMem_Id())); // 資料庫取出的list物件,存入request
			}
			String url = requestURL;
			RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("刪除資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher(requestURL);
			failureView.forward(req, res);
		}		
	}
}
