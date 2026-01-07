 package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Entity.*;
import File.*;

public class DrinkShopPage extends JFrame implements ActionListener {

    Font font = new Font("Arial", Font.BOLD, 15);

    JLabel title, sizeLbl, nameLbl, mobileLbl, priceLbl, typeLbl;
    JComboBox<String> sizeBox;
    JTextField nameTf, mobileTf, priceTf;

    JRadioButton juice, water, tea, coffee;
    ButtonGroup bg;

    JButton addBtn, updateBtn, deleteBtn, loadBtn, saveBtn, exitBtn;
    JTextArea screen;

    Drink[] drinks = new Drink[100];

    public DrinkShopPage() {
        super("Drink Shop Management");
        setSize(950, 520);
        setLocation(200, 80);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBounds(0,0,950,520);
        panel.setBackground(new Color(245,235,221));
        add(panel);

        DrinkFileIO.loadFromFile(drinks);

        title = createLabel(panel, 260, 10, 450, 40, "Drink Shop Management");

        sizeLbl = createLabel(panel, 30, 60, 150, 30, "Drink Size:");
        sizeBox = new JComboBox<>(new String[]{"Small","Medium","Large"});
        sizeBox.setBounds(180,60,200,30);
        sizeBox.setFont(font);
        panel.add(sizeBox);

        nameLbl = createLabel(panel, 30, 100, 150, 30, "Customer Name:");
        nameTf = createText(panel, 180, 100);

        mobileLbl = createLabel(panel, 30, 140, 150, 30, "Mobile Number:");
        mobileTf = createText(panel, 180, 140);

        priceLbl = createLabel(panel, 30, 180, 150, 30, "Drink Price:");
        priceTf = createText(panel, 180, 180);

        typeLbl = createLabel(panel, 30, 220, 200, 30, "Drink Type:");

        juice  = createRadio(panel, 30, 260, "Juice");
        water  = createRadio(panel, 130,260, "Water");
        tea    = createRadio(panel, 230,260, "Tea");
        coffee = createRadio(panel, 330,260, "Coffee");

        bg = new ButtonGroup();
        bg.add(juice);
        bg.add(water);
        bg.add(tea);
        bg.add(coffee);

        addBtn    = createButton(panel, 30, 310, "Add");
        updateBtn = createButton(panel, 130,310, "Update");
        deleteBtn = createButton(panel, 230,310, "Delete");
        loadBtn   = createButton(panel, 330,310, "Load");
        saveBtn   = createButton(panel, 430,310, "Save");
        exitBtn   = createButton(panel, 530,310, "Exit");

        screen = new JTextArea();
        screen.setFont(font);
        JScrollPane sp = new JScrollPane(screen);
        sp.setBounds(30,360,600,140);
        panel.add(sp);

        ImageIcon img = new ImageIcon("drink.png");
        JLabel imgLbl = new JLabel(img);
        imgLbl.setBounds(500, 30, 413, 275);
        panel.add(imgLbl);

        updateScreen();
        setVisible(true);
    }

    // ---------- helper ----------
    JLabel createLabel(JPanel p,int x,int y,int w,int h,String t){
        JLabel l=new JLabel(t);
        l.setBounds(x,y,w,h);
        l.setFont(font);
        p.add(l);
        return l;
    }

    JTextField createText(JPanel p,int x,int y){
        JTextField t=new JTextField();
        t.setBounds(x,y,200,30);
        t.setFont(font);
        p.add(t);
        return t;
    }

    JRadioButton createRadio(JPanel p,int x,int y,String t){
        JRadioButton r=new JRadioButton(t);
        r.setBounds(x,y,90,30);
        r.setFont(font);
        r.setBackground(new Color(245,235,221));
        p.add(r);
        return r;
    }

    JButton createButton(JPanel p,int x,int y,String t){
        JButton b=new JButton(t);
        b.setBounds(x,y,90,30);
        b.setFont(font);
        b.addActionListener(this);
        p.add(b);
        return b;
    }

    String getDrinkType(){
        if(juice.isSelected()) return "Juice";
        if(water.isSelected()) return "Water";
        if(tea.isSelected()) return "Tea";
        if(coffee.isSelected()) return "Coffee";
        return "";
    }

    double getAutoPrice(){
        if(juice.isSelected()) return 100;
        if(water.isSelected()) return 20;
        if(tea.isSelected()) return 20;
        if(coffee.isSelected()) return 150;
        return 0;
    }

    void updateScreen(){
        String s="";
        for(int i=0;i<drinks.length;i++){
            if(drinks[i]!=null)
                s += i + " -> " + drinks[i].getInfo() + "\n";
        }
        screen.setText(s);
    }

    int getEmptyIndex(){
        for(int i=0;i<drinks.length;i++)
            if(drinks[i]==null) return i;
        return -1;
    }

    public void actionPerformed(ActionEvent e){
        try{
            if(e.getSource()==addBtn){
                drinks[getEmptyIndex()] = new Drink(
                    sizeBox.getSelectedItem().toString(),
                    nameTf.getText(),
                    mobileTf.getText(),
                    getAutoPrice(),
                    getDrinkType()
                );
                updateScreen();
            }
            else if(e.getSource()==updateBtn){
                int line = screen.getCaretPosition();
                int index = screen.getText().substring(0,line).split("\n").length - 1;

                if(drinks[index]!=null){
                    drinks[index].setType(getDrinkType());
                    drinks[index].setPrice(getAutoPrice());
                }
                updateScreen();
            }
            else if(e.getSource()==deleteBtn){
                int line = screen.getCaretPosition();
                int index = screen.getText().substring(0,line).split("\n").length - 1;
                drinks[index] = null;
                updateScreen();
            }
            else if(e.getSource()==loadBtn){
                DrinkFileIO.loadFromFile(drinks);
                updateScreen();
            }
            else if(e.getSource()==saveBtn){
                DrinkFileIO.saveToFile(drinks);
                JOptionPane.showMessageDialog(this,"Saved Successfully");
            }
            else if(e.getSource()==exitBtn){
                System.exit(0);
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,"Select row & drink type properly");
        }
    }
}
