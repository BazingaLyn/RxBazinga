package org.lyncc.bazinga.rx.bazinga.algorithm.answer.bst;


import java.util.*;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
public class BstLevelOrder {

    List<List<Integer>> levels = new ArrayList<List<Integer>>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return levels;
        helper(root, 0);
        return levels;
    }

    public void helper(TreeNode node, int level) {
        // start the current level
        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());

        // fulfil the current level
        levels.get(level).add(node.val);

        // process child nodes for the next level
        if (node.left != null)
            helper(node.left, level + 1);
        if (node.right != null)
            helper(node.right, level + 1);
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        if(root==null){
            return Collections.emptyList();
        }
        List<List<Integer>> result=new ArrayList<>();
        Deque<TreeNode> queue=new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            List<Integer> list=new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode temp=queue.poll();
                list.add(temp.val);
                if(temp.left!=null){
                    queue.offer(temp.left);
                }
                if(temp.right!=null){
                    queue.offer(temp.right);
                }
            }
            result.add(list);
        }
        return result;
    }


}
