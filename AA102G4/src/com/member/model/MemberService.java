package com.member.model;

import java.util.List;
import java.util.Map;

import com.emp.model.EmpVO;

public class MemberService{
	private MemberDAO_interface dao;
	public MemberService(){
		dao = new MemberDAO();
	}
	public MemberVO addMember(String mem_acc,String mem_pw,String mem_name,String mem_nickname,String mem_add,String mem_phone,String mem_email,byte[] mem_photo,Integer mem_reg,Integer mem_del){
		MemberVO memberVO = new MemberVO();
		memberVO.setMem_acc(mem_acc);
		memberVO.setMem_pw(mem_pw);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_nickname(mem_nickname);
		memberVO.setMem_add(mem_add);
		memberVO.setMem_phone(mem_phone);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_photo(mem_photo);
		memberVO.setMem_reg(mem_reg);
		memberVO.setMem_del(mem_del);
		dao.insert(memberVO);
		return memberVO;
	}
	public MemberVO updateMember(Integer mem_no,String mem_acc,String mem_pw,String mem_name,String mem_nickname,String mem_add,String mem_phone,String mem_email,byte[] mem_photo,Integer mem_reg,Integer mem_del){
		MemberVO memberVO = new MemberVO();
		memberVO.setMem_no(mem_no);
		memberVO.setMem_acc(mem_acc);
		memberVO.setMem_pw(mem_pw);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_nickname(mem_nickname);
		memberVO.setMem_add(mem_add);
		memberVO.setMem_phone(mem_phone);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_photo(mem_photo);
		memberVO.setMem_reg(mem_reg);
		memberVO.setMem_del(mem_del);
		dao.update(memberVO);
		return memberVO;
	}
	public MemberVO updateMemberPw(Integer mem_no,String mem_name,String mem_pw){
		MemberVO memberVO = new MemberVO();
		memberVO.setMem_no(mem_no);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_pw(mem_pw);
		dao.update_pw(memberVO);
		return memberVO;
	}
	public void deleteMember(Integer mem_no){
		dao.delete(mem_no);
	}
	public MemberVO getOneMember(Integer mem_no){
		return dao.findByPrimaryKey(mem_no);
	}
	public MemberVO getOneMemberByAcc(String mem_acc){
		return dao.findByAcc(mem_acc);
	}
	public MemberVO getAcc_Email(String mem_acc,String mem_email){
		return dao.findByACC_MAIL(mem_acc,mem_email);
	}
	public List<MemberVO> getAll(){
		return dao.getAll();
	}
	public List<MemberVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	//-------------------------合-------------------------
	public MemberVO getOneMemByAcc(String mem_acc){
		return dao.findOneMemByAcc(mem_acc);
	}
	//-------------------------合-------------------------
}