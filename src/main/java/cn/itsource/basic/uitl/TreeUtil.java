package cn.itsource.basic.uitl;

import cn.hutool.core.collection.CollectionUtil;
import cn.itsource.basic.domain.BaseTreeNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @author Hello
 */
@Slf4j
public class TreeUtil {

    //java8构建树

    /**
     * 将平铺的List<T>转换成Tree
     * @param listNodes
     * @param <T>
     * @return
     */
    public static <T extends BaseTreeNode> List<T> getTree(List<T> listNodes){
//        判断当前listNodes 是否为空
        if (CollectionUtil.isNotEmpty(listNodes)){
            //根据pid分组 key 都是parentId ,value 所有的permission对象
            Map<Long, List<T>> listMap = listNodes.stream().collect(Collectors.groupingBy(t -> Optional.ofNullable(t.getParentId()).orElse(0L)));
            //得到一级分类,第一级pid==0

            List<T> oneLevelClassify = listMap.get(0L);
            //递归
            List<T> list = getChildList(oneLevelClassify, listMap);
            return list;
        }
        return new ArrayList<>();
    }

    /**
     * 获取子节点
     * @param classifies
     * @param groupMap
     * @param <T>
     * @return
     */
    private static <T extends BaseTreeNode> List<T> getChildList(List<T> classifies, Map<Long, List<T>> groupMap) {
        //判断一级的数据是否为空
       if (CollectionUtil.isNotEmpty(classifies)){
           //遍历 tree 父级对象
           for (T tree:classifies){
               //所有的二级的list对象
               List<T> classifyList = groupMap.get(tree.getId());
               //判断是否为空
               if (!CollectionUtils.isEmpty(classifyList)) {
                   tree.setChildren((List<BaseTreeNode>)classifyList);
                   getChildList(classifyList, groupMap);
               } else {
                   tree.setChildren(null);
               }
           }
           //循环遍历
//           classifies.forEach(tree -> {
//               List<T> classifyList = groupMap.get(tree.getId());
//               if (!CollectionUtils.isEmpty(classifyList)) {
//                   tree.setChildren((List<BaseTreeNode>)classifyList);
//                   getChildList(classifyList, groupMap);
//               } else {
//                   tree.setChildren(null);
//               }
//           });
           return classifies;
       }
       return new ArrayList<>();
    }
//
//
//    /**
//     * 在list中检索具体一条数据，拿出这条数据以及上面的所有的父级
//     * @param listNodes 树中的所有元素
//     * @param t 目标节点
//     */
//    public static <T extends BaseEntity & IBaseEntityTree> List<T> fatherList(List<T> listNodes, T t){
//        if(ObjectUtil.isNull(t) || !listNodes.contains(t)) return new ArrayList<>();
//        List<T> list = new ArrayList<>();
//        list.add(t);
//        getAllFatherNode(listNodes, t, list);
//        Collections.reverse(list);
//        return list;
//    }
//
//    private static <T extends BaseEntity & IBaseEntityTree> void getAllFatherNode(List<T> listNodes, T t, List<T> list){
//        if(!ObjectUtil.equal(t.getPid(),0)){
//            List<T> tList = listNodes.stream().filter(p -> ObjectUtil.equal(t.getPid(), p.getId())).collect(Collectors.toList());
//            if(CollectionUtil.isEmpty(tList)) return;
//            list.add(tList.get(0));
//            getAllFatherNode(listNodes, tList.get(0), list);
//        }
//    }
//
//    /**
//     *  检索一个树中的某一节点下的子节点
//     * @param allList 树中的所有元素list
//     * @param t  要检索的元素
//     * @param treeTypeEnum 类型：ALL_CHILDREN 查询该节点下所有的子节点，LAST_NODE_CHILDREN查询该节点下所有的末级节点
//     * @param <T>
//     * @return
//     */
//    public static <T extends BaseEntity & IBaseEntityTree> List<T> childrenList(List<T> allList, T t, TreeTypeEnum treeTypeEnum){
//        if(ObjectUtil.isNull(t) || !allList.contains(t)) return new ArrayList<>();
//        List<T> result = new ArrayList<>();
//        List<T> childList = allList.stream().filter(p -> ObjectUtil.equal(t.getId(), p.getPid())).collect(Collectors.toList());
//        if(CollectionUtil.isEmpty(childList)) result.add(t);
//        getAllLastNode(allList, result, childList, treeTypeEnum.getType());
//        return result;
//    }
//
//
//    private static <T extends BaseEntity & IBaseEntityTree> void getAllLastNode(List<T> allList, List<T> result, List<T> childList, Integer type){
//        if(CollectionUtil.isEmpty(childList)) return;
//        for (T t : childList) {
//            List<T> childrens = allList.stream().filter(p -> ObjectUtil.equal(p.getPid(), p.getId())).collect(Collectors.toList());
//            if(CollectionUtil.isEmpty(childrens)) {
//                result.add(t);
//            }else {
//                if(ObjectUtil.equal(type, TreeTypeEnum.ALL_CHILDREN.getType())) result.addAll(childrens);
//                getAllLastNode(allList, result, childrens, type);
//            }
//        }
//    }
//
//
//
//
//
//    /**
//     *  将所有的数据转换为一个树形结构
//     * @param list
//     * @param <T>
//     * @return
//     */
//    public static <T extends BaseEntity & IBaseEntityTree> List<Ztree<T>> buildTree(List<T> list){
//        List<Ztree<T>> res = new ArrayList<>();
//        List<T> collect = list.stream().filter(p -> ObjectUtil.equal(p.getPid(),0)).collect(Collectors.toList());
//        for (T t : collect) {
//            Ztree<T> ztree = new Ztree();
//            setZtree(t, list, ztree);
//            res.add(ztree);
//        }
//        return res;
//    }
//
//    private static <T extends BaseEntity & IBaseEntityTree> void setZtree(T t, List<T> list, Ztree ztree) {
//        ztree.setT(t);
//        ztree.setChildren(getChildrens(t, list));
//    }
//
//    private static <T extends BaseEntity & IBaseEntityTree> List getChildrens(T t, List<T> list) {
//        List res = new ArrayList<>();
//        for (T l : list) {
//            Ztree ztree = new Ztree();
//            if(Objects.equals(l.getPid(), t.getId())){
//                setZtree(l, list, ztree);
//                res.add(ztree);
//            }
//        }
//        return res;
//    }







//    public  static void main(String[] args){

        /*List<Classify> treeNodeList = new ArrayList<>();
        Classify treeNode1 = new Classify(1,0,"首页");
        Classify treeNode2 = new Classify(2,0,"订单");
        Classify treeNode3 = new Classify(3,1,"预约");
        Classify treeNode4 = new Classify(4,2,"捐献");
        Classify treeNode5 = new Classify(5,4,"我的订单");
        Classify treeNode6 = new Classify(6,5,"个人中心");
        Classify treeNode7 = new Classify(7,6,"个人中心2");
        Classify treeNode8 = new Classify(8,9,"个人中心3");
        treeNodeList.add(treeNode1);
        treeNodeList.add(treeNode6);
        treeNodeList.add(treeNode5);
        treeNodeList.add(treeNode3);
        treeNodeList.add(treeNode4);
        treeNodeList.add(treeNode2);
        treeNodeList.add(treeNode7);
        treeNodeList.add(treeNode8);
        TreeUtil treeUtil = new TreeUtil();
        try {
            System.out.print(JSONUtil.obj2json(treeUtil.treeMenu(treeNodeList)));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
//    }

}
