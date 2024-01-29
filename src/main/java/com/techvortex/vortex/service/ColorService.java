package com.techvortex.vortex.service;
import java.util.List;
import com.techvortex.vortex.entity.Color;

public interface ColorService {

    public List<Color> findAll(); // in ra lưu vào danh sách

    public Color findById(Integer ColorId); // tìm kiếm theo id 

   public Color create(Color color); // thêm danh sách

   public Color update(Color color); // sửa danh sách

   public void delete(Color color);
}
