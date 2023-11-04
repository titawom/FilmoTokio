package com.example.FilmoTokio.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private String title;
    private String review;
    private Long filmId;
}
