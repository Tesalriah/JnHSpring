package kr.co.jnh.domain;

import lombok.*;
import java.util.Date;

@NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode

public class NoticeDto {
    private Integer bno;
    private String id;
    private Integer must_read;
    private String category;
    private String title;
    private String contents;
    private Date reg_date;
    private Date up_date;
    private Integer number;


    public NoticeDto(Integer bno, String id, Integer must_read, String category, String title, String contents) {
        this.bno = bno;
        this.id = id;
        this.must_read = must_read;
        this.category = category;
        this.title = title;
        this.contents = contents;
    }
}
