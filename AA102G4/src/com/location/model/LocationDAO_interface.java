package com.location.model;

import java.util.*;
import com.activity.model.*;

public interface LocationDAO_interface {
          public void insert(LocationVO LocationVO);
          public void update(LocationVO LocationVO);
          public void delete(Integer loc_no);
          public LocationVO findByPrimaryKey(Integer loc_no);
          public List<LocationVO> getAll();
          public List<ActivityVO> getActivityByLoc_no(Integer loc_no);
          public LocationVO findLngNLatByLoc_no(Integer loc_no);
}