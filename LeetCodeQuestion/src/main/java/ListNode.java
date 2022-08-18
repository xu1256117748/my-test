/**
 * @author 徐亚奎
 * @date 2021-10-26 08:50
 * @Description
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("["+val);
        ListNode mid = next;
        while (mid != null) {
            sb.append(",").append(mid.val);
            mid = mid.next;
        }
        sb.append("]");
        return sb == null ? "" : sb.toString();
    }
}
