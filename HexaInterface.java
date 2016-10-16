import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class HexaInterface extends UserInterface {

	private boolean hexMode;
	
	public HexaInterface(CalcEngine calc){
		super(calc);
		hexMode = true;
	}

	@Override
	protected void makeFrame()
    {
		super.makeFrame();
		
		JPanel contentPane = (JPanel)frame.getContentPane();
		JPanel buttonPanel = new JPanel(new GridLayout(7, 1));
		
		JCheckBox boxCheck = new JCheckBox();
		boxCheck.setText("Hex Mode");
        boxCheck.setSelected(hexMode);
        boxCheck.doClick();
        boxCheck.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		hexMode = boxCheck.isSelected();
        		for(int i = 1; i < buttonPanel.getComponentCount(); ++i)
        			buttonPanel.getComponent(i).setEnabled(hexMode);
        		if (hexMode){
        			display.setText("" + convertTo(display.getText(),"hex"));
        		} else{
        			display.setText("" + convertTo(display.getText(),"dec"));
        		}
        	}
        });
		
        buttonPanel.add(boxCheck);
        
		addButton(buttonPanel, "A");
        addButton(buttonPanel, "B");
        addButton(buttonPanel, "C");
        addButton(buttonPanel, "D");
        addButton(buttonPanel, "E");
        addButton(buttonPanel, "F");
        
        contentPane.add(buttonPanel, BorderLayout.WEST);
    }
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();

		if(command.equals("CE")) {
            calc.clear();
            redisplay();
        }else if(command.equals("?")) {
        	showInfo();
        }else if(command.equals("=")) {
        	calc.setDisplayValue(display.getText());
        	if(hexMode) {
        		calc.setDisplayValue(convertTo(display.getText(),"dec"));
        	}
        	calc.calculateResult();
        	redisplay();
        }else{
        	display.setText(display.getText()+command);
        }
		
	}
	
	public String convertTo(String input, String toMode){
		String s = input.replaceAll("[^(|)|\\+|\\*|\\-|\\/|(|)|^|a-z|A-Z|0-9]+", "");
		String r = "";
		String[] nums = s.split("\\+|\\-|\\*|\\/|\\^|\\(|\\)+");
		String[] chars = s.split("[a-zA-Z0-9]+");
		int opCounter = 1;
		if (s.startsWith("(")){
			r = "(";
			opCounter = 0;
		}
		if(!input.equals("")){
			for (String num: nums){
				if (toMode.equals("hex")){
					r += Integer.toHexString(Integer.parseInt(num)).toUpperCase();
				}
				else if (toMode.equals("dec")){
					r += Integer.parseInt(num, 16);
				}
				else{
					throw new IllegalArgumentException();
				}
				if(opCounter <=chars.length-1){
					r += chars[opCounter];
					opCounter++;
				}
			}
		}
		return r;
	}
	
	@Override
	public void redisplay(){
		if (hexMode){
			display.setText("" + convertTo(calc.getDisplayValue(),"hex"));
		} else{
			display.setText("" + calc.getDisplayValue());
		}
	}
}
