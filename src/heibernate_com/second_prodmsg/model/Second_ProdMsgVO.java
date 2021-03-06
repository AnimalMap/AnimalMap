package heibernate_com.second_prodmsg.model;
import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.OrderBy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import heibernate_com.second_prod.model.Second_ProdVO;

import heibernate_com.mem.model.MemVO;



/** 
* @TableDeclaration 類別說明:
* <pre>
* 表格名稱 : 
*	中文: 二手商品留言 
*	英文: second_ProdMsg  
* </pre>
*/   
@Entity
@Table(name = "SECOND_PRODMSG")
public class Second_ProdMsgVO implements java.io.Serializable{  
	private static final long serialVersionUID = 1L; ;
	private String second_ProdMsg_Id;
	private Second_ProdVO second_ProdVO;
	private MemVO memVO;
	private String second_ProdMsg_Msg;
	private java.sql.Timestamp second_ProdMsg_DATE;
	private java.sql.Timestamp second_ProdMsg_adp_upDate;


	public Second_ProdMsgVO() {} //必需有一個不傳參數建構子(JavaBean基本知識)
	
	@Id
	@Column(name = "SECOND_PRODMSG_ID")
	@GenericGenerator(name = "STRING_SEQUENCE_GENERATOR", strategy = "StringSequenceGenerator", parameters = { @Parameter(name = "sequence", value = "second_ProdMsg_seq1") })
	//@SequenceGenerator(name="xxx", sequenceName="second_ProdMsg_seq1", allocationSize=1) //1.先用@SequenceGenerator建立一個generator
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="STRING_SEQUENCE_GENERATOR")       //2.再用@GeneratedValue的generator屬性指定要用哪個generator //【strategy的GenerationType, 有四種值: AUTO, IDENTITY, SEQUENCE, TABLE】 
	public String getSecond_ProdMsg_Id() {
		return this.second_ProdMsg_Id;
	}
	
	public void setSecond_ProdMsg_Id(String second_ProdMsg_Id) {
		this.second_ProdMsg_Id = second_ProdMsg_Id;
	}	
	@ManyToOne //(雙向多對一/一對多)的多對一    //【原預設為 @ManyToOne(fetch=FetchType.LAZY)】--> 【是指原為lazy="true"之意】
	@JoinColumn(name = "SECOND_PROD_ID")  //指定用來join table的column
	public Second_ProdVO getSecond_ProdVO() {
		return this.second_ProdVO;
	}
	
	public void setSecond_ProdVO(Second_ProdVO second_ProdVO) {
		this.second_ProdVO = second_ProdVO;
	}
	@ManyToOne //(雙向多對一/一對多)的多對一    //【原預設為 @ManyToOne(fetch=FetchType.LAZY)】--> 【是指原為lazy="true"之意】
	@JoinColumn(name = "MEM_ID")  //指定用來join table的column
	public MemVO getMemVO() {
		return this.memVO;
	}
	
	public void setMemVO(MemVO memVO) {
		this.memVO = memVO;
	}
	@Column(name = "SECOND_PRODMSG_MSG")
	public String getSecond_ProdMsg_Msg() {
		return this.second_ProdMsg_Msg;
	}
	
	public void setSecond_ProdMsg_Msg(String second_ProdMsg_Msg) {
		this.second_ProdMsg_Msg = second_ProdMsg_Msg;
	}
		
	@Column(name = "SECOND_PRODMSG_DATE")
	public java.sql.Timestamp getSecond_ProdMsg_DATE() {
		return this.second_ProdMsg_DATE;
	}
	
	public void setSecond_ProdMsg_DATE(java.sql.Timestamp second_ProdMsg_DATE) {
		this.second_ProdMsg_DATE = second_ProdMsg_DATE;
	}
		
	@Column(name = "SECOND_PRODMSG_ADP_UPDATE")
	public java.sql.Timestamp getSecond_ProdMsg_adp_upDate() {
		return this.second_ProdMsg_adp_upDate;
	}
	
	public void setSecond_ProdMsg_adp_upDate(java.sql.Timestamp second_ProdMsg_adp_upDate) {
		this.second_ProdMsg_adp_upDate = second_ProdMsg_adp_upDate;
	}
		
}
