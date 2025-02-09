package kr.co.jnh.domain;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode

public class Question {
    private Integer qno;
    private Integer ano;
    private String user_id;
    private String product_id;
    private String contents;
    private Date reg_date;

    public Question(Integer qno, Integer ano, String user_id, String product_id, String contents, Date reg_date) {
        this.qno = qno;
        this.ano = ano;
        this.user_id = user_id;
        this.product_id = product_id;
        this.contents = contents;
    }
}
