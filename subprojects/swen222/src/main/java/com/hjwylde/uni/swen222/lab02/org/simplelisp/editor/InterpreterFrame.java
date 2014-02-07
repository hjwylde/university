// This file is part of the Simple Lisp Interpreter.
//
// The Simple Lisp Interpreter is free software; you can
// redistribute it and/or modify it under the terms of the
// GNU General Public License as published by the Free Software
// Foundation; either version 2 of the License, or (at your
// option) any later version.
//
// The Simpular Program Interpreter is distributed in the hope
// that it will be useful, but WITHOUT ANY WARRANTY; without
// even the implied warranty of MERCHANTABILITY or FITNESS FOR
// A PARTICULAR PURPOSE. See the GNU General Public License
// for more details.
//
// You should have received a copy of the GNU General Public
// License along with the Simpular Program Interpreter; if not,
// write to the Free Software Foundation, Inc., 59 Temple Place,
// Suite 330, Boston, MA 02111-1307 USA
//
// (C) David James Pearce, 2005.

package com.hjwylde.uni.swen222.lab02.org.simplelisp.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;

import com.hjwylde.uni.swen222.lab02.org.simplelisp.Main;
import com.hjwylde.uni.swen222.lab02.org.simplelisp.compiler.Interpreter;
import com.hjwylde.uni.swen222.lab02.org.simplelisp.compiler.Lexer;
import com.hjwylde.uni.swen222.lab02.org.simplelisp.compiler.Parser;
import com.hjwylde.uni.swen222.lab02.org.simplelisp.error.ParseException;
import com.hjwylde.uni.swen222.lab02.org.simplelisp.lang.LispExpr;
import com.hjwylde.uni.swen222.lab02.org.simplelisp.lang.LispString;
import com.hjwylde.uni.swen222.lab02.org.simplelisp.util.PrettyPrinter;

public class InterpreterFrame extends JFrame implements ActionListener, CaretListener,
        DocumentListener, WindowListener {

    private JConsoleArea consoleView;
    private JConsoleArea problemsView;
    private JTextPane textView;
    private DefaultStyledDocument document;
    private JLabel statusView;
    private JSplitPane splitPane;
    private JPanel panelthing;
    private JPanel thePub;
    private JToolBar toolbar;
    private JMenuBar menubar;
    private JTabbedPane tabbedPane;
    private JLabel funnylinething;
    private JButton runButton;
    private DisplayThread highlighter;

    private int topProportion = 60;
    private int bottomProportion = 40;

    // Actions for copy, cut and paste
    private Action cutAction = new AbstractAction("Cut", makeImageIcon("stock_cut.png")) {

        @Override
        public void actionPerformed(ActionEvent e) {
            textView.cut();
        }
    };

    private Action copyAction = new AbstractAction("Copy", makeImageIcon("stock_copy.png")) {

        @Override
        public void actionPerformed(ActionEvent e) {
            textView.copy();
        }
    };

    private Action pasteAction = new AbstractAction("Paste", makeImageIcon("stock_paste.png")) {

        @Override
        public void actionPerformed(ActionEvent e) {
            textView.paste();
        }
    };

    // The run thread is used for executing
    // the simple lisp program
    private RunThread runThread = null;

    private ImageIcon runImage = makeImageIcon("Play24.gif");

    private ImageIcon stopImage = makeImageIcon("stock_stop.png");

    // Holds current state of Lisp Interpreter
    private Interpreter interpreter = new Interpreter();

    // The dirty bit is used to signal when the source file
    // has been modified. This is useful as it allows us
    // to ask the user if they want to save the file before
    // doing an operation such as "file new" or "file open".
    private boolean dirty = false;

    // The dirty bit is used to signal when the source file
    // has been modified. This is useful as it allows us
    // to ask the user if they want to save the file before
    // doing an operation such as "file new" or "file open".
    private File file = null;

    // The file chooser is used to open a file browser dialog
    // when opening or saving files.
    private final JFileChooser fileChooser = new JFileChooser(new File("."));

    // The serial version is needed for serialization
    private static final long serialVersionUID = 201L;

    public InterpreterFrame() {
        super("Simple Lisp Interpreter");

        // Create the menu
        menubar = buildMenuBar();
        setJMenuBar(menubar);
        // Create the toolbar
        toolbar = buildToolBar();
        // disable cut and copy actions
        cutAction.setEnabled(false);
        copyAction.setEnabled(false);
        // Setup text area for editing source code
        // and setup document listener so interpreter
        // is notified when current file modified and
        // when the cursor is moved.
        textView = buildEditor();
        textView.getDocument().addDocumentListener(this);
        textView.addCaretListener(this);

        // set default key bindings
        try {
            bindKeyToCommand("ctrl C", "(buffer-copy)");
            bindKeyToCommand("ctrl X", "(buffer-cut)");
            bindKeyToCommand("ctrl V", "(buffer-paste)");
            bindKeyToCommand("ctrl E", "(buffer-eval)");
            bindKeyToCommand("ctrl O", "(file-open)");
            bindKeyToCommand("ctrl S", "(file-save)");
            bindKeyToCommand("ctrl Q", "(exit)");
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        // Give text view scrolling capability
        Border border =
                BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3),
                        BorderFactory.createLineBorder(Color.gray));
        JScrollPane topSplit = addScrollers(textView);
        topSplit.setBorder(border);

        // Create tabbed pane for console/problems
        consoleView = makeConsoleArea(10, 50, true);
        problemsView = makeConsoleArea(10, 50, false);
        tabbedPane = buildProblemsConsole();

        // Plug the editor and problems/console together
        // using a split pane. This allows one to change
        // their relative size using the split-bar in
        // between them.
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topSplit, tabbedPane);

        // Create status bar
        statusView = new JLabel(" Status");
        funnylinething = new JLabel("0:0");
        thePub = buildStatusBar();

        // Now, create the outer panel which holds
        // everything together
        panelthing = new JPanel();
        panelthing.setLayout(new BorderLayout());
        panelthing.add(toolbar, BorderLayout.PAGE_START);
        panelthing.add(splitPane, BorderLayout.CENTER);
        panelthing.add(thePub, BorderLayout.SOUTH);
        getContentPane().add(panelthing);

        // tell frame to fire a WindowsListener event
        // but not to close when "x" button clicked.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(this);
        // set minimised icon to use
        setIconImage(makeImageIcon("spi.png").getImage());

        // setup additional internal functions
        InternalFunctions.setup_internals(interpreter, this);

        // set default window size
        Component top = splitPane.getTopComponent();
        Component bottom = splitPane.getBottomComponent();
        top.setPreferredSize(new Dimension(100, 400));
        bottom.setPreferredSize(new Dimension(100, 200));
        pack();

        // load + run user configuration file (if there is one)
        String homedir = System.getProperty("user.home");
        try {
            interpreter.load(homedir + File.separatorChar + ".simplelisp");
        } catch (FileNotFoundException e) {
            // do nothing if file does not exist!
            System.out.println("Didn't find \"" + homedir + "/.simplelisp\"");
        }

        textView.grabFocus();
        setVisible(true);

        // redirect all I/O to problems/console
        redirectIO();

        // start highlighter thread
        highlighter = new DisplayThread(250);
        highlighter.setDaemon(true);
        highlighter.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // When a toolbar button or menu item is
        // clicked on this function will be called
        String cmd = e.getActionCommand();
        if (cmd.equals("New"))
            newFile();
        else if (cmd.equals("Open"))
            openFile();
        else if (cmd.equals("Save"))
            saveFile();
        else if (cmd.equals("Save As"))
            savefa();
        else if (cmd.equals("Exit"))
            exit();
        else if (cmd.equals("Run"))
            evaluate();
        else if (cmd.equals("Stop"))
            stopEvaluate();
        else if (cmd.equals("Pretty"))
            prettyPrint();
    }

    public void bindKeyToCommand(String keySequence, LispExpr cmd) {
        // see Java API for info on keySequence format
        KeyStroke ks = KeyStroke.getKeyStroke(keySequence);
        if (ks == null)
            throw new Error("Invalid key sequence \"" + keySequence + "\"");
        textView.getKeymap().addActionForKeyStroke(ks, new KeyAction(cmd));
    }

    // bindKeyToCommand binds a given key sequence to a Lisp command
    // so that pressing the key sequence executes the command.
    public void bindKeyToCommand(String keySequence, String cmd) throws ParseException {
        bindKeyToCommand(keySequence, Parser.parse(cmd));
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        // when the cursor moves on _textView
        // this method will be called. Then, we
        // must determine what the line number is
        // and update the line number view
        Element root = textView.getDocument().getDefaultRootElement();
        int line = root.getElementIndex(e.getDot());
        root = root.getElement(line);
        int col = root.getElementIndex(e.getDot());
        funnylinething.setText(line + ":" + col);
        // if text is selected then enable copy and cut
        boolean isSelection = e.getDot() != e.getMark();
        copyAction.setEnabled(isSelection);
        cutAction.setEnabled(isSelection);

    }

    @Override
    public void changedUpdate(DocumentEvent e) {}

    // Many of the following methods have been added purely
    // so InternalFunctions can work. Originally, the code in
    // that class was inline here, so its functions had direct
    // access. I removed it so that students do not need to wade
    // through all the functions! But, that leaves the question as
    // to what to do with the following methods, but I don't think
    // there's much you can do ...
    public void changeSize(int width, int height) {
        setSize(width, height);
        Component top = splitPane.getTopComponent();
        Component bottom = splitPane.getBottomComponent();
        int totalHeight = top.getHeight() + bottom.getHeight();
        int topHeight = (totalHeight * topProportion) / 100;
        int bottomHeight = (totalHeight * bottomProportion) / 100;
        top.setPreferredSize(new Dimension(width - 10, topHeight));
        bottom.setPreferredSize(new Dimension(width - 10, bottomHeight));
        splitPane.resetToPreferredSizes();
        pack();
    }

    public void copy() {
        textView.copy();
    }

    public void cut() {
        textView.cut();
    }

    public void evaluate() {
        try {
            // clear problems and console messages
            problemsView.setText("");
            consoleView.setText("");
            // update status view
            statusView.setText(" Parsing ...");
            tabbedPane.setSelectedIndex(0);
            LispExpr root = Parser.parse(textView.getText());
            statusView.setText(" Running ...");
            tabbedPane.setSelectedIndex(1);
            // update run button
            runButton.setIcon(stopImage);
            runButton.setActionCommand("Stop");
            // start run thread
            runThread = new RunThread(root);
            runThread.start();
        } catch (ParseException e) {
            tabbedPane.setSelectedIndex(0);
            System.err.println("Syntax Error at " + e.getLine() + ", " + e.getColumn() + " : "
                    + e.getMessage());
        } catch (Error e) {
            // parsing error
            System.err.println(e.getMessage());
            statusView.setText(" Errors.");
        }
    }

    public void exit() {
        // user is attempting to exit the interpreter.
        // make sure this is what they want to do
        // and check if changes need to be saved.
        int r =
                JOptionPane.showConfirmDialog(this, new JLabel("Exit Interpreter?"),
                        "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (r == JOptionPane.YES_OPTION)
            // user still wants to go ahead.
            // if file not saved then prompt to check
            // whether it should be.
            if (!dirty || checkForSave())
                System.exit(0);
    }

    public int getCaretPosition() {
        Caret c = textView.getCaret();
        return c.getDot();
    }

    // convert color name in to Java Color object
    public Color getColour(String name) {
        if (name.equals("red"))
            return Color.red;
        else if (name.equals("blue"))
            return Color.blue;
        else if (name.equals("black"))
            return Color.black;
        else if (name.equals("cyan"))
            return Color.cyan;
        else if (name.equals("dark gray"))
            return Color.darkGray;
        else if (name.equals("gray"))
            return Color.gray;
        else if (name.equals("light gray"))
            return Color.lightGray;
        else if (name.equals("green"))
            return Color.green;
        else if (name.equals("magenta"))
            return Color.magenta;
        else if (name.equals("orange"))
            return Color.orange;
        else if (name.equals("pink"))
            return Color.pink;
        else if (name.equals("white"))
            return Color.white;
        else if (name.equals("yellow"))
            return Color.yellow;
        try {
            // see if the colour is expressed in
            // 0xAABBCC format for RGB...
            return Color.decode(name);
        } catch (NumberFormatException e) {
        }
        // no, ok bail then ... but this will certainly
        // through an exception
        return null;
    }

    public Document getDocument() {
        return document;
    }

    public int getSelectedTab() {
        return tabbedPane.getSelectedIndex();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        // when text is typed into _textView
        // this will be called
        dirty = true;
    }

    public void newFile() {
        if (!dirty || checkForSave()) {
            textView.setText("");
            consoleView.setText("");
            problemsView.setText("");
            statusView.setText(" Created new file.");
            file = null;
            // reset dirty bit
            dirty = false;
        }
    }

    public void openFile() {
        if (!dirty || checkForSave()) {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
                file = fileChooser.getSelectedFile();
            else
                // user cancelled open after all
                return;
            // load file into text view
            textView.setText(physReadTextFile(file));
            // update status
            statusView.setText(" Loaded file \"" + file.getName() + "\".");
            // reset dirty bit
            dirty = false;
        }
    }

    public void paste() {
        textView.paste();
    }

    public void prettyPrint() {
        try {
            // clear old problem messages
            problemsView.setText("");
            // update status view
            statusView.setText(" Parsing ...");
            LispExpr root = Parser.parse(textView.getText());
            statusView.setText(" Pretty Printing ...");
            String newText = PrettyPrinter.prettyPrint(root);
            textView.setText(newText);
            statusView.setText(" Done.");
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            statusView.setText(" Errors.");
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        // when text is deleted from _textView
        // this will be called
        dirty = true;
    }

    public void runFinished() {
        // program execution finished so update
        // status and run button accordingly
        if (runThread == null)
            // _runThread = null only if
            // execution stopped by user via
            // run button
            statusView.setText(" Stopped.");
        else
            statusView.setText(" Done.");
        runButton.setActionCommand("Run");
        runButton.setIcon(runImage);
    }

    public void savefa() {
        // Force user to enter new file name
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
            file = fileChooser.getSelectedFile();
        else
            // user cancelled save after all
            return;
        // file selected, so write it.
        physWriteTextFile(file, textView.getText());
        // update status
        statusView.setText(" Saved file \"" + file.getName() + "\".");
        // reset dirty bit
        dirty = false;
    }

    public void saveFile() {
        if (file == null)
            // first save file, so prompt for name.
            savefa();
        else {
            // file already named so just write it.
            physWriteTextFile(file, textView.getText());
            // update status
            statusView.setText(" Saved file \"" + file.getName() + "\".");
            // reset dirty bit
            dirty = false;
        }
    }

    public void setCaretPosition(int position) {
        Caret c = textView.getCaret();
        // move the caret
        c.setDot(position);
    }

    public void setMenuBarMode(boolean enable) {
        if (enable)
            setJMenuBar(menubar);
        else
            setJMenuBar(null);
    }

    public void setSelectedTab(int pos) {
        tabbedPane.setSelectedIndex(pos);
    }

    // ----------------
    // Helper Functions
    // ----------------

    public void setStatusBarMode(boolean enable) {
        if (enable) {
            if (!panelthing.isAncestorOf(thePub))
                panelthing.add(thePub, BorderLayout.SOUTH);
        } else {
            panelthing.remove(thePub);
            pack();
        }
    }

    public void setToolBarMode(boolean enable) {
        if (enable) {
            if (!panelthing.isAncestorOf(toolbar))
                panelthing.add(toolbar, BorderLayout.PAGE_START);
        } else
            panelthing.remove(toolbar);
    }

    public void setTopProportion(int top) {
        topProportion = top;
        bottomProportion = 100 - top;
    }

    public void stopEvaluate() {
        // user requested run be stopped so tell
        // run thread to stop
        Thread tmp = runThread;
        runThread = null;
        tmp.interrupt();
    }

    public void unbindKey(String keySequence) {
        KeyStroke ks = KeyStroke.getKeyStroke(keySequence);
        if (ks == null)
            throw new Error("Invalid key sequence \"" + keySequence + "\"");
        textView.getKeymap().removeKeyStrokeBinding(ks);
    }

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        // when the user clicks on 'x' button
        // in top-right-hand corner of frame,
        // this method is called.
        exit();
    }

    @Override
    public void windowDeactivated(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowOpened(WindowEvent e) {}

    private JScrollPane addScrollers(JComponent c) {
        return new JScrollPane(c, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private String backgroundColour(String type) {
        LispExpr e = interpreter.getGlobalExpr(type);
        if (!(e instanceof LispString)) {
            e = interpreter.getGlobalExpr("text-background-colour");
            if (!(e instanceof LispString))
                return "white";
        }
        return ((LispString) e).toString();
    }

    private JTextPane buildEditor() {
        // build the editor pane
        JTextPane ta = makeTextPane(true);
        ta.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        return ta;
    }

    // This function builds the menu bar
    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem i1 = new JMenuItem("New");
        i1.setActionCommand("New");
        i1.addActionListener(this);
        fileMenu.add(i1);
        i1 = new JMenuItem("Open");
        i1.setActionCommand("Open");
        i1.addActionListener(this);
        fileMenu.add(i1);
        fileMenu.addSeparator();
        i1 = new JMenuItem("Save");
        i1.setActionCommand("Save");
        i1.addActionListener(this);
        fileMenu.add(i1);
        fileMenu.add(makeMenuItem("Save As"));
        fileMenu.addSeparator();
        fileMenu.add(makeMenuItem("Exit"));
        menuBar.add(fileMenu);
        // edit menu
        JMenu editMenu = new JMenu("Edit");
        editMenu.add(makeMenuItem(cutAction));
        editMenu.add(makeMenuItem(copyAction));
        editMenu.add(makeMenuItem(pasteAction));
        menuBar.add(editMenu);
        return menuBar;
    }

    private JTabbedPane buildProblemsConsole() {
        // build the problems/console editor
        JTabbedPane tp = new JTabbedPane();
        ImageIcon consoleIcon = makeImageIcon("stock_print-layout-16.png");
        tp.addTab("Problems", addScrollers(problemsView));
        tp.addTab("Console", consoleIcon, addScrollers(consoleView));
        // empty border to give padding around tab pane
        tp.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        return tp;
    }

    private JPanel buildStatusBar() {
        // build the status bar. this sits at
        // the bottom of the window and indicates
        // the status of the last operation and
        // the current line number.
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(statusView, BorderLayout.WEST);
        panel.add(funnylinething, BorderLayout.EAST);
        return panel;
    }

    private JToolBar buildToolBar() {
        // build tool bar
        JToolBar toolBar = new JToolBar("Toolbar");
        toolBar.add(makeToolbarButton("stock_new.png", "New file", "New"));
        toolBar.add(makeToolbarButton("stock_open.png", "Open file", "Open"));
        toolBar.add(makeToolbarButton("stock_save.png", "Save file", "Save"));
        // run button is special
        runButton = makeToolbarButton("Play24.gif", "Run Program", "Run");
        toolBar.add(runButton);
        toolBar.add(makeToolbarButton("stock_text_left.png", "Pretty Print", "Pretty"));
        toolBar.addSeparator();
        toolBar.add(new JButton(copyAction));
        toolBar.add(new JButton(cutAction));
        toolBar.add(new JButton(pasteAction));
        return toolBar;
    }

    private boolean checkForSave() {
        // build warning message
        String message;
        if (file == null)
            message = "File has been modified.  Save changes?";
        else
            message = "File \"" + file.getName() + "\" has been modified.  Save changes?";

        // show confirm dialog
        int r =
                JOptionPane.showConfirmDialog(this, new JLabel(message), "Warning!",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

        if (r == JOptionPane.YES_OPTION)
            // Save File
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
                // write the file
                physWriteTextFile(fileChooser.getSelectedFile(), textView.getText());
            else
                // user cancelled save after all
                return false;
        return r != JOptionPane.CANCEL_OPTION;
    }

    private String foregroundColour(String type) {
        LispExpr e = interpreter.getGlobalExpr(type);
        if (!(e instanceof LispString)) {
            e = interpreter.getGlobalExpr("text-foreground-colour");
            if (!(e instanceof LispString))
                return "black";
        }
        return ((LispString) e).toString();
    }

    private void highlightArea(int pos, int len, String col) {
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setForeground(attrs, getColour(col));
        document.setCharacterAttributes(pos, len, attrs, true);
    }

    // ----------------------
    // ActionListener Methods
    // ----------------------

    private JConsoleArea makeConsoleArea(int width, int height, boolean editable) {
        JConsoleArea ta = new JConsoleArea();
        ta.setEditable(editable);
        return ta;
    }

    // ---------------------
    // CaretListener Methods
    // ---------------------

    private ImageIcon makeImageIcon(String name) {
        String fileName = "icons/" + name;
        // using the URL means the image loads when stored
        // in a jar or expanded into individual files.
        java.net.URL imageURL = InterpreterFrame.class.getResource(fileName);

        ImageIcon icon = null;
        if (imageURL != null)
            icon = new ImageIcon(imageURL);
        return icon;
    }

    // ------------------------
    // DocumentListener Methods
    // ------------------------

    private JMenuItem makeMenuItem(Action a) {
        JMenuItem item = new JMenuItem(a);
        item.addActionListener(this);
        return item;
    }

    private JMenuItem makeMenuItem(String s) {
        JMenuItem item = new JMenuItem(s);
        item.setActionCommand(s);
        item.addActionListener(this);
        return item;
    }

    private JTextPane makeTextPane(boolean editable) {
        document = new DefaultStyledDocument();
        JTextPane ta = new JTextPane(document);
        ta.setEditable(editable);
        return ta;
    }

    // ----------------------
    // WindowListener Methods
    // ----------------------

    private JButton makeToolbarButton(String name, String toolTipText, String action) {
        // Create and initialize the button.
        JButton button = new JButton(makeImageIcon(name));
        button.setToolTipText(toolTipText);
        button.setActionCommand(action);
        button.addActionListener(this);

        return button;
    }

    private String physReadTextFile(File file) {
        // physically read text file
        try {
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            StringBuffer tmp = new StringBuffer();
            while (input.ready()) {
                tmp.append(input.readLine());
                tmp.append("\n");
            }
            return tmp.toString();
        } catch (FileNotFoundException e) {
            // not sure how this can happen
            showErrorDialog("Unable to load \"" + file.getName() + "\" (file not found)");
        } catch (IOException e) {
            // This happens if e.g. file already exists and
            // we do not have write permissions
            showErrorDialog("Unable to load \"" + file.getName() + "\" (I/O error)");
        }
        return new String("");
    }

    private void physWriteTextFile(File file, String text) {
        // physically write file
        try {
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(text.getBytes());
            fo.close();
        } catch (FileNotFoundException e) {
            // not sure how this can happen
            showErrorDialog("Saving failed due to file not found error?");
        } catch (IOException e) {
            // This happens if e.g. file already exists and
            // we do not have write permissions
            showErrorDialog("Saving failed due I/O error.");
        }
    }

    private void redirectIO() {
        // redirect std I/O to console and problems:
        // > System.out => console
        // > System.err => problems
        // > console => System.in
        System.setOut(consoleView.getOutputStream());
        System.setErr(problemsView.getOutputStream());
        // redirect input from console to System.in
        System.setIn(consoleView.getInputStream());
    }

    private void showErrorDialog(String msg) {
        // show a dialog window containing the error message
        JOptionPane.showMessageDialog(this, new JLabel(msg), "Error!", JOptionPane.ERROR_MESSAGE);
    }

    private void updateDisplay() {
        // first, set block colours
        textView.setBackground(getColour(backgroundColour("text-background-colour")));
        textView.setForeground(getColour(foregroundColour("text-foreground-colour")));
        textView.setCaretColor(getColour(foregroundColour("text-caret-colour")));
        problemsView.setBackground(getColour(backgroundColour("problems-background-colour")));
        problemsView.setForeground(getColour(foregroundColour("problems-foreground-colour")));
        consoleView.setBackground(getColour(backgroundColour("console-background-colour")));
        consoleView.setForeground(getColour(foregroundColour("console-foreground-colour")));
        // second, set colours on the code!
        java.util.List<Lexer.Token> tokens = Lexer.tokenise(textView.getText(), true);
        int pos = 0;
        for (Lexer.Token t : tokens) {
            int len = t.toString().length();
            if ((t instanceof Lexer.RightBrace) || (t instanceof Lexer.LeftBrace))
                highlightArea(pos, len, foregroundColour("text-brace-colour"));
            else if (t instanceof Lexer.Strung)
                highlightArea(pos, len, foregroundColour("text-string-colour"));
            else if (t instanceof Lexer.Comment)
                highlightArea(pos, len, foregroundColour("text-comment-colour"));
            else if (t instanceof Lexer.Quote)
                highlightArea(pos, len, foregroundColour("text-quote-colour"));
            else if (t instanceof Lexer.Comma)
                highlightArea(pos, len, foregroundColour("text-comma-colour"));
            else if (t instanceof Lexer.Identifier)
                highlightArea(pos, len, foregroundColour("text-identifier-colour"));
            else if (t instanceof Lexer.Integer)
                highlightArea(pos, len, foregroundColour("text-integer-colour"));
            pos += len;
        }
    }

    public static void main(String argv[]) {
        // basically, if a command-line parameter is supplied
        // then assume it is a file name and execute it as
        // a simpular program without using the GUI at all.
        InterpreterFrame f;

        if (argv.length == 0)
            f = new InterpreterFrame();
        else if (argv[0].equals("-no-gui")) {
            // run interpreter without GUI!

            // setup new argument list for original
            // Main method
            String[] newargv = new String[argv.length - 1];
            for (int i = 1; i != argv.length; ++i)
                newargv[i - 1] = argv[i];
            Main.main(newargv);
        } else {
            // run interpreter with GUI, but
            // load requested file.

            f = new InterpreterFrame();
            // load file into text view
            f.textView.setText(f.physReadTextFile(new File(argv[0])));
            // update status
            f.statusView.setText(" Loaded file \"" + argv[0] + "\".");
            // reset dirty bit
            f.dirty = false;
        }
    }

    // ---------------
    // private classes
    // ---------------

    private class DisplayThread extends Thread {

        // the dislpay thread is responsible for
        // syntax highlighting the users code and
        // updating various colours
        private int _period; // in ms

        public DisplayThread(int period) {
            _period = period;
        }

        @Override
        public void run() { // how to let this terminate gracefully?
            while (1 != 2)
                try {
                    Thread.sleep(_period);
                    updateDisplay();
                } catch (InterruptedException e) {
                }
        }

    }

    // A key action simply runs its command when
    // invoked from the keyboard.
    private class KeyAction extends AbstractAction {

        private LispExpr command;

        public KeyAction(LispExpr c) {
            command = c;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            interpreter.evaluate(command);
        }
    }

    private class RunThread extends Thread {

        // the runthread is responsible for
        // executing the users simpular program.
        // Using a separate thread here means
        // that the user interface will still
        // respond to events whilst the program
        // is running.
        LispExpr program;

        RunThread(LispExpr p) {
            program = p;
        }

        @Override
        public void run() {
            try {
                while (runThread == this) {
                    interpreter.evaluate(program);
                    break;
                }
            } catch (Error e) {
                tabbedPane.setSelectedIndex(0);
                System.err.println("Runtime Error: " + e.getMessage());
            }

            // notify GUI that program execution finished
            runFinished();
        }
    }
}
