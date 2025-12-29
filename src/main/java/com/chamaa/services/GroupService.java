package com.chamaa.services;

import com.chamaa.entities.Group;
import com.chamaa.repositories.GroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GroupService {

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    private final GroupRepository groupRepository;

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public Optional<Group> getGroupById(Long id) {
        return groupRepository.findById(id);
    }

    public List<Group> getGroupsByCreatorId(Long creatorId) {
        return groupRepository.findByCreatorId(creatorId);
    }

    public List<Group> getActiveGroups() {
        return groupRepository.findByIsActiveTrue();
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group updateGroup(Long id, Group groupDetails) {
        return groupRepository.findById(id).map(group -> {
            group.setName(groupDetails.getName());
            group.setDescription(groupDetails.getDescription());
            group.setTargetAmount(groupDetails.getTargetAmount());
            group.setGroupImageUrl(groupDetails.getGroupImageUrl());
            return groupRepository.save(group);
        }).orElseThrow(() -> new RuntimeException("Group not found"));
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}
