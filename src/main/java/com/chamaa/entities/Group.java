package com.chamaa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "groups", indexes = {
    @Index(name = "idx_groups_creator_id", columnList = "creator_id"),
    @Index(name = "idx_groups_is_active", columnList = "is_active"),
    @Index(name = "idx_groups_name", columnList = "name")
})
@Getter
@Setter
public class Group extends BaseEntity {

    @Column(nullable = false, length = 255)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @Column(nullable = false)
    private Integer memberCount = 0;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false)
    private Double targetAmount = 0.0;

    @Column(length = 500)
    private String groupImageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public String getGroupImageUrl() {
        return groupImageUrl;
    }

    public void setGroupImageUrl(String groupImageUrl) {
        this.groupImageUrl = groupImageUrl;
    }
}

