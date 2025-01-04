package kr.co.jnh.domain;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode

public class AskingDto {
    private Integer no;
    private Integer cno;
    private String user_id;
    private String title;
    private String contents;
    private Date reg_date;
    private Date up_date;
    private Integer state;

    public AskingDto(Integer no, Integer cno, String id, String title, String contents, Date reg_date, Date up_date, Integer state) {
        this.no = no;
        this.cno = cno;
        this.user_id = id;
        this.title = title;
        this.contents = contents;
        this.state = state;
    }
}
