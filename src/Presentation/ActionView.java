package Presentation;

import javax.swing.*;

import Persistence.CalendarModel;

import java.awt.*;
import java.awt.event.ActionListener;

/**View class for performing basic calendar interactions */
public class ActionView extends JPanel
{
   CalendarModel calendarModel;
   JButton create;
   JButton edit;
   JButton delete;
   JButton exit;

   /**Constructor method for ActionView*/
   public ActionView(CalendarModel c)
   {
      calendarModel = c;

      setLayout(null);
      setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

      final int PANEL_WIDTH = 698;
      final int PANEL_HEIGHT = 44;
      final int BUTTON_WIDTH = 100;
      final int BUTTON_HEIGHT = 25;
      final String CREATE_EVENT = "Create";
      final String EDIT_EVENTS = "Edit";
      final String DELETE_EVENTS = "Delete";
      final String EXIT_APPLICATION = "Exit";

      create = new JButton(CREATE_EVENT);
      create.setFocusPainted(false);
      edit = new JButton(EDIT_EVENTS);
      edit.setFocusPainted(false);
      delete = new JButton(DELETE_EVENTS);
      delete.setFocusPainted(false);
      exit = new JButton(EXIT_APPLICATION);
      exit.setFocusPainted(false);

      // Set bounds
      setBounds(0, 340, PANEL_WIDTH, PANEL_HEIGHT);
      create.setBounds((PANEL_WIDTH-BUTTON_WIDTH)/2-120, (PANEL_HEIGHT-BUTTON_HEIGHT)/2, BUTTON_WIDTH, BUTTON_HEIGHT);
      edit.setBounds((PANEL_WIDTH-BUTTON_WIDTH)/2-240, (PANEL_HEIGHT-BUTTON_HEIGHT)/2, BUTTON_WIDTH, BUTTON_HEIGHT);
      delete.setBounds((PANEL_WIDTH-BUTTON_WIDTH)/2, (PANEL_HEIGHT-BUTTON_HEIGHT)/2, BUTTON_WIDTH, BUTTON_HEIGHT);
      exit.setBounds((PANEL_WIDTH-BUTTON_WIDTH)/2+120, (PANEL_HEIGHT-BUTTON_HEIGHT)/2, BUTTON_WIDTH, BUTTON_HEIGHT);

      add(create);
      add(edit);
      add(delete);
      add(exit);
   }

   /**Event listener to view the next month*/
   public void createButtonListener(ActionListener listenForButton)
   {
      create.addActionListener(listenForButton);
   }

   /**Event listener to delete events for the selected date */
   public void deleteButtonListener(ActionListener listenForButton)
   {
      delete.addActionListener(listenForButton);
   }

   /**Event listener to edit events for the selected date */
   public void editButtonListener(ActionListener listenForButton)
   {
      edit.addActionListener(listenForButton);
   }

   /**Event listener to view the next month*/
   public void exitButtonListener(ActionListener listenForButton)
   {
      exit.addActionListener(listenForButton);
   }
}