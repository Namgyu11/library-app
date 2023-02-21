package com.group.libraryapp.domain.user.loanhistory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private long userId;

    private String bookname;

    private boolean isReturn;

    protected UserLoanHistory(){

    }
    public UserLoanHistory(long userId, String bookname) {
        this.userId = userId;
        this.bookname = bookname;
        this.isReturn = false;
    }

}