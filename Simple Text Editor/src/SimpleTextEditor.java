import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class SimpleTextEditor implements ActionListener {
    JFrame frame;
    JTextArea jtextArea;
    JMenuBar jMenuBar;
    JMenu File;
    JMenu Edit;
    JMenu Close;
    JMenuItem NewFile;
    JMenuItem OpenFile;
    JMenuItem SaveFile;
    JMenuItem PrintFile;
    JMenuItem Cut;
    JMenuItem Copy;
    JMenuItem Paste;
    JMenuItem CloseEditor;

    SimpleTextEditor(){
        frame = new JFrame("Simple Text Editor");
        frame.setBounds(0,0,800,1000);
        jtextArea = new JTextArea("Welcome to Editor");
        jMenuBar = new JMenuBar();
        File = new JMenu("File");
        Edit = new JMenu("Edit");
        Close = new JMenu("Close");
        jMenuBar.add(File);
        jMenuBar.add(Edit);
        jMenuBar.add(Close);
        OpenFile = new JMenuItem("Open");
        OpenFile.addActionListener(this);

        SaveFile = new JMenuItem("Save");
        SaveFile.addActionListener(this);

        NewFile = new JMenuItem("New");
        NewFile.addActionListener(this);

        PrintFile = new JMenuItem("Print");
        PrintFile.addActionListener(this);

        Copy = new JMenuItem("Copy");
        Copy.addActionListener(this);

        Cut = new JMenuItem("Cut");
        Cut.addActionListener(this);

        Paste = new JMenuItem("Paste");
        Paste.addActionListener(this);

        CloseEditor = new JMenuItem("CloseEditor");
        CloseEditor.addActionListener(this);

        File.add(NewFile);
        File.add(OpenFile);
        File.add(SaveFile);
        File.add(PrintFile);
        Edit.add(Cut);
        Edit.add(Copy);
        Edit.add(Paste);
        Close.add(CloseEditor);


        frame.setJMenuBar(jMenuBar);

        frame.add(jtextArea);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SimpleTextEditor editor = new SimpleTextEditor();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
//        for copy text
        if((s).equals("Copy")){
            jtextArea.copy();
        }
//        for cut text
        else if((s).equals("Cut")){
            jtextArea.cut();

        }
//        for pasting text
        else if((s).equals("Paste")){
            jtextArea.paste();

        }
//        for the print
        else if((s).equals("Print")){
            try {
                jtextArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }

        }
//        for the new file.
        else if((s).equals("New")){
            jtextArea.setText("");

        }
//        closing
        else if(s.equals("CloseEditor")){
            System.exit(1);

        }
//        Opening the file
        else if(s.equals("Open")){
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String s1="" , s2 ="";
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null){
                        s2 += s1+"\n";
                    }
                    jtextArea.setText(s2);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }

        }
//        Saving the file
        else if(s.equals("Save")){
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showSaveDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter Writer = null;
                try {
                    Writer = new BufferedWriter(new FileWriter(file,false));
                    Writer.write((jtextArea.getText()));
                    Writer.flush();
                    Writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
    }
}