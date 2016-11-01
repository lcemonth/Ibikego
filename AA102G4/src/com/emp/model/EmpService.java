package com.emp.model;
import java.sql.Date;
import com.tool.MailService;
import java.util.List;
import pwd.RanPwd;
public class EmpService {
	private EmpDAO_interface dao;
	public EmpService(){
		dao = new EmpDAO();
	}
	public EmpVO addEmp(String emp_acc,String emp_name,String emp_email,String emp_tel,String emp_phone,String emp_ps,Date emp_hire) {

		String pwd=new RanPwd().RanPwd(8);
		MailService mail =new MailService();
		mail.sendMail(emp_email, "員工訊息通知", emp_name+"您好，您的系統密碼為:"+pwd);
		EmpVO empvo = new EmpVO();
			empvo.setEmp_acc(emp_acc);
			empvo.setEmp_pw(pwd);
			empvo.setEmp_name(emp_name);
			empvo.setEmp_email(emp_email);
			empvo.setEmp_tel(emp_tel);
			empvo.setEmp_phone(emp_phone);
			empvo.setEmp_ps(emp_ps);
			empvo.setEmp_hire(emp_hire);
		dao.insert(empvo);
		return empvo;
	}
	public EmpVO updateEmp(Integer emp_no, String emp_acc,String emp_pw,String emp_name,String emp_email,String emp_tel,String emp_phone,String emp_ps,java.sql.Date emp_hire) {
		EmpVO empvo = new EmpVO();
			empvo.setEmp_no(emp_no);
			empvo.setEmp_acc(emp_acc);
			empvo.setEmp_pw(emp_pw);
			empvo.setEmp_name(emp_name);
			empvo.setEmp_email(emp_email);
			empvo.setEmp_tel(emp_tel);
			empvo.setEmp_phone(emp_phone);
			empvo.setEmp_ps(emp_ps);
			empvo.setEmp_hire(emp_hire);
		dao.update(empvo);
		return empvo;
	}
	public void deleteEmp(Integer emp_no) {
		dao.delete(emp_no);
	}
	public EmpVO getOneEmp(Integer emp_no) {
		return dao.findByPrimaryKey(emp_no);
	}
	public EmpVO getOneEmpAcc(String emp_acc) {	//登入 員工帳號比對
		return dao.getOneEmpAcc(emp_acc);
	}
	public List<EmpVO> getEmpName(String emp_name) {	// 查詢 員工
		return dao.getEmpName(emp_name);
	}
	public List<EmpVO> getAll() {
		return dao.getAll();
	}
}
