package com.myphotoapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Album {

    @Id
    private String id;
    private String albumId;
    @Length(max = 10)
    private String name;
    private String coverPhotoUrl;

    private String createdBy;
    private String dateCreated;

    public Album(String name, String coverPhotoUrl, String createdBy, String dateCreated) {
        this.name = name;
        this.coverPhotoUrl = coverPhotoUrl;
        this.createdBy = createdBy;
        this.dateCreated = dateCreated;
    }
}
