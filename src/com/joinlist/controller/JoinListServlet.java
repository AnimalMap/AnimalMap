package com.joinlist.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.pet_group.model.*;
import com.mem.model.*;
import com.pet_group.model.*;
import com.mem.model.*;
import com.joinlist.model.*;
/** 
 *表格名稱 : <br>
 *	中文:揪團參加名單<br>
 *	英文:JoinList<br>
 */ 
@WebServlet(urlPatterns = { "/joinlist/joinlist.do" })
public class JoinListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("JoinList servlet運行成功 ");

		//====getOne_For_Display====
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("joinList_GrpId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				String  joinList_GrpId = null;
				try {
					joinList_GrpId = new String (str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/***************************2.開始查詢資料*****************************************/
				JoinListService joinlistSvc = new JoinListService();
				JoinListVO joinlistVO = joinlistSvc.getOneJoinList_By_joinList_GrpId(joinList_GrpId);
				if (joinlistVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("joinlistVO", joinlistVO); 
				String url = "/joinlist/listOneJoinList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		//====getOne_For_Update====
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); 
			try {

				/***************************1.接收請求參數****************************************/
				String  joinList_GrpId = new String (req.getParameter("joinList_GrpId").trim());

				/***************************2.開始查詢資料*****************************************/
				JoinListService joinlistSvc = new JoinListService();
				JoinListVO joinlistVO = joinlistSvc.getOneJoinList_By_joinList_GrpId(joinList_GrpId);

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("joinlistVO", joinlistVO); 
				String url = "/joinlist/update_joinlist_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		//====update====
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); 
			try {

				/***************************1.接收請求參數****************************************/
				String joinList_GrpId = req.getParameter("joinList_GrpId").trim();
				String joinList_MemId = req.getParameter("joinList_MemId").trim();

				JoinListVO joinlistVO = new JoinListVO();
				joinlistVO.setJoinList_GrpId(joinList_GrpId);
				joinlistVO.setJoinList_MemId(joinList_MemId);
				if (!errorMsgs.isEmpty()) {
					String url = "/joinlist/update_joinlist_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
					return;//程式中斷
				}

				/***************************2.開始修改資料*****************************************/
				JoinListService joinlistSvc = new JoinListService();
				joinlistVO = joinlistSvc.updateJoinList(joinList_GrpId,joinList_MemId);

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("joinlistVO", joinlistVO); 
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/joinlist/update_joinlist_input.jsp");
				failureView.forward(req, res);
			}
		}
        if ("insert".equals(action)) { // 來自addJoinList.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

                JoinListVO joinlistVO = new JoinListVO();
       
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    req.setAttribute("joinlistVO", joinlistVO); // 含有輸入格式錯誤的joinlistVO物件,也存入req
                    RequestDispatcher failureView = req.getRequestDispatcher("/joinlist/addJoinList.jsp");
                    failureView.forward(req, res);
                    return;
                }
                
                /***************************2.開始新增資料***************************************/
                JoinListService joinlistSvc = new JoinListService();
                joinlistVO = joinlistSvc.addJoinList(); 
                     
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/joinlist/listAllJoinList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
			/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/joinlist/addJoinList.jsp");
				failureView.forward(req, res);
			}
		}
        if ("delete".equals(action)) { // 來自listAllJoinList.jsp 或  /dept/listJoinLists_ByDeptno.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);
            
            String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/joinlist/listAllJoinList.jsp】 或  【/dept/listJoinLists_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】 或 【 /joinlist/listJoinLists_ByCompositeQuery.jsp】

            try {
                /***************************1.接收請求參數***************************************/
                String joinList_GrpId = new String(req.getParameter("joinList_GrpId"));

                
                /***************************2.開始刪除資料***************************************/
                JoinListService joinlistSvc = new JoinListService();
                JoinListVO joinlistVO = joinlistSvc.getOneJoinList_By_joinList_GrpId(joinList_GrpId);
   
                joinlistSvc.deleteJoinList_By_joinList_GrpId(joinList_GrpId);
             
                
                /***************************3.刪除完成,準備轉交(Send the Success view)***********/             
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
}