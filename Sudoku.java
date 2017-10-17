package mysudoku;
import java.awt.*;
import java.awt.color.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.TreeSet;
import java.io.*;
class Sudoku extends JFrame implements ActionListener, KeyListener
{

		JButton saveButton = new JButton("Save");
		JButton resumeButton =  new JButton("Resume");
		JButton score = new JButton("High Score");
		JButton author = new JButton("Developed By");
		JButton rules = new JButton("Rules");
		Color bisque = new Color(255, 228, 225);
		Color tan = new Color(230, 230, 250);
		Color fgreen = new Color(240, 128, 128);

		JTextField tf[][]=new JTextField[9][9];
		int[][] solution = new int[9][9];
		int[][] puzzle = new int[9][9];
		Sudoku()
		{
				super("Play Sudoku .............");
				setSize(500,500);
				JPanel pe = new JPanel();
				pe.setLayout(new GridLayout(9,1));
				JButton playEasy = new JButton("Play[Easy]");
				pe.add(playEasy);
				JButton playMedium = new JButton("Play[Medium]");
				pe.add(playMedium);
				JButton playDifficult = new JButton("Play[Difficult]");
				pe.add(playDifficult);
				JButton submitButton = new JButton("Submit");
				pe.add(submitButton);
				pe.add(saveButton);
				pe.add(resumeButton);
				pe.add(score);
				pe.add(rules);
				pe.add(author);
				this.add(pe, BorderLayout.EAST);
				JPanel pc= new JPanel();
				pc.setLayout(new GridLayout(9,9));
				for(int i=0;i<9;i++)
				{	for(int j=0;j<9;j++)
					{
						tf[i][j]=new JTextField("");
						tf[i][j].setEditable(false);
						pc.add(tf[i][j]);
						if(i==3 || i==4 || i==5 || j==3 || j==4 || j==5)
						{
							if(2<i && i<6 && 2<j && j<6)
							{
								tf[i][j].setBackground(tan);
							}
							else
								tf[i][j].setBackground(bisque);
						}
						else
							tf[i][j].setBackground(tan);
					}
				}
				this.add(pc);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				SubmitListener sl = new SubmitListener(tf, puzzle);
				submitButton.addActionListener(sl);
				saveButton.addActionListener(this);
				resumeButton.addActionListener(this);
				score.addActionListener(this);
				author.addActionListener(this);
				rules.addActionListener(this);
				PuzzleListener pl = new PuzzleListener(solution,puzzle,tf, this);
				playEasy.addActionListener(pl);
				playMedium.addActionListener(pl);
				playDifficult.addActionListener(pl);
				setVisible(true);
				//show();
		}
		public void actionPerformed(ActionEvent e)
		{
			JButton but = (JButton) e.getSource();
			if(but == saveButton)
			{
				try
				{
					FileOutputStream fos = new FileOutputStream("lastgame.sud");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(puzzle);
					String partiallyFilledPuzzle[][] = new String[9][9];
					for(int i = 0; i < 9; i++)
						for(int j = 0; j < 9; j++)
							partiallyFilledPuzzle[i][j] = tf[i][j].getText();
					oos.writeObject(partiallyFilledPuzzle);
					oos.writeObject(solution);
				}
				catch(IOException ex)
				{
					System.out.println(ex.getMessage());
				}
			}
			else if(but == resumeButton)
			{
				try
				{
					FileInputStream fis = new FileInputStream("lastgame.sud");
					ObjectInputStream ois = new ObjectInputStream(fis);
					puzzle = (int[][]) ois.readObject();
					String partiallyFilledPuzzle[][] = (String[][])ois.readObject();
					solution = (int[][]) ois.readObject();
					for(int i = 0; i < 9; i++)
					{
						for(int j = 0; j < 9; j++)
						{
							tf[i][j].setText(partiallyFilledPuzzle[i][j]);
							tf[i][j].setHorizontalAlignment(JTextField.CENTER);
							tf[i][j].setFont(new Font("ARIALBD",Font.BOLD,20));
							tf[i][j].setForeground(Color.BLUE);
							if(puzzle[i][j] == 0)
							{	tf[i][j].setEditable(true);
								tf[i][j].addKeyListener(this);
								tf[i][j].setForeground(fgreen);
							}
							else
							{
								tf[i][j].setEditable(false);
							}
							/*
							if(i==3 || i==4 || i==5 || j==3 || j==4 || j==5)
							{
								if(2<i && i<6 && 2<j && j<6)
								{
									tf[i][j].setBackground(Color.YELLOW);
								}
								else
									tf[i][j].setBackground(Color.RED);
							}
							else
								tf[i][j].setBackground(Color.YELLOW);
						   */
						}
					}
					/*
					for(int i = 0; i < 9; i++)
					{
						for(int j = 0; j < 9; j++)
						{
							tf[i][j].setText(partiallyFilledPuzzle[i][j]);
						}
					}
					*/
					setVisible(true);
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
			}
			else if(but == score)
			{
				int highScore = 0;
				try
				{	File f = new File("Score.sud");
					Scanner sc = new Scanner(f);
					highScore = sc.nextInt();
				}
				catch(Exception ex)
				{
					//ex.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, "Current Highest Score is : " + highScore);
			}
			else if (but == author)
			{
				JOptionPane.showMessageDialog(this, "Developed By:\n        Nimisha Jain\n        Akshita Gupta\n");
			}
			else if (but == rules)
			{
				JFrame helpFrame = new JFrame("Rules");
				JTextArea  ta = new JTextArea();
				JScrollPane js = new JScrollPane(ta);
				helpFrame.add(js);
				helpFrame.setSize(500,500);
				helpFrame.setVisible(true);
				String help = "A standard Sudoku puzzle consists of a grid of 9 blocks. Each block contains 9 boxes arranged in 3 rows and 3 columns.\n\n";
				help = help + "The Basic Rules of Sudoku:\n\n";
				help = help + "There is valid solution to each Sudoku puzzle. ";
				help = help + "The only way the puzzle can be considered solved correctly is ";
				help = help + "when all 81 boxes contain numbers and the other Sudoku rules ";
				help = help + "have been followed.\n\n";
				help = help + "When you start a game of Sudoku, some blocks will be pre-filled ";
				help = help + "for you. You cannot change these numbers in the course of the game.\n\n";
				help = help + "Each column must contain all of the numbers 1 through 9 and no ";
				help = help + "two numbers in the same column of a Sudoku puzzle can be the same.\n\n";
				help = help + "Each row must contain all of the numbers 1 through 9 and no two ";
				help = help + "numbers in the same row of a Sudoku puzzle can be the same.\n\n";
				help = help + "Each block must contain all of the numbers 1 through 9 and no two ";
				help = help + "numbers in the same block of a Sudoku puzzle can be the same.";
				ta.setText(help);
				ta.setFont(new Font("Arial", Font.BOLD, 18));
				ta.setWrapStyleWord(true);
				ta.setLineWrap(true);
				ta.setEditable(false);
		}
		}
		public void keyPressed(KeyEvent e)
		{
		}
		public void keyTyped(KeyEvent e)
		{
				char c = e.getKeyChar();
				if (!((c >= '1') && (c <= '9') ||
      					(c == KeyEvent.VK_BACK_SPACE) ||
      								(c == KeyEvent.VK_DELETE)))
   				{
   					Toolkit.getDefaultToolkit().beep();
   					e.consume();
				}
				JTextField tf = (JTextField) e.getSource();
				int l = tf.getText().length();
				if(l >= 1)
				{	Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
		}
		public void keyReleased(KeyEvent e)
		{
		}
		public static void main (String[] args)
		{
				Sudoku ob=new Sudoku();
		}
}

class SubmitListener implements ActionListener
{	
	
	JTextField tf[][];
	int puzzle[][];
	SubmitListener(JTextField tf[][], int[][] puzzle)
	{
		this.tf = tf;
		this.puzzle = puzzle;
	}
	public void actionPerformed(ActionEvent e)
	{   int no;
		boolean won = true;
		int newScore = 0;
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				try
				{
					no = Integer.parseInt(tf[i][j].getText());
					if(no < 1 || no > 9)
					{	JOptionPane.showMessageDialog(null, "Only digits 1 to 9 are allowed");
						return;
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Puzzle is incomplete");
					return;
				}
				if(puzzle[i][j] == 0)
					newScore++;
			}
		}
		TreeSet ts = null;
		for(int i=0;i<9;i++)
		{   ts = new TreeSet();
			for(int j=0;j<9;j++)
			{  no = Integer.parseInt(tf[i][j].getText());
				ts.add(no);
			}
			if(ts.size() != 9)
			{
				won = false;
				break;
			}
		}
		if(won)
		{	for(int j=0;j<9;j++)
			{   ts = new TreeSet();
				for(int i=0; i<9; i++)
				{  no = Integer.parseInt(tf[i][j].getText());
					ts.add(no);
				}
				if(ts.size() != 9)
				{
					won = false;
					break;
				}
			}
		}
		if(won)
		{
        	l1:for (int row = 0; row < 9;  row = row + 3)
        	{
            	for (int col = 0; col < 9; col = col + 3)
            	{  ts = new TreeSet();
            		for(int i = row; i < row + 3; i++)
            			for(int j = col; j < col + 3; j++)
            				ts.add(Integer.parseInt(tf[i][j].getText()));
            				if(ts.size() != 9)
							{
								won = false;
								break l1;
							}
	            }
        	}
		}
		if(won)
		{      int score = 0;
				JOptionPane.showMessageDialog(null, "You won the Game\n" + "     Score :" + newScore);
				File f = new File("Score.sud");
				try
				{
					if(f.isFile())
					{
						Scanner sc = new Scanner(f);
						score = sc.nextInt();
					}
					if(newScore > score)
					{
						FileWriter fw = new FileWriter(f);
						fw.write("" + newScore);
						fw.close();
					}
				}
				catch(IOException ex)
				{
					ex.printStackTrace();
				}
		}
		else
			JOptionPane.showMessageDialog(null,"You lose the Game");
	}
}

class PuzzleListener implements ActionListener, KeyListener
{
	Color fgreen = new Color(240, 128, 128);
	public static final int EASY  = 1;
	public static final int MEDIUM = 2;
	public static final int DIFFICULT  = 3;
	static int solution[][];
	static int puzzle[][];
	JTextField tf[][];
	Sudoku frame;

	PuzzleListener(int solution[][], int puzzle[][], JTextField tf[][], Sudoku frame)
	{
		this.solution = solution;
		this.puzzle = puzzle;
		this.tf = tf;
		this.frame = frame;
	}
	public void keyPressed(KeyEvent e)
	{
	}
	public void keyTyped(KeyEvent e)
	{
    	char c = e.getKeyChar();
      	if (!((c >= '1') && (c <= '9') ||
         		(c == KeyEvent.VK_BACK_SPACE) ||
         				(c == KeyEvent.VK_DELETE)))
        {
        	Toolkit.getDefaultToolkit().beep();
        	e.consume();
      	}
      	JTextField tf = (JTextField) e.getSource();
      	int l = tf.getText().length();
      	if(l >= 1)
      	{	Toolkit.getDefaultToolkit().beep();
      		e.consume();
      	}
    }
	public void keyReleased(KeyEvent e)
	{
	}
	public void actionPerformed(ActionEvent e)
	{
		for(int i=0;i<9;i++)
		{	for(int j=0;j<9;j++)
			{
				solution[i][j]  = 0;
				puzzle[i][j] = 0;
			}
		}
		generateSolution();
		JButton but = (JButton) e.getSource();
		if(but.getLabel().equals("Play[Easy]"))
			generatePuzzle(EASY);
		else if(but.getLabel().equals("Play[Medium]"))
			generatePuzzle(MEDIUM);
		else if(but.getLabel().equals("Play[Difficult]"))
			generatePuzzle(DIFFICULT);
		for(int i=0;i<9;i++)
		{	for(int j=0;j<9;j++)
			{
				tf[i][j].setText(""+puzzle[i][j]);
				tf[i][j].setHorizontalAlignment(JTextField.CENTER);
				tf[i][j].setFont(new Font("ARIALBD",Font.BOLD,20));
				tf[i][j].setForeground(Color.BLUE);
				if(puzzle[i][j]==0)
				{
					tf[i][j].setText("");
					tf[i][j].addKeyListener(this);
					tf[i][j].setEditable(true);
					tf[i][j].setForeground(fgreen);
				}
				/*
				if(i==3 || i==4 || i==5 || j==3 || j==4 || j==5)
				{
					if(2<i && i<6 && 2<j && j<6)
					{
						tf[i][j].setBackground(Color.YELLOW);
		 						//continue;
					}
					else
						tf[i][j].setBackground(Color.RED);
				}
				else
					tf[i][j].setBackground(Color.YELLOW);
				*/
			}
		}
		frame.setVisible(true);
	}
	static private void  generateSolution()
    {
   		List<Integer> numbers = new ArrayList<Integer>();
		for (int k = 1; k <= 9; k++)
			numbers.add(k);
        Collections.shuffle(numbers);
        for(int i  = 0; i  < 9; i++)
        {
        	for(int j = 0; j < 9;  j++)
        	{
        			int number = getNextValidNumber(j, i, numbers);
        			solution[i][j] = number;
  	     	}
    	}
    }
    static private int getNextValidNumber(int x, int y, List<Integer> numbers)
    {
        while (numbers.size() > 0) {
            int number = numbers.remove(0); numbers.add(number);
            if (isNotPresentInRow(y, number) && isNotPresentInColumn(x, number)
            		&& isNotPresentIn3By3Grid(x, y, number))
                	return number;
        }
        return -1;
    }
	static private boolean isNotPresentInRow(int y, int number) {
        for (int x = 0; x < 9; x++) {
            if (solution[y][x] == number)
                return false;
        }
        return true;
    }
    static private boolean isNotPresentInColumn(int x, int number) {
        for (int y = 0; y < 9; y++) {
            if (solution[y][x] == number)
                return false;
        }
        return true;
    }
    static private boolean isNotPresentIn3By3Grid(int x, int y, int number) {
        int x0 = x < 3 ? 0 : x < 6 ? 3 : 6;
        int y0 = y < 3 ? 0 : y < 6 ? 3 : 6;
        for (int row = y0; row < y0 + 3; row++) {
            for (int col = x0; col < x0 + 3; col++) {
                if (solution[row][col] == number)
                    return false;
            }
        }
        return true;
    }
    static private void  generatePuzzle(int level)
    {
    	int row,col, emptyCells=0;
    	Random random, randomRow, randomCol;
    	randomRow = new Random();
    	randomCol = new Random();
    	random = new Random();

    	for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				puzzle[i][j] = solution[i][j];
		if(level == EASY)
			 emptyCells = 32 + random.nextInt(14);
		else if(level == MEDIUM)
			 emptyCells = 46 + random.nextInt(4);
		else if(level == DIFFICULT)
			 emptyCells = 50 + random.nextInt(4);
		int i = 0;
		while(i < emptyCells)
		{
			row = randomRow.nextInt(9);
			col  = randomCol.nextInt(9);
			if(puzzle[row][col] != 0)
			{	puzzle[row][col]=0;
				i++;
			};
		}
    }
}