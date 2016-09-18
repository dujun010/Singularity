package qid.excel.singularity.test;

import java.io.Serializable;

import qid.excel.singularity.util.ExcelVOAttribute;


public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;// 唯一标识
	@ExcelVOAttribute(name = "姓名", column = "A", isExport = true)
	private String name;//	名称
	
	@ExcelVOAttribute(name = "账号", column = "B", isExport = true)
	private String userName;//	登录名
	
	private Boolean userNameFlag;	//登陆名是否模糊查询
	
	@ExcelVOAttribute(name = "密码", column = "C", prompt = "密码不可以显示!", isExport = false)
	private String passWord;//	口令
	
	
	private Integer sex;//	性别 	   1：男    2：女
	
	@ExcelVOAttribute(name = "性别", column = "D", combo = { "男 ", "女"})
	private String sexSTR;
	
	
	private Integer duty;//	职务		1：职员     2：副科长    3：正科长   4：副处长    5：正处长    6：副局长    7：正局长
	
	@ExcelVOAttribute(name = "职务", column = "E", combo = { "职员 ","副科长","正科长 ","副处长","正处长","副局长","正局长"})
	private String dutySTR;
	
	@ExcelVOAttribute(name = "身份证", column = "F")
	private String iCd;//	身份证
	
	@ExcelVOAttribute(name = "出生日期", column = "G")
	private String birthDate;//	出生日期
	
	
	private String birthdateBegin;
	private String birthdateEnd;
	
	@ExcelVOAttribute(name = "邮箱", column = "H")
	private String emal;//	邮箱
	
	@ExcelVOAttribute(name = "手机", column = "I")
	private String phone;//	手机
	
	
	private Integer state;//	状态		默认为0  0：启用    1：禁用
	
	@ExcelVOAttribute(name = "状态", column = "J", combo = { "启用 ", "禁用"})
	private String stateSTR;
	
	
	private String orgId;//	组织ID
	
	@ExcelVOAttribute(name = "所属组织", column = "K")
	private String orgName;//	组织名称
	
	private String roleId;//角色ID
	
	@ExcelVOAttribute(name = "所属角色", column = "L")
	private String roleName;//角色名称
	
	@ExcelVOAttribute(name = "创建时间", column = "M",isExport = true)
	private String ts;//	创建时间
	
	private String tsBegin;
	
	private String tsEnd;
	
	private String nt;//	备注
	
	@ExcelVOAttribute(name = "数据", column = "N",isExport = true)
	private Double data;
	
	
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Boolean getUserNameFlag() {
		return userNameFlag;
	}
	public void setUserNameFlag(Boolean userNameFlag) {
		this.userNameFlag = userNameFlag;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getSexSTR() {
		if(sex != null && !sex.equals("")){
			switch (sex) {
			case 1:
				return "男";
			case 2:
				return "女";
			default:
				return "未知";
			}
		}
		return sexSTR;
	}
	public void setSexSTR(String sexSTR) {
		this.sexSTR = sexSTR;
	}
	public Integer getDuty() {
		return duty;
	}
	public void setDuty(Integer duty) {
		this.duty = duty;
	}
	public String getDutySTR() {
		if(duty == null || duty.equals("")) return dutySTR;
		switch (duty) {
		case 1:
			return "职员";
		case 2:
			return "副科长";
		case 3:
			return "正科长";
		case 4:
			return "副处长";
		case 5:
			return "正处长";
		case 6:
			return "副局长";
		case 7:
			return "正局长";
		default:
			return "未知";
		}
	}
	public void setDutySTR(String dutySTR) {
		this.dutySTR = dutySTR;
	}
	
	public String getiCd() {
		return iCd;
	}
	public void setiCd(String iCd) {
		this.iCd = iCd != null && !iCd.equals("") ? iCd.replaceAll("-", "") : iCd;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getBirthdateBegin() {
		return birthdateBegin;
	}
	public void setBirthdateBegin(String birthdateBegin) {
		this.birthdateBegin = birthdateBegin;
	}
	public String getBirthdateEnd() {
		return birthdateEnd;
	}
	public void setBirthdateEnd(String birthdateEnd) {
		this.birthdateEnd = birthdateEnd;
	}
	
	public String getEmal() {
		return emal;
	}
	public void setEmal(String emal) {
		this.emal = emal;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getStateSTR() {
		if(state == null || state.equals("")) return stateSTR;
		switch (state) {
		case 0:
			return "启用";
		case 1:
			return "禁用";
		default:
			return "未知";
		}
	}
	public void setStateSTR(String stateSTR) {
		this.stateSTR = stateSTR;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getTs() {
		if(ts != null && !ts.equals("")) return ts.substring(0,ts.length()-2);
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getNt() {
		return nt;
	}
	public void setNt(String nt) {
		this.nt = nt;
	}
	
	public String getTsBegin() {
		return tsBegin;
	}
	public void setTsBegin(String tsBegin) {
		this.tsBegin = tsBegin;
	}
	public String getTsEnd() {
		return tsEnd;
	}
	public void setTsEnd(String tsEnd) {
		this.tsEnd = tsEnd;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Double getData() {
		return data;
	}
	public void setData(Double data) {
		this.data = data;
	}
	
	
}
