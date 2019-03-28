package com.example.jwt.system;

import com.example.jwt.domain.system.Department;
import com.example.jwt.domain.system.TreeNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TreeNodeRepository extends JpaRepository<TreeNode, Long> {
//    Department findByDeptKey(String department);


    List<TreeNode> findByLevel(Long level);

    @Query(value = "select id from tree_node order by id desc limit 1", nativeQuery = true)
    Long getLatestId();
}

