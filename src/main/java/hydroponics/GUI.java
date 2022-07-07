package hydroponics;

import arduino.PortDropdownMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GUI {

    JFrame frame;
    JPanel panel;

    PortDropdownMenu portDropdownMenu;

    JButton refresh;
    JButton connect;

    JButton waterUp,waterDown,waterStop;
    JButton LEDs;
    boolean LEDsState = false;

    public GUI (){
        frame = new JFrame("Smart-Hydroponics");
        frame.setSize(600,400);
        frame.setLocation(300,300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel();
        frame.add(panel);

        portDropdownMenu = new PortDropdownMenu();
        portDropdownMenu.refreshMenu();
        panel.add(portDropdownMenu);

        refresh = new JButton("Refresh");
        panel.add(refresh);
        refresh.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                portDropdownMenu.refreshMenu();
            }
        });

        connect = new JButton("Connect");
        connect.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hydro.hydroCommand = new HydroCommand((String) portDropdownMenu.getSelectedItem());
                //System.out.println(portDropdownMenu.getSelectedItem());

                Hydro.hydroCommand.addArduinoCommandListener(new ArduinoCommandListener() {
                    @Override
                    void processCommand(String command) {
                        System.out.println(command);
                    }
                });

                Hydro.hydroCommand.addArduinoCommandListener(new ArduinoCommandListener(){
                    @Override
                    void processCommand(String command) {
                        if(command.contains("Maximum water level reached!")){
                            System.out.println("Warning!");
                        }
                    }
                });
            }
        });
        panel.add(connect);


        waterUp = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hydro.hydroCommand.waterUp();
            }
        });
        waterUp.setText("Water UP");

        waterDown = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hydro.hydroCommand.waterDown();
            }
        });
        waterDown.setText("Water DOWN");

        waterStop = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hydro.hydroCommand.waterStop();
            }
        });
        waterStop.setText("Water STOP");

        panel.add(waterUp);
        panel.add(waterDown);
        panel.add(waterStop);

        LEDs = new JButton("LEDs OFF");
        LEDs.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(LEDsState){
                    Hydro.hydroCommand.LEDsOff();
                    LEDsState = false;
                    LEDs.setText("LEDs OFF");
                }else{
                    Hydro.hydroCommand.LEDsOn();
                    LEDsState = true;
                    LEDs.setText("LEDs ON");
                }
            }
        });

        panel.add(LEDs);

        frame.setVisible(true);
    }
}
