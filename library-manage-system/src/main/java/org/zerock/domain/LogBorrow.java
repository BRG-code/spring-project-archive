package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogBorrow {
    private long borrowId;
    private long bookId;
    private String bookName;
    private String memberName;
    private LocalDateTime borrowTime;
    private boolean isBorrowed;
}
