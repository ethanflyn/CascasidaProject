
public class Display    {

    public static void printTile(String[][] arr)   {
        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 4 ; j++)   {
                System.out.print(arr[i][j]);
            }
            System.out.println("\u001b[0m");
        }
        return;
    }

  /*    public static void main(String[] args)  {
            String[][] tile = {
                                    {"\u001b[42m  ", "  ", "  ", "  "},
                                    {"\u001b[42m  ", "\u001b[0m  ", "  ", "\u001b[42m  "},
                                    {"\u001b[42m  ", "\u001b[0m  ", "  ", "\u001b[42m  "},
                                    {"\u001b[42m  ", "  ", "  ", "  "}
                                };
            printTile(tile);
        }
    */
}