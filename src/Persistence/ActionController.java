package Persistence;

import javax.swing.*;

import Presentation.ActionView;
import Presentation.CreateDialog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**Class to connect the view to the model and handle events */
public class ActionController
{
   CalendarModel model;
   ActionView view;

   /**Constructor method for ActionController*/
   public ActionController(CalendarModel cm, ActionView av)
   {
      model = cm;
      view = av;
      final String EVENTS_FILE = "Database/Events.csv";

      view.createButtonListener(e-> {
         JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(av);
         CreateDialog dialog = new CreateDialog(parentFrame, model);
         dialog.setSize(300, 150);
         model.notifyObservers();
      });

      view.editButtonListener(e-> {
         JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(av);
         CreateDialog dialog = new CreateDialog(parentFrame, model);
         dialog.setSize(300, 150);
         model.notifyObservers();
   
     });

      view.deleteButtonListener(e-> {
         String dateText = String.valueOf(model.getSelectedMonth()+1) + "/" +
            model.getSelectedDay() + "/" + model.getSelectedYear();
         SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");
         Date selectedDate = null;
         try
         {
            selectedDate = formatDate.parse(dateText);
         }
         catch (ParseException pe)
         {
            pe.printStackTrace();
         }
         model.deleteDayEvents(selectedDate);
         model.notifyObservers();
      });

      view.exitButtonListener(e -> {
         FileWriter fw = null;
         try
         {
            fw = new FileWriter(new File(EVENTS_FILE));
            StringBuilder sb = new StringBuilder();
            Map<Date, List<Appointment>> events = model.getEvents();
            Iterator<Map.Entry<Date, List<Appointment>>> it = events.entrySet().iterator();
            while (it.hasNext())
            {
               Map.Entry<Date, List<Appointment>> pair = it.next();
               for (Appointment ev : pair.getValue())
               {
                  sb.append(ev.getStart().getTimeInMillis()).append("\n");
                  sb.append(ev.getEnd().getTimeInMillis()).append("\n");
                  sb.append(ev.getTitle());
                  sb.append("\n");
                  sb.append(ev.getStatus());
                  if (pair.getValue().indexOf(ev) < pair.getValue().size() - 1 || it.hasNext())
                  {
                     sb.append("\n");
                  }
               }
               it.remove();
            }
            sb.trimToSize();
            fw.write(sb.toString());
         } catch (IOException ioe)
         {
            ioe.printStackTrace();
         } finally
         {
            try
            {
               if (fw != null)
               {
                  fw.close();
                 
                  
               }
            } catch (IOException ioe)
            {
               ioe.printStackTrace();
            }
         }
         System.exit(0);
      });
   }
}
