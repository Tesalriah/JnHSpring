package kr.co.jnh.domain;

import java.util.Arrays;

public enum ReportReason {
    UNRELATED_CONTENT(1, "상품과 관련없는 내용"),
    ABUSE(2, "욕설"),
    INAPPROPRIATE_IMAGE(3, "부적절한 이미지 게시"),
    ETC(4, "기타");

    private final int code;
    private final String description;

    ReportReason(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static ReportReason fromCode(int code) {
        return Arrays.stream(values())
                .filter(r -> r.code == code)
                .findFirst()
                .orElseGet(() -> ReportReason.ETC);
    }

    /*public static Optional<ReportReason> fromCode(int code) {
        return Arrays.stream(values())
                .filter(r -> r.code == code)
                .findFirst();
    }*/
}