package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Isbn {
    private long isbn;
    private String name;
    private String writer;
    private int category;
    private long location;
    private int count;
}
