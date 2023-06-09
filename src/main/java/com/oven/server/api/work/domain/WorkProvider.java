package com.oven.server.api.work.domain;

import com.oven.server.api.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Slf4j
public class WorkProvider extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_provider_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    private Work work;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    public void setWork(Work work) {
        this.work = work;
        work.getProviderList().add(this);
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
        provider.getWorkList().add(this);
    }

}
