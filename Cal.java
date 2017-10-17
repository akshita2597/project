import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Cal extends JFrame implements ActionListener{
	String s1,s2,s3,s4,s5;
	JTextField txt = new JTextField();
	JButton cadd, csub, cmul, cdiv, cequal, one, two, dec, three, four, five, six, seven;
	JButton eight, nine, czero;
	int c, n;
public Cal()
	{
	
		setTitle("Calculator");
		setSize(203, 230);
		txt.setEditable(false);
		setResizable(false);
		Color blue1 = new Color(204, 255, 255);
		txt.setBackground(blue1);
		
		cadd = new JButton("+");
		cadd.addActionListener(this);
		csub = new JButton("-");
		csub.addActionListener(this);
		cmul = new JButton("*");
		cmul.addActionListener(this);
		cdiv = new JButton("/");
		cdiv.addActionListener(this);
		cequal = new JButton("=");
		cequal.addActionListener(this);
		one = new JButton("1");
		one.addActionListener(this);
		two = new JButton("2");
		two.addActionListener(this);
		three = new JButton("3");
		three.addActionListener(this);
		four = new JButton("4");
		four.addActionListener(this);
		five = new JButton("5");
		five.addActionListener(this);
		six = new JButton("6");
		six.addActionListener(this);
		seven = new JButton("7");
		seven.addActionListener(this);
		eight = new JButton("8");
		eight.addActionListener(this);
		nine = new JButton("9");
		nine.addActionListener(this);
		czero = new JButton("0");
		czero.addActionListener(this);
		dec = new JButton("C");
		dec.addActionListener(this);
		ArrayList<JButton> jButtonList = new ArrayList<JButton>();
		jButtonList.add(one);
		jButtonList.add(two);
		jButtonList.add(three);
		jButtonList.add(cadd);
		jButtonList.add(four);
		jButtonList.add(five);
		jButtonList.add(six);
		jButtonList.add(csub);
		jButtonList.add(seven);
		jButtonList.add(eight);
		jButtonList.add(nine);
		jButtonList.add(cmul);
		jButtonList.add(czero);
		jButtonList.add(dec);
		jButtonList.add(cequal);
		jButtonList.add(cdiv);
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(4,4,4,4);
		
		c.gridx = 0;
		c.weightx = 1.0;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(txt, c);
		int k =0;
		for(int j=1; j<5; j++)
		{
			for(int i=0; i<4; i++)
			{
				
				c.gridx = i;
				c.gridy = j;
				c.weightx = 0.0;
				c.gridwidth = 1;
				c.fill = GridBagConstraints.HORIZONTAL;
				Color purple = new Color(255, 171, 145);
				jButtonList.get(k).setBackground(purple);
				panel.add(jButtonList.get(k),c);
				k++;
				if(k==16) 
				{
					break;
				}
			}
			
			this.add(panel);	
		}
		
		setVisible(true);
		
		
	}
public void actionPerformed(ActionEvent e)
{
	
	JButton but = (JButton)e.getSource();
	
	if(but==czero)
    {
        s3 = txt.getText();
        s4 = "0";
        s5 = s3+s4;
        txt.setText(s5);
    }
    if(but==one)
    {
        s3 = txt.getText();
        s4 = "1";
        s5 = s3+s4;
        txt.setText(s5);
    }
    if(but==two)
    {
        s3 = txt.getText();
        s4 = "2";
        s5 = s3+s4;
        txt.setText(s5);
    }if(but==three)
    {
        s3 = txt.getText();
        s4 = "3";
        s5 = s3+s4;
        txt.setText(s5);
    }
    if(but==four)
    {
        s3 = txt.getText();
        s4 = "4";
        s5 = s3+s4;
        txt.setText(s5);
    }
    if(but==five)
    {
        s3 = txt.getText();
        s4 = "5";
        s5 = s3+s4;
        txt.setText(s5);
    }
    if(but==six)
    {
        s3 = txt.getText();
        s4 = "6";
        s5 = s3+s4;
        txt.setText(s5);
    }
    if(but==seven)
    {
        s3 = txt.getText();
        s4 = "7";
        s5 = s3+s4;
        txt.setText(s5);
    }
    if(but==eight)
    {
        s3 = txt.getText();
        s4 = "8";
        s5 = s3+s4;
        txt.setText(s5);
    }
    if(but==nine)
    {
        s3 = txt.getText();
        s4 = "9";
        s5 = s3+s4;
        txt.setText(s5);
    }
    if(but==cadd)
    {
        s1 = txt.getText();
        txt.setText("");
        c=1;

    }
    if(but==csub)
    {
        s1 = txt.getText();
        txt.setText("");
        c=2;

    }
    if(but==cmul)
    {
        s1 = txt.getText();
        txt.setText("");
        c=3;

    }
    if(but==cdiv)
    {
    	s1 = txt.getText();
    	txt.setText("");
    	c=4;
    }
    
    if(but==cequal)
    {
        s2 = txt.getText();
        if(c==1)
        {
            n = Integer.parseInt(s1)+Integer.parseInt(s2);
            txt.setText(String.valueOf(n));
        }
        else
        if(c==2)
        {
            n = Integer.parseInt(s1)-Integer.parseInt(s2);
            txt.setText(String.valueOf(n));
        }
        else
        if(c==3)
        {
            n = Integer.parseInt(s1)*Integer.parseInt(s2);
            txt.setText(String.valueOf(n));
        }
        else
            if(c==4)
            {
            	try
            	{
            		 int p=Integer.parseInt(s2);
                     if(p!=0)
                     {
                                         n = Integer.parseInt(s1)/Integer.parseInt(s2);
                     txt.setText(String.valueOf(n));
                      }
                      else
                         txt.setText("infinite");
  
                 }
                 catch(Exception i){}
            	
            }
    }
    if(but == dec)
    {
    	txt.setText("");
    }
}
public static void main(String args[])
{
	Cal c1 = new Cal();
	c1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}
