package com.Spring.LibraryService.Repository;

import com.Spring.LibraryService.entity.LibraryMember;
import com.Spring.LibraryService.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LibraryMemberRepository extends JpaRepository<LibraryMember, Integer> {

}