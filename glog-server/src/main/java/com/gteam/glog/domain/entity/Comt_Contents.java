package com.gteam.glog.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "comt_cont")
public class Comt_Contents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comt_cont_idx")
    private int idx;

    @Column(name = "cret_dt")
    private Date cret_dt;

    @Column(name = "cont")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "comt_idx")
    private Commnet comt_idx;
}
