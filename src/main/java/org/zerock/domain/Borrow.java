package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {
    private long id;
    private long bookId;
    private long person;
    private LocalDateTime borrowTime;
    private boolean isBorrowed;
}
