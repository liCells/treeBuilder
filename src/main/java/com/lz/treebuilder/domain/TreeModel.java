package com.lz.treebuilder.domain;

import com.lz.treebuilder.tree.*;
import lombok.*;

import java.util.Collection;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TreeModel {
    @TreeId
    private String id;
    private String label;
    @TreeParentId
    private String parentId;
    @TreeSort(type = TreeSortType.DATETIME, rule = TreeSortRule.DESC)
    private String sort;
    @TreeChild
    private Collection<TreeModel> child;
}
