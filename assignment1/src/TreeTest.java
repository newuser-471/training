import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val){
        this.val = val;
    }

}

public class TreeTest {

    public List<List<Integer>> levelOrderTraversal(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int size = q.size();
            for(int i = 0;i<size;i++){
                TreeNode cur = q.poll();
                list.add(cur.val);
                if(cur.left!=null){
                    q.offer(cur.left);
                }
                if(cur.right!=null){
                    q.offer(cur.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
