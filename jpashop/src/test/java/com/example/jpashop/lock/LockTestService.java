package com.example.jpashop.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LockTestService {
    @Autowired private LockTestRepository lockTestRepository;

    @Transactional
    public void slowSave() {
        JpaLockTest.LockTest lockTest = new JpaLockTest.LockTest("mskim");
        lockTestRepository.saveAndFlush(lockTest);
//        lockTestRepository.findByName("mskim");
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
