package utils.excel.data;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
@WebListener()
public class Common_variable implements ServletContextListener{	
	public static LinkedHashMap<String, List> linkhashMap_excel_DB;
	public static String excel_path_fakeDB;
	public static String excel_fakeDB_output_path;
	public static String excel_fakeDB_input_path;
	public static boolean able_fakeDB_output ;
	public static boolean able_fakeDB_input ;
	public void contextInitialized(ServletContextEvent event) {
		// ==== 取得context物件 ====
		ServletContext context = event.getServletContext();
		// ==== 假資料Excel DB庫 ====
		excel_path_fakeDB = context.getRealPath("\\Input") + "\\SQL假資料.xls";
		// ==== 輸入輸出路徑 ====
		excel_fakeDB_output_path =  context.getRealPath("\\Output") + "\\FakeDB_Excel\\";
		excel_fakeDB_input_path =  context.getRealPath("\\Input") + "\\FakeDB_Excel\\";
		System.out.println("excel_fakeDB_input_path :" + excel_fakeDB_input_path);
		// ==== 控制布林值 ====
		able_fakeDB_output = true ;
		able_fakeDB_input = true ;
	}
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("ServletContextListener通知: FakeDB_Common_contextDestroyed....");
	}		
	static{
		linkhashMap_excel_DB = new LinkedHashMap<String, List>();	

		//==== table名稱 : aniHome_Msg ====
		{
			//====表格====
			ArrayList<List> 列List = new ArrayList<List>();
			linkhashMap_excel_DB.put("aniHome_Msg", 列List);
			//====第 1 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("PK"); //====第1欄====  
				欄List.add("aniHome_Msg_Id"); //====第2欄====  
				欄List.add("動物之家留言編號"); //====第3欄====  
				欄List.add("NUMBER"); //====第4欄====  
				欄List.add("8"); //====第5欄====  
				欄List.add("PK"); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add("PK"); //====第9欄====  
				欄List.add("1"); //====第10欄====  
				欄List.add("1"); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 2 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("FK"); //====第1欄====  
				欄List.add("aniHome_Id"); //====第2欄====  
				欄List.add("動物之家編號"); //====第3欄====  
				欄List.add("NUMBER"); //====第4欄====  
				欄List.add("8"); //====第5欄====  
				欄List.add("FK"); //====第6欄====  
				欄List.add("aniHome"); //====第7欄====  
				欄List.add("aniHome_Id"); //====第8欄====  
				欄List.add("FK"); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add("aniHome_title"); //====第12欄====  
			}	
			//====第 3 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("FK"); //====第1欄====  
				欄List.add("mem_Id"); //====第2欄====  
				欄List.add("留言會員編號"); //====第3欄====  
				欄List.add("NUMBER"); //====第4欄====  
				欄List.add("8"); //====第5欄====  
				欄List.add("FK"); //====第6欄====  
				欄List.add("mem"); //====第7欄====  
				欄List.add("mem_Id"); //====第8欄====  
				欄List.add("FK"); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add("mem_name/mem_nick_name"); //====第12欄====  
			}	
			//====第 4 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("內容"); //====第1欄====  
				欄List.add("aniHome_Msg"); //====第2欄====  
				欄List.add("動物之家留言"); //====第3欄====  
				欄List.add("VARCHAR2"); //====第4欄====  
				欄List.add("3000"); //====第5欄====  
				欄List.add(""); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add("內容上限字數-1000個中文字"); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 5 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("發布日期"); //====第1欄====  
				欄List.add("adp_start_date"); //====第2欄====  
				欄List.add("留言發布日期"); //====第3欄====  
				欄List.add("DATE"); //====第4欄====  
				欄List.add(""); //====第5欄====  
				欄List.add("NOT NULL"); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add(""); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
		}
	

		//==== table名稱 : aniHome ====
		{
			//====表格====
			ArrayList<List> 列List = new ArrayList<List>();
			linkhashMap_excel_DB.put("aniHome", 列List);
			//====第 1 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("PK"); //====第1欄====  
				欄List.add("aniHome_Id"); //====第2欄====  
				欄List.add("動物之家編號"); //====第3欄====  
				欄List.add("NUMBER"); //====第4欄====  
				欄List.add("8"); //====第5欄====  
				欄List.add("PK"); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add("PK"); //====第9欄====  
				欄List.add("1"); //====第10欄====  
				欄List.add("1"); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 2 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("FK"); //====第1欄====  
				欄List.add("mem_Id"); //====第2欄====  
				欄List.add("會員"); //====第3欄====  
				欄List.add("NUMBER"); //====第4欄====  
				欄List.add("8"); //====第5欄====  
				欄List.add("FK"); //====第6欄====  
				欄List.add("mem"); //====第7欄====  
				欄List.add("mem_Id"); //====第8欄====  
				欄List.add("FK"); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add("mem_name/mem_nick_name"); //====第12欄====  
			}	
			//====第 3 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("標題"); //====第1欄====  
				欄List.add("aniHome_title"); //====第2欄====  
				欄List.add("動物之家標題"); //====第3欄====  
				欄List.add("VARCHAR2"); //====第4欄====  
				欄List.add("90"); //====第5欄====  
				欄List.add("NOT NULL"); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add("標題上限字數-30個中文字"); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 4 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("內容"); //====第1欄====  
				欄List.add("aniHome_content"); //====第2欄====  
				欄List.add("動物之家內容"); //====第3欄====  
				欄List.add("VARCHAR2"); //====第4欄====  
				欄List.add("3000"); //====第5欄====  
				欄List.add("NOT NULL"); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add("內容上限字數-1000個中文字"); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 5 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("發布日期"); //====第1欄====  
				欄List.add("aniHome_start_date"); //====第2欄====  
				欄List.add("動物之家發布時間"); //====第3欄====  
				欄List.add("DATE"); //====第4欄====  
				欄List.add(""); //====第5欄====  
				欄List.add("NOT NULL"); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add(""); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 6 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("修改時間"); //====第1欄====  
				欄List.add("aniHome_upDate"); //====第2欄====  
				欄List.add("動物之家更新時間"); //====第3欄====  
				欄List.add("DATE"); //====第4欄====  
				欄List.add(""); //====第5欄====  
				欄List.add(""); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add(""); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 7 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("縣市"); //====第1欄====  
				欄List.add("aniHome_city"); //====第2欄====  
				欄List.add("縣市"); //====第3欄====  
				欄List.add("VARCHAR2"); //====第4欄====  
				欄List.add("12"); //====第5欄====  
				欄List.add(""); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add(""); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 8 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("鄉鎮市區"); //====第1欄====  
				欄List.add("aniHome_town"); //====第2欄====  
				欄List.add("鄉鎮市區"); //====第3欄====  
				欄List.add("VARCHAR2"); //====第4欄====  
				欄List.add("12"); //====第5欄====  
				欄List.add(""); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add(""); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 9 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("道路街名村里"); //====第1欄====  
				欄List.add("aniHome_road"); //====第2欄====  
				欄List.add("道路街名村里"); //====第3欄====  
				欄List.add("VARCHAR2"); //====第4欄====  
				欄List.add("50"); //====第5欄====  
				欄List.add(""); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add(""); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 10 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("台灣經度"); //====第1欄====  
				欄List.add("aniHome_lon"); //====第2欄====  
				欄List.add("動物之家經度座標"); //====第3欄====  
				欄List.add("NUMBER"); //====第4欄====  
				欄List.add("9,6"); //====第5欄====  
				欄List.add(""); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add("由住址分析出來，或手機抓GPS取得(有小數點)"); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 11 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("台灣緯度"); //====第1欄====  
				欄List.add("aniHome_lat"); //====第2欄====  
				欄List.add("緯度座標緯度座標"); //====第3欄====  
				欄List.add("NUMBER"); //====第4欄====  
				欄List.add("9,6"); //====第5欄====  
				欄List.add(""); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add("由住址分析出來，或手機抓GPS取得(有小數點)"); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
		}
	

		//==== table名稱 : mem ====
		{
			//====表格====
			ArrayList<List> 列List = new ArrayList<List>();
			linkhashMap_excel_DB.put("mem", 列List);
			//====第 1 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("PK"); //====第1欄====  
				欄List.add("mem_Id"); //====第2欄====  
				欄List.add("會員編號"); //====第3欄====  
				欄List.add("NUMBER"); //====第4欄====  
				欄List.add("8"); //====第5欄====  
				欄List.add("PK"); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add("PK"); //====第9欄====  
				欄List.add("7000"); //====第10欄====  
				欄List.add("1"); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 2 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("信箱"); //====第1欄====  
				欄List.add("mem_email"); //====第2欄====  
				欄List.add("信箱"); //====第3欄====  
				欄List.add("VARCHAR2"); //====第4欄====  
				欄List.add("60"); //====第5欄====  
				欄List.add("NOT NULL"); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add(""); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 3 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("信箱"); //====第1欄====  
				欄List.add("mem_account"); //====第2欄====  
				欄List.add("帳號"); //====第3欄====  
				欄List.add("VARCHAR2"); //====第4欄====  
				欄List.add("60"); //====第5欄====  
				欄List.add("NOT NULL"); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add("電子信箱就是帳號"); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 4 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("密碼"); //====第1欄====  
				欄List.add("mem_Psw"); //====第2欄====  
				欄List.add("密碼"); //====第3欄====  
				欄List.add("VARCHAR2"); //====第4欄====  
				欄List.add("60"); //====第5欄====  
				欄List.add("NOT NULL"); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add(""); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 5 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("暱稱"); //====第1欄====  
				欄List.add("mem_nick_name"); //====第2欄====  
				欄List.add("會員暱稱"); //====第3欄====  
				欄List.add("VARCHAR2"); //====第4欄====  
				欄List.add("60"); //====第5欄====  
				欄List.add("NOT NULL"); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add(""); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 6 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("姓名"); //====第1欄====  
				欄List.add("mem_name"); //====第2欄====  
				欄List.add("姓名"); //====第3欄====  
				欄List.add("VARCHAR2"); //====第4欄====  
				欄List.add("40"); //====第5欄====  
				欄List.add("NOT NULL"); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add(""); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 7 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("性別"); //====第1欄====  
				欄List.add("mem_gender"); //====第2欄====  
				欄List.add("性別"); //====第3欄====  
				欄List.add("VARCHAR2"); //====第4欄====  
				欄List.add("3"); //====第5欄====  
				欄List.add("NOT NULL"); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add("M.男F.女"); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 8 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("身分證"); //====第1欄====  
				欄List.add("mem_Tw_Id"); //====第2欄====  
				欄List.add("身份證字號"); //====第3欄====  
				欄List.add("VARCHAR2"); //====第4欄====  
				欄List.add("10"); //====第5欄====  
				欄List.add("NOT NULL"); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add(""); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 9 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("出生日期"); //====第1欄====  
				欄List.add("mem_birth_date"); //====第2欄====  
				欄List.add("出生年月日"); //====第3欄====  
				欄List.add("DATE"); //====第4欄====  
				欄List.add(""); //====第5欄====  
				欄List.add("NOT NULL"); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add(""); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 10 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("手機"); //====第1欄====  
				欄List.add("mem_phone"); //====第2欄====  
				欄List.add("手機"); //====第3欄====  
				欄List.add("VARCHAR2"); //====第4欄====  
				欄List.add("30"); //====第5欄====  
				欄List.add("NOT NULL"); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add(""); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 11 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("簡介"); //====第1欄====  
				欄List.add("mem_Intro"); //====第2欄====  
				欄List.add("個人簡介"); //====第3欄====  
				欄List.add("VARCHAR2"); //====第4欄====  
				欄List.add("150"); //====第5欄====  
				欄List.add(""); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add(""); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 12 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("BLOB"); //====第1欄====  
				欄List.add("mem_profile"); //====第2欄====  
				欄List.add("大頭照"); //====第3欄====  
				欄List.add("BLOB"); //====第4欄====  
				欄List.add(""); //====第5欄====  
				欄List.add(""); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add(""); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 13 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("黑名單"); //====第1欄====  
				欄List.add("mem_black_list"); //====第2欄====  
				欄List.add("黑名單"); //====第3欄====  
				欄List.add("VARCHAR2"); //====第4欄====  
				欄List.add("1"); //====第5欄====  
				欄List.add(""); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add("0.非黑名單1.黑名單"); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 14 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("會員權限"); //====第1欄====  
				欄List.add("mem_permission"); //====第2欄====  
				欄List.add("權限"); //====第3欄====  
				欄List.add("VARCHAR2"); //====第4欄====  
				欄List.add("1"); //====第5欄====  
				欄List.add(""); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add("1.一般 2.志工 3.店家"); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 15 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("偏好設定"); //====第1欄====  
				欄List.add("mem_setting"); //====第2欄====  
				欄List.add("偏好設定"); //====第3欄====  
				欄List.add("VARCHAR2"); //====第4欄====  
				欄List.add("30"); //====第5欄====  
				欄List.add(""); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add("0.流浪動物 1.領養活動 2.揪團 3.緊急求救 4.店家 5.二手 6.自家寵物"); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
			//====第 16 列====
			{
				ArrayList<String> 欄List = new ArrayList<String>();
				列List.add(欄List);
				//====欄====
				欄List.add("金錢"); //====第1欄====  
				欄List.add("mem_balance"); //====第2欄====  
				欄List.add("餘額"); //====第3欄====  
				欄List.add("NUMBER"); //====第4欄====  
				欄List.add("10"); //====第5欄====  
				欄List.add(""); //====第6欄====  
				欄List.add(""); //====第7欄====  
				欄List.add(""); //====第8欄====  
				欄List.add(""); //====第9欄====  
				欄List.add(""); //====第10欄====  
				欄List.add(""); //====第11欄====  
				欄List.add(""); //====第12欄====  
			}	
		}
	
	}
}