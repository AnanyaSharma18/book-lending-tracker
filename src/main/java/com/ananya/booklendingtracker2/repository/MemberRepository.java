package com.ananya.booklendingtracker2.repository;

import com.ananya.booklendingtracker2.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}