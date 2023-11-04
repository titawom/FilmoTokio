package com.example.FilmoTokio.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private String title;
    private String textReview;
    private String date;
    private Long filmId;
    private Long userId;
}
