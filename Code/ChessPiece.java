package Coursework;

public class ChessPiece {
   private String color;
   private int index,position,step;
   private int inHome;
  public ChessPiece(String newColor, int newIndex)
  {
      step = 0;
      color = newColor;
      index = newIndex;
      inHome =0;
  }
  //进入棋盘
  public void inGame()
  {
      switch (color)
      {
          case "yellow": position = 1;break;  // 1st player
          case "blue": position = 8;break; // 2nd player
          case "red": position = 15;break; // 3rd player
          case "purple":position = 22;break;  // 4th player
      }
  }
  //Update position and number of steps
    public void setStep(int move)
    {
        step += move;
        if(step == 0) step+=1;
        if(step !=1 &&  (position + move <= 28 && position + move >= 1)) position += move; else
        {
            if(step !=1 && position + move > 28) position = position + move - 28;
            if(step != 1 && position + move <1) position = position + move + 28;
        }
    }

    public void reSet()
    {
        step =0;
        position =0;
    }
    public void setInHome() { inHome ++;}
    public int getInHome() { return inHome;}

    public int getStep()
    {
        return step;
    }

    public int getPosition()
    {
        return position;
    }
}
