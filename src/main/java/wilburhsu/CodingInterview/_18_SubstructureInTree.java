package wilburhsu.CodingInterview;

public class _18_SubstructureInTree {
    public boolean hasSubtree(TreeNode root1,TreeNode root2) {
        boolean result = false;
        //当Tree1和Tree2的根结点都不为空的时候才进行比较，否则直接返回false
        if(root1 != null && root2 != null){
            //如果在Tree1中找到了与Tree2根结点值相同的结点
            if(root1.val == root2.val)
                //以这个根结点为起点判断是否包含Tree2
                result = doesTree1HaveTree2(root1,root2);
            //如果找不到，那么就以root的左儿子为起点，判断是否包含Tree2
            if(!result)
                result = hasSubtree(root1.left,root2);
            //如果还是找不到，那么就以root的右儿子为起点，判断是否包含Tree2
            if(!result)
                result = hasSubtree(root1.right,root2);
        }
        //返回结果
        return result;
    }

    public boolean doesTree1HaveTree2(TreeNode node1,TreeNode node2){
        //注意第一个if判断不能放到后面
        //到达了Tree2的叶结点，Tree2已遍历完，说明前面所有的结点都能匹配上，返回true
        if(node2 == null)
            return true;
        //如果Tree2还没有遍历完，Tree1却遍历完了。返回false
        if(node1 == null)
            return false;
        //如果其中有一个点没有对应上，返回false
        if(node1.val != node2.val)
            return false;
        //如果根结点对应的上，那么就分别去左右子结点里面匹配
        return doesTree1HaveTree2(node1.left,node2.left) &&
                doesTree1HaveTree2(node1.right,node2.right);
    }
}
