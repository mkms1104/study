package com.example.jpashop.lock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

import static com.example.jpashop.lock.JpaLockTest.*;

public interface LockTestRepository extends JpaRepository<LockTest, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    @QueryHints({
//            @QueryHint(name = "javax.persistence.lock.timeout", value = "3000")
//    })
    List<LockTest> findByName(String name);
}
