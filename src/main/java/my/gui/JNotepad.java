/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

/**
 *
 * @author ADMIN
 */
public class JNotepad extends JFrame{
    
    private JMenuBar menuBar;
    private JMenu mFile, mEdit, mFormat, mView, mHelp, mZoom;
    private JMenuItem itemNew, itemOpen, itemSave, itemSaveAs, itemPageSetup, itemPrint, itemExit;
    private JMenuItem itemUndo, itemCut, itemCopy, itemPaste, itemDelete, itemSearchWithBing, itemFind
            , itemReplace, itemGoTo, itemSelectAll, itemTimeDate;
    private JMenuItem itemFont;
    private JCheckBoxMenuItem itemWrap;
    private JMenuItem itemZoomIn, itemZoomOut, itemRDZ;
    private JCheckBoxMenuItem itemStatusBar;
    private JMenuItem itemViewHelp, itemSendFeedback, itemAboutNotepad;
    private JTextArea txtEditor;
    private File currentFile;
    
    public  JNotepad (String title){
        super(title);
        createMenu();
        createGUI();
        processevent();
        processEvent();
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createMenu() {
        //Tạo đối tượng thanh thực đơn
        menuBar = new JMenuBar();
        
        //Tạo các thực đơn và thêm vào thanh thực đơn
        menuBar.add(mFile = new JMenu("File"));
        menuBar.add(mEdit = new JMenu("Edit"));
        menuBar.add(mFormat = new JMenu("Format"));
        menuBar.add(mView = new JMenu("View"));
        menuBar.add(mHelp = new JMenu("Help"));
        
        //Tạo các item cho Menu File
        mFile.add(itemNew = new JMenuItem("New"));
        mFile.add(itemOpen = new JMenuItem("Open..."));
        mFile.add(itemSave = new JMenuItem("Save"));
        mFile.add(itemSaveAs = new JMenuItem("Save As..."));
        mFile.add(new JSeparator());
        mFile.add(itemPageSetup = new JMenuItem("Page Setup ..."));
        mFile.add(itemPrint = new JMenuItem("Print..."));
        mFile.addSeparator();
        mFile.add(itemExit = new JMenuItem("Exit"));
        
        //Tạo phím nóng cho item File
        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        itemSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        itemPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        
        //Tạo các item cho Menu Edit
        mEdit.add(itemUndo = new JMenuItem("Undo"));
        mEdit.addSeparator();
        mEdit.add(itemCut = new JMenuItem("Cut"));
        mEdit.add(itemCopy = new JMenuItem("Copy"));
        mEdit.add(itemPaste = new JMenuItem("Paste"));
        mEdit.add(itemDelete = new JMenuItem("Delete"));
        mEdit.addSeparator();
        mEdit.add(itemSearchWithBing = new JMenuItem("Search with Bing..."));
        mEdit.add(itemFind = new JMenuItem("Find..."));
        mEdit.add(itemReplace = new JMenuItem("Replace..."));
        mEdit.add(itemGoTo = new JMenuItem("Go to"));
        mEdit.addSeparator();
        mEdit.add(itemSelectAll = new JMenuItem("Select All"));
        mEdit.add(itemTimeDate = new JMenuItem("Time/Date"));
        
        //Tạo phím nóng cho item Edit
        itemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        itemCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        itemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        itemPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        itemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, KeyEvent.CTRL_DOWN_MASK));
        itemSearchWithBing.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
        itemFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        itemReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        itemGoTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
        itemSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        itemTimeDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
              
        //Tạo các item cho Menu Format
        mFormat.add(itemWrap = new JCheckBoxMenuItem("Word Wrap"));
        mFormat.add(itemFont = new JMenuItem("Font..."));    
        
        //Tạo các item cho Menu View
        mView.add(mZoom = new JMenu("Zoom"));
        mZoom.add(itemZoomIn = new JMenuItem("Zoom in"));
        mZoom.add(itemZoomOut = new JMenuItem("Zoom out"));
        mZoom.add(itemRDZ = new JMenuItem("Restore Default Zoom"));
        mView.add(itemStatusBar = new JCheckBoxMenuItem("Status Bar"));
        
        //Tạo phím nóng cho item View
        itemZoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.CTRL_DOWN_MASK));
        itemZoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK));
        itemRDZ.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK));
        
        //Tạo các item cho Menu Help
        mHelp.add(itemViewHelp = new JMenuItem("View help"));
        mHelp.add(itemSendFeedback = new JMenuItem("Send Feedback"));
        mHelp.addSeparator();
        mHelp.add(itemAboutNotepad = new JMenuItem("About Notepad"));   
        
        //Gắn thanh thực đơn vào cửa sổ
        setJMenuBar(menuBar);
        
    }
    
    private void processevent(){
        itemOpen.addActionListener(e -> openFile());
        itemSave.addActionListener(e -> saveFile(false));
        itemSaveAs.addActionListener(e -> saveFile(true));
        itemPrint.addActionListener(e -> printFile());
        itemExit.addActionListener(e -> System.exit(0));
        itemCopy.addActionListener(e -> txtEditor.copy());
        itemPaste.addActionListener(e -> txtEditor.paste());
        itemFind.addActionListener(e -> findText());
        itemReplace.addActionListener(e -> replaceText());
        itemWrap.addActionListener(e -> txtEditor.setLineWrap(itemWrap.isSelected()));
        itemFont.addActionListener(e -> changeFont());
    }
    
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                txtEditor.read(reader, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + ex.getMessage());
            }
        }
    }

    private void saveFile(boolean saveAs) {
        if (saveAs || currentFile == null) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
            } else {
                return;
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
            txtEditor.write(writer);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
        }
    }

    private void printFile() {
        try {
            txtEditor.print();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error printing: " + ex.getMessage());
        }
    }

    private void findText() {
        String findText = JOptionPane.showInputDialog(this, "Enter text to find:");
        if (findText != null && !findText.isEmpty()) {
            String editorText = txtEditor.getText();
            int index = editorText.indexOf(findText);
            if (index >= 0) {
                txtEditor.setCaretPosition(index + findText.length());
                txtEditor.select(index, index + findText.length());
            } else {
                JOptionPane.showMessageDialog(this, "Text not found!");
            }
        }
    }

    private void replaceText() {
        String findText = JOptionPane.showInputDialog(this, "Enter text to find:");
        if (findText != null && !findText.isEmpty()) {
            String replaceText = JOptionPane.showInputDialog(this, "Enter replacement text:");
            if (replaceText != null) {
                txtEditor.setText(txtEditor.getText().replace(findText, replaceText));
            }
        }
    }

    private void changeFont() {
        Font currentFont = txtEditor.getFont();
        Font newFont = JFontChooser.showDialog(this, "Choose Font", currentFont);
        if (newFont != null) {
            txtEditor.setFont(newFont);
        }
    }
    
    private static class JFontChooser {

        private static Font showDialog(JNotepad aThis, String choose_Font, Font currentFont) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public JFontChooser() {
        }
    }

    private void createGUI() {
        txtEditor = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(txtEditor);
        add(scrollPane);
        txtEditor.setLineWrap(true);
        txtEditor.setFont(new Font("Arial", Font.PLAIN, 20));
    }

    private void processEvent(){
        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are u sure ?") == JOptionPane.YES_OPTION){
                    System.exit(0);
            }
            }
        });
    }
}
        
