package com.example.querydsl.repository;

import com.example.querydsl.dto.MemberSearchCondition;
import com.example.querydsl.dto.MemberTeamDto;
import com.example.querydsl.entity.Member;
import com.example.querydsl.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberJpaRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired MemberJpaRepository memberJpaRepository;

    @Test
    public void basicTest() {
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.findById(member.getId()).get();
        assertEquals("member1", findMember.getUsername());

        List<Member> findAll = memberJpaRepository.findAll();
        assertEquals(1, findAll.size());

        List<Member> findByUserName = memberJpaRepository.findByUsername("member1");
        assertEquals(1, findByUserName.size());
    }

    @Test
    public void basicQuerydslTest() {
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        List<Member> findAll = memberJpaRepository.findAll_querydsl();
        assertEquals(1, findAll.size());

        List<Member> findByUserName = memberJpaRepository.findByUsername_querydsl("member1");
        assertEquals(1, findByUserName.size());
    }
    
    @Test
    public void searchTest() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        MemberSearchCondition memberSearchCondition = new MemberSearchCondition();
        memberSearchCondition.setAgeGoe(35); // greater or equal
        memberSearchCondition.setAgeLoe(40); // less or equal
        memberSearchCondition.setTeamName("teamB");

        List<MemberTeamDto> result = memberJpaRepository.searchByBuilder(memberSearchCondition);
        assertEquals("member4", result.get(0).getUsername());
    }
}