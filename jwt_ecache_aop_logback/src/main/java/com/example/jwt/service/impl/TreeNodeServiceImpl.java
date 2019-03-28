package com.example.jwt.service.impl;

import com.example.jwt.constants.DepartmentConstants;
import com.example.jwt.domain.system.Department;
import com.example.jwt.domain.system.TreeNode;
import com.example.jwt.service.DepartmentService;
import com.example.jwt.service.TreeNodeService;
import com.example.jwt.system.DepartmentRepository;
import com.example.jwt.system.TreeNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreeNodeServiceImpl implements TreeNodeService {

    @Autowired
    private TreeNodeRepository treeNodeRepository;


    @Override
    public TreeNode createTreeNode(TreeNode treeNode) {
        TreeNode newTreeNode = new TreeNode();

        newTreeNode.setTreeKey(treeNode.getTreeKey());
        newTreeNode.setName(treeNode.getName());
        newTreeNode.setParent_id(treeNode.getParent_id());
        newTreeNode.setAge(treeNode.getAge());
        newTreeNode.setLevel(treeNode.getLevel());
        newTreeNode.setExpand(treeNode.getExpand());
        newTreeNode.setAddress(treeNode.getAddress());
        newTreeNode.setRemarks(treeNode.getRemarks());
//        newTreeNode.setChildren(treeNode.getChildren());

        return treeNodeRepository.save(newTreeNode);
    }


    @Override
    public TreeNode save(TreeNode treeNode) {
        return treeNodeRepository.save(treeNode);
    }

    @Override
    public List<TreeNode> getAllTreeNodes() {
        return treeNodeRepository.findAll();
    }

    @Override
    public Optional<TreeNode> getTreeNode(Long id) {
        Optional<TreeNode> treeNode = treeNodeRepository.findById(id);
        return treeNode;
    }

    @Override
    public void delete(Long id) {
        treeNodeRepository.deleteById(id);
    }
}
