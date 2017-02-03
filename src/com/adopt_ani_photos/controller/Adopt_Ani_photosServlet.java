package com.adopt_ani_photos.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.adopt_ani.model.*;
import com.mem.model.*;
import com.adopt_ani_photos.model.*;
/** 
 *表格名稱 : <br>
 *	中文:送養動物相簿<br>
 *	英文:adopt_Ani_photos<br>
 */ 
@WebServlet(urlPatterns = { "/adopt_ani_photos/adopt_ani_photos.do" })
public class Adopt_Ani_photosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("Adopt_Ani_photos servlet運行成功 ");

		//====getOne_For_Display====
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("ado_Ani_Pic_No");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入送養動物相片編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				String  ado_Ani_Pic_No = null;
				try {
					ado_Ani_Pic_No = new String (str);
				} catch (Exception e) {
					errorMsgs.add("送養動物相片編號格式不正確");				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/***************************2.開始查詢資料*****************************************/
				Adopt_Ani_photosService adopt_ani_photosSvc = new Adopt_Ani_photosService();
				Adopt_Ani_photosVO adopt_ani_photosVO = adopt_ani_photosSvc.getOneAdopt_Ani_photos(ado_Ani_Pic_No);
				if (adopt_ani_photosVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("adopt_ani_photosVO", adopt_ani_photosVO); 
				String url = "/adopt_ani_photos/listOneAdopt_Ani_photos.jsp";
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
				String  ado_Ani_Pic_No = new String (req.getParameter("ado_Ani_Pic_No").trim());

				/***************************2.開始查詢資料*****************************************/
				Adopt_Ani_photosService adopt_ani_photosSvc = new Adopt_Ani_photosService();
				Adopt_Ani_photosVO adopt_ani_photosVO = adopt_ani_photosSvc.getOneAdopt_Ani_photos(ado_Ani_Pic_No);

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("adopt_ani_photosVO", adopt_ani_photosVO); 
				String url = "/adopt_ani_photos/update_adopt_ani_photos_input.jsp";
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
				String ado_Ani_Pic_No = req.getParameter("ado_Ani_Pic_No").trim();
				String adopt_Ani_Id = req.getParameter("adopt_Ani_Id").trim();
				String mem_Id = req.getParameter("mem_Id").trim();
				byte[] ado_Ani_Pic = null;
				try {
					Part part = req.getPart("ado_Ani_Pic");
					InputStream in = part.getInputStream();
					ado_Ani_Pic = new byte[part.getInputStream().available()];
					in.read(ado_Ani_Pic);
					in.close();
				} catch (Exception e) {
					ado_Ani_Pic = null;
					//errorMsgs.add("送養動物相片請上傳照片.");
				}
				String ado_Pic_name = req.getParameter("ado_Pic_name").trim();
				String ado_Pic_extent = req.getParameter("ado_Pic_extent").trim();
				java.sql.Date ado_Pic_time = null;
				try {
					ado_Pic_time = java.sql.Date.valueOf(req.getParameter("ado_Pic_time").trim());
				} catch (IllegalArgumentException e) {
					ado_Pic_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				String ado_Pic_type = req.getParameter("ado_Pic_type").trim();

				Adopt_Ani_photosVO adopt_ani_photosVO = new Adopt_Ani_photosVO();
				adopt_ani_photosVO.setAdo_Ani_Pic_No(ado_Ani_Pic_No);
				adopt_ani_photosVO.setAdopt_Ani_Id(adopt_Ani_Id);
				adopt_ani_photosVO.setMem_Id(mem_Id);
				adopt_ani_photosVO.setAdo_Ani_Pic(ado_Ani_Pic);
				adopt_ani_photosVO.setAdo_Pic_name(ado_Pic_name);
				adopt_ani_photosVO.setAdo_Pic_extent(ado_Pic_extent);
				adopt_ani_photosVO.setAdo_Pic_time(ado_Pic_time);
				adopt_ani_photosVO.setAdo_Pic_type(ado_Pic_type);
				if (!errorMsgs.isEmpty()) {
					String url = "/adopt_ani_photos/update_adopt_ani_photos_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
					return;//程式中斷
				}

				/***************************2.開始修改資料*****************************************/
				Adopt_Ani_photosService adopt_ani_photosSvc = new Adopt_Ani_photosService();
				adopt_ani_photosVO = adopt_ani_photosSvc.updateAdopt_Ani_photos(ado_Ani_Pic_No,adopt_Ani_Id,mem_Id,ado_Ani_Pic,ado_Pic_name,ado_Pic_extent,ado_Pic_time,ado_Pic_type);

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("adopt_ani_photosVO", adopt_ani_photosVO); 
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/adopt_ani_photos/update_adopt_ani_photos_input.jsp");
				failureView.forward(req, res);
			}
		}
        if ("insert".equals(action)) { // 來自addAdopt_Ani_photos.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
                String adopt_Ani_Id = req.getParameter("adopt_Ani_Id").trim();
                String mem_Id = req.getParameter("mem_Id").trim();
                byte[] ado_Ani_Pic = null;
                try {
                    Part part = req.getPart("ado_Ani_Pic");
                    InputStream in = part.getInputStream();
                    ado_Ani_Pic = new byte[part.getInputStream().available()];
                    in.read(ado_Ani_Pic);
                    in.close();
                } catch (Exception e) {
                    //errorMsgs.add("送養動物相片請上傳照片.");
                    //e.printStackTrace();
                    ado_Ani_Pic = null;
                }
                String ado_Pic_name = req.getParameter("ado_Pic_name").trim();
                String ado_Pic_extent = req.getParameter("ado_Pic_extent").trim();
                java.sql.Date ado_Pic_time = null;
                try {
                    ado_Pic_time = java.sql.Date.valueOf(req.getParameter("ado_Pic_time").trim());
                } catch (IllegalArgumentException e) {
                    ado_Pic_time=new java.sql.Date(System.currentTimeMillis());
                    errorMsgs.add("請輸入日期!");
                    e.printStackTrace();
                }
                String ado_Pic_type = req.getParameter("ado_Pic_type").trim();

                Adopt_Ani_photosVO adopt_ani_photosVO = new Adopt_Ani_photosVO();
                adopt_ani_photosVO.setAdopt_Ani_Id(adopt_Ani_Id);
                adopt_ani_photosVO.setMem_Id(mem_Id);
                adopt_ani_photosVO.setAdo_Ani_Pic(ado_Ani_Pic);
                adopt_ani_photosVO.setAdo_Pic_name(ado_Pic_name);
                adopt_ani_photosVO.setAdo_Pic_extent(ado_Pic_extent);
                adopt_ani_photosVO.setAdo_Pic_time(ado_Pic_time);
                adopt_ani_photosVO.setAdo_Pic_type(ado_Pic_type);
       
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    req.setAttribute("adopt_ani_photosVO", adopt_ani_photosVO); // 含有輸入格式錯誤的adopt_ani_photosVO物件,也存入req
                    RequestDispatcher failureView = req.getRequestDispatcher("/adopt_ani_photos/addAdopt_Ani_photos.jsp");
                    failureView.forward(req, res);
                    return;
                }
                
                /***************************2.開始新增資料***************************************/
                Adopt_Ani_photosService adopt_ani_photosSvc = new Adopt_Ani_photosService();
                adopt_ani_photosVO = adopt_ani_photosSvc.addAdopt_Ani_photos(adopt_Ani_Id,mem_Id,ado_Ani_Pic,ado_Pic_name,ado_Pic_extent,ado_Pic_time,ado_Pic_type); 
                     
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/adopt_ani_photos/listAllAdopt_Ani_photos.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
			/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/adopt_ani_photos/addAdopt_Ani_photos.jsp");
				failureView.forward(req, res);
			}
		}
        if ("delete".equals(action)) { // 來自listAllAdopt_Ani_photos.jsp 或  /dept/listAdopt_Ani_photoss_ByDeptno.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);
            
            String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/adopt_ani_photos/listAllAdopt_Ani_photos.jsp】 或  【/dept/listAdopt_Ani_photoss_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】 或 【 /adopt_ani_photos/listAdopt_Ani_photoss_ByCompositeQuery.jsp】

            try {
                /***************************1.接收請求參數***************************************/
                String ado_Ani_Pic_No = new String(req.getParameter("ado_Ani_Pic_No"));

                
                /***************************2.開始刪除資料***************************************/
                Adopt_Ani_photosService adopt_ani_photosSvc = new Adopt_Ani_photosService();
                Adopt_Ani_photosVO adopt_ani_photosVO = adopt_ani_photosSvc.getOneAdopt_Ani_photos(ado_Ani_Pic_No);
   
                adopt_ani_photosSvc.deleteAdopt_Ani_photos(ado_Ani_Pic_No);
             
                
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