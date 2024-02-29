package com.Spring.LibraryService.Service;
import com.Spring.LibraryService.entity.Address;
import com.Spring.LibraryService.entity.BookIsbn;
import com.Spring.LibraryService.entity.Checkout;
import com.Spring.LibraryService.entity.LibraryMember;
import com.Spring.LibraryService.Model.Book;
import com.Spring.LibraryService.Model.Member;
import com.Spring.LibraryService.Model.AddressDTO;
import com.Spring.LibraryService.Model.CheckoutDTO;
//import com.Spring.LibraryService.Repository.LibraryBookRepository;
import com.Spring.LibraryService.Repository.LibraryMemberRepository;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    private LibraryMemberRepository memberRepository;
    /*@Autowired
    private LibraryBookRepository libraryBookRepository;*/

    //private Map<Long, Member> memberMap = new HashMap<>();

    public Member createMember(Member member){

        // call the database
        //Integer memberId = new Random().nextInt();
        //member.setMemberId(memberId);
        //memberMap.put(memberId, member);
        //return  member;

        // Create a new LibraryMember object
        LibraryMember libraryMember = new LibraryMember();

        // Manually map properties from LibraryMemberDTO to LibraryMember
        BeanUtils.copyProperties(member, libraryMember);

        // If the address field is not null, map its properties to LibraryMember's Address
        if (member.getAddress() != null) {
            Address address = new Address();
            BeanUtils.copyProperties(member.getAddress(), address);
            libraryMember.setAddress(address);
        }

        // If the checkouts field is not null, map its properties to LibraryMember's Checkouts
        if (member.getCheckouts() != null) {
            List<Checkout> checkouts = member.getCheckouts().stream().map(dto -> {
                Checkout checkout = new Checkout();
                BeanUtils.copyProperties(dto, checkout);
                return checkout;
            }).collect(Collectors.toList());
            libraryMember.setCheckouts(checkouts);
        }

        // Save the LibraryMember to the database
        LibraryMember savedLibraryMember = memberRepository.save(libraryMember);

        // Create a new Member object to return
        Member savedMember = new Member();

        // Manually map properties from saved LibraryMember to Member
        BeanUtils.copyProperties(savedLibraryMember, savedMember);

        // Return the saved LibraryMember
        return savedMember;

    }


    public Member getMember(Integer memberId) {
        //return memberMap.get(memberId);
        Optional<LibraryMember> memberOptional =
                memberRepository.findById(memberId);
        LibraryMember libraryMember =
                memberOptional.orElse(new LibraryMember());

        Member member = new Member();
        member.setMemberId(libraryMember.getMemberId());
        member.setMemberShipLevel(libraryMember.getMemberShipLevel());
        member.setEmailAddress(libraryMember.getEmailAddress());
        member.setFirstName(libraryMember.getFirstName());
        member.setLastName(libraryMember.getLastName());
        member.setPhoneNumber(libraryMember.getPhoneNumber());

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressId(libraryMember.getAddress().getAddressId());
        addressDTO.setLine1(libraryMember.getAddress().getLine1());
        addressDTO.setLine2(libraryMember.getAddress().getLine2());
        addressDTO.setCity(libraryMember.getAddress().getCity());
        addressDTO.setState(libraryMember.getAddress().getState());
        addressDTO.setZip(libraryMember.getAddress().getZip());

        List<CheckoutDTO> checkoutDTOS =
                libraryMember.getCheckouts().stream().map(c -> {
                    CheckoutDTO cdo = new CheckoutDTO();
                    cdo.setId(c.getId());
                    cdo.setIsbn(c.getIsbn());
                    cdo.setCheckoutDate(c.getCheckoutDate());
                    cdo.setDueDate(c.getDueDate());
                    cdo.setReturned(c.isReturned());
                    return  cdo;
                }).collect(Collectors.toList());

        member.setAddress(addressDTO);
        member.setCheckouts(checkoutDTOS);

        return member;

    }

    public Member updateMember(Member member) {
        //Integer memberId = member.getMemberId();
        //memberMap.put(memberId, LibraryMember);
//        return memberRepository.save(member);

        // Create a new LibraryMember object
        LibraryMember libraryMember = new LibraryMember();

        // Manually map properties from LibraryMemberDTO to LibraryMember
        BeanUtils.copyProperties(member, libraryMember);

        // If the address field is not null, map its properties to LibraryMember's Address
        if (member.getAddress() != null) {
            Address address = new Address();
            BeanUtils.copyProperties(member.getAddress(), address);
            libraryMember.setAddress(address);
        }

        // If the checkouts field is not null, map its properties to LibraryMember's Checkouts
        if (member.getCheckouts() != null) {
            List<Checkout> checkouts = member.getCheckouts().stream().map(dto -> {
                Checkout checkout = new Checkout();
                BeanUtils.copyProperties(dto, checkout);
                return checkout;
            }).collect(Collectors.toList());
            libraryMember.setCheckouts(checkouts);
        }

        // Save the LibraryMember to the database
        LibraryMember savedLibraryMember = memberRepository.save(libraryMember);

        // Create a new Member object to return
        Member savedMember = new Member();

        // Manually map properties from saved LibraryMember to Member
        BeanUtils.copyProperties(savedLibraryMember, savedMember);

        // Return the saved LibraryMember
        return savedMember;

    }

    public void deleteMember(Integer memberId) {
        memberRepository.deleteById(memberId);
    }
}
// relational databases ( SQL )
// tables and rows, foreign key, structured
// mysql, oracle, postgres, db2, H2 Microsoft Sql Server
// SQL - structured query language
// programming -
// create and manage tables - DDL - data definition language
// create and manage data within tables - DML - Data manipulation
// access control - DCL - data control language
// install mysql
// TOAD, mysql workbench - IDE

// non- relational databases ( No-SQL)
// unstructured
// document based - mongodb,
// key value database - redis, dynamodb,  cassandra
// graph database -


// schema
// tables
// rows and columns
// functions -
// stored procedures -

// user name and password


// JAVA to Database connectivity

//  JDBC package , java.sql.*

// Connection - url, username and password, mysql - driver
// PreparedStatement
// ResultSet
// CallabaleStatement

// ORM - object relational mapping
// JPA Repositories

// GET POST PUT DELETE - member and book