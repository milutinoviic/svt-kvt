package com.example.sitpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sitpass.model.AccountRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRequestRepository extends JpaRepository<AccountRequest, Long> {


    public boolean existsAccountRequestByEmail(String email);


    @Query("SELECT ar FROM AccountRequest ar WHERE ar.status = 'PENDING' OR ar.status = 'REJECTED'")
    List<AccountRequest> findPendingOrRejectedRequests();




}
