package com.techvortex.vortex.service;
import java.util.List;

import com.techvortex.vortex.entity.Account;
import java.util.Optional;
public interface AccountService {
    // void UpdatePasswordByMail(String gmail);

	public List<Account> findAll(); // in ra lưu vào danh sách

	// public Page<Account> findAll(Pageable pageable); // phân trang
    // public Optional<Account> findById(String UserName);

	 public Account findById(String UserName); // tìm kiếm theo id // tìm kiếm theo username

	public Account create(Account account); // thêm danh sách

	public Account update(Account account); // sửa danh sách

	public void delete(Account account);

	// Account findByGmail(String gmail);
	
	// Page<Account> search(String Fullname, Pageable pageable);
	
	// Account findByPhone(Integer Phone);
	// Account findByEmail(String email);

	// Account getUsernameAndPassword(String username, String password);

	// void deleteReferencingRecords(String id);

	// void deleteReferenceOrders(String id);
}
