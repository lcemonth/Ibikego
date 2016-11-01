package com.forum.model;
import java.util.*;

import com.blogScore.model.BlogScoreVO;
import com.reportcollect.model.ReportcollectVO;
import com.travelScore.model.*;
import com.blog.model.*;


public interface ForumDAO_interface {
          public void insert(ForumVO forumVO);
          public void update(ForumVO forumVO);
          public void delete(Integer forum_no);
          public void delete_mem(Integer forum_no);
          public ForumVO findByPrimaryKey(Integer forum_no);
          public List<ForumVO> getAll();
          public List<ForumVO> mem_getAll();
          //萬用複合查詢
          public List<ForumVO> getAll(Map<String, String[]> map);
          //查詢某文章的回應(一對多)(回傳 Set)
//          public Set<ForumresVO> getforumresByforum_no(Integer forum_no);
          /***文章最新***/
          public List<ForumVO> getAll_new();
          public List<BlogScoreVO> getAll_bsnew();
          
          /***檢舉新增***/
          public void insert_handle(ReportcollectVO rcVO);
          /**8/29最新日誌**/
          public List<BlogVO> getAll_newblog();
          /**8/31會員管理查詢文章**/
          public List<ForumVO> findByMem(Integer mem_no);
          /**8/31會員管理查詢文章**/
          
          /**9/4新增**/
          public List<ForumVO> getForum_title(String forum_title);
          /**9/4新增**/
}
