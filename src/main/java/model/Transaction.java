package model;

import lombok.Getter;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    @Getter
    private LocalDateTime date;
    @Getter
    private TransactionType type;
    @Getter
    private int value;
}
