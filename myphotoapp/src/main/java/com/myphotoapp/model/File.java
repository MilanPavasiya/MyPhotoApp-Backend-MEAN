package com.myphotoapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @Id
    private String fileId;
    private String name;
    private String createdBy;
    private String dateCreated;
}
