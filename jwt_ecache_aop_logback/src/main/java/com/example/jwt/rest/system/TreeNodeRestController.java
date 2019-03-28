package com.example.jwt.rest.system;

import com.example.jwt.domain.system.TreeNode;
import com.example.jwt.domain.system.TreeNode;
//import com.example.jwt.service.treeNodeService;
import com.example.jwt.service.TreeNodeService;
import com.example.jwt.utils.HeaderUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;


@RestController
@RequestMapping("/api")
@Slf4j
@Api(value = "TreeNodeRestController", description = "treeNode API")
public class TreeNodeRestController {

    @Autowired
    private TreeNodeService treeNodeService;

    //    ng-zorro tree table
    @PostMapping("/tree-nodes")

    public ResponseEntity<TreeNode> createTreeNode(@Valid @RequestBody TreeNode treeNode ) throws URISyntaxException {
        log.info("REST request to save TreeNode : {}", treeNode);
//        List<TreeNode> children = new ArrayList<>();
        TreeNode newTreeNode = treeNodeService.createTreeNode(treeNode);
        return ResponseEntity.created(new URI("/api/tree-nodes/" + newTreeNode.getName()))
                .headers(HeaderUtil.createAlert("treeNode.created", newTreeNode.getName()))
                .body(newTreeNode);

    }

    @PutMapping("/tree-nodes")
    public ResponseEntity updateTreeNode(@Valid @RequestBody TreeNode treeNode) throws URISyntaxException {
        log.info("REST request to update TreeNode : {}", treeNode);

        TreeNode newTreeNode = treeNodeService.save(treeNode);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, treeNode.getId().toString()))
                .body(newTreeNode);
    }

    @GetMapping( "/tree-nodes")
    public ResponseEntity getAllTreeNode() throws URISyntaxException {
        log.info("REST request to get All TreeNode : {}");
        List<TreeNode> newTreeNode = treeNodeService.getAllTreeNodes();

        return new ResponseEntity<>(newTreeNode, HttpStatus.OK);
    }

    @GetMapping( "/tree-nodes/{id}")
    public ResponseEntity getTreeNode(@PathVariable Long id) throws URISyntaxException {
        log.info("REST request to get single TreeNode : {}",id);
        Optional<TreeNode> newTreeNode = treeNodeService.getTreeNode(id);

        return new ResponseEntity<>(newTreeNode, HttpStatus.OK);
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(newTreeNode));
    }

    @DeleteMapping("/tree-nodes/{id}")
    public ResponseEntity<Void> deleteTreeNode(@PathVariable Long id) {
        log.info("REST request to delete TreeNode : {}", id);
        treeNodeService.delete(id);

        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
