import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class JavaCalculator implements ActionListener{
    private JButton[] numButtons = new JButton[10];
    private JButton[] funcButtons = new JButton[6];
    private JTextField input;
    private JButton clear;
    private JButton delete;
    private JPanel buttonPanel = new JPanel(new GridLayout(4,3, 2,2 ));
    private String calculation;

    public JavaCalculator(){
        ImageIcon icon = new ImageIcon("C:/Users/Teriq/OneDrive/Documents/Programs/Java/Gui/Calculator app/icon.jpg");

        buttonPanel.setBounds(25, 148, 250, 400);
        setButtons();

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 400, 600);
        panel.setBackground(Color.gray);
        
        input = new JTextField(30);
        input.setEditable(false);
        input.setFont(new Font(Font.DIALOG, Font.CENTER_BASELINE, 25));
        input.setBounds(20, 10 ,350, 50);

        clear= new JButton("clear");
        clear.setBounds(50, 80, 100, 50);
        clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                calculation = null;
                input.setText(calculation);
            }
        });

        delete = new JButton("delete");
        delete.setBounds(240, 80, 100, 50);
        delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(calculation!=null){
                    if(calculation.length()!=0){
                        calculation= calculation.substring(0, (calculation.length()-1));
                        input.setText(calculation);
                        if(calculation==""){calculation= null;}
                    }
                }
            }
        });

        panel.add(input);
        panel.add(clear);
        panel.add(delete);
        
        for(int i=0; i<(funcButtons.length-2); i++){
            panel.add(funcButtons[i]);
        }

        JFrame frame = new JFrame("Java-Calculator");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.add(buttonPanel);
        frame.add(panel);
    }

    public void setButtons(){
        int height = 100;
        int width = 80;
        int x = 280;
        int y = 150;
        for(int i=0; i <funcButtons.length; i++){
            funcButtons[i]= new JButton();
            funcButtons[i].addActionListener(this);
        }
        for(int i = 0; i<4; i++){
            funcButtons[i].setBounds( x, y, width, height);
            y = y + 100;
        }
        funcButtons[4].setBounds(116, 450, width, height);
        funcButtons[5].setBounds(198, 450, width, height);
        funcButtons[0].setText("+");
        funcButtons[1].setText("*");
        funcButtons[2].setText("-");
        funcButtons[3].setText("=");
        funcButtons[4].setText(".");
        funcButtons[5].setText("/");

        for(int i=1; i <numButtons.length; i++){
            numButtons[i]= new JButton(""+i+"");
            numButtons[i].addActionListener(this);
            buttonPanel.add(numButtons[i]);
        }
        numButtons[0] = new JButton("0");
        numButtons[0].addActionListener(this);
        buttonPanel.add(numButtons[0]);
        buttonPanel.add(funcButtons[4]);
        buttonPanel.add(funcButtons[5]);
    }

    public static void main(String args[]){
        new JavaCalculator();
    }

    public Float calculate(){
        if(calculation != null){
            if(calculation.contains("++")||calculation.contains("--")||calculation.contains("**")||calculation.contains("--")||calculation.contains("..")){
                Float answer = 0f;
                return answer;
            }else{
                if(calculation.contains("+")||calculation.contains("-")||calculation.contains("*")||calculation.contains("/")){
                    String[] numbers = calculation.split("[*/+-]");
                    String[] operators = calculation.split("[.0-9]+");
                    for(int i=0; i<numbers.length;i++){
                        System.out.print(numbers[i]);
                    }
                    Float answer = Float.parseFloat(numbers[0]);
                    for(int i=1; i<numbers.length;i++){
                        switch(operators[i]){
                            case "*":
                                answer = answer* Float.parseFloat(numbers[i]);
                                break;
                            case "/":
                                answer = answer/ Float.parseFloat(numbers[i]);
                                break;
                            case "+":
                                answer = answer+ Float.parseFloat(numbers[i]);
                                break;
                            case "-":
                                answer = answer- Float.parseFloat(numbers[i]);
                                break;
                        }
                    }
                    return answer;
                }else{
                    Float answer = Float.parseFloat(calculation); 
                    return answer;
                }
            }
        }else{
            Float answer = 0f;
            return answer;
        }
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource() != funcButtons[3]){
            if(calculation==null){calculation = event.getActionCommand();}
            else{calculation = calculation + event.getActionCommand();}
            input.setText(calculation);
        }else{
            calculation = ""+calculate()+"";
            input.setText(calculation);
        }
    }
}