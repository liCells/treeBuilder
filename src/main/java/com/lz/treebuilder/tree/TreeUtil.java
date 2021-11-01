package com.lz.treebuilder.tree;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class TreeUtil {

    private String[] rootKeys = {"0"};

    Field id, parentId, child, sort;

    public TreeUtil rootKey(String... keys) {
        rootKeys = keys;
        return this;
    }

    public TreeUtil rootKey(Collection<String> keys) {
        rootKeys = keys.toArray(new String[0]);
        return this;
    }

    public <T> List<T> build(Collection<T> collection, Class<T> clazz) {
        setFields(clazz);

        // build element
        Map<Object, List<T>> map = collection.stream()
                .collect(Collectors.groupingBy(item -> {
                    try {
                        return parentId.get(item);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException();
                    }
                }));

        // sort
        if (sort != null) {
            TreeSort annotation = sort.getAnnotation(TreeSort.class);
            TreeSortRule rule = annotation.rule();

            Set<Object> keys = map.keySet();
            for (Object key : keys) {
                List<T> list = map.get(key).stream()
                        .sorted(Comparator.comparing(sub -> {
                            try {
                                return sort.get(sub).hashCode();
                            } catch (IllegalAccessException e) {
                                return 1;
                            }
                        }))
                        .collect(Collectors.toList());

                if (rule == TreeSortRule.DESC) {
                    Collections.reverse(list);
                }

                map.put(key, list);
            }
        }

        // get root
        List<T> treeList = new ArrayList<>();
        for (String rootKey : rootKeys) {
            treeList.addAll(map.get(rootKey));
        }
        if (treeList.isEmpty()) {
            return Collections.emptyList();
        }

        // build tree
        try {
            for (Object item : treeList) {
                build(item, map);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return treeList;
    }

    private <T> void build(Object root, Map<Object, List<T>> map) throws IllegalAccessException {
        List<T> trees = map.get(id.get(root));
        if (trees == null) {
            return;
        }
        child.set(root, trees);
        for (Object tree : trees) {
            build(tree, map);
        }
    }

    private <T> void setFields(Class<T> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(TreeId.class)) {
                declaredField.setAccessible(true);
                id = declaredField;
            } else if (declaredField.isAnnotationPresent(TreeParentId.class)) {
                declaredField.setAccessible(true);
                parentId = declaredField;
            } else if (declaredField.isAnnotationPresent(TreeChild.class)) {
                declaredField.setAccessible(true);
                child = declaredField;
            } else if (declaredField.isAnnotationPresent(TreeSort.class)) {
                declaredField.setAccessible(true);
                sort = declaredField;
            }
        }
    }

    private Date getDate(String str, String pattern) {
        Date date;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            date = formatter.parse(str);
        } catch (ParseException e) {
            return new Date();
        }
        return date;
    }

    private Date strToDateTime(String str) {
        return getDate(str, "yyyy-MM-dd HH:mm:ss");
    }

    private Date strToDate(String str) {
        return getDate(str, "yyyy-MM-dd");
    }
}
