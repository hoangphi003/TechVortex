package com.techvortex.vortex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.techvortex.vortex.entity.Brand;
import com.techvortex.vortex.entity.Review;
import com.techvortex.vortex.entity.ReviewImg;

public interface ReviewsimgService {
    // Trả về danh sách tất cả các danh mục
    List<ReviewImg> findAll();

    // Trả về một trang danh sách các danh mục dựa trên phân trang
    Page<ReviewImg> findAll(Pageable pageable);

    // Tìm kiếm danh mục theo ID và trả về kết quả dưới dạng Optional
    Optional<ReviewImg> findById(Integer categoryId);

    // Tạo mới một danh mục và trả về nó
    ReviewImg create(ReviewImg categories);

    // Cập nhật thông tin của một danh mục và trả về nó
    ReviewImg update(ReviewImg categories);

    // Xóa một danh mục khỏi hệ thống
    void delete(ReviewImg categories);

     // Tìm kiếm các danh mục dựa trên tên và phân trang kết quả
     Page<ReviewImg> search(String name, Pageable pageable);
}
