import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class AdvancedInterface extends UserInterface {

	private boolean hexMode;
	private boolean dateMode;
	
	public AdvancedInterface(CalcEngine calc){
		super(calc);
		hexMode = false;
		dateMode = false;
	}

	@Override
	protected void makeFrame()
    {
		//calls UserInterface method to create basic calculator
		super.makeFrame();
		
		//new content panel for different modes
		JPanel contentPane = (JPanel)frame.getContentPane();
		JPanel buttonPanel = new JPanel(new GridLayout(8, 1));
		
		JRadioButton decCheck = new JRadioButton("Dec (G to J)");
		JRadioButton hexCheck = new JRadioButton("Hex (D to H)");
		JRadioButton julianCheck = new JRadioButton("Date (J to G)");
		ButtonGroup radioButtons = new ButtonGroup();
		radioButtons.add(decCheck);
		radioButtons.add(hexCheck);
		radioButtons.add(julianCheck);
		
        hexCheck.addItemListener(new ItemListener() {
        	@Override
        	public void itemStateChanged(ItemEvent e) {
        		
        		hexMode = hexCheck.isSelected();
        		buttonPanel.getComponent(4).setEnabled(hexMode);
        		buttonPanel.getComponent(6).setEnabled(hexMode);
        		buttonPanel.getComponent(8).setEnabled(hexMode);
        		buttonPanel.getComponent(10).setEnabled(hexMode);
        		for(int i = 12; i < buttonPanel.getComponentCount(); ++i)
        			buttonPanel.getComponent(i).setEnabled(hexMode);
        		if((display.getText()!=null)&&!(display.getText().equals(""))){
        			if (hexMode){
        				display.setText("" + convertTo(display.getText(),"hex"));
        			} else{
        				display.setText("" + convertTo(display.getText(),"dec"));
        			}
        		}
        	}
        });
        
        julianCheck.addItemListener(new ItemListener() {
        	@Override
        	public void itemStateChanged(ItemEvent e) {
        		dateMode = julianCheck.isSelected();
        		
        		buttonPanel.getComponent(5).setEnabled(dateMode);
        		buttonPanel.getComponent(7).setEnabled(dateMode);
        		buttonPanel.getComponent(9).setEnabled(dateMode);
        		buttonPanel.getComponent(11).setEnabled(dateMode);
        		if((display.getText()!=null)&&!(display.getText().equals(""))){
        			if (dateMode){
        				int curr = Integer.parseInt(display.getText());
        				display.setText("" + new GregorianDate(curr).toString());
        			}else{
        				display.setText("" + convertTo(display.getText(),"julian"));
        			}
        		}
        	}
        });
		
        //adding buttons for the advanced functionality on the left
        buttonPanel.add(decCheck);
        buttonPanel.add(new JLabel(" "));
        buttonPanel.add(hexCheck);
        buttonPanel.add(julianCheck);
        
		addButton(buttonPanel, "A");
		addButton(buttonPanel, "G");
		addButton(buttonPanel, "B");
		addButton(buttonPanel, ".");
		addButton(buttonPanel, "C");
		addButton(buttonPanel, "DoW");
        addButton(buttonPanel, "D");
        addButton(buttonPanel, "~");
        addButton(buttonPanel, "E");
		buttonPanel.add(new JLabel(" "));
        addButton(buttonPanel, "F");
        
        contentPane.add(buttonPanel, BorderLayout.WEST);
        
        
        //Default value is decimal mode
        decCheck.isSelected();
        decCheck.doClick();
        for(int i = 4; i < buttonPanel.getComponentCount(); ++i)
			buttonPanel.getComponent(i).setEnabled(false);
        
    }
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if(command.equals("CE")) {
            calc.clear();
            display.setText("0");
        }else if(command.equals("?")) {
        	showInfo();
        }else if(command.equals("DoW")){
        	String gDate = display.getText();
        	//Is Gregorian Date valid?
        	if (gDate.startsWith("G")&&gDate.matches("[G||0-9|\\.]+")){
        		int jDate = Integer.parseInt(convertTo(gDate, "julian"));
        		display.setText(JulianDate.getDayOfWeek(jDate));
        	} else {
        		throw new IllegalArgumentException("Invalid input for Gregorian Date.");
        	}
        }else if(command.equals("=")) {
        	calc.setDisplayValue(display.getText());
        	if(hexMode) {
        		calc.setDisplayValue(convertTo(display.getText(),"dec"));
        	} else if (dateMode){
        		if(display.getText().contains("~")){
        			int temp = calc.getDisplayValue().indexOf("~");
        			String date = calc.getDisplayValue().substring(0, temp);
        			JulianDate jul = createJulianDate(date);
        			String date2 = calc.getDisplayValue().substring(temp+1);
        		 	JulianDate jd = createJulianDate(date2);
        			calc.setDisplayValue(""+jd.getDifference(jul));
        		}else{
        			calc.setDisplayValue(convertTo(display.getText(),"julian"));
        		}
        	}
        	calc.calculateResult();
        	redisplay();
        }else{
        	display.setText(display.getText()+command);
        }
		
	}

	public JulianDate createJulianDate(String input){
		String s = input.replaceAll("G", "");
		String[] dates = s.split("\\.");
		int year = Integer.parseInt(dates[2]);
		int month = Integer.parseInt(dates[1]);
		int day = Integer.parseInt(dates[0]);
		JulianDate jd = new JulianDate(year, month, day);
		return jd;
	}
	
	public String convertTo(String input, String toMode){
		String s = input.replaceAll("[^(|)|\\+|\\.|\\*|\\-|\\/|(|)|^|a-z|A-Z|0-9]+", "");
		String r = "";
		String[] nums = s.split("\\+|\\-|\\*|\\.|\\/|\\^|\\(|\\)+");
		nums = Postfix.removeEmptyElements(nums);
		String[] chars = s.split("[a-zA-Z0-9]+");
		int opCounter = 1;
		if (s.startsWith("(")){
			r = "(";
			opCounter = 0;
		}
		if(!input.equals("")){
			int[] jdValues = null;
			int record = -1;
			for (String num: nums){
				
				if (toMode.equals("hex")){
					r += Integer.toHexString(Integer.parseInt(num)).toUpperCase();
				}
				
				else if (toMode.equals("dec")){
					r += Integer.parseInt(num, 16);
				}
				
				else if (toMode.equals("julian")){
					String temp = "";
					if (num.startsWith("G")){
						record = 0;
						jdValues = new int[3];
						temp = num.substring(1,num.length());
						
					}
					if(!(jdValues==null)){
						switch (record){
							case 0: jdValues[0]=Integer.parseInt(temp);record=1;break;
							case 1: jdValues[1]=Integer.parseInt(num);record=2;break;
							case 2: if((opCounter <=chars.length-1) &&
									   (chars[opCounter].endsWith("-")&&!(chars[opCounter].startsWith("-")))){
										jdValues[2]=Integer.parseInt(num)*(-1);
									} else {
										jdValues[2]=Integer.parseInt(num);
									}
									record=3;break;
						}
						if (record==3){
							JulianDate jd = new JulianDate(jdValues[2],jdValues[1], jdValues[0]);
							r += jd.getJDate();
							record=-1;
							jdValues = null;
						}
					} else{
						r += num;
					}
				}
				else{
					throw new IllegalArgumentException();
				}
				if(opCounter <=chars.length-1){
					if (!(chars[opCounter].startsWith("."))){
						r += chars[opCounter];
						opCounter++;
					} else if (chars[opCounter].equals(".")){
						opCounter++;
					}
				}
			}
		}
		return r;
	}
	
	@Override
	public void redisplay(){
		if (hexMode){
			display.setText("" + convertTo(calc.getDisplayValue(),"hex"));
		} else if(dateMode){
			if(display.getText().contains("~")){
				display.setText(calc.getDisplayValue());
			}else{
				int curr = Integer.parseInt(calc.getDisplayValue());
				display.setText("" + new GregorianDate(curr).toString());
			}
		}else{
			display.setText("" + calc.getDisplayValue());
		}
	}
	
}