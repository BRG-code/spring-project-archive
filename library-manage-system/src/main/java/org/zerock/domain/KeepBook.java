package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeepBook {
    private long id;
    private String name;
    private String writer;
    private long isbn;
    private int categoryCode;
    private String categoryName;
    private String location;
    // JSTL 에서 불러오기 위해서는 java.lang.Boolean을 사용해야함.
    private Boolean isBorrowed;
}
