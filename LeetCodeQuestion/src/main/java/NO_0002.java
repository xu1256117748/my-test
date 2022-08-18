import com.sun.org.apache.regexp.internal.RE;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author 徐亚奎
 * @date 2021-10-25 08:28
 * @Description
 * 2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 *
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * 提示：
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 * 通过次数1,036,825提交次数2,531,600
 */
public class NO_0002 {
    public static void main(String[] args) {

        List<Integer> listOne = Arrays.asList(1, 2, 3, 4);
        List<Integer> listTwo = Arrays.asList(5, 7, 8, 6, 1);
//        handle(listOne,listTwo);
        ListNode oneNode = new ListNode(1,
                new ListNode(2,
                        new ListNode(2,
                                new ListNode(2,
                                        new ListNode(2,new ListNode(2))))));

        System.out.println(oneNode);
        ListNode twoNode = new ListNode(9,
                new ListNode(9,
                        new ListNode(9,
                                new ListNode(9))));
        System.out.println(twoNode);
        handle(oneNode, twoNode);
    }
    public static void handle(List<Integer> listOne, List<Integer> listTwo){
        Integer oneLength = listOne.size();
        Integer twoLength = listTwo.size();
        Integer index = 0;
        List<Integer> result = new ArrayList<>(150);
        while (index < oneLength || index < twoLength){
            if (index==result.size()){
                result.add(index, 0);
            }
            Integer oneNum = index < oneLength ? listOne.get(index) : 0;
            Integer twoNum = index < twoLength ? listTwo.get(index) : 0;
            Integer resultNum = result.get(index);
            Integer total = oneNum + twoNum + resultNum;
            result.set(index,total%10);
            result.add(index+1, total/10);
            index++;
        }
        result.remove(result.size()-1);
        StringBuilder sb1 = new StringBuilder("");
        listOne.stream().map(s->sb1.append(s)).count();
        while (sb1.toString().length() < result.size()){
            sb1.append(0);
        }
        StringBuilder sb2 = new StringBuilder("");
        listTwo.stream().map(s->sb2.append(s)).count();
        while (sb2.toString().length() < result.size()){
            sb2.append(0);
        }
        StringBuilder sb3 = new StringBuilder("");
        result.stream().map(s->sb3.append(s)).count();
        System.out.println(sb1.reverse()+" +");
        System.out.println(sb2.reverse()+" =");
        System.out.println(sb3.reverse());
    }
    public static void handle(ListNode l1,ListNode l2){
        ListNode iNode = l1;
        ListNode jNode = l2;
        ListNode resultNode = new ListNode(0);
        ListNode midNode = resultNode;
        while (iNode != null || jNode != null){
            Integer oneNum = iNode == null ? 0 : iNode.val;
            Integer twoNum = jNode == null ? 0 : jNode.val;
            Integer resultNum = midNode.val;
            Integer total = oneNum + twoNum + resultNum;
            midNode.val = total%10;
            iNode = iNode == null ? null : iNode.next;
            jNode = jNode == null ? null : jNode.next;
            midNode.next = new ListNode(total/10);
            midNode = midNode.next;

        }

//        resultNode.

        System.out.println(resultNode);
//        result.remove(result.size()-1);
//        StringBuilder sb1 = new StringBuilder("");
//        oneNode.stream().map(s->sb1.append(s)).count();
//        StringBuilder sb2 = new StringBuilder("");
//        listTwo.stream().map(s->sb2.append(s)).count();
//        while (sb2.toString().length() < result.size()){
//            sb2.append(0);
//        }
//        StringBuilder sb3 = new StringBuilder("");
//        result.stream().map(s->sb3.append(s)).count();
//        System.out.println(sb1.reverse()+" +");
//        System.out.println(sb2.reverse()+" =");
//        System.out.println(sb3.reverse());
    }
    public Integer getValue(ListNode listNode,Integer index){
        if (listNode == null || index < 0){
            return null;
        }else if (index == 0){
            return listNode.val;
        }else {
            ListNode midNode = listNode.next;
            Integer num = index-1;
            while(num != 0 && midNode != null){
                midNode = midNode.next;
                num--;
            }
            return midNode == null ? null : midNode.val;
        }
    }
}
