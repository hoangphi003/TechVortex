package com.techvortex.vortex.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techvortex.vortex.entity.Review;
import com.techvortex.vortex.repository.ReviewsDao;
import com.techvortex.vortex.service.ReviewsService;

@Service
public class ReviewsServiceImp implements ReviewsService {
    @Autowired
    ReviewsDao cdao;

    @Override
    public List<Review> findAll() {
        // Lấy danh sách tất cả các danh mục từ dao và trả về
        return cdao.findAll();
    }

    @Override
    public Page<Review> findAll(Pageable pageable) {
        // Lấy một trang danh sách danh mục dựa trên phân trang từ dao và trả về
        return cdao.findAll(pageable);
    }

    @Override
    public Optional<Review> findById(Integer categoryId) {
        // Tìm kiếm một danh mục theo ID từ dao và trả về kết quả dưới dạng Optional
        return cdao.findById(categoryId);
    }

    @Override
    public Page<Review> search(String name, Pageable pageable) {
        // Tìm kiếm danh mục dựa trên tên với hỗ trợ phân trang từ dao và trả về
        return cdao.findByReviewNameContaining(name, pageable);
    }
}
