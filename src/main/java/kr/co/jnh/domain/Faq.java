package kr.co.jnh.domain;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode

public class Faq {
    private Integer no;
    private String question_type;
    private String question;
    private String answer;

    public Faq(Integer no, String question_type, String question, String answer) {
        this.no = no;
        this.question_type = question_type;
        this.question = question;
        this.answer = answer;
    }

}
