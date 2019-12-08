package id_713;

import java.util.HashMap;

/**
 * 36. 有效的数独
 */
public class LeetCode_36_ValidSudoku {


    /*
    判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

    数字 1-9 在每一行只能出现一次。
    数字 1-9 在每一列只能出现一次。
    数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。

    上图是一个部分填充的有效的数独。

    数独部分空格内已填入了数字，空白格用 '.' 表示。

    示例 1:

    输入:
    [
      ["5","3",".",".","7",".",".",".","."],
      ["6",".",".","1","9","5",".",".","."],
      [".","9","8",".",".",".",".","6","."],
      ["8",".",".",".","6",".",".",".","3"],
      ["4",".",".","8",".","3",".",".","1"],
      ["7",".",".",".","2",".",".",".","6"],
      [".","6",".",".",".",".","2","8","."],
      [".",".",".","4","1","9",".",".","5"],
      [".",".",".",".","8",".",".","7","9"]
    ]
    输出: true

    示例 2:

    输入:
    [
      ["8","3",".",".","7",".",".",".","."],
      ["6",".",".","1","9","5",".",".","."],
      [".","9","8",".",".",".",".","6","."],
      ["8",".",".",".","6",".",".",".","3"],
      ["4",".",".","8",".","3",".",".","1"],
      ["7",".",".",".","2",".",".",".","6"],
      [".","6",".",".",".",".","2","8","."],
      [".",".",".","4","1","9",".",".","5"],
      [".",".",".",".","8",".",".","7","9"]
    ]
    输出: false
    解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
         但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。

    说明:

        一个有效的数独（部分已被填充）不一定是可解的。
        只需要根据以上规则，验证已经填入的数字是否有效即可。
        给定数独序列只包含数字 1-9 和字符 '.' 。
        给定数独永远是 9x9 形式的。

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/valid-sudoku
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public boolean isValidSudoku(char[][] board) {
        // 初始化数据
        HashMap<Integer, Integer>[] rows = new HashMap[9];
        HashMap<Integer, Integer>[] columns = new HashMap[9];
        HashMap<Integer, Integer>[] boxes = new HashMap[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<>();
            columns[i] = new HashMap<>();
            boxes[i] = new HashMap<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if(num != '.') {
                    int n = (int) num;
                    /*
                    这个公式来头:
                        每行第1列分别为 0, 3, 6
                        每行第2列分别为 1, 5, 7

                        对于i,j而言, 先用对应基数 [0,3,6] 再用j对应偏移量 +0, +1, +2

                        (i / 3) * 3 则对应 [0,3,6]
                        (j / 3)则对应 [0,1,2]
                        所以公式为 (i / 3) * 3 + j / 3
                     */
                    int boxIndex = (i / 3) * 3 + j / 3;

                    rows[i].put(n, rows[i].getOrDefault(n , 0) + 1);
                    columns[j].put(n, columns[j].getOrDefault(n , 0) + 1);
                    boxes[boxIndex].put(n, boxes[i].getOrDefault(n , 0) + 1);

                    if (rows[i].get(n) > 1 || columns[j].get(n) > 1 || boxes[boxIndex].get(n) > 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
