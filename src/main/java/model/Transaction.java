package model;

import lombok.Getter;

import java.util.Date;

public class Transaction {
    private int id;
    @Getter
    private Date date;
    @Getter
    private TransactionType type;
    @Getter
    private int value;
}
