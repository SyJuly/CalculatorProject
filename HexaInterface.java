import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        		redisplay();
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

        if(command.equals("0") ||
           command.equals("1") ||
           command.equals("2") ||
           command.equals("3") ||
           command.equals("4") ||
           command.equals("5") ||
           command.equals("6") ||
           command.equals("7") ||
           command.equals("8") ||
           command.equals("9") ||
           command.equals("A") ||
           command.equals("B") ||
           command.equals("C") ||
           command.equals("D") ||
           command.equals("E") ||
           command.equals("F")) {
        	int number;
        	if(hexMode) {
        		number = Integer.parseInt(command, 16);
        		calc.numberPressed(number, 16);
        	}
        	else {
        		number = Integer.parseInt(command, 10);
        		calc.numberPressed(number);
        	}
        }
        else if(command.equals("+")) {
            calc.plus();
        }
        else if(command.equals("-")) {
            calc.minus();
        }
        else if(command.equals("=")) {
            calc.equals();
        }
        else if(command.equals("CE")) {
            calc.clear();
        }
        else if(command.equals("*")) {
            calc.multiply();
        }
        else if(command.equals("/")) {
            calc.divide();
        }
        else if(command.equals("?")) {
        	showInfo();
        }
        // else unknown command.

        redisplay();
		
	}
	
	@Override
	public void redisplay(){
		if (hexMode){
			display.setText("" + Integer.toHexString(calc.getDisplayValue()).toUpperCase());
		} else{
			display.setText("" + calc.getDisplayValue());
		}
	}
}
