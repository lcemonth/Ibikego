package com.questionsanswers.model;
import java.util.*;

public interface Que_ansDAO_interface {
	public void insert(Que_ansVO que_ansVO);
	public void update(Que_ansVO que_ansVO);
	public void delete(Integer que_ans_no);
	public Que_ansVO findByque_ans_con(Integer que_ans_no);
	public List<Que_ansVO> getAll();

}
