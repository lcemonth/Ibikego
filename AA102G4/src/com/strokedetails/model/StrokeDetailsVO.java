package com.strokedetails.model;

public class StrokeDetailsVO implements java.io.Serializable{
	private Integer stroke_det_no;		//行程明細編號
	private Integer stroke_no;			//行程編號
	private Integer tra_no;				//旅遊點編號
	private Integer stroke_whichday;	//行程天數(第幾天行程)
	public Integer getStroke_det_no() {
		return stroke_det_no;
	}
	public void setStroke_det_no(Integer stroke_det_no) {
		this.stroke_det_no = stroke_det_no;
	}
	public Integer getStroke_no() {
		return stroke_no;
	}
	public void setStroke_no(Integer stroke_no) {
		this.stroke_no = stroke_no;
	}
	public Integer getTra_no() {
		return tra_no;
	}
	public void setTra_no(Integer tra_no) {
		this.tra_no = tra_no;
	}
	public Integer getStroke_whichday() {
		return stroke_whichday;
	}
	public void setStroke_whichday(Integer stroke_whichday) {
		this.stroke_whichday = stroke_whichday;
	}

}
