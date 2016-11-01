package com.questionsanswers.model;
import java.util.List;
public class Que_ansService {

		private Que_ansDAO_interface dao;

		public Que_ansService() {
			dao = new Que_ansDAO();
		}

		public Que_ansVO addForumres(String que_ans_q,String que_ans_a) {

			Que_ansVO que_ansVO = new Que_ansVO();

			que_ansVO.setQue_ans_q(que_ans_q);
			que_ansVO.setQue_ans_a(que_ans_a);

			
			dao.insert(que_ansVO);

			return que_ansVO;
		}

		
		
		public Que_ansVO updateForumres( String que_ans_q,String que_ans_a,Integer que_ans_no) {

			Que_ansVO que_ansVO = new Que_ansVO();
			
			que_ansVO.setQue_ans_q(que_ans_q);
			que_ansVO.setQue_ans_a(que_ans_a);
			que_ansVO.setQue_ans_no(que_ans_no);
			
			dao.update(que_ansVO);

			return que_ansVO;
		}
				
		public void deleteForumres(Integer que_ans_no) {
			dao.delete(que_ans_no);
		}

		public Que_ansVO getOneQue_ans(Integer que_ans_no) {
			return dao.findByque_ans_con(que_ans_no);
		}
		
		
		public List<Que_ansVO> getAll() {
			return dao.getAll();
		}
	}
