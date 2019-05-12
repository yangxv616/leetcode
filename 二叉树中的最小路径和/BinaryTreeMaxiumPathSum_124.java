public class BinaryTreeMaxiumPathSum_124
{
    public static int maxPathSum(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
        setMap(root, map);

        ArrayList<TreeNode> nodes = new ArrayList<>();
        order(root, nodes);

        int r1 = findMax(map);

        int r2 = -10000000;
        for(Map.Entry<TreeNode, Integer> entry : map.entrySet())
        {
            int v = entry.getKey().val;
            if(entry.getKey().left != null) v += map.get(entry.getKey().left);
            if(entry.getKey().right != null) v += map.get(entry.getKey().right);
            r2 = v > r2 ? v : r2;
        }

        return r1 > r2 ? r1 : r2;
    }

    public static int setMap(TreeNode root, Map<TreeNode, Integer> map)
    {
        if(root.left == null && root.right == null)
            map.put(root, root.val);
        else if(root.left == null)
            map.put(root, root.val + setMap(root.right, map));
        else if(root.right == null)
            map.put(root, root.val + setMap(root.left, map));
        else
        {
            int l = setMap(root.left, map);
            int r = setMap(root.right, map);
            int max = l > r ? l : r;
            map.put(root, root.val + max);
        }
        return map.get(root) > 0 ? map.get(root) : 0;
    }

    public static void order(TreeNode node, ArrayList<TreeNode> nodes)
    {
        if(node == null)
            return;

        order(node.left, nodes);
        nodes.add(node);
        order(node.right, nodes);
    }

    public static int findMax(Map<TreeNode, Integer> map)
    {
        int max = -1000000;
        for(Map.Entry<TreeNode, Integer> enter : map.entrySet())
            max = enter.getValue() > max ? enter.getValue() : max;
        return max;
    }
}