package com.techvortex.vortex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.techvortex.vortex.entity.Review;

public interface ReviewsService {
    // Trả về danh sách tất cả các danh mục
    List<Review> findAll();

    // Trả về một trang danh sách các danh mục dựa trên phân trang
    Page<Review> findAll(Pageable pageable);

    // Tìm kiếm danh mục theo ID và trả về kết quả dưới dạng Optional
    Optional<Review> findById(Integer categoryId);

    // Tìm kiếm các danh mục dựa trên tên và phân trang kết quả
    Page<Review> search(String name, Pageable pageable);
}
