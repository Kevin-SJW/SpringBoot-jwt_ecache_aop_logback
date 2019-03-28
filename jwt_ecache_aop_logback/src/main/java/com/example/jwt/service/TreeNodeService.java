package com.example.jwt.service;

import com.example.jwt.domain.system.Department;
import com.example.jwt.domain.system.TreeNode;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


public interface TreeNodeService {


    TreeNode createTreeNode(TreeNode treeNode);

    TreeNode save(TreeNode treeNode);

    List<TreeNode> getAllTreeNodes();

    Optional<TreeNode> getTreeNode(Long id);

    void delete(Long id);


}
