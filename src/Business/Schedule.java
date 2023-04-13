package Business;

import javax.swing.*;

import Persistence.ActionController;
import Persistence.CalendarModel;
import Persistence.DayController;
import Persistence.MonthController;
import Presentation.ActionView;
import Presentation.DayView;
import Presentation.MonthView;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 Class to launch Appointment application
*/
public class Schedule extends JFrame
{
	
   /**Constructor for the Schedule class.*/
   public Schedule()
   {
      try
      {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }
      catch (ClassNotFoundException | UnsupportedLookAndFeelException |
         IllegalAccessException | InstantiationException e)
      {
         e.printStackTrace();
      }

      final String FRAME_TITLE = "Appointment Schedule";

      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      setTitle(FRAME_TITLE);

      CalendarModel model = new CalendarModel(new GregorianCalendar(Locale.ENGLISH));
      loadEvents(model);
      MonthView monthView = new MonthView(model);
      DayView dayView = new DayView(model);
      ActionView actionView = new ActionView(model);

      model.addObserver(monthView);
      model.addObserver(dayView);

      new MonthController(model, monthView);
      new DayController(model, dayView);
      new ActionController(model, actionView);

      add(actionView);
      add(dayView);
      add(monthView);
   }

   /**Loads stored events from a plain text file.*/
   public void loadEvents(CalendarModel model)
   {
      // Load events from the file and turn them into objects
      FileReader fr = null;
      BufferedReader br = null;
      
      try
      {
         fr = new FileReader(new File("Database/Events.csv"));
         br = new BufferedReader(fr);
         String line;

         // File is stored as 3 line data blocks.
         while ((line = br.readLine())!= null)
         {
            Calendar start = Calendar.getInstance();
            start.setTimeInMillis(Long.parseLong(line));
   
            line = br.readLine();
            Calendar end = Calendar.getInstance();
            end.setTimeInMillis(Long.parseLong(line));
   
            line = br.readLine();
            String title = line;

            line = br.readLine();
            String stat = line;
            

            model.addNewEvent(start, end, title, stat);
         }

         System.out.printf("File has successfully been loaded.");
      }
      catch (FileNotFoundException fnfe)
      {
         System.out.printf("File does not exist.\n");
      }
      catch (IOException ioe)
      {
         ioe.printStackTrace();
      }
      finally
      {
         try
         {
            if (fr != null)
            {
               fr.close();
            }
            if (br != null)
            {
               br.close();
            }
         }
         catch (IOException ioe)
         {
            ioe.printStackTrace();
         }
      }
   }

}
