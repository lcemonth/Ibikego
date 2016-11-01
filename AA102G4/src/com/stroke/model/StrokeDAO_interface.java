package com.stroke.model;

import java.util.List;
import java.util.Map;

public interface StrokeDAO_interface {
          public int insert(StrokeVO strokeVO,Map strokeList);
          public int update(StrokeVO strokeVO);
          public int delete(Integer stroke_no);
          public StrokeVO findByPrimaryKey(Integer stroke_no);
          public List<StrokeVO> getAll();
          public List<StrokeVO> findStrokesByMem_no(Integer mem_no);
          public List<StrokeVO> getMemberStrokeAll(Integer mem_no);
}
