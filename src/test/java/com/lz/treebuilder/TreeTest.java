package com.lz.treebuilder;

import com.lz.treebuilder.domain.TreeModel;
import com.lz.treebuilder.tree.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TreeTest {

    @Test
    public void main() {
        List<TreeModel> all = new ArrayList<>();
        // add element ...
        addElement(all);

        TreeUtil treeUtil = new TreeUtil();
        List<TreeModel> tree = treeUtil.rootKey("0")
                .build(all, TreeModel.class);
        log.info("tree -> {}", tree);
    }

    private void addElement(List<TreeModel> all) {
        all.add(TreeModel.builder()
                .id("1")
                .label("a")
                .parentId("0")
                .sort("2021-01-01")
                .build());
        all.add(TreeModel.builder()
                .id("2")
                .label("b")
                .parentId("1")
                .build());
        all.add(TreeModel.builder()
                .id("3")
                .label("c")
                .parentId("0")
                .sort("2021-01-02")
                .build());
        all.add(TreeModel.builder()
                .id("4")
                .label("d")
                .parentId("1")
                .build());
        all.add(TreeModel.builder()
                .id("5")
                .label("e")
                .parentId("1")
                .build());
        all.add(TreeModel.builder()
                .id("6")
                .label("f")
                .parentId("2")
                .build());
        all.add(TreeModel.builder()
                .id("7")
                .label("g")
                .parentId("2")
                .build());
        all.add(TreeModel.builder()
                .id("8")
                .label("h")
                .parentId("2")
                .build());
        all.add(TreeModel.builder()
                .id("9")
                .label("i")
                .parentId("2")
                .build());
        all.add(TreeModel.builder()
                .id("10")
                .label("j")
                .parentId("2")
                .build());
        all.add(TreeModel.builder()
                .id("11")
                .label("k")
                .parentId("2")
                .build());
        all.add(TreeModel.builder()
                .id("12")
                .label("l")
                .parentId("2")
                .build());
        all.add(TreeModel.builder()
                .id("13")
                .label("m")
                .parentId("2")
                .build());
        all.add(TreeModel.builder()
                .id("14")
                .label("n")
                .parentId("2")
                .build());
        all.add(TreeModel.builder()
                .id("15")
                .label("o")
                .parentId("2")
                .build());
        all.add(TreeModel.builder()
                .id("16")
                .label("p")
                .parentId("2")
                .build());
        all.add(TreeModel.builder()
                .id("17")
                .label("q")
                .parentId("2")
                .build());
        all.add(TreeModel.builder()
                .id("18")
                .label("r")
                .parentId("2")
                .build());
        all.add(TreeModel.builder()
                .id("19")
                .label("s")
                .parentId("2")
                .build());
        all.add(TreeModel.builder()
                .id("20")
                .label("t")
                .parentId("2")
                .build());
    }

}
