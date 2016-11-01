package com.forumresponse.model;
import java.util.List;
public class ForumresService {



		private ForumresDAO_interface dao;

		public ForumresService() {
			dao = new ForumresDAO();
		}

		public ForumresVO addForumres(Integer mem_no,Integer forum_no, String forumres_con, java.sql.Date forumres_cretime,Integer forumres_del) {

			ForumresVO forumresVO = new ForumresVO();
			
			forumresVO.setMem_no(mem_no);
			forumresVO.setForum_no(forum_no);
			forumresVO.setForumres_con(forumres_con);
			forumresVO.setForumres_cretime(forumres_cretime);
			forumresVO.setForumres_del(forumres_del);
			dao.insert(forumresVO);

			return forumresVO;
		}

		
		
		public ForumresVO updateForumres( Integer forum_no,Integer mem_no,String forumres_con,java.sql.Date forumres_cretime,Integer forumres_del,Integer forumres_no
				) {

			ForumresVO forumresVO = new ForumresVO();
			
			forumresVO.setForum_no(forum_no);
			forumresVO.setMem_no(mem_no);
			forumresVO.setForumres_con(forumres_con);
			forumresVO.setForumres_cretime(forumres_cretime);
			forumresVO.setForumres_del(forumres_del);
			forumresVO.setForumres_no(forumres_no);
			
			dao.update(forumresVO);

			return forumresVO;
		}
		
		

		public void deleteForumres(Integer froumres_no) {
			dao.delete(froumres_no);
		}

		public ForumresVO getOneForumresno(Integer forumres_no){
			return dao.findByforumres_no(forumres_no);
		}
		public List<ForumresVO> getOneForumres(Integer forum_no) {
			return dao.findByforum_con(forum_no);
		}
		
		

		public List<ForumresVO> getAll() {
			return dao.getAll();
		}
	}
