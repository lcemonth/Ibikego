package com.forumresponse.model;
import java.util.*;

public interface ForumresDAO_interface {
	public void insert(ForumresVO forumresVO);
	public void update(ForumresVO forumresVO);
	public void delete(Integer forumres_no);
	public List<ForumresVO> findByforum_con(Integer forum_no);
	public ForumresVO findByforumres_no(Integer forumres_no);
	public List<ForumresVO> getAll();

}
