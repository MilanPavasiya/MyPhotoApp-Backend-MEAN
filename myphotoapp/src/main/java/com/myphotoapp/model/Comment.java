package com.myphotoapp.model;

import com.myphotoapp.validation.ValidCreatedBy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    private String id;
    private String photoId;
    @Length(min = 5)
    private String message;
    @ValidCreatedBy
    private String createdBy;
    private String dateCreated;

}
