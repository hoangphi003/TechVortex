package com.techvortex.vortex.serviceimplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techvortex.vortex.entity.Brand;
import com.techvortex.vortex.entity.Discount;
import com.techvortex.vortex.entity.Review;
import com.techvortex.vortex.entity.ReviewImg;
import com.techvortex.vortex.repository.ReviewsDao;
import com.techvortex.vortex.repository.ReviewsimgDao;
import com.techvortex.vortex.service.ReviewsimgService;
@Service
public class ReviewsimgServiceImp implements ReviewsimgService{
    @Autowired
    ReviewsimgDao cdao;

    @Override
    public List<ReviewImg> findAll() {
        // Lấy danh sách tất cả các danh mục từ dao và trả về
        return cdao.findAll();
    }

    @Override
    public Page<ReviewImg> findAll(Pageable pageable) {
        // Lấy một trang danh sách danh mục dựa trên phân trang từ dao và trả về
        return cdao.findAll(pageable);
    }

    @Override
    public ReviewImg create(ReviewImg categories) {
        // Tạo mới một danh mục thông qua dao và trả về
        return cdao.save(categories);
    }

    @Override
    public ReviewImg update(ReviewImg categories) {
        // Cập nhật thông tin của một danh mục thông qua dao và trả về
        return cdao.save(categories);
    }

    @Override
    public void delete(ReviewImg categories) {
        // Xóa một danh mục thông qua dao
        cdao.delete(categories);
    }

    @Override
    public Optional<ReviewImg> findById(Integer categoryId) {
        // Tìm kiếm một danh mục theo ID từ dao và trả về kết quả dưới dạng Optional
        return cdao.findById(categoryId);
    }

     @Override
     public Page<ReviewImg> search(String name, Pageable pageable) {
         // Tìm kiếm danh mục dựa trên tên với hỗ trợ phân trang từ dao và trả về
         return cdao.findByReviewimgNameContaining(name, pageable);
     }

}
