package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import models.ModelBlocDeNotas;
import views.ViewBlocDeNotas;

/**
 *
 * @author Norberto
 */
public class ControllerBlocDeNotas implements ActionListener {

    ModelBlocDeNotas modelBlocDeNotas;
    ViewBlocDeNotas viewBlocDeNotas;

    public ControllerBlocDeNotas(ModelBlocDeNotas modelBlocDeNotas, ViewBlocDeNotas viewBlocDeNotas) {
        this.modelBlocDeNotas = modelBlocDeNotas;
        this.viewBlocDeNotas = viewBlocDeNotas;
        this.viewBlocDeNotas.jMenuItemOpen.addActionListener(this);
        this.viewBlocDeNotas.jMenuItemReadSave.addActionListener(this);
        this.viewBlocDeNotas.jMenuItemEncrypt.addActionListener(this);
        this.viewBlocDeNotas.jMenuItemDecrypt.addActionListener(this);
        inComp();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewBlocDeNotas.jMenuItemOpen) {
            jmiRead();
        } else if (e.getSource() == viewBlocDeNotas.jMenuItemReadSave) {
            jmiSave();
        } else if (e.getSource() == viewBlocDeNotas.jMenuItemEncrypt) {
            jmiEncrypt();
        } else if (e.getSource() == viewBlocDeNotas.jMenuItemDecrypt) {
            jmiDecrypt();
        }
    }

    public void jmiRead() {
        try {
            selectPathOpen();
            modelBlocDeNotas.fileRead(modelBlocDeNotas.getPath());
            viewBlocDeNotas.jtaFile.setText(modelBlocDeNotas.getText());
        } catch (Exception ex) {
            Logger.getLogger(ControllerBlocDeNotas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jmiSave() {
        try {
            selectPathSave();
            modelBlocDeNotas.setText(viewBlocDeNotas.jtaFile.getText());
            modelBlocDeNotas.writeFile(modelBlocDeNotas.getPath(), modelBlocDeNotas.getText());
        } catch (Exception ex) {
            Logger.getLogger(ControllerBlocDeNotas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jmiEncrypt() {
        JFileChooser filechoser = new JFileChooser();
        filechoser.setMultiSelectionEnabled(false);
        filechoser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto *.npr", "npr");
        filechoser.setFileFilter(filter);
        filechoser.showSaveDialog(null);
        modelBlocDeNotas.setPath("" + filechoser.getSelectedFile());
        modelBlocDeNotas.setText(viewBlocDeNotas.jtaFile.getText());
        modelBlocDeNotas.encryptFile(modelBlocDeNotas.getPath(), modelBlocDeNotas.getText());
        viewBlocDeNotas.jtaFile.setText(modelBlocDeNotas.getText());

    }

    private void jmiDecrypt() {
        JFileChooser filechoser = new JFileChooser();
        filechoser.setMultiSelectionEnabled(false);
        filechoser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto *.npr", "npr");
        filechoser.setFileFilter(filter);
        filechoser.showSaveDialog(null);
        modelBlocDeNotas.decryptFile(modelBlocDeNotas.getPath());
        viewBlocDeNotas.jtaFile.setText(modelBlocDeNotas.getText());

    }

    private void selectPathSave() {
        JFileChooser filechoser = new JFileChooser();
        filechoser.setMultiSelectionEnabled(false);
        filechoser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto *.npr", "npr");
        filechoser.setFileFilter(filter);
        filechoser.showSaveDialog(null);
        modelBlocDeNotas.setPath("" + filechoser.getSelectedFile());
    }

    private void selectPathOpen() {
        JFileChooser filechoser = new JFileChooser();
        filechoser.setMultiSelectionEnabled(false);
        filechoser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto *.npr", "npr");
        filechoser.setFileFilter(filter);
        //Abriremos la ventana de dialogo para escoger las imagenes
        int option = filechoser.showOpenDialog(null);
        //Si hacemos click en el boton abrir
        if (option == JFileChooser.APPROVE_OPTION) {
            modelBlocDeNotas.setPath("" + filechoser.getSelectedFile());
        }
    }

    public void inComp() {
        viewBlocDeNotas.setTitle("Open, encrypt, decrypt and save documents text");
        viewBlocDeNotas.setLocationRelativeTo(null);
        viewBlocDeNotas.setResizable(false);
        viewBlocDeNotas.setVisible(true);
    }

}
