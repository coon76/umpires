package org.umpires.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Data
@Table(name = "location")
public class Location {
    @Id
    private long id;
    @Column(name = "short_desc")
    private String shortDescription;
    @Column(name = "long_desc")
    private String longDescription;
}
