package com.myphotoapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

    @Id
    private String id;
    private String albumId;
    private String photoUrl;

    private String createdBy;
    private String dateCreated;

}
